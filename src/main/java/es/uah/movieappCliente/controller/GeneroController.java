package es.uah.movieappCliente.controller;

import es.uah.movieappCliente.model.Genero;
import es.uah.movieappCliente.paginator.PageRender;
import es.uah.movieappCliente.service.IGeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cgeneros")
public class GeneroController {

    private String tituloVentana = "titulo";
    private String generosv = "genero";
    private String formGenero = "generos/formGenero";
    private String formBuscarGenero = "generos/buscarGenero";
    private String listadoG = "listadoGeneros";

    @Autowired
    IGeneroService generoService;

    @GetMapping("/nuevo")
    public String nuevo(Model model){
        model.addAttribute(tituloVentana, "Nuevo Genero");
        Genero genero = new Genero();
        model.addAttribute(generosv, genero);
        return formGenero;
    }
    @GetMapping("/buscar")
    public String buscar(Model model){
        return formBuscarGenero;
    }

    @GetMapping("/listado")
    public String listadoGeneros(Model model, @RequestParam(name = "page", defaultValue = "0") int page){
        Pageable pageable = PageRequest.of(page, 5);
        Page<Genero> listado = generoService.buscarTodos(pageable);
        PageRender<Genero> pageRender = new PageRender<Genero>("/cgeneros/listado", listado);
        model.addAttribute(tituloVentana, "Listado de todos los generos");
        model.addAttribute(listadoG, listado);
        model.addAttribute("page", pageRender);
        return "generos/listaGenero";
    }
    @GetMapping("/idGenero/{id}")
    public String buscarGeneroPorId(Model model, @PathVariable("id") Integer id){
        Genero genero = generoService.buscarGeneroPorId(id);
        model.addAttribute(generosv, genero);
        return formBuscarGenero;
    }

    @GetMapping("/genero")
    public String buscarGeneroPorGenero(Model model, @RequestParam(name="page", defaultValue="0") int page, @RequestParam("genero") String genero) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Genero> listado;
        if (genero.equals("")) {
            listado = generoService.buscarTodos(pageable);
        } else {
            listado = generoService.buscarGeneroPorGenero(genero, pageable);
        }
        PageRender<Genero> pageRender = new PageRender<Genero>("cgeneros/listado", listado);
        model.addAttribute(tituloVentana, "Listado de Generos");
        model.addAttribute(listadoG, listado);
        model.addAttribute("page", pageRender);
        return "cgeneros/listado";
    }

    @PostMapping("/guardar/")
    public String guardarGenero(Model model, Genero genero, RedirectAttributes attributes){
        generoService.guardarGenero(genero);
        model.addAttribute(tituloVentana, "Nuevo Genero");
        attributes.addFlashAttribute("msg", "Los datos del genero fueron guardados con éxito!");
        return "redirect:/cgeneros/listado";
    }

    @GetMapping("/borrar/{id}")
    public String eliminarGenero(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes){
        generoService.eliminarGenero(id);
        attributes.addFlashAttribute("msg", "Los datos del genero fueron borrados con éxito!");
        return "redirect:/cgeneros/listado";
    }

    @GetMapping("/editar/{id}")
    public String editarGenero(Model model, @PathVariable("id") Integer id){
        Genero genero = generoService.buscarGeneroPorId(id);
        model.addAttribute(tituloVentana, "Editar Genero");
        model.addAttribute(generosv, genero);
        return formGenero;
    }
}
