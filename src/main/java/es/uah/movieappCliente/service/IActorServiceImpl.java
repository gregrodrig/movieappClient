package es.uah.movieappCliente.service;

import es.uah.movieappCliente.model.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class IActorServiceImpl implements IActorService{

    @Autowired
    RestTemplate template;
    String url = "http://localhost:8000/actores";
    @Override
    public Page<Actor> buscarTodos(Pageable pageable) {
        Actor[] actores = template.getForObject(url, Actor[].class);
        List<Actor> actorList = Arrays.asList(actores);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Actor> list;

        if (actorList.size() < startItem){
            list =  Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, actorList.size());
            list = actorList.subList(startItem, toIndex);
        }

        Page<Actor> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), actorList.size());
        return page;
    }

    @Override
    public Actor buscarActorPorId(Integer idActor) {
        Actor actor = template.getForObject(url + "/" + idActor, Actor.class);
        return actor;
    }

    @Override
    public Page<Actor> buscarAutorPorNombre(String nombre, Pageable pageable) {
        Actor[] actores = template.getForObject(url + "/genero/" + nombre, Actor[].class);
        List<Actor> lista = Arrays.asList(actores);
        Page<Actor> page = new PageImpl<>(lista, pageable, lista.size());
        return page;
    }

    @Override
    public void guardarActor(Actor actor) {
        if (actor.getIdActor() != null && actor.getIdActor() > 0){
            template.put(url, actor);
        } else {
            actor.setIdActor(0);
            template.postForObject(url, actor, String.class);
        }
    }

    @Override
    public void eliminarActor(Integer idAutor) {
        template.delete(url + "/" + idAutor);
    }
}
