package com.spring.api.controlador;

import com.spring.api.modelo.Libro;
import com.spring.api.servicio.LibroServicio;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<?> guardar(@Valid @RequestBody Libro libro, BindingResult errores) {

        if (errores.hasErrors()) {

            //LO MISMO QUE EL DE AUTOR PERO USANDO STREAM
            Map<String, String> mapeoErrores = errores.getFieldErrors()
            .stream()
            .collect(Collectors.toMap(
                error -> error.getField(), error -> error.getDefaultMessage()));
                    //CAMPO                             //MENSAJE DE ERROR

                    //STATUS 400
            return ResponseEntity.badRequest().body(mapeoErrores);
        }

        servicio.guardarLibro(libro);
        return ResponseEntity.status(201).build(); //STATUS 201
    }

    @GetMapping("/lista")
    public ResponseEntity<?> listaAutores(){
        List<Libro>lista=servicio.listarLibros();

        if(lista.isEmpty()){
            return ResponseEntity.ok().body("No hay libros registrado"); //STATUS 200
        }
        return ResponseEntity.ok().build(); //STATUS 200
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminar(@RequestParam long id) {
        Libro libro = servicio.obtenerPorId(id);

        if (libro==null) {
            return ResponseEntity.notFound().build(); //STATUS 404 NO ENCONTRADO ESE RECURSO
        }

        servicio.eliminarLibro(id);
        return ResponseEntity.noContent().build(); //STATUS 204 ELIMINADO CORRECTAMENTE
    }
}
