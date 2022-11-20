package es.uah.movieappCliente.controller;

import es.uah.movieappCliente.model.Actor;
import es.uah.movieappCliente.paginator.PageRender;
import es.uah.movieappCliente.service.IActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cactores")
public class ActorController {
    private String tituloVentana = "titulo";
    private String actoresv = "actor";
    private String formActor = "actores/formActor";
    private String formBuscarActor = "actores/buscarActor";
    private String listadoA = "listadoActores";

    @Autowired
    IActorService actorService;

    @GetMapping("/nuevo")
    public String nuevo(Model model){
        model.addAttribute(tituloVentana, "Nuevo Actor");
        Actor actor = new Actor();
        model.addAttribute(actoresv, actor);
        return formActor;
    }
    @GetMapping("/buscar")
    public String buscar(Model model){
        return formBuscarActor;
    }

    @GetMapping("/listado")
    public String listadoActores(Model model, @RequestParam(name = "page", defaultValue = "0") int page){
        Pageable pageable = PageRequest.of(page, 5);
        Page<Actor> listado = actorService.buscarTodos(pageable);
        PageRender<Actor> pageRender = new PageRender<Actor>("/cactores/listado", listado);
        model.addAttribute(tituloVentana, "Listado de todos los Actores");
        model.addAttribute(listadoA, listado);
        model.addAttribute("page", pageRender);
        return "actores/listaActor";
    }
    @GetMapping("/idActor/{id}")
    public String buscarActorPorId(Model model, @PathVariable("id") Integer id){
        Actor actor = actorService.buscarActorPorId(id);
        model.addAttribute(actoresv, actor);
        return formBuscarActor;
    }

    @GetMapping("/nombre")
    public String buscarAutorPorNombre(Model model, @RequestParam(name="page", defaultValue="0") int page, @RequestParam("nombre") String nombre) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Actor> listado;
        if (nombre.equals("")) {
            listado = actorService.buscarTodos(pageable);
        } else {
            listado = actorService.buscarAutorPorNombre(nombre, pageable);
        }
        PageRender<Actor> pageRender = new PageRender<Actor>("cactoress/listado", listado);
        model.addAttribute(tituloVentana, "Listado de Actores");
        model.addAttribute(listadoA, listado);
        model.addAttribute("page", pageRender);
        return "cactores/listado";
    }

    @PostMapping("/guardar/")
    public String guardarActor(Model model, Actor actor, RedirectAttributes attributes){
        actorService.guardarActor(actor);
        model.addAttribute(tituloVentana, "Nuevo Actor");
        attributes.addFlashAttribute("msg", "Los datos del Actor fueron guardados con éxito!");
        return "redirect:/cactores/listado";
    }

    @GetMapping("/borrar/{id}")
    public String eliminarActor(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes){
        actorService.eliminarActor(id);
        attributes.addFlashAttribute("msg", "Los datos del Actor fueron borrados con éxito!");
        return "redirect:/cactores/listado";
    }

    @GetMapping("/editar/{id}")
    public String editarActor(Model model, @PathVariable("id") Integer id){
        Actor actor = actorService.buscarActorPorId(id);
        model.addAttribute(tituloVentana, "Editar Genero");
        model.addAttribute(actoresv, actor);
        return formActor;
    }
}
