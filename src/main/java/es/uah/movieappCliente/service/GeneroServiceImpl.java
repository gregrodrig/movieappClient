package es.uah.movieappCliente.service;

import es.uah.movieappCliente.model.Genero;
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
public class GeneroServiceImpl implements IGeneroService{

    @Autowired
    RestTemplate template;

    String url = "http://localhost:8000/generos";

    @Override
    public Page<Genero> buscarTodos(Pageable pageable) {
        Genero[] generos = template.getForObject(url, Genero[].class);
        List<Genero> generoList = Arrays.asList(generos);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Genero> list;

        if (generoList.size() < startItem){
            list =  Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, generoList.size());
            list = generoList.subList(startItem, toIndex);
        }

        Page<Genero> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), generoList.size());
        return page;
    }

    @Override
    public Genero buscarGeneroPorId(Integer idGenero) {
        Genero genero = template.getForObject(url + "/" + idGenero, Genero.class);
        return genero;
    }

    @Override
    public Page<Genero> buscarGeneroPorGenero(String genero, Pageable pageable) {
        Genero[] generos = template.getForObject(url + "/genero/" + genero, Genero[].class);
        List<Genero> lista = Arrays.asList(generos);
        Page<Genero> page = new PageImpl<>(lista, pageable, lista.size());
        return page;
    }

    @Override
    public void guardarGenero(Genero genero) {
        if (genero.getIdGenero() != null && genero.getIdGenero() > 0){
            template.put(url, genero);
        } else {
            genero.setIdGenero(0);
            template.postForObject(url, genero, String.class);
        }
    }

    @Override
    public void eliminarGenero(Integer idGenero) {
        template.delete(url + "/" + idGenero);
    }

}
