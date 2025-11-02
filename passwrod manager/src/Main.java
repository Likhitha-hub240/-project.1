import java.io.*;
import java.util.*;

public class Main {
    private static final String FILE_NAME = "passwords.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== 🔐 PASSWORD MANAGER =====");
            System.out.println("1. Save a new password");
            System.out.println("2. View saved passwords");
            System.out.println("3. Search for a password");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    savePassword(sc);
                    break;
                case 2:
                    viewPasswords();
                    break;
                case 3:
                    searchPassword(sc);
                    break;
                case 4:
                    System.out.println("Goodbye 👋");
                    break;
                default:
                    System.out.println("❌ Invalid choice! Please try again.");
            }
        } while (choice != 4);

        sc.close();
    }

    // Save a new password
    private static void savePassword(Scanner sc) {
        try {
            System.out.print("Enter website/app name: ");
            String website = sc.nextLine();
            System.out.print("Enter username/email: ");
            String username = sc.nextLine();
            System.out.print("Enter password: ");
            String password = sc.nextLine();

            FileWriter fw = new FileWriter(FILE_NAME, true);
            fw.write("Website: " + website + "\n");
            fw.write("Username: " + username + "\n");
            fw.write("Password: " + password + "\n");
            fw.write("--------------------------------\n");
            fw.close();

            System.out.println("✅ Password saved successfully!");
        } catch (IOException e) {
            System.out.println("❌ Error saving password: " + e.getMessage());
        }
    }

    // View all saved passwords
    private static void viewPasswords() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                System.out.println("No saved passwords found.");
                return;
            }

            Scanner fileReader = new Scanner(file);
            System.out.println("\n===== 🔒 Saved Passwords =====");
            while (fileReader.hasNextLine()) {
                System.out.println(fileReader.nextLine());
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("❌ Error reading file.");
        }
    }

    // Search password by website/app name
    private static void searchPassword(Scanner sc) {
        try {
            System.out.print("Enter website/app name to search: ");
            String search = sc.nextLine();

            File file = new File(FILE_NAME);
            if (!file.exists()) {
                System.out.println("No saved passwords found.");
                return;
            }

            Scanner fileReader = new Scanner(file);
            boolean found = false;
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                if (line.contains("Website: " + search)) {
                    System.out.println("\n🔍 Password details:");
                    System.out.println(line);
                    System.out.println(fileReader.nextLine());
                    System.out.println(fileReader.nextLine());
                    found = true;
                    break;
                }
            }
            fileReader.close();

            if (!found) {
                System.out.println("❌ No password found for " + search);
            }

        } catch (Exception e) {
            System.out.println("❌ Error while searching: " + e.getMessage());
        }
    }
}

