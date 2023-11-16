// Bank class with Factory Method
package bankPkg;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private List<Account> accounts;
    private Map<Integer, BankBranch> branchFactory;

    public Bank() {
        accounts = new ArrayList<>();
        branchFactory = new HashMap<>();
        branchFactory.put(1, new NaturalPeopleBranch("Natural People Branch"));
        branchFactory.put(2, new BusinessBranch("Business Branch"));
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public BankBranch createBranch(int userTypeChoice) {
        return branchFactory.get(userTypeChoice);
    }
    public void achieveObjectives() {
        System.out.println("Branch-specific objectives achieved.");
        for (BankBranch branch : branchFactory.values()) {
            for (Account account : branch.getAccounts()) {
                if (account.getBalance().compareTo(new BigDecimal("1000")) > 0 && !account.hasLoan()) {
                    branch.provideLoans(account, 5000); // Maximum loan amount for natural people
                    branch.openCDT(account);
                }
            }
        }
    }
}