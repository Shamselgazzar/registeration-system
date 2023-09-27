package org.example;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterationSystem {
    List<User> users = new ArrayList<>();

    public RegisterationSystem() {
        addUsers();

    }

    public void addUsers() {
        users.add(new User("Ahmed", "ahmed@gmail.com", "1234"));
        users.add(new User("Sarah", "sarah@yahoo.com", "5678"));
        users.add(new User("Abdullah", "abdullah@gmail.com", "213"));
        users.add(new User("Maryam", "maryam@hotmail.com", "3233"));
        users.add(new User("Yousef", "yousef@gmail.com", "4355"));
    }

    public void start() throws NotValidMailException {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1 - Login");
            System.out.println("2 - Signup");
            System.out.println("3 - Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    signup();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.. Please try again.");
            }
        }
    }

    private void login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your email: ");
        String email = scanner.nextLine();
        System.out.println("Please enter your password: ");
        String password = scanner.nextLine();

        User userRegistered = getUser(email, password);

        if (userRegistered != null) {
            System.out.println("Login successful. Welcome, " + userRegistered.getName() + "!");
            System.exit(0);
        } else {
            System.out.println("Login failed. Please check your credentials.");
        }

    }

    private User getUser(String email, String password) {
        User userLoggedIn = null;
        for (User user : users){
            if(user.getEmail().equals(email) && user.getPassword().equals(password)){
                userLoggedIn = user;
                break;
            }
        }
        return userLoggedIn;
    }

    private User getUser(String email) {
        User userRegistered = null;
        for (User user : users){
            if(user.getEmail().equals(email)){
                userRegistered = user;
                break;
            }
        }
        return userRegistered;
    }

    private void signup() throws NotValidMailException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Please enter your email: ");
        String email = scanner.nextLine();
        System.out.println("Please enter your password: ");
        String password = scanner.nextLine();

        User userRegistered = getUser(email);

        if (userRegistered != null) {
            System.out.println("this email is already registered. Login instead? (y/n)");
            var choice = scanner.nextLine();
            switch (choice) {
                case "y":
                    login();
                    break;
                case "n":
                    System.out.println("Try to signup again.");
                    signup();
                    break;
                default:
                    System.out.println("Invalid choice.. Please try again.");
            }
        } else {
            String regex = "^(.+)@(.+)$";
            Pattern pattern = Pattern.compile(regex);

            Matcher matcher = pattern.matcher(email);

            System.out.println(email +" : "+ matcher.matches()+"\n");

            if(matcher.matches()){
                users.add(new User(name,email,password));
                System.out.println("user signed up successfully.");
            }else{
                throw new NotValidMailException("This is an invalid email address.");
                //System.out.println("This is an invalid email. try to signup again..");
                //signup();
            }



        }

    }
}