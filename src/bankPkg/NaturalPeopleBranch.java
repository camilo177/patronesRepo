package bankPkg;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class NaturalPeopleBranch implements BankBranch {
    private String branchName;
    private List<Account> accounts;

    public NaturalPeopleBranch(String branchName) {
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
        System.out.println("Natural People branch specific objectives achieved.");
        for (Account account : accounts) {
            if (account.getBalance().compareTo(new BigDecimal("1000")) > 0 && !account.hasLoan()) {
                provideLoans(account, 5000); // Maximum loan amount for natural people
                openCDT(account);
            }
        }
    }


    @Override
    public void openCDT(Account accountToOpenCDT) {
        if (!accountToOpenCDT.hasCDT()) {
            accountToOpenCDT.setCdtAmount(new BigDecimal("2000"));
            System.out.println("CDT opened for " + accountToOpenCDT.getPerson() + " with amount: " + accountToOpenCDT.getCdtAmount());
        } else {
            System.out.println("CDT already exists for " + accountToOpenCDT.getPerson());
        }
    }


    @Override
    public void grantDataphones(Account account) {
        System.out.println("You are unable to receive a Dataphone");
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
            account.setLoanAmount(account.getLoanAmount().add(loanAmount));
            System.out.println("Updated balance for " + account.getPerson() + ": " + account.getBalance());
        }
    }


    @Override
    public String getBranchName() {
        return branchName;
    }
}
