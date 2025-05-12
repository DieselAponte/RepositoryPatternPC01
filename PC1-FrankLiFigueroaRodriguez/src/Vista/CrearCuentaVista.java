package Vista;

import Modelo.ServicioBancario;
import Controlador.CrearCuentaControlador;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class CrearCuentaVista extends JPanel {
    public CrearCuentaVista(ServicioBancario servicio) {
        CrearCuentaControlador controlador = new CrearCuentaControlador(servicio);

        // Configuración del layout
        setLayout(new GridLayout(3, 2, 5, 5));

        // Componentes de la UI
        JComboBox<String> tipoCombo = new JComboBox<>(new String[]{"Ahorro", "Corriente"});
        JTextField saldoField = new JTextField(10);
        JTextField dniField = new JTextField(10);
        JTextField nombreField = new JTextField(20);
        JButton crearBtn = new JButton("Crear");

        // Agregar componentes al panel
        add(new JLabel("Tipo de cuenta:"));
        add(tipoCombo);
        add(new JLabel("Saldo inicial:"));
        add(saldoField);
        add(new JLabel());
        add(crearBtn);

        // Acción del botón Crear
        crearBtn.addActionListener(e -> {
            try {
                controlador.crearCuenta(
                        (String) tipoCombo.getSelectedItem(),
                        Double.parseDouble(saldoField.getText()),
                        dniField.getText(),
                        nombreField.getText()
                );

                JOptionPane.showMessageDialog(this, "Cuenta creada!");

                // Actualizar las otras vistas
                actualizarOtrasVistas(this);

                // Limpiar campo de saldo
                saldoField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Ingrese un saldo válido",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void actualizarOtrasVistas(Component componenteActual) {
        // Obtener el JTabbedPane padre
        Container parent = componenteActual.getParent();
        while (parent != null && !(parent instanceof JTabbedPane)) {
            parent = parent.getParent();
        }

        if (parent != null) {
            JTabbedPane tabbedPane = (JTabbedPane) parent;

            // Recorrer todas las pestañas
            for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                Component tab = tabbedPane.getComponentAt(i);

                if (tab instanceof TransferenciaVista) {
                    ((TransferenciaVista) tab).actualizarCombos();
                } else if (tab instanceof MovimientoVista) {
                    ((MovimientoVista) tab).actualizarCombos();
                }
            }
        }
    }
}