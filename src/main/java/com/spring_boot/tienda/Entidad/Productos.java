package com.spring_boot.tienda.Entidad;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity

public class Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String nombre;

    private Double precio;
    private int stock;
    private String descripcion;


}
