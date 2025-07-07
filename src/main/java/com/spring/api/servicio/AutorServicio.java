
package com.spring.api.servicio;

import com.spring.api.modelo.Autor;
import com.spring.api.repositorio.AutorRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServicio {
    
    @Autowired
    private AutorRepositorio repositorio;
    
    public Autor guadarAutor(Autor autor){
        return repositorio.save(autor);
    }
    
    public void eliminarAutor(long id){
        repositorio.deleteById(id);
    }
    
    public List<Autor> listarAutores(){
        return repositorio.findAll();
    }
    
    public Autor obtenerPorId(long id){
        return repositorio.findById(id).orElse(null);
    }
}
