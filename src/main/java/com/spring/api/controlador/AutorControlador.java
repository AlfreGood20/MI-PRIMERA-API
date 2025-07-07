package com.spring.api.controlador;

import com.spring.api.modelo.Autor;
import com.spring.api.servicio.AutorServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autor")
public class AutorControlador {
    
    @Autowired
    private AutorServicio servicio;
    
    @PostMapping("/guardar")
    public Autor guardar(@RequestBody Autor autor){
        return servicio.guadarAutor(autor);
    } 
    
    @GetMapping("/lista")
    public List<Autor> lista(){
        return servicio.listarAutores();
    }
}