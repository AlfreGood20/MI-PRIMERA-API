package com.spring.api.controlador;

import com.spring.api.modelo.Libro;
import com.spring.api.servicio.LibroServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/libros")
public class LibroControlador {

    @Autowired
    private LibroServicio servicio;

    @PostMapping("/guardar")
    public Libro guardar(@RequestBody Libro libro) {
        return servicio.guardarLibro(libro);
    }

    @GetMapping("/lista")
    public List<Libro> listaLibros() {
        return servicio.listarLibros();
    }
    
    @DeleteMapping("/eliminar")
    public String eliminar(@RequestParam long id){
        Libro libro= servicio.obtenerPorId(id);
        servicio.eliminarLibro(id);
        return "Se elimino: "+libro.getNombre()+" año: "+libro.getAño();
    }
}
