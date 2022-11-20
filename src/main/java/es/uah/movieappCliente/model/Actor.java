package es.uah.movieappCliente.model;
import java.util.*;

public class Actor {
    private Integer idActor;
    private String nombre;
    private String apellidos;
    private String fechaNacimiento;
    private Integer tblPaisIdPais;
    private Set<Pelicula> peliculas = new LinkedHashSet<>();
    public Actor() {
    }
    public Actor(String nombre, String apellidos, String fechaNacimiento) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
    }
    public Actor(String nombre, String apellidos, String fechaNacimiento, Integer tblPaisIdPais) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.tblPaisIdPais = tblPaisIdPais;
    }
    public Actor(Integer idActor, String nombre, String apellidos, String fechaNacimiento, Integer tblPaisIdPais, Set<Pelicula> peliculas) {
        this.idActor = idActor;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.tblPaisIdPais = tblPaisIdPais;
        this.peliculas = peliculas;
    }

    public Set<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(Set<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }
    public Integer getIdActor() {
        return idActor;
    }
    public void setIdActor(Integer idActor) {
        this.idActor = idActor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getTblPaisIdPais() {
        return tblPaisIdPais;
    }

    public void setTblPaisIdPais(Integer tblPaisIdPais) {
        this.tblPaisIdPais = tblPaisIdPais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return Objects.equals(idActor, actor.idActor) && Objects.equals(nombre, actor.nombre) && Objects.equals(apellidos, actor.apellidos) && Objects.equals(fechaNacimiento, actor.fechaNacimiento) && Objects.equals(tblPaisIdPais, actor.tblPaisIdPais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idActor, nombre, apellidos, fechaNacimiento, tblPaisIdPais);
    }
}
