package com.spring_boot.tienda.Servicio;

import com.spring_boot.tienda.Entidad.Productos;
import com.spring_boot.tienda.Repositorios.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicio {
    @Autowired
    ProductoRepository productoRepository;

    public List<Productos> buscarProductoNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Productos> listaProductos() {
        return productoRepository.findAll();
    }

    public Optional<Productos> buscarProductos(Long id) {
        return productoRepository.findById(id);
    }

    public void guardarProductos(Productos productos) {
        this.productoRepository.save(productos);
    }

    public void borrarProductos(Long id) {
        this.productoRepository.deleteById(id);
    }
}
