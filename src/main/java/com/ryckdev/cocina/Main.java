package com.ryckdev.cocina;

import javafx.application.Application;

/**
 * Punto de entrada real de la aplicación.
 *
 * Existe separado de CocinaApplication porque Java 17+ no permite
 * que la clase con main() extienda Application en un JAR sin módulos.
 *
 * Flujo de arranque:
 *   1. main() llama a Application.launch()
 *   2. JavaFX crea una instancia de InterfazPrincipal
 *   3. InterfazPrincipal.init() arranca Spring Boot
 *   4. InterfazPrincipal.start() construye la ventana
 */
public class Main {

    public static void main(String[] args) {
        Application.launch(InterfazPrincipal.class, args);
    }
}