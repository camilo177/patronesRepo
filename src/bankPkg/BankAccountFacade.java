// BankAccountFacade class with updated methods
package bankPkg;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

public class BankAccountFacade {
    private static final AtomicInteger accountCounter = new AtomicInteger(999_999_99);

    private String bankName;
    private BankBranch bankBranch;

    public BankAccountFacade(String bankName, BankBranch bankBranch) {
        this.bankName = bankName;
        this.bankBranch = bankBranch;
    }

    private int generateAccountNumber() {
        int generatedNumber = accountCounter.incrementAndGet();
        if (generatedNumber > 999_999_99) {
            accountCounter.set(1_000_000);
            generatedNumber = accountCounter.get();
        }
        return generatedNumber;
    }

    public int createAccount(String name, String address, String email, BigDecimal balance) {
        int accountNumber = generateAccountNumber();
        Client client = new Client(name, address, email);
        Account account = new Account(accountNumber, client, balance);
        bankBranch.addAccount(account);

        System.out.println("Account created successfully for " + client.getName() +
                ". Initial balance: " + account.getBalance() +
                ". Branch: " + bankBranch.getBranchName());

        return accountNumber;
    }

    public void achieveBranchObjectives() {
        bankBranch.achieveObjectives();
    }

    public BigDecimal provideLoans(int accountNumber, BigDecimal maxLoanAmount) {
        Account account = findAccountByAccountNumber(accountNumber);
        if (account != null) {
            if (account.getBalance().compareTo(new BigDecimal("1000")) > 0 && !account.hasLoan()) {
                BigDecimal currentBalance = account.getBalance();
                BigDecimal loanAmount = currentBalance.compareTo(maxLoanAmount) > 0
                        ? maxLoanAmount
                        : currentBalance;
                account.debit(loanAmount);
                System.out.println("Loan provided to " + account.getPerson() +
                        " in the amount of " + loanAmount);
                account.setLoanAmount(account.getLoanAmount().add(loanAmount));
                System.out.println("Updated balance for " + account.getPerson() + ": " + account.getBalance());
                return account.getBalance();  // Return the updated balance
            }
        } else {
            System.out.println("Account not found.");
        }
        return BigDecimal.ZERO;  // Return a default value if the operation fails
    }


    public BigDecimal openCDT(int accountNumber, BigDecimal cdtAmount) {
        Account account = findAccountByAccountNumber(accountNumber);
        if (account != null) {
            if (!account.hasCDT()) {
                account.setCdtAmount(cdtAmount);
                System.out.println("CDT opened for " + account.getPerson() + " with amount: " + account.getCdtAmount());
                return account.getBalance();  // Return the updated balance
            }
        } else {
            System.out.println("Account not found.");
        }
        return null;  // Return null if the operation fails
    }


    public void grantDataphones(int accountNumber) {
        Account account = findAccountByAccountNumber(accountNumber);
        if (account != null) {
            bankBranch.grantDataphones(account);
        } else {
            System.out.println("Account not found.");
        }
    }
    private Account findAccountByAccountNumber(int accountNumber) {
        for (Account account : bankBranch.getAccounts()) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    public void deposit(int accountNumber, BigDecimal amount) {
        Account account = findAccountByAccountNumber(accountNumber);
        if (account != null) {
            account.credit(amount);
            System.out.println("Deposit successful. New balance for " + account.getPerson() + ": " + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    public void withdraw(int accountNumber, BigDecimal amount) {
        Account account = findAccountByAccountNumber(accountNumber);
        if (account != null) {
            try {
                account.debit(amount);
                System.out.println("Withdrawal successful. New balance for " + account.getPerson() + ": " + account.getBalance());
            } catch (InsufficientFundsException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    public void checkBalance(int accountNumber) {
        Account account = findAccountByAccountNumber(accountNumber);
        if (account != null) {
            System.out.println("Account holder: " + account.getPerson());
            System.out.println("Balance: " + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    public String getBankName() {
        return bankName;
    }
}
