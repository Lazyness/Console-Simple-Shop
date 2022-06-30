package guru.work.prog.main;

import guru.work.prog.includes.FileWorker;
import guru.work.prog.includes.Login;
import guru.work.prog.includes.Registration;
import guru.work.prog.info.Info;
import guru.work.prog.models.Cash;
import guru.work.prog.models.products.Pizza;
import guru.work.prog.models.products.Purchase;
import guru.work.prog.models.products.Shawerma;
import guru.work.prog.models.products.Sushi;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

import static guru.work.prog.includes.FileWorker.*;

public class MenuSystem {
    private static final ArrayList<Purchase> purchaseArrayList = new ArrayList<>();

    public static String getData(String message) {
        System.out.print(message);
        Scanner s = new Scanner(System.in);
        String sS;
        return !(sS = s.nextLine()).contains(" ") ? sS : null;
    }

    private static boolean menu() {
        System.out.println("Menu:\n\t1.Authorization.\n\t2.Registration.\n\t3.Exit");
        System.out.print("Your choice is - ");
        Scanner scanChoice = new Scanner(System.in);
        try {
            int choice = scanChoice.nextInt();
            switch (choice) {
                case 1: {
                    System.out.println("Authorization");
                    try {
                        Login l = new Login(getData("Enter your login please "), getData("Enter your password please "));
                        l.start();
                        while (true) {
                            String role = checkUserRole(l.getLogin(), l.getPassword());
                            if (role != null) {
                                if (!menuAfterAuthorization(role, l.getLogin(), l.getPassword(), checkUserMoney(l.getLogin(), l.getPassword())))
                                    break;
                            } else break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Exception authorization!");
                    }
                    return true;
                }
                case 2: {
                    System.out.println("Registration");
                    try {
                        new Registration(getData("Enter your login please "), getData("Enter your e-mail please "),
                                getData("Enter your password please "), getData("Enter your sex please "),
                                Double.parseDouble(Objects.requireNonNull(getData("Enter your money please ")))).start();
                    } catch (Exception e) {
                        System.out.println("Maybe you enter bad symbol in field");
                    }
                    return true;
                }
                case 3: {
                    scanChoice.close();
                    return false;
                }
                default: {
                    System.out.println("You enter useless number!");
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("You enter symbol, not number! Please change your choice...");
            return true;
        }
    }

    private static boolean menuAfterAuthorization(String role, String login, String password, double money) {
        String temp = role.equals("admin") ? "\n\t\t3.Create purchase.\n\t\t4.Exit" : "\n\t\t3.Exit";
        System.out.println("\tMenu:\n\t\t1.Show list purchase and bill.\n\t\t2.Top up the account." + temp);
        System.out.print("Your choice is - ");
        Scanner scanChoice = new Scanner(System.in);
        try {
            int choice = scanChoice.nextInt();
            if (role.equals("admin")) {
                switch (choice) {
                    case 1: {
                        System.out.println("List purchase and bill");
                        try {
                            Info.printData(FileWorker.readerPurchase());
                            System.out.print("Your choice is - ");
                            int yourChoice = scanChoice.nextInt();
                            Cash cash = new Cash(login, password);
                            cash.setResultCalculate(money - FileWorker.readerPurchase().get(yourChoice - 1).getPrice());
                            FileWorker.editorUserInFile(login, password, cash.getResultCalculate());
                        } catch (Exception e) {
                            System.out.println("Exception show list purchase!");
                        }
                        return true;
                    }
                    case 2: {
                        System.out.println("Top up the account");
                        try {
                            System.out.print("Your summa is wanna to top up - ");
                            Scanner scanner = new Scanner(System.in);
                            scanner.useLocale(Locale.US);
                            double yourChoice = scanner.nextDouble();
                            Cash cash = new Cash(login, password);
                            cash.setResultCalculate(money + yourChoice);
                            FileWorker.editorUserInFile(login, password, cash.getResultCalculate());
                        } catch (Exception e) {
                            System.out.println("Exception top up money account user: " + login);
                        }
                        return true;
                    }
                    case 3: {
                        System.out.println("Create purchase");
                        try {
                            System.out.println("\t\tMenu:\n\t\t\t1.Create Pizza.\n\t\t\t2.Create Sushi.\n\t\t\t3.Create Shawerma.\n\t\t\t4.Clear list purchase.");
                            System.out.print("Your choice is - ");
                            try {
                                int yourChoice = scanChoice.nextInt();
                                switch (yourChoice) {
                                    case 1: {
                                        purchaseArrayList.add(new Pizza(getData("Enter name pizza "), Double.parseDouble(Objects.requireNonNull(getData("Enter weight pizza "))),
                                                Double.parseDouble(Objects.requireNonNull(getData("Enter height pizza "))),
                                                Double.parseDouble(Objects.requireNonNull(getData("Enter price pizza "))),
                                                "pizza")
                                        );
                                        writePurchaseInFileLog(purchaseArrayList);
                                        break;
                                    }
                                    case 2: {
                                        purchaseArrayList.add(new Sushi(getData("Enter name sushi "), Double.parseDouble(Objects.requireNonNull(getData("Enter weight sushi "))),
                                                Double.parseDouble(Objects.requireNonNull(getData("Enter height sushi "))),
                                                Double.parseDouble(Objects.requireNonNull(getData("Enter price sushi "))),
                                                "sushi")
                                        );
                                        writePurchaseInFileLog(purchaseArrayList);
                                        break;
                                    }
                                    case 3: {
                                        purchaseArrayList.add(new Shawerma(getData("Enter name shawerma "), Double.parseDouble(Objects.requireNonNull(getData("Enter weight shawerma "))),
                                                Double.parseDouble(Objects.requireNonNull(getData("Enter height shawerma "))),
                                                Double.parseDouble(Objects.requireNonNull(getData("Enter price shawerma "))),
                                                "shawerma")
                                        );
                                        writePurchaseInFileLog(purchaseArrayList);
                                        break;
                                    }
                                    case 4: {
                                        FileWorker.clearFile(new File("./src/guru/work/prog/files/purchase.txt"));
                                        break;
                                    }
                                    default: {

                                    }
                                }
                            } catch (Exception e) {

                            }
                        } catch (Exception e) {
                            System.out.println("");
                        }
                        return true;
                    }
                    case 4: {
                        return false;
                    }
                    default: {
                        System.out.println("You enter useless number!");
                        return true;
                    }
                }
            } else {
                switch (choice) {
                    case 1: {
                        System.out.println("List purchase and bill");
                        try {
                            Info.printData(FileWorker.readerPurchase());
                            System.out.print("Your choice is - ");
                            int yourChoice = scanChoice.nextInt();
                            Cash cash = new Cash(login, password);
                            cash.setResultCalculate(money - FileWorker.readerPurchase().get(yourChoice - 1).getPrice());
                            FileWorker.editorUserInFile(login, password, cash.getResultCalculate());
                        } catch (Exception e) {
                            System.out.println("Exception authorization!");
                        }
                        return true;
                    }
                    case 2: {
                        System.out.println("Top up the account");
                        try {
                            System.out.print("Your summa is wanna to top up - ");
                            int yourChoice = scanChoice.nextInt();
                            Cash cash = new Cash(login, password);
                            cash.setResultCalculate(money + FileWorker.readerPurchase().get(yourChoice - 1).getPrice());
                            FileWorker.editorUserInFile(login, password, cash.getResultCalculate());
                        } catch (Exception e) {
                            System.out.println("Exception top up money account user: " + login);
                        }
                        return true;
                    }
                    case 3: {
                        return false;
                    }
                    default: {
                        System.out.println("You enter useless number!");
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("You enter symbol, not number! Please change your choice...");
            return true;
        }
    }


    public static void start() {
        while (true) if (!menu()) break;
    }
}
