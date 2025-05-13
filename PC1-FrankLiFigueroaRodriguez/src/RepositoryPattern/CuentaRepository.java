package RepositoryPattern;
import Modelo.Cuenta;
import java.util.List;


public interface CuentaRepository {
    void guardar(Cuenta cuenta);
    Cuenta buscarPorId(int id);
    List<Cuenta> buscarTodos();
    List<Cuenta> buscarPorDni(String dni);
    void actualizarCuenta(Cuenta cuenta);
    void eliminarCuenta(int id);
}
