package Vista;
import Modelo.*;
import Controlador.*;
import javax.swing.*;


public class BancoHomeVista extends JFrame {
    private final ServicioBancario servicio; // Instancia compartida

    public BancoHomeVista() {
        servicio = new ServicioBancario(); // Única instancia

        setTitle("Sistema Bancario");
        setSize(800, 600);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Crear Cuenta", new CrearCuentaVista(servicio));
        tabbedPane.addTab("Transferencias", new TransferenciaVista(servicio));
        tabbedPane.addTab("Movimientos", new MovimientoVista(servicio));

        add(tabbedPane);
    }
}
