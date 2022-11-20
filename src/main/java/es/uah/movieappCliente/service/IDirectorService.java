package es.uah.movieappCliente.service;

import es.uah.movieappCliente.model.Director;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDirectorService {
    Page<Director> buscarTodos(Pageable pageable);
    Director buscarDirectorPorId(Integer idDirector);
    Director buscarDirectorPorNombre(String nombre);
    void guardarDirector(Director director);
    void eliminarDirector(Integer idDirector);
    void agregarPelicula(Integer idDirector, Integer idPelicula);
}
