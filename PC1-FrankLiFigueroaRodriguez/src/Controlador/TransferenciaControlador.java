package Controlador;

import Modelo.ServicioBancario;
import Modelo.Movimiento;
import Modelo.Cuenta;
import java.util.Date;

public class TransferenciaControlador {
    private final ServicioBancario servicio;

    public TransferenciaControlador(ServicioBancario servicio) {
        this.servicio = servicio;
    }

    public boolean transferir(int origenId, int destinoId, double monto) {
        // Verificar si las cuentas existen
        Cuenta origen = servicio.buscarCuentaPorId(origenId);
        Cuenta destino = servicio.buscarCuentaPorId(destinoId);

        if (origen == null || destino == null) {
            return false;
        }

        // Verificar fondos suficientes
        if (!origen.puedeRetirar(monto)) {
            return false;
        }

        // Realizar la transferencia
        origen.setSaldo(origen.getSaldo() - monto);
        destino.setSaldo(destino.getSaldo() + monto);

        // Registrar movimientos
        servicio.agregarMovimiento(new Movimiento(
                new Date(),
                "Transferencia a " + destinoId,
                -monto,
                origenId
        ));

        servicio.agregarMovimiento(new Movimiento(
                new Date(),
                "Transferencia de " + origenId,
                monto,
                destinoId
        ));

        return true;
    }
}