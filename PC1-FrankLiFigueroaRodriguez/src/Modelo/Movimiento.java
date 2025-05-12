package Modelo;

import java.util.Date;

public class Movimiento {
    private Date fecha;
    private String descripcion;
    private double monto;
    private int cuentaId;

    public Movimiento(Date fecha, String descripcion, double monto, int cuentaId) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.monto = monto;
        this.cuentaId = cuentaId;
    }

    // Getters
    public Date getFecha() { return fecha; }
    public String getDescripcion() { return descripcion; }
    public double getMonto() { return monto; }
    public int getCuentaId() { return cuentaId; }
}
