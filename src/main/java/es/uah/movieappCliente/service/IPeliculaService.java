package es.uah.movieappCliente.service;

import es.uah.movieappCliente.model.Pelicula;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPeliculaService {
    Page<Pelicula> buscarTodas(Pageable pageable);
    Pelicula buscarPeliculaPorId(Integer idPelicula);
    Page<Pelicula> buscarPeliculaPorTitulo(String titulo, Pageable pageable);
    void guardarPelicula(Pelicula pelicula);
    void eliminarPelicula(Integer idPelicula);
    void actualizarPelicula(Pelicula pelicula);
}
