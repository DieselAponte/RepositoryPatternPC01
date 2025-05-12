package Modelo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServicioBancario {
    private List<Cuenta> cuentas = new ArrayList<>();
    private List<Movimiento> movimientos = new ArrayList<>();
    private int nextId = 1; // Contador para IDs automáticos


    public synchronized void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    public synchronized List<Cuenta> getCuentas() {
        return new ArrayList<>(cuentas); // Devuelve copia para evitar modificaciones
    }
    public List<Movimiento> getMovimientosPorCuenta(int cuentaId) {
        return movimientos.stream()
                .filter(m -> m.getCuentaId() == cuentaId)
                .toList();
    }
    // Método para agregar movimientos
    public void agregarMovimiento(Movimiento movimiento) {
        movimientos.add(movimiento);
    }

    // Método para buscar cuentas por ID
    public List<Cuenta> buscarCuentasPorDni(String dni) {
        return cuentas.stream()
                .filter(c -> c.getDniTitular().equals(dni))
                .collect(Collectors.toList());
    }
}
