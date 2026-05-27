package com.ryckdev.cocina.dominio;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

/**
 * Representa una materia prima del negocio.
 *
 * Ejemplos:
 *   nombre="Carne molida",  unidad=KILOGRAMO, precio=4500.00
 *   nombre="Tapa empanada", unidad=UNIDAD,    precio= 120.00
 *   nombre="Muzzarella",    unidad=KILOGRAMO, precio=6800.00
 */
@Entity
@Table(name = "insumos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Insumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del insumo no puede estar vacío")
    @Column(nullable = false, unique = true)
    private String nombre;

    @NotNull(message = "La unidad de medida es obligatoria")
    @Enumerated(EnumType.STRING)
    @Column(name = "unidad_medida", nullable = false)
    private UnidadMedida unidadMedida;

    /**
     * Precio por 1 unidad de medida.
     * Si la unidad es KILOGRAMO y el precio es 4500,
     * significa $4500 por kg.
     */
    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0001", message = "El precio debe ser mayor a cero")
    @Column(name = "precio_por_unidad", nullable = false, precision = 12, scale = 4)
    private BigDecimal precioPorUnidad;
}