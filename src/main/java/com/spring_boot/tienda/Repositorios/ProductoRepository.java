package com.spring_boot.tienda.Repositorios;

import com.spring_boot.tienda.Entidad.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Productos, Long> {
    List<Productos> findByNombreContainingIgnoreCase(String nombre);
}


