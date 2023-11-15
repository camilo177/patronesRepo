package bankPkg;

import java.math.BigDecimal;

public class Cuenta {
    private static int cuentaCounter = 1000;
    private int numeroCuenta;
    private String persona;
    private BigDecimal saldo;

    public Cuenta(String persona, BigDecimal saldo) {
        this.numeroCuenta = ++cuentaCounter;
        this.persona = persona;
        this.saldo = saldo;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getPersona() {
        return persona;
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
