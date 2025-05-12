package Modelo;

import javax.swing.*;

public class CuentaAhorro extends Cuenta{
    public CuentaAhorro(int id, double saldo, String dniTitular, String nombreTitular) {
        super(id, "Ahorro", saldo, dniTitular, nombreTitular);
    }

    @Override
    public boolean puedeRetirar(double monto) {
        return getSaldo() >= monto;
    }
}
