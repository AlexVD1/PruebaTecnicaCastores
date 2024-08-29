package com.example.PruebaTecnica.controller;

import com.example.PruebaTecnica.model.MovimientoInventario;
import com.example.PruebaTecnica.service.MovimientoInventarioService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class MovimientoInventarioController {
    @Autowired
    private MovimientoInventarioService movimientoInventariosService;

    @GetMapping("/movimientoInventarios")
    public String mostrarMovimientoInventarios(Model model) {
        List<MovimientoInventario> movimientoInventarios = movimientoInventariosService.obtenerMIs();
        model.addAttribute("movimientoInventarios", movimientoInventarios);  
        return "movimientoInventarios";  
    }
}