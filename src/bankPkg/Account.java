package bankPkg;

import java.math.BigDecimal;

public class Account {
    private int accountNumber;
    private Client client;
    private BigDecimal balance;
    private BigDecimal loanAmount = BigDecimal.ZERO;
    private BigDecimal dataphoneAccountNumber; // For businesses
    private BigDecimal cdtAmount; // For natural people

    public Account(int accountNumber, Client client, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.client = client;
        this.balance = balance;
    }

    public String getPerson() {
        return client.getName();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public boolean hasLoan() {
        return loanAmount.compareTo(BigDecimal.ZERO) > 0;
    }

    public BigDecimal getDataphoneAccountNumber() {
        return dataphoneAccountNumber;
    }

    public BigDecimal getCdtAmount() {
        return cdtAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public void setDataphoneAccountNumber(BigDecimal dataphoneAccountNumber) {
        this.dataphoneAccountNumber = dataphoneAccountNumber;
    }

    public void setCdtAmount(BigDecimal cdtAmount) {
        this.cdtAmount = cdtAmount;
    }

    public boolean hasDataphone() {
        return dataphoneAccountNumber != null;
    }

    public boolean hasCDT() {
        return cdtAmount != null;
    }

    public void debit(BigDecimal amount) {
        BigDecimal newBalance = this.balance.subtract(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientFundsException("Insufficient Funds");
        }
        this.balance = newBalance;
    }

    public void credit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }
}
