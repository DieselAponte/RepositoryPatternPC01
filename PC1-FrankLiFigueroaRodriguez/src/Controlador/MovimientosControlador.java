package Controlador;
import Modelo.*;
import java.util.List;

public class MovimientosControlador {
    private ServicioBancario servicio;

    public MovimientosControlador(ServicioBancario servicio) {
        this.servicio = servicio;
    }

    public List<Movimiento> obtenerMovimientos(int cuentaId) {
        return servicio.getMovimientosPorCuenta(cuentaId);
    }

}
