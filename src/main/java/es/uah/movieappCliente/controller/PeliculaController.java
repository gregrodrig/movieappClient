package es.uah.movieappCliente.controller;

import es.uah.movieappCliente.model.Pelicula;
import es.uah.movieappCliente.paginator.PageRender;
import es.uah.movieappCliente.service.IPeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cpeliculas")
public class PeliculaController {
    private String tituloVentana = "titulo";
    private String peliculasv = "pelicula";
    private String formPelicula = "peliculas/formPelicula";
    private String formBuscarPelicula = "peliculas/buscarPelicula";
    private String listadoP = "listadoPeliculas";
    @Autowired
    IPeliculaService peliculaService;

    @GetMapping(value = {"/", "/inicio", ""})
    public String home(Model model){
        return "inicio";
    }
    @GetMapping("/nueva")
    public String nueva(Model model){
        model.addAttribute(tituloVentana, "Nueva pelicula");
        Pelicula pelicula = new Pelicula();
        model.addAttribute(peliculasv, pelicula);
        return formPelicula;
    }
    @GetMapping("/buscar")
    public String buscar(Model model){
        return formBuscarPelicula;
    }
    @GetMapping("/listado")
    public String listadoPeliculas(Model model, @RequestParam(name = "page", defaultValue = "0") int page){
        Pageable pageable = PageRequest.of(page, 5);
        Page<Pelicula> listado = peliculaService.buscarTodas(pageable);
        PageRender<Pelicula> pageRender = new PageRender<Pelicula>("/cpeliculas/listado", listado);
        model.addAttribute(tituloVentana, "Listado de todas las Películas");
        model.addAttribute(listadoP, listado);
        model.addAttribute("page", pageRender);
        return "peliculas/listaPelicula";
    }
    @GetMapping("/titulo")
    public String buscarPeliculaPorTitulo(Model model, @RequestParam(name="page", defaultValue="0") int page, @RequestParam("titulo") String titulo) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Pelicula> listado;
        if (titulo.equals("")) {
            listado = peliculaService.buscarTodas(pageable);
        } else {
            listado = peliculaService.buscarPeliculaPorTitulo(titulo, pageable);
        }
        PageRender<Pelicula> pageRender = new PageRender<Pelicula>("cpeliculas/listado", listado);
        model.addAttribute(tituloVentana, "Listado de películas por título");
        model.addAttribute(listadoP, listado);
        model.addAttribute("page", pageRender);
        return "cpeliculas/listado"; }

    @GetMapping("/idPelicula/{id}")
    public String buscarPeliculaPorId(Model model, @PathVariable("id") Integer id){
        Pelicula pelicula = peliculaService.buscarPeliculaPorId(id);
        model.addAttribute(peliculasv, pelicula);
        return formBuscarPelicula;
    }
    @PostMapping("/guardar/")
    public String guardarPelicula(Model model, Pelicula pelicula, RedirectAttributes attributes){
        peliculaService.guardarPelicula(pelicula);
        model.addAttribute(tituloVentana, "Nueva pelicula");
        attributes.addFlashAttribute("msg", "Los datos de la pelicula fueron guardados!");
        return "redirect:/cpeliculas/listado";
    }
    @GetMapping("/editar/{id}")
    public String editarPelicula(Model model, @PathVariable("id") Integer id){
        Pelicula pelicula = peliculaService.buscarPeliculaPorId(id);
        model.addAttribute(tituloVentana, "Editar pelicula");
        model.addAttribute(peliculasv, pelicula);
        return formPelicula;
    }
    @GetMapping("/borrar/{id}")
    public String eliminarPelicula(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes){
        peliculaService.eliminarPelicula(id);
        attributes.addFlashAttribute("msg", "Los datos de la pelicula fueron borrados con éxito!");
        return "redirect:/cpeliculas/listado";
    }
}
