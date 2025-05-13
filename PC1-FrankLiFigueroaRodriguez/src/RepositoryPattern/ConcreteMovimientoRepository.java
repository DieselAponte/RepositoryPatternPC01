package RepositoryPattern;

import Modelo.Movimiento;
import java.sql.*;
import java.util.*;

public class ConcreteMovimientoRepository implements MovimientoRepository {
    private Connection connection;

    public ConcreteMovimientoRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void guardarMovimiento(Movimiento movimiento) {
        if (movimiento == null) return;
        String sql = "INSERT INTO movimientos (fecha, descripcion, monto, cuentaId) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setTimestamp(1, new Timestamp(movimiento.getFecha().getTime()));
            stmt.setString(2, movimiento.getDescripcion());
            stmt.setDouble(3, movimiento.getMonto());
            stmt.setInt(4, movimiento.getCuentaId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al guardar movimiento: " + e.getMessage());
        }
    }

    @Override
    public List<Movimiento> buscarMovimientoPorCuentaId(int cuentaId) {
        List<Movimiento> lista = new ArrayList<>();
        String sql = "SELECT * FROM movimientos WHERE cuentaId = ? ORDER BY fecha DESC";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cuentaId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Movimiento m = new Movimiento(
                            rs.getTimestamp("fecha"),
                            rs.getString("descripcion"),
                            rs.getDouble("monto"),
                            rs.getInt("cuentaId")
                    );
                    lista.add(m);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar movimientos por cuenta: " + e.getMessage());
        }
        return lista;
    }
}
