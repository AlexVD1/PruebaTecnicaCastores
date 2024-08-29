package com.example.PruebaTecnica.controller;

import com.example.PruebaTecnica.Util.TipoMovimiento;
import com.example.PruebaTecnica.model.Producto;
import com.example.PruebaTecnica.service.MovimientoInventarioService;
import com.example.PruebaTecnica.service.ProductoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @Autowired
    private MovimientoInventarioService movimientoInventariosService;

    @GetMapping("/producto")
    public String verProducto(Model model) {
        List<Producto> productosActivos = productoService.obtenerProductosActivos();
        List<Producto> productosInactivos = productoService.obtenerProductosInactivos();
        model.addAttribute("productosActivos", productosActivos);
        model.addAttribute("productosInactivos", productosInactivos);
        return "producto";
    }

    @PostMapping("/api/agregar")
    public String agregarProducto(@RequestParam String nombre, Double precio) {
        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        productoService.agregarProducto(producto);
        return "redirect:/producto";
    }

    @PostMapping("/api/aumentar")
    public String aumentarProducto(@RequestParam Long idProducto, @RequestParam int cantidad) {
        Producto producto =productoService.aumentarProducto(idProducto, cantidad);
        if (producto != null) {
            movimientoInventariosService.registrarMovimiento(producto,cantidad,TipoMovimiento.ENTRADA);
        }
        return "redirect:/producto";
    }

    @PostMapping("/api/bajar")
    public String darDeBajaProducto(@RequestParam Long idProducto) {
        productoService.darDeBajaProducto(idProducto);
        return "redirect:/producto";
    }

    @PostMapping("/api/activar")
    public String activarProducto(@RequestParam Long idProducto) {
        productoService.activarProducto(idProducto);
        return "redirect:/producto";
    }

    @PostMapping("/api/restar")
    public String restarProducto(@RequestParam Long idProducto, @RequestParam int cantidad) {        
        try {
            Producto producto =productoService.restarProducto(idProducto, cantidad);
            if (producto != null) {
                movimientoInventariosService.registrarMovimiento(producto,cantidad,TipoMovimiento.SALIDA);
            }
        } catch (IllegalArgumentException e) {
            return "redirect:/producto?error=" + e.getMessage();
        }
        return "redirect:/producto";
    }

}