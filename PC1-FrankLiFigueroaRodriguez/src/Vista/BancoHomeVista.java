package Vista;

import Modelo.*;
import Controlador.*;
import RepositoryPattern.*;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class BancoHomeVista extends JFrame {
    private final ServicioBancario servicio;

    public BancoHomeVista() {
        ServicioBancario tempServicio = null;

        try {
            Connection conn = DatabaseConnection.getConnection();
            CuentaRepository cuentaRepo = new ConcreteCuentaRepository(conn);
            MovimientoRepository movimientoRepo = new ConcreteMovimientoRepository(conn);
            tempServicio = new ServicioBancario(cuentaRepo, movimientoRepo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error de conexión a la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1); // Detiene la app si no hay conexión
        }

        this.servicio = tempServicio;

        setTitle("Sistema Bancario");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Crear Cuenta", new CrearCuentaVista(servicio));
        tabbedPane.addTab("Transferencias", new TransferenciaVista(servicio));
        tabbedPane.addTab("Movimientos", new MovimientoVista(servicio));

        add(tabbedPane);
    }
}
