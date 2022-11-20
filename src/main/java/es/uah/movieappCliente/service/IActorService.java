package es.uah.movieappCliente.service;

import es.uah.movieappCliente.model.Actor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IActorService {
    Page<Actor> buscarTodos(Pageable pageable);
    Actor buscarActorPorId(Integer idActor);
    Page<Actor> buscarAutorPorNombre(String nombre, Pageable pageable);
    void guardarActor(Actor actor);
    void eliminarActor(Integer idAutor);
}
