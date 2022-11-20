package es.uah.movieappCliente.controller;


import es.uah.movieappCliente.model.Actor;
import es.uah.movieappCliente.model.Director;
import es.uah.movieappCliente.model.Pelicula;
import es.uah.movieappCliente.paginator.PageRender;
import es.uah.movieappCliente.service.IDirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cdirectores")
public class DirectorController {
    private String tituloVentana = "titulo";
    private String directoresv = "director";
    private String formDirector = "directores/formDirector";
    private String formBuscarDirector = "directores/buscarDirector";
    private String listadoD = "listadoDirectores";
    @Autowired
    IDirectorService directorService;

    @GetMapping("/nuevo")
    public String nuevo(Model model){
        model.addAttribute(tituloVentana, "Nuevo Director");
        Director director = new Director();
        model.addAttribute(directoresv, director);
        return formDirector;
    }
    @GetMapping("/buscar")
    public String buscar(Model model){
        return formBuscarDirector;
    }

    @GetMapping("/listado")
    public String listadoDirectores(Model model, @RequestParam(name = "page", defaultValue = "0") int page){
        Pageable pageable = PageRequest.of(page, 5);
        Page<Director> listado = directorService.buscarTodos(pageable);
        PageRender<Director> pageRender = new PageRender<Director>("/cdirectores/listado", listado);
        model.addAttribute(tituloVentana, "Listado de todos los Directores");
        model.addAttribute(listadoD, listado);
        model.addAttribute("page", pageRender);
        return "directores/listaDirector";
    }

    @GetMapping("/idDirector/{id}")
    public String buscarDirectorPorId(Model model, @PathVariable("id") Integer id){
        Director director = directorService.buscarDirectorPorId(id);
        model.addAttribute(directoresv, director);
        return formBuscarDirector;
    }

    @GetMapping("/nombre")
    public String buscarDirectorPorNombre(Model model, @RequestParam(name="page", defaultValue="0") int page, @RequestParam("nombre") String nombre) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Director> listado;
        if (nombre.equals("")) {
            listado = directorService.buscarTodos(pageable);
        } else {
            listado = directorService.buscarDirectorPorNombre(nombre, pageable);
        }
        PageRender<Director> pageRender = new PageRender<Director>("cdirectores/nombre", listado);
        model.addAttribute(tituloVentana, "Listado de Director por nombre");
        model.addAttribute(listadoD, listado);
        model.addAttribute("page", pageRender);
        return "directores/listaDirector";
    }

    @PostMapping("/guardar/")
    public String guardarDirector(Model model, Director director, RedirectAttributes attributes){
        directorService.guardarDirector(director);
        model.addAttribute(tituloVentana, "Nuevo Director");
        attributes.addFlashAttribute("msg", "Los datos del Director fueron guardados con éxito!");
        return "redirect:/cdirectores/listado";
    }

    @GetMapping("/borrar/{id}")
    public String eliminarDirector(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes){
        directorService.eliminarDirector(id);
        attributes.addFlashAttribute("msg", "Los datos del Director fueron borrados con éxito!");
        return "redirect:/cdirectores/listado";
    }

    @GetMapping("/editar/{id}")
    public String editarDirector(Model model, @PathVariable("id") Integer id){
        Director director = directorService.buscarDirectorPorId(id);
        model.addAttribute(tituloVentana, "Editar Director");
        model.addAttribute(directoresv, director);
        return formDirector;
    }

    @GetMapping("/pelicula/agregar/{idDirector}/{idPelicula}")
    public void agregarPelicula(@PathVariable("idDirector") Integer idDirector, @PathVariable("idPelicula") Integer idPelicula) {
        directorService.agregarPelicula(idDirector, idPelicula);
    }
    @GetMapping(value = "/ver/{id}")
    public String ver(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes) {
        Director director = directorService.buscarDirectorPorId(id);
        model.addAttribute(directoresv, director);
        model.addAttribute(tituloVentana, "Detalles del Director: " + director.getNombre());
        return "directores/verDirector";
    }
}
