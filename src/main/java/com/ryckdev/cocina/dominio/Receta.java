package com.ryckdev.cocina.dominio;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recetas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Column(nullable = false, unique = true)
    private String nombre;

    /**
     * Cuántas unidades produce esta receta.
     * Ej: "Docena de empanadas" → porciones = 12
     *     "Pizza entera"        → porciones = 1
     */
    @NotNull
    @Min(value = 1, message = "Debe producir al menos 1 porción")
    @Column(nullable = false)
    private Integer porciones;

    /**
     * Receta de la que hereda (puede ser null).
     *
     * Ejemplo:
     *   "Empanada de Carne" → recetaBase = "Base Empanada"
     *   "Base Empanada"     → recetaBase = null
     *
     * Al calcular el costo se suman los ingredientes propios
     * MÁS todos los de la cadena de bases.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receta_base_id")
    private Receta recetaBase;

    /**
     * Ingredientes propios de esta receta.
     * En una variedad, son solo los ingredientes adicionales a la base.
     */
    @OneToMany(
            mappedBy = "receta",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @Builder.Default
    private List<LineaDeReceta> ingredientes = new ArrayList<>();

    // ─── Métodos de conveniencia ──────────────────────────────────

    public void agregarIngrediente(LineaDeReceta linea) {
        linea.setReceta(this);
        this.ingredientes.add(linea);
    }

    public void reemplazarIngredientes(List<LineaDeReceta> nuevas) {
        this.ingredientes.clear();
        nuevas.forEach(this::agregarIngrediente);
    }

    public boolean tieneBase() {
        return this.recetaBase != null;
    }
}