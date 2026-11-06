package proyectoEmpresa.vista;

import javax.swing.SwingUtilities;
import proyectoEmpresa.controlador.Controlador;

/** Punto de entrada de la aplicación con interfaz gráfica. */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal(new Controlador()).setVisible(true));
    }
}
