package bankPkg;

import java.math.BigDecimal;

public class BankAccountFacade {
    private String bankName;
    private Banco banco;

    public BankAccountFacade(String bankName, Banco banco) {
        this.bankName = bankName;
        this.banco = banco;
    }

    public void createAccount(String name, String address, String email, BigDecimal saldo) {
        Client cliente = new Client(name, address, email);
        Cuenta cuenta = new Cuenta(cliente, saldo);
        banco.anadirCuenta(cuenta);
        System.out.println("Cuenta creada con éxito para " + cliente.getName() + ". Saldo inicial: " + cuenta.getSaldo());
    }

    public void deposit(int accountNumber, BigDecimal amount) {
        Cuenta cuenta = findCuentaByAccountNumber(accountNumber);
        if (cuenta != null) {
            cuenta.credito(amount);
            System.out.println("Depósito exitoso. Nuevo saldo de " + cuenta.getPersona() + ": " + cuenta.getSaldo());
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    public void withdraw(int accountNumber, BigDecimal amount) {
        Cuenta cuenta = findCuentaByAccountNumber(accountNumber);
        if (cuenta != null) {
            try {
                cuenta.debito(amount);
                System.out.println("Retiro exitoso. Nuevo saldo de " + cuenta.getPersona() + ": " + cuenta.getSaldo());
            } catch (DineroInsuficienteException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    public void checkBalance(int accountNumber) {
        Cuenta cuenta = findCuentaByAccountNumber(accountNumber);
        if (cuenta != null) {
            System.out.println("Titular de cuenta: " + cuenta.getPersona());
            System.out.println("Balance: " + cuenta.getSaldo());
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    public String getBankName() {
        return bankName;
    }

    private Cuenta findCuentaByAccountNumber(int accountNumber) {
        for (Cuenta cuenta : banco.getCuentas()) {
            if (cuenta.getNumeroCuenta() == accountNumber) {
                return cuenta;
            }
        }
        return null;
    }
}
