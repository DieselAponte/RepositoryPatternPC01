package Vista;

import Controlador.*;
import Modelo.*;

import javax.swing.*;
import java.awt.*;

public class MovimientoVista extends JPanel {
    private ServicioBancario servicio;
    private JComboBox<Cuenta> cuentaCombo;
    private JTextArea movimientosArea;

    public MovimientoVista(ServicioBancario servicio) {
        this.servicio = servicio;
        MovimientosControlador controlador = new MovimientosControlador(servicio);

        setLayout(new BorderLayout(5, 5)); // Mejor organización del layout

        cuentaCombo = new JComboBox<>();
        movimientosArea = new JTextArea(10, 30);
        movimientosArea.setEditable(false);
        JButton mostrarBtn = new JButton("Mostrar");
        JButton actualizarBtn = new JButton("Actualizar Cuentas");

        // Panel superior para controles
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panelSuperior.add(new JLabel("Cuenta:"));
        panelSuperior.add(cuentaCombo);
        panelSuperior.add(mostrarBtn);
        panelSuperior.add(actualizarBtn);

        // Configurar renderer del combo
        cuentaCombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                                                          int index, boolean isSelected,
                                                          boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Cuenta) {
                    Cuenta cuenta = (Cuenta) value;
                    setText(String.format("%d - %s ($%.2f)",
                            cuenta.getId(), cuenta.getTipo(), cuenta.getSaldo()));
                }
                return c;
            }
        });

        actualizarCombos();

        actualizarBtn.addActionListener(e -> actualizarCombos());

        mostrarBtn.addActionListener(e -> {
            movimientosArea.setText("");
            Cuenta seleccionada = (Cuenta) cuentaCombo.getSelectedItem();
            if(seleccionada != null) {
                controlador.obtenerMovimientos(seleccionada.getId())
                        .forEach(m -> movimientosArea.append(
                                String.format("%tF - %s: $%.2f\n",
                                        m.getFecha(), m.getDescripcion(), m.getMonto())
                        ));
            }
        });

        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(movimientosArea), BorderLayout.CENTER);
    }

    public void actualizarCombos() {
        SwingUtilities.invokeLater(() -> {
            cuentaCombo.removeAllItems();
            servicio.getCuentas().forEach(cuentaCombo::addItem);
        });
    }
}