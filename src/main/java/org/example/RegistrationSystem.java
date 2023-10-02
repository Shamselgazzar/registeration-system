package org.example;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationSystem {
    List<User> users = new ArrayList<>();
    private User userRegistered;
    private String email;
    private String password;
    public boolean loggedInFlag = false;
    public String signupFlag = "";


    public RegistrationSystem() {
        addUsers();
    }

    public void addUsers() {
        users.add(new User("Ahmed", "ahmed@gmail.com", "1234"));
        users.get(0).addAccount(AccountType.CURRENT);
        users.add(new User("Sarah", "sarah@yahoo.com", "5678"));
        users.get(1).addAccount(AccountType.SAVINGS);
        users.add(new User("Abdullah", "abdullah@gmail.com", "213"));
        users.get(2).addAccount(AccountType.CURRENT);
        users.get(2).addAccount(AccountType.SAVINGS);
        users.add(new User("Maryam", "maryam@hotmail.com", "3233"));
        users.get(3).addAccount(AccountType.CURRENT);
        users.get(3).addAccount(AccountType.SAVINGS);
        users.add(new User("Yousef", "yousef@gmail.com", "4355"));
        users.get(4).addAccount(AccountType.SAVINGS);
    }

    public void start() throws NotValidMailException {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1 - Login");
            System.out.println("2 - Signup");
            System.out.println("3 - Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1 || choice == 2) {
                Scanner scanner2 = new Scanner(System.in);
                System.out.println("Please enter your email: ");
                this.email = scanner2.nextLine();
                System.out.println("Please enter your password: ");
                this.password = scanner2.nextLine();
            }

            switch (choice) {
                case 1:
                    login(email, password);
                    break;
                case 2:
                    signup(email, password);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.. Please try again.");
            }
        }
    }

    public void login(String email, String password) throws NotValidMailException {


        userRegistered = getUser(email, password);

        if (userRegistered != null) {
            System.out.println("Login successful. Welcome, " + userRegistered.getName() + "!");
            loggedInFlag = true;
            loggedIn();

        } else {
            loggedInFlag = false;
            System.out.println("Login failed. Please check your credentials.");
        }

    }


    public void signup(String email, String password) throws NotValidMailException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your name: ");
        String name = scanner.nextLine();

        User userRegistered = getUser(email);

        if (userRegistered != null) {
            System.out.println("this email is already registered. Login instead? (y/n)");
            var choice = scanner.nextLine();
            switch (choice) {
                case "y":
                    login(email, password);
                    break;
                case "n":
                    System.out.println("Try to signup again.");
                    signup(email, password);
                    break;
                default:
                    System.out.println("Invalid choice.. Please try again.");
            }
        } else {
            String regex = "^(.+)@(.+)$";
            Pattern pattern = Pattern.compile(regex);

            Matcher matcher = pattern.matcher(email);

            System.out.println(email + " : " + matcher.matches() + "\n");

            if (matcher.matches()) {
                users.add(new User(name, email, password));
                System.out.println("user signed up successfully.");
            } else {
                throw new NotValidMailException("This is an invalid email address.");
            }


        }

    }


    private void loggedIn() throws NotValidMailException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1 - List user accounts");
            System.out.println("2 - Deposit");
            System.out.println("3 - Withdraw");
            System.out.println("4 - Open new Account");
            System.out.println("5 - Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    userRegistered.listUserAccounts();
                    break;
                case 2:
                    userRegistered.deposit(8, 200);
                    break;
                case 3:
                    userRegistered.withdraw(8, 200);
                    break;
                case 4:
                    userRegistered.addAccount(AccountType.SAVINGS);
                    break;
                case 5:
                    userRegistered = null;
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    private User getUser(String email, String password) {
        User userLoggedIn = null;
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                userLoggedIn = user;
                break;
            }
        }
        return userLoggedIn;
    }

    private User getUser(String email) {
        User userRegistered = null;
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                userRegistered = user;
                break;
            }
        }
        return userRegistered;
    }

}