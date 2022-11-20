package es.uah.movieappCliente.service;

import es.uah.movieappCliente.model.Director;
import es.uah.movieappCliente.model.Pelicula;
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
public class DirectorServiceImpl implements IDirectorService{

    @Autowired
    RestTemplate template;

    String url = "http://localhost:8000/directores";

    @Override
    public Page<Director> buscarTodos(Pageable pageable) {
        Director[] directores = template.getForObject(url, Director[].class);

        assert directores != null;
        List<Director> directorList = Arrays.asList(directores);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Director> list;
        if(directorList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize,  directorList.size());
            list =  directorList.subList(startItem, toIndex);
        }
        Page<Director> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), directorList.size());
        return page;
    }

    @Override
    public Director buscarDirectorPorId(Integer idDirector) {
        Director director = template.getForObject(url + "/" + idDirector, Director.class);
        return director;
    }

    @Override
    public Page<Director> buscarDirectorPorNombre(String nombre, Pageable pageable) {
        Director[] director = template.getForObject(url + "/nombre/" + nombre, Director[].class);
        List<Director> lista = Arrays.asList(director);
        Page<Director> page = new PageImpl<>(lista, pageable, lista.size());
        return page;
    }

    @Override
    public void guardarDirector(Director director) {
        if (director.getIdDirector() != null && director.getIdDirector() > 0){
            template.put(url, director);
        } else {
            director.setIdDirector(0);
            template.postForObject(url, director, String.class);
        }
    }

    @Override
    public void eliminarDirector(Integer idDirector) {
        template.delete(url + "/" + idDirector);
    }

    @Override
    public void agregarPelicula(Integer idDirector, Integer idPelicula) {
        template.getForObject(url+"/pelicula/agregar/" + idDirector+ "/" + idPelicula, String.class);
    }
}
