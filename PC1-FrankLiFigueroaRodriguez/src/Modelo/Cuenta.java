package Modelo;

public abstract class Cuenta {
    private int id;
    private String tipo;
    private double saldo;
    private String dniTitular;
    private String nombreTitular;

    public Cuenta(int id, String tipo, double saldo, String dniTitular, String nombreTitular) {
        this.id = id;
        this.tipo = tipo;
        this.saldo = saldo;
    }

    // Getters y Setters
    public int getId() { return id; }
    public String getTipo() { return tipo; }
    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }
    public int setId(int id) { return this.id = id; }
    public abstract boolean puedeRetirar(double monto);
    public String getDniTitular() { return dniTitular; }
    public String getNombreTitular() { return nombreTitular; }
}
