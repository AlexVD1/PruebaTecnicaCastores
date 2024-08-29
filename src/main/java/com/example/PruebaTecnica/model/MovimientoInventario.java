package com.example.PruebaTecnica.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

import com.example.PruebaTecnica.Util.TipoMovimiento;

@Entity
@Data
public class MovimientoInventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idProducto", nullable = false)
    private Producto producto;

    private LocalDateTime fecha;

    private int cantidad;

    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipo;

    private String descripcion;
    
}
