package com.spring.api.servicio;

import com.spring.api.modelo.Libro;
import com.spring.api.repositorio.LibroRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio repositorio;

    public Libro guardarLibro(Libro libro) {
        return repositorio.save(libro);
    }

    public List<Libro> listarLibros() {
        return repositorio.findAll();
    }

    public Libro obtenerPorId(Long id) {
        return repositorio.findById(id).orElse(null);
    }
    
    public void eliminarLibro(Long id){
        repositorio.deleteById(id);
    }    
}
