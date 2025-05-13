package RepositoryPattern;
import Modelo.*;
import java.sql.*;
import java.util.*;

public class ConcreteCuentaRepository implements CuentaRepository {
    private Connection connection;

    public ConcreteCuentaRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void guardar(Cuenta cuenta) {
        if (cuenta == null) return;
        String sql = "INSERT INTO cuentas (id, tipo, saldo, dniTitular, nombreTitular, limite) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cuenta.getId());
            stmt.setString(2, cuenta.getTipo());
            stmt.setDouble(3, cuenta.getSaldo());
            stmt.setString(4, cuenta.getDniTitular());
            stmt.setString(5, cuenta.getNombreTitular());
            if (cuenta instanceof CuentaCorriente cc) {
                stmt.setDouble(6, cc.getLimite());
            } else {
                stmt.setDouble(6, 0);
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al guardar cuenta: " + e.getMessage());
        }
    }

    @Override
    public Cuenta buscarPorId(int id) {
        String sql = "SELECT * FROM cuentas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return construirCuentaDesdeRS(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar cuenta por ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Cuenta> buscarTodos() {
        List<Cuenta> lista = new ArrayList<>();
        String sql = "SELECT * FROM cuentas";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(construirCuentaDesdeRS(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar todas las cuentas: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public List<Cuenta> buscarPorDni(String dni) {
        List<Cuenta> lista = new ArrayList<>();
        if (dni == null || dni.isBlank()) return lista;

        String sql = "SELECT * FROM cuentas WHERE dniTitular = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, dni);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(construirCuentaDesdeRS(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar cuentas por DNI: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void actualizarCuenta(Cuenta cuenta) {
        if (cuenta == null) return;

        String sql = "UPDATE cuentas SET saldo = ?, limite = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, cuenta.getSaldo());
            if (cuenta instanceof CuentaCorriente cc) {
                stmt.setDouble(2, cc.getLimite());
            } else {
                stmt.setDouble(2, 0);
            }
            stmt.setInt(3, cuenta.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar cuenta: " + e.getMessage());
        }
    }

    @Override
    public void eliminarCuenta(int id) {
        String sql = "DELETE FROM cuentas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar cuenta: " + e.getMessage());
        }
    }

    private Cuenta construirCuentaDesdeRS(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String tipo = rs.getString("tipo");
        double saldo = rs.getDouble("saldo");
        String dni = rs.getString("dniTitular");
        String nombre = rs.getString("nombreTitular");
        double limite = rs.getDouble("limite");

        return tipo.equalsIgnoreCase("Corriente")
                ? new CuentaCorriente(id, saldo, limite, dni, nombre)
                : new CuentaAhorro(id, saldo, dni, nombre);
    }
}
