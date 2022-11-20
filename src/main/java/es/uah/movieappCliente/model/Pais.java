package es.uah.movieappCliente.model;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Pais {
    private Integer idPais;
    private String pais;
    private Set<Actor> actors = new LinkedHashSet<>();
    private Set<Pelicula> peliculas = new LinkedHashSet<>();

    public Set<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(Set<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public Pais() {
    }

    public Pais(String pais) {
        this.pais = pais;
    }

    public Pais(Integer idPais, String pais, Set<Actor> actors, Set<Pelicula> peliculas) {
        this.idPais = idPais;
        this.pais = pais;
        this.actors = actors;
        this.peliculas = peliculas;
    }

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pais pais1 = (Pais) o;
        return Objects.equals(idPais, pais1.idPais) && Objects.equals(pais, pais1.pais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPais, pais);
    }
}
