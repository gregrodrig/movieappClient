package es.uah.movieappCliente.service;

import es.uah.movieappCliente.model.Pais;
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
public class PaisServiceImpl implements IPaisService{
    @Autowired
    RestTemplate template;

    String url = "http://localhost:8000/pais";

    @Override
    public Page<Pais> buscarTodos(Pageable pageable) {
        Pais[] pais = template.getForObject(url, Pais[].class);
        List<Pais> paisList = Arrays.asList(pais);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Pais> list;

        if (paisList.size() < startItem){
            list =  Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, paisList.size());
            list = paisList.subList(startItem, toIndex);
        }

        Page<Pais> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), paisList.size());
        return page;
    }

    @Override
    public Pais buscarPaisPorPais(String pais) {
        Pais pais1 = template.getForObject(url+"/pais/" + pais, Pais.class);
        return pais1;
    }

    @Override
    public Pais buscarPaisPorId(Integer idPais) {
        Pais pais = template.getForObject(url + "/" + idPais, Pais.class);
        return pais;
    }

    @Override
    public void guardarPais(Pais pais) {
        if (pais.getIdPais() != null && pais.getIdPais() > 0){
            template.put(url, pais);
        } else {
            pais.setIdPais(0);
            template.postForObject(url, pais, String.class);
        }
    }

    @Override
    public void eliminarPais(Integer idPais) {
        template.delete(url + "/" + idPais);
    }
}
