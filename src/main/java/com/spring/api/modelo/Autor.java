
package com.spring.api.modelo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Autores")
public class Autor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NotBlank(message = "Este campo es obligatorio.")
    @Column(nullable = false, unique = true)
    private String nombre;

    @NotBlank(message = "Ingresar nacionalidad (MX,FR,USA ETC).")
    private String nacionalidad;
    
    @OneToMany(mappedBy = "autor")
    @JsonManagedReference //serializa los libros dentro del autor
    private List<Libro>libros;
}
