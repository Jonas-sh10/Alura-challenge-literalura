package com.alurachallenge.literalura.main;

import com.alurachallenge.literalura.model.Autor;
import com.alurachallenge.literalura.model.DatosLibro;
import com.alurachallenge.literalura.model.Idioma;
import com.alurachallenge.literalura.model.Libro;
import com.alurachallenge.literalura.repository.AutorRepository;
import com.alurachallenge.literalura.repository.LibroRepository;
import com.alurachallenge.literalura.service.ConsumoApi;
import com.alurachallenge.literalura.service.ConvierteDatos;

import java.util.*;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private List<DatosLibro> datosLibros = new ArrayList<>();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;
    private List<Libro> libros;
    private List<Autor> autores;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void menuDeLiteralura() {
        var opcion = -1;
        while (opcion != 0) {

                var menu = """
                1 - Buscar libro por titulo
                2 - Listar libros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos en un determinado año
                5 - Listar libros por idioma
                              
                0 - Salir
                """;
                System.out.println(menu);
                opcion = teclado.nextInt();
                teclado.nextLine();

                switch (opcion) {
                    case 1:
                        buscarLibroPorTitulo();
                        break;
                    case 2:
                        listarLibrosBuscados();
                        break;
                    case 3:
                        listarAutoresRegistrados();
                        break;
                    case 4:
                        listarAutoresPorAnio();
                        break;
                    case 5:
                         listarLibrosPorIdioma();
                        break;
                    case 0:
                        System.out.println("Cerrando la aplicación...");
                        System.exit(0);  // Salir del programa
                        break;
                    default:
                        System.out.println("Opción inválida");
                }

        }
    }

    public DatosLibro getDatosLibro() {
        System.out.println("Ingrese el nombre del libro: ");
        var nombreLibro = teclado.nextLine().toLowerCase();
        var json = consumoApi.obternerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "%20"));
        System.out.println(json);
        DatosLibro datos = convierteDatos.obtenerDatos(json, DatosLibro.class);
        return datos;
    }

    public void buscarLibroPorTitulo() {
        try {
            DatosLibro datos = getDatosLibro();
            Libro libro = new Libro(datos);

            // Verificar si el autor ya existe
            Autor autor = libro.getAutor();
            Optional<Autor> autorExistente = autorRepository.findByNombre(autor.getNombre());

            if (autorExistente.isPresent()) {
                autor = autorExistente.get(); // Obtener el autor si ya existe
            } else {
                autor = autorRepository.save(autor); // Guardar el autor primero si no existe
            }

            libro.setAutor(autor); // Asignar el autor guardado al libro
            libroRepository.save(libro); // Guardar el libro
            System.out.println(datos);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void listarLibrosBuscados() {
        libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println("\n*****************************************");
            System.out.println("¡La Base de Datos no tiene libros buscados!");
            System.out.println("*****************************************\n");
        }else{
            libros.stream().forEach(System.out::println);
        }
    }

    public void listarAutoresRegistrados() {
        autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("\n*****************************************");
            System.out.println("¡La Base de Datos no tiene autores registrados!");
            System.out.println("*****************************************\n");
        }else{
            autores.stream().forEach(System.out::println);
        }
    }

    public void listarAutoresPorAnio() {
        System.out.println("Ingrese el año para encontrar Autor(es) que de esa época: ");
        try{
            Integer anio = teclado.nextInt();
            autores = autorRepository.findAutorByYear(anio);
            if (autores.isEmpty()){
                System.out.println("\n*****************************************");
                System.out.println("¡No se encontraron autores en esa época!");
                System.out.println("*****************************************\n");
            }else{
                autores.stream().forEach(System.out::println);
            }
        }catch(Exception e){
            System.out.println("\n*******************************");
            System.out.println("¡Errror, ingrese un año correcto!");
            System.out.println("*******************************\n");
            teclado.nextLine();
        }
    }

    private void listarLibrosPorIdioma() {
        var opcionIdioma = -1;
        var menu = """
        1 - Buscar libro por es - Español
        2 - Buscar libro por en - Inglés
        3 - Buscar libro por fr - Francés
        4 - Buscar libro por pt - Portugués
                          
        """;
        System.out.println(menu);
        try {
            opcionIdioma = teclado.nextInt();
            teclado.nextLine(); // Consumir el salto de línea pendiente
            Collection<Idioma> idiomas = null;
            switch (opcionIdioma) {
                case 1:
                    idiomas = List.of(Idioma.fromEspanol("Español"));
                    break;
                case 2:
                    idiomas = List.of(Idioma.fromEspanol("Inglés"));
                    break;
                case 3:
                    idiomas = List.of(Idioma.fromEspanol("Francés"));
                    break;
                case 4:
                    idiomas = List.of(Idioma.fromEspanol("Portugués"));
                    break;
                default:
                    System.out.println("\n*******************************");
                    System.out.println("Opción inválida");
                    System.out.println("*******************************\n");
            }
            buscarPorIdioma(idiomas);
        } catch (InputMismatchException e) {
            System.out.println("\n***********************************");
            System.out.println("¡Error, ingrese un número correcto!");
            System.out.println("***********************************\n");
            teclado.nextLine(); // Consumir la entrada incorrecta
        }
    }

    public void buscarPorIdioma(Collection<Idioma> idioma) {
        libros = libroRepository.findLibrosByIdiomasIn(idioma);
        if (libros.isEmpty()){
            System.out.println("\n*******************************");
            System.out.println("¡No se encontraron libros en ese idioma!");
            System.out.println("*******************************\n");
        }else{
            libros.stream().forEach(System.out::println);
        }
    }

}
