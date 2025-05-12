package Controlador;

import Modelo.*;

import java.util.List;

public class BancoHomeControlador {
    private ServicioBancario servicio;

    public BancoHomeControlador(ServicioBancario servicio) {
        this.servicio = servicio;
    }

    public List<Cuenta> obtenerCuentas() {
        return servicio.getCuentas();
    }
}
