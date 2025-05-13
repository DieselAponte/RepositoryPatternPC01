package RepositoryPattern;
import Modelo.Movimiento;
import java.util.List;

public interface MovimientoRepository {
    public void guardarMovimiento(Movimiento movimiento);
    List<Movimiento> buscarMovimientoPorCuentaId(int cuentaId);
}
