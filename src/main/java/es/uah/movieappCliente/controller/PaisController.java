package es.uah.movieappCliente.controller;

import es.uah.movieappCliente.model.Genero;
import es.uah.movieappCliente.model.Pais;
import es.uah.movieappCliente.paginator.PageRender;
import es.uah.movieappCliente.service.IPaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cpaises")
public class PaisController {
    private String tituloVentana = "titulo";
    private String paisesv = "pais";
    private String formPais = "paises/formPais";
    private String formBuscarPais = "paises/buscarPais";
    private String listadoP = "listadoPaises";

    @Autowired
    IPaisService paisService;

    @GetMapping("/nuevo")
    public String nuevo(Model model){
        model.addAttribute(tituloVentana, "Nuevo País");
        Pais pais = new Pais();
        model.addAttribute(paisesv, pais);
        return formPais;
    }
    @GetMapping("/buscar")
    public String buscar(Model model){
        return formBuscarPais;
    }

    @GetMapping("/listado")
    public String listadoPaises(Model model, @RequestParam(name = "page", defaultValue = "0") int page){
        Pageable pageable = PageRequest.of(page, 5);
        Page<Pais> listado = paisService.buscarTodos(pageable);
        PageRender<Pais> pageRender = new PageRender<Pais>("/cpaises/listado", listado);
        model.addAttribute(tituloVentana, "Listado de todos los Países");
        model.addAttribute(listadoP, listado);
        model.addAttribute("page", pageRender);
        return "paises/listaPais";
    }

    @GetMapping("/idDirector/{id}")
    public String buscarPaisPorId(Model model, @PathVariable("id") Integer id){
        Pais pais = paisService.buscarPaisPorId(id);
        model.addAttribute(paisesv, pais);
        return formBuscarPais;
    }

    @GetMapping("/pais")
    public String buscarPaisPorPais(Model model, @RequestParam("pais") String pais) {
        Pais pais1 = paisService.buscarPaisPorPais(pais);
        model.addAttribute(paisesv, pais1);
        return formBuscarPais;
    }

    @PostMapping("/guardar/")
    public String guardarPais(Model model, Pais pais, RedirectAttributes attributes){
        paisService.guardarPais(pais);
        model.addAttribute(tituloVentana, "Nuevo País");
        attributes.addFlashAttribute("msg", "Los datos del País fueron guardados con éxito!");
        return "redirect:/cpaises/listado";
    }

    @GetMapping("/borrar/{id}")
    public String eliminarPais(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes){
        paisService.eliminarPais(id);
        attributes.addFlashAttribute("msg", "Los datos del País fueron borrados con éxito!");
        return "redirect:/cpaises/listado";
    }

    @GetMapping("/editar/{id}")
    public String editarPais(Model model, @PathVariable("id") Integer id){
        Pais pais = paisService.buscarPaisPorId(id);
        model.addAttribute(tituloVentana, "Editar País");
        model.addAttribute(paisesv, pais);
        return formPais;
    }
    @GetMapping(value = "/ver/{id}")
    public String ver(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes) {
        Pais pais = paisService.buscarPaisPorId(id);
        model.addAttribute(paisesv, pais);
        model.addAttribute(tituloVentana, "Detalles del País: " + pais.getPais());
        return "paises/verPais";
    }
}
