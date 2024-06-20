package com.alurachallenge.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nombre;
    private Integer fechaDeNacimiento;
    private Integer fechaDeFallecimiento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor(){}

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        try {
            this.fechaDeNacimiento = Integer.parseInt(datosAutor.fechaDeNacimiento());
        }catch (NumberFormatException e){
            this.fechaDeNacimiento = 0;
        }
        try{
            this.fechaDeFallecimiento = Integer.parseInt(datosAutor.fechaDeFallecimiento());
        }catch(NumberFormatException e){
            this.fechaDeFallecimiento = 0;
        }
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        libros.forEach(e -> e.setAutor(this));
        this.libros = libros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaDeNacimiento = fechaNacimiento;
    }

    public Integer getFechaFallecimiento() {
        return fechaDeFallecimiento;
    }

    public void setFechaFallecimiento(Integer fechaFallecimiento) {
        this.fechaDeFallecimiento = fechaFallecimiento;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("*******************************")
                .append("\n\t\t\tAutor")
                .append("\n\t  Id: ").append(Id)
                .append("\n\t  Nombre: '").append(nombre).append('\'')
                .append("\n\t  Fecha de Nacimiento: ").append(fechaDeNacimiento != null ? fechaDeNacimiento : "N/A")
                .append("\n\t  Fecha de Fallecimiento: ").append(fechaDeFallecimiento != null ? fechaDeFallecimiento : "N/A")
                .append("\n\t  Libros: ").append(libros != null && !libros.isEmpty() ? libros.stream().map(Libro::getTitulo).toList() : "No tiene libros")
                .append("\n")
                .append("\n*******************************")
                .append("\n");
        return sb.toString();
    }

}
