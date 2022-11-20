package es.uah.movieappCliente.service;

import es.uah.movieappCliente.model.Genero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IGeneroService {
    Page<Genero> buscarTodos(Pageable pageable);
    Genero buscarGeneroPorId(Integer idGenero);
    Page<Genero> buscarGeneroPorGenero(String genero, Pageable pageable);
    void guardarGenero(Genero genero);
    void eliminarGenero(Integer idGenero);

}
