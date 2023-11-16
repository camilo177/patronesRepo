package bankPkg;

import java.util.List;

public interface BankBranch {
    List<Account> getAccounts();

    void addAccount(Account account);

    void achieveObjectives();

    void provideLoans(Account account, int maxLoanAmount);

    void openCDT(Account account);

    void grantDataphones(Account account);

    String getBranchName();
}
