package es.uah.movieappCliente.model;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Director {
    private Integer idDirector;
    private String nombre;
    private Set<Pelicula> peliculas = new LinkedHashSet<>();
    public Director() {
    }
    public Director(String nombre) {
        this.nombre = nombre;
    }
    public Director(Integer idDirector) {
        this.idDirector = idDirector;
    }
    public Director(Integer idDirector, String nombre) {
        this.idDirector = idDirector;
        this.nombre = nombre;
    }
    public Set<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(Set<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    public Integer getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(Integer idDirector) {
        this.idDirector = idDirector;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Director director = (Director) o;
        return Objects.equals(idDirector, director.idDirector) && Objects.equals(nombre, director.nombre);
    }
    @Override
    public int hashCode() {
        return Objects.hash(idDirector, nombre);
    }
}
