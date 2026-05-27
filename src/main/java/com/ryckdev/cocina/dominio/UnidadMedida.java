package com.ryckdev.cocina.dominio;

/**
 * Unidades de medida aceptadas para los insumos.
 *
 * El precio de cada insumo se expresa siempre por 1 de su unidad.
 * Ejemplos:
 *   Carne molida  → KILOGRAMO  → precio por kg
 *   Levadura      → GRAMO      → precio por gramo
 *   Aceite        → LITRO      → precio por litro
 *   Tapa empanada → UNIDAD     → precio por tapa
 */
public enum UnidadMedida {

    KILOGRAMO("kg"),
    GRAMO("g"),
    LITRO("lt"),
    MILILITRO("ml"),
    UNIDAD("u"),
    METRO("m");

    private final String simbolo;

    UnidadMedida(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return simbolo;
    }
}