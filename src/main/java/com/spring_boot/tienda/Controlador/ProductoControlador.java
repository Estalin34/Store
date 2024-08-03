package com.spring_boot.tienda.Controlador;

import com.spring_boot.tienda.Entidad.Productos;
import com.spring_boot.tienda.Servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductoControlador {
    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping("/productos")
    public String listarProductos(@RequestParam(value = "BuscarProducto", required = false) String nombre, Model model) {
        List<Productos> productos;
        if (nombre != null && !nombre.isEmpty()) {
            productos = productoServicio.buscarProductoNombre(nombre);
        } else {
            productos = productoServicio.listaProductos();
        }
        model.addAttribute("productos", productos);
        model.addAttribute("BuscarProducto", nombre);
        return "VistaProductos";
    }

    @GetMapping("/formulario")
    public String formularioProducto(Model model) {
        model.addAttribute("producto", new Productos());
        return "FormularioProducto";
    }

    @PostMapping("/guardar")
    public String crearProducto(Productos productos) {
        this.productoServicio.guardarProductos(productos);
        return "redirect:/productos";
    }

    @GetMapping("/editar/producto/{id}")
    public String editarProducto(@PathVariable Long id, Model model) {
        Optional<Productos> productos = this.productoServicio.buscarProductos(id);
        if (productos.isPresent()) {
            model.addAttribute("producto", productos.get());
            return "FormularioProducto";
        } else {
            return "redirect:/productos";
        }
    }

    @GetMapping("/eliminar/producto/{id}")
    public String borrarProducto(@PathVariable Long id) {
        this.productoServicio.borrarProductos(id);
        return "redirect:/productos";
    }
}
