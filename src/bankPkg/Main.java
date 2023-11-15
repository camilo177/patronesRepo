package bankPkg;
import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BankAccountFacade bankAccountFacade = new BankAccountFacade("MiBanco");

        Scanner scanner = new Scanner(System.in);

        displayBanner();

        while (true) {
            displayMenu();

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1: {
                    System.out.print("Ingrese el número de cuenta: ");
                    int accountNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    System.out.print("Ingrese el nombre del titular de la cuenta: ");
                    String accountHolder = scanner.nextLine();

                    bankAccountFacade.createAccount(accountNumber, accountHolder);
                    break;
                }
                case 2: {
                    System.out.print("Ingrese el número de cuenta: ");
                    int accountNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    System.out.print("Ingrese la cantidad para el depósito: ");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume the newline character

                    bankAccountFacade.deposit(accountNumber, depositAmount);
                    break;
                }
                case 3: {
                    System.out.print("Ingrese el número de cuenta: ");
                    int accountNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    System.out.print("Ingrese la cantidad para el retiro: ");
                    double withdrawAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume the newline character

                    bankAccountFacade.withdraw(accountNumber, withdrawAmount);
                    break;
                }
                case 4: {
                    System.out.print("Ingrese el número de cuenta: ");
                    int accountNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    bankAccountFacade.checkBalance(accountNumber);
                    break;
                }
                case 5: {
                    System.out.println("Gracias por usar " + bankAccountFacade.getBankName() + ". ¡Hasta luego!");
                    scanner.close();
                    System.exit(0);
                }
                default:
                    System.out.println("Selección inválida. Por favor, intente de nuevo.");
            }
        }
    }

    private static void displayBanner() {
        System.out.println("***************************");
        System.out.println("     BIENVENIDO A MIBANCO    ");
        System.out.println("***************************");
    }

    private static void displayMenu() {
        System.out.println("\nMenú:");
        System.out.println("1. Crear Cuenta");
        System.out.println("2. Depositar Fondos");
        System.out.println("3. Retirar Fondos");
        System.out.println("4. Consultar Saldo");
        System.out.println("5. Salir");
        System.out.print("Ingrese su elección: ");
    }
}

