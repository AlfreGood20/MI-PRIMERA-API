package com.spring.api.controlador;

import com.spring.api.modelo.Autor;
import com.spring.api.servicio.AutorServicio;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
    public ResponseEntity<?> guardar(@Valid @RequestBody Autor autor,BindingResult errores){

        if(errores.hasErrors()){ //SI HAY ERRORES
             Map<String, String> mapeoErrores = new HashMap<>(); //MAPEAR DATO : ERROR

             List<FieldError> listaError=errores.getFieldErrors(); //LISTA DE ERRORES {"datos":error,etc}
             
             for(FieldError error: listaError){
                                //EL NOMBRE DEL CAMPO     //EL ERROR    ejmplo: {"nombre": error}
                mapeoErrores.put(error.getField(),error.getDefaultMessage());
             }
             //error 400 
            return ResponseEntity.badRequest().body(mapeoErrores); //MANDAMOS EL MAPA DE ERROR Y EL CAMPO
        }

        //GUARDAR AUTOR
        servicio.guadarAutor(autor);
        //RESPONSE DE ESTADO  201    ESTADO DE CREACION     //CUERPO DEL MENSAJE
        return ResponseEntity.status(201).body("Se creo correctamente");
    } 
    
    @GetMapping("/lista")
    public ResponseEntity<?> listaAutores(){
        List<Autor>lista=servicio.listarAutores(); //LISTA USUARIO

        if(lista.isEmpty()){
            return ResponseEntity.ok().body("No hay autores registrado"); //STATUS 200
        }
        return ResponseEntity.ok().body(lista); //ESTATUS 200 PERO CON LA LISTA DE AUTORES
    }
}