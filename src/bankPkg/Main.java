package bankPkg;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        displayBanner();

        System.out.print("Select the type of user (1 for Natural People, 2 for Business): ");
        int userTypeChoice = scanner.nextInt();
        scanner.nextLine();

        BankBranch bankBranch;
        if (userTypeChoice == 1) {
            bankBranch = new NaturalPeopleBranch("Natural People Branch");
        } else if (userTypeChoice == 2) {
            bankBranch = new BusinessBranch("Business Branch");
        } else {
            System.out.println("Invalid user type selection. Exiting...");
            scanner.close();
            return;
        }

        BankAccountFacade bankAccountFacade = new BankAccountFacade("UCC Bank", bankBranch);

        while (true) {
            displayMenu(userTypeChoice);

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: {
                    createAccount(scanner, bankAccountFacade);
                    break;
                }
                case 2: {
                    performDeposit(scanner, bankAccountFacade);
                    break;
                }
                case 3: {
                    performWithdrawal(scanner, bankAccountFacade);
                    break;
                }
                case 4: {
                    checkBalance(scanner, bankAccountFacade);
                    break;
                }
                case 5: {
                    System.out.println("Thank you for using " + bankAccountFacade.getBankName() + ". Goodbye!");
                    scanner.close();
                    System.exit(0);
                }
                case 6: {
                    performUserTypeSpecificOperation(scanner, userTypeChoice, bankAccountFacade);
                    break;
                }
                case 7: {
                    performUserTypeSpecificOperation(scanner, userTypeChoice, bankAccountFacade);
                    break;
                }
                default:
                    System.out.println("Invalid selection. Please try again.");
            }
        }
    }

    private static void createAccount(Scanner scanner, BankAccountFacade bankAccountFacade) {
        System.out.print("Enter the account holder's name: ");
        String accountHolderName = scanner.nextLine();

        System.out.print("Enter the account holder's address: ");
        String accountHolderAddress = scanner.nextLine();

        System.out.print("Enter the account holder's email: ");
        String accountHolderEmail = scanner.nextLine();

        System.out.print("Enter the initial amount: ");
        BigDecimal initialBalance = scanner.nextBigDecimal();
        scanner.nextLine();

        int generatedAccountNumber = bankAccountFacade.createAccount(accountHolderName, accountHolderAddress, accountHolderEmail, initialBalance);
        System.out.println("Generated Account Number: " + generatedAccountNumber);
    }

    private static void performDeposit(Scanner scanner, BankAccountFacade bankAccountFacade) {
        System.out.print("Enter the account number: ");
        int accountNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter the deposit amount: ");
        BigDecimal depositAmount = scanner.nextBigDecimal();
        scanner.nextLine();

        bankAccountFacade.deposit(accountNumber, depositAmount);
    }

    private static void performWithdrawal(Scanner scanner, BankAccountFacade bankAccountFacade) {
        System.out.print("Enter the account number: ");
        int accountNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter the withdrawal amount: ");
        BigDecimal withdrawAmount = scanner.nextBigDecimal();
        scanner.nextLine();

        bankAccountFacade.withdraw(accountNumber, withdrawAmount);
    }

    private static void checkBalance(Scanner scanner, BankAccountFacade bankAccountFacade) {
        System.out.print("Enter the account number: ");
        int accountNumber = scanner.nextInt();
        scanner.nextLine();

        bankAccountFacade.checkBalance(accountNumber);
    }

    private static void performUserTypeSpecificOperation(Scanner scanner, int userTypeChoice, BankAccountFacade bankAccountFacade) {
        if (userTypeChoice == 1) {
            // Natural People specific option
            handleNaturalPeopleOperation(scanner, bankAccountFacade);
        } else if (userTypeChoice == 2) {
            // Business specific option
            handleBusinessOperation(scanner, bankAccountFacade);
        }
    }

    private static void handleNaturalPeopleOperation(Scanner scanner, BankAccountFacade bankAccountFacade) {
        System.out.println("Natural People specific options:");
        System.out.println("6. Provide Loans");
        System.out.println("7. Open CDT");
        System.out.print("Enter the operation number: ");
        int operationChoice = scanner.nextInt();
        scanner.nextLine();

        switch (operationChoice) {
            case 6:
                System.out.print("Enter the account number: ");
                int accountNumberForLoans = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter the loan amount: ");
                BigDecimal loanAmount = scanner.nextBigDecimal();
                scanner.nextLine();
                BigDecimal newBalanceAfterLoan = bankAccountFacade.provideLoans(accountNumberForLoans, loanAmount);
                System.out.println("Loan provided. Updated balance for the account: " + newBalanceAfterLoan);
                break;

            case 7:
                System.out.print("Enter the account number: ");
                int accountNumberForCDT = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter the CDT amount: ");
                BigDecimal cdtAmount = scanner.nextBigDecimal();
                scanner.nextLine();
                BigDecimal newBalanceAfterCDT = bankAccountFacade.openCDT(accountNumberForCDT, cdtAmount);
                System.out.println("CDT opened. Updated balance for the account: " + newBalanceAfterCDT + " with a CDT of: " +
                        cdtAmount);
                break;

            default:
                System.out.println("Invalid operation choice for Natural People.");
                break;
        }
    }

    private static void handleBusinessOperation(Scanner scanner, BankAccountFacade bankAccountFacade) {
        System.out.println("Business specific options:");
        System.out.println("6. Grant Dataphones");
        System.out.println("7. Achieve Business Objectives (Takes a fee of 10.000 for services)");
        System.out.print("Enter the operation number: ");
        int operationChoice = scanner.nextInt();
        scanner.nextLine();

        switch (operationChoice) {
            case 6:
                System.out.print("Enter the account number: ");
                int accountNumberForDataphones = scanner.nextInt();
                scanner.nextLine();
                bankAccountFacade.grantDataphones(accountNumberForDataphones);
                break;

            case 7:
                bankAccountFacade.achieveBranchObjectives();
                break;

            default:
                System.out.println("Invalid operation choice for Business.");
                break;
        }
    }

    private static void displayBanner() {
        System.out.println("***************************");
        System.out.println("   WELCOME TO UCC BANK   ");
        System.out.println("***************************");
    }

    private static void displayMenu(int userTypeChoice) {
        System.out.println("\nMenu:");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit Funds");
        System.out.println("3. Withdraw Funds");
        System.out.println("4. Check Balance");
        System.out.println("5. Exit");

        // Additional options based on user type
        if (userTypeChoice == 1) {
            System.out.println("6. Provide Loans");
            System.out.println("7. Open CDT");
        } else if (userTypeChoice == 2) {
            System.out.println("6. Grant Dataphones");
            System.out.println("7. Achieve Business Objectives ()");
        }

        System.out.print("Enter your choice: ");
    }
}
