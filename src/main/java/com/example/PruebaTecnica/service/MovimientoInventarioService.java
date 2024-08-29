package com.example.PruebaTecnica.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.PruebaTecnica.repository.MovimientoInventarioRepository;
import com.example.PruebaTecnica.Util.TipoMovimiento;
import com.example.PruebaTecnica.model.MovimientoInventario;
import com.example.PruebaTecnica.model.Producto;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimientoInventarioService {
    @Autowired 
    private MovimientoInventarioRepository movimientoInventarioRepository;

    public List<MovimientoInventario> obtenerMIs() {
        return movimientoInventarioRepository.findAll();
    }

    public MovimientoInventario  registrarMovimiento(Producto producto, int cantidad,TipoMovimiento tipo)
    {
        MovimientoInventario movimiento = new MovimientoInventario();
        movimiento.setCantidad(cantidad);
        movimiento.setFecha(LocalDateTime.now());
        movimiento.setProducto(producto);
        movimiento.setTipo(tipo);
        movimiento.setDescripcion("Nuevo movimiento de "+tipo);
        return movimientoInventarioRepository.save(movimiento);
    }
}