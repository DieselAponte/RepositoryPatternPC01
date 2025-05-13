package Modelo;

public class CuentaCorriente extends Cuenta {
    private double limite;

    public CuentaCorriente(int id, double saldo, double limite, String dniTitular, String nombreTitular) {
        super(id, "Corriente", saldo, dniTitular, nombreTitular);
        this.limite = limite;
    }

    @Override
    public boolean puedeRetirar(double monto) {
        return getSaldo() + limite >= monto;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }
}
