package Controlador;

import Modelo.*;

public class CrearCuentaControlador {
    private ServicioBancario servicio;

    public CrearCuentaControlador(ServicioBancario servicio) {
        this.servicio = servicio;
    }

    public void crearCuenta(String tipo, double saldoInicial, String dniTitular, String nombreTitular) {
        Cuenta cuenta = switch (tipo) {
            case "Ahorro" -> new CuentaAhorro(servicio.getCuentas().size() + 1, saldoInicial, dniTitular, nombreTitular);
            case "Corriente" -> new CuentaCorriente(servicio.getCuentas().size() + 1, saldoInicial, 1000, dniTitular, nombreTitular);
            default -> throw new IllegalArgumentException("Tipo no válido");
        };
        servicio.agregarCuenta(cuenta);
    }
}
