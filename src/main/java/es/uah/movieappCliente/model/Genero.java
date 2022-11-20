package es.uah.movieappCliente.model;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Genero {
    private Integer idGenero;
    private String genero;
    public Genero() {
    }
    public Genero(String genero) {
        this.genero = genero;
    }
    public Genero(Integer idGenero, String genero) {
        this.idGenero = idGenero;
        this.genero = genero;
    }
    public Genero(Integer idGenero, String genero, Set<Pelicula> peliculas) {
        this.idGenero = idGenero;
        this.genero = genero;
        this.peliculas = peliculas;
    }
    private Set<Pelicula> peliculas = new LinkedHashSet<>();

    public Set<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(Set<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }
    public Integer getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Integer idGenero) {
        this.idGenero = idGenero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genero genero1 = (Genero) o;
        return Objects.equals(idGenero, genero1.idGenero) && Objects.equals(genero, genero1.genero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGenero, genero);
    }

}
