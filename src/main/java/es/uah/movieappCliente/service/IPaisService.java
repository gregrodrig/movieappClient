package es.uah.movieappCliente.service;

import es.uah.movieappCliente.model.Pais;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPaisService {
    Page<Pais> buscarTodos(Pageable pageable);
    Pais buscarPaisPorPais(String pais);
    Pais buscarPaisPorId(Integer idPais);
    void guardarPais(Pais pais);
    void eliminarPais(Integer idPais);

}
