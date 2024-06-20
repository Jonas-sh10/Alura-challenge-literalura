package com.alurachallenge.literalura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    @ManyToOne
    private Autor autor;
    @Enumerated(EnumType.STRING)
    private List<Idioma> idiomas;
    private Integer descargas;

    public Libro() {}

    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();
        if (datosLibro.idioma() != null && !datosLibro.idioma().isEmpty()) {
            this.idiomas = datosLibro.idioma().stream()
                    .map(Idioma::fromString)
                    .collect(Collectors.toList());
        } else {
            this.idiomas = List.of();
        }
        this.descargas = datosLibro.descargas();
        this.autor = new Autor(datosLibro.autores().get(0));
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Idioma> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<Idioma> idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("*******************************")
                .append("\n\t\t\tLibro")
                .append("\n\t  Id: ").append(Id)
                .append("\n\t  Titulo: '").append(titulo).append('\'')
                .append("\n\t  Idiomas: ").append(idiomas)
                .append("\n\t  Descargas: ").append(descargas)
                .append("\n\t  Autor: ").append(autor != null ? autor.getNombre() : "N/A")
                .append("\n")
                .append("\n*******************************")
                .append("\n");
        return sb.toString();
    }

}
