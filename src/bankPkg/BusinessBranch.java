package bankPkg;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BusinessBranch implements BankBranch {
    private String branchName;
    private List<Account> accounts;

    public BusinessBranch(String branchName) {
        this.branchName = branchName;
        this.accounts = new ArrayList<>();
    }

    @Override
    public List<Account> getAccounts() {
        return accounts;
    }

    @Override
    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    @Override
    public void achieveObjectives() {
        System.out.println("Business branch specific objectives achieved.");
        for (Account account : accounts) {
            if (account.getBalance().compareTo(new BigDecimal("1000")) > 0 && !account.hasLoan()) {
                provideLoans(account, 10000); // Maximum loan amount for businesses
                grantDataphones(account);
            }
        }
    }

    @Override
    public void grantDataphones(Account accountForDataphone) {
        if (!accountForDataphone.hasDataphone()) {
            accountForDataphone.setDataphoneAccountNumber(new BigDecimal("1234567890"));
            System.out.println("Dataphone granted for " + accountForDataphone.getPerson() +
                    ". Dataphone Account Number: " + accountForDataphone.getDataphoneAccountNumber());
        }
    }

    @Override
    public void provideLoans(Account account, int maxLoanAmount) {
        if (account.getBalance().compareTo(new BigDecimal("1000")) > 0 && !account.hasLoan()) {
            BigDecimal currentBalance = account.getBalance();
            BigDecimal loanAmount = currentBalance.compareTo(new BigDecimal(maxLoanAmount)) > 0
                    ? new BigDecimal(maxLoanAmount)
                    : currentBalance;
            account.debit(loanAmount);
            System.out.println("Loan provided to " + account.getPerson() +
                    " in the amount of " + loanAmount);
            System.out.println("Updated balance for " + account.getPerson() + ": " + account.getBalance());
            account.setLoanAmount(account.getLoanAmount().add(loanAmount));
        }
    }

    @Override
    public void openCDT(Account account) {
        System.out.println("You are not able to open a CDT");
    }

    @Override
    public String getBranchName() {
        return branchName;
    }
}

