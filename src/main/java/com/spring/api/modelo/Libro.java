package com.spring.api.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Este campo es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @NotNull(message = "Debe ingresar el año")
    @Min(value = 0, message = "El año no puede ser negativo")
    @Max(value = 99, message = "Solo se permiten dos dígitos")
    private Integer año;

    @ManyToOne
    @JsonBackReference // evita serializar el autor dentro del libro
    private Autor autor;
}