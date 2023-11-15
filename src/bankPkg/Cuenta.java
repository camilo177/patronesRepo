package bankPkg;

import java.math.BigDecimal;

public class Cuenta {
    private static int cuentaCounter = 1000;
    private int numeroCuenta;
    private Client cliente;
    private BigDecimal saldo;

    public Cuenta(Client cliente, BigDecimal saldo) {
        this.numeroCuenta = ++cuentaCounter;
        this.cliente = cliente;
        this.saldo = saldo;
    }

    public String getPersona() {
        return cliente.getName(); // Use the client's name as persona
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void debito(BigDecimal monto) {
        BigDecimal nuevoSaldo = this.saldo.subtract(monto);
        if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new DineroInsuficienteException("Dinero Insuficiente");
        }
        this.saldo = nuevoSaldo;
    }

    public void credito(BigDecimal monto) {
        this.saldo = this.saldo.add(monto);
    }
}
