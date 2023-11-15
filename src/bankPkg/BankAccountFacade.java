package bankPkg;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BankAccountFacade {
    private String bankName;
    private Map<Integer, Cuenta> cuentas;

    public BankAccountFacade(String bankName) {
        this.bankName = bankName;
        this.cuentas = new HashMap<>();
    }

    public void createAccount(int accountNumber, String accountHolder) {
        Cuenta cuenta = new Cuenta(accountHolder, BigDecimal.ZERO);
        cuentas.put(accountNumber, cuenta);
        System.out.println("Cuenta creada con éxito para " + accountHolder + ". Saldo inicial: " + cuenta.getSaldo());
    }

    public void deposit(int accountNumber, double amount) {
        Cuenta cuenta = findCuentaByAccountNumber(accountNumber);
        if (cuenta != null) {
            cuenta.credito(BigDecimal.valueOf(amount));
            System.out.println("Depósito exitoso. Nuevo saldo de " + cuenta.getPersona() + ": " + cuenta.getSaldo());
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    public void withdraw(int accountNumber, double amount) {
        Cuenta cuenta = findCuentaByAccountNumber(accountNumber);
        if (cuenta != null) {
            try {
                cuenta.debito(BigDecimal.valueOf(amount));
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
        return cuentas.get(accountNumber);
    }
}
