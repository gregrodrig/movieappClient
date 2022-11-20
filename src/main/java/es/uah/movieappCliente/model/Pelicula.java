package es.uah.movieappCliente.model;

public class Pelicula {
    private Integer idPelicula;
    private String titulo;
    private Integer duracion;
    private String sinopsis;
    private String imagen;
    private String anno;
    private Integer tblPaisIdPais;

    public Pelicula() {
    }

    public Pelicula(Integer idPelicula, String titulo, Integer duracion, String sinopsis, String imagen, String anno, Integer tblPaisIdPais) {
        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.duracion = duracion;
        this.sinopsis = sinopsis;
        this.imagen = imagen;
        this.anno = anno;
        this.tblPaisIdPais = tblPaisIdPais;
    }

    public Integer getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Integer idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getAnno() {
        return anno;
    }

    public void setAnno(String anno) {
        this.anno = anno;
    }

    public Integer getTblPaisIdPais() {
        return tblPaisIdPais;
    }

    public void setTblPaisIdPais(Integer tblPaisIdPais) {
        this.tblPaisIdPais = tblPaisIdPais;
    }
}
