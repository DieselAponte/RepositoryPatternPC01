package Modelo;

import RepositoryPattern.*;
import java.util.List;

public class ServicioBancario {
    private CuentaRepository cuentaRepo;
    private MovimientoRepository movimientoRepo;

    public ServicioBancario(CuentaRepository cuentaRepo, MovimientoRepository movimientoRepo) {
        this.cuentaRepo = cuentaRepo;
        this.movimientoRepo = movimientoRepo;
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentaRepo.guardar(cuenta);
    }

    public List<Cuenta> getCuentas() {
        return cuentaRepo.buscarTodos();
    }

    public Cuenta buscarCuentaPorId(int id) {
        return cuentaRepo.buscarPorId(id); // 🔧 Este es el método que faltaba
    }

    public void agregarMovimiento(Movimiento movimiento) {
        movimientoRepo.guardarMovimiento(movimiento);
    }

    public List<Movimiento> getMovimientosPorCuenta(int cuentaId) {
        return movimientoRepo.buscarMovimientoPorCuentaId(cuentaId);
    }

    public List<Cuenta> buscarCuentasPorDni(String dni) {
        return cuentaRepo.buscarPorDni(dni);
    }
}
