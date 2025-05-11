package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Admin extends User {
    private String adminLevel;
    Scanner input = new Scanner(System.in);

    public Admin(String user_id, String email, String password, String age, String adminLevel) {
        super(user_id, age, email, password);
        this.adminLevel = adminLevel;
    }

    @Override
    public String toString() {
        return "Admin [user_id=" + user_id + ", adminLevel=" + adminLevel + ", email=" + email
                + ", password=" + password + ", age=" + age + "]";
    }

    public void manageUser(User user, String action, String userFilePath) throws IOException {
        String logMessage = "";

        switch (action.toLowerCase()) {
            case "add":
                if (userExists(user.getUser_id(), userFilePath)) {
                    logMessage = "User with ID " + user.getUser_id() + " already exists.";
                } else {
                    // Encrypt the password before saving
                    String encryptedPassword = encryptString(user.getPassword());
                    user.setPassword(encryptedPassword);
                    fileManagement.writeToFile(user.toString(), userFilePath, true);
                    logMessage = "Created user: " + user;
                }
                break;

            case "update":
                handleUpdate(userFilePath);
                logMessage = "User updated successfully.";
                break;
            case "delete":
                if (userExists(user.getUser_id(), userFilePath)) {
                    deleteUserFromFile(user.getUser_id(), userFilePath);
                    logMessage = "Deleted user with ID: " + user.getUser_id();
                } else {
                    logMessage = "User with ID " + user.getUser_id() + " does not exist.";
                }
                break;
            default:
                logMessage = "Invalid action: " + action;
                break;
        }

        logAction(logMessage);
    }

    private static void handleUpdate(String userFilePath) {
        Scanner scanner = new Scanner(System.in);
    
        // Prompt user for the field to update
        System.out.println("Enter what you want to update (e.g., address, email, password, age):");
        String updateType = scanner.nextLine().trim();
    
        // Prompt user for the ID of the user to update
        System.out.println("Enter user ID:");
        String userId = scanner.nextLine().trim();
    
        // Fetch the user to update
        User user = User.getUserById(userId, userFilePath);
        if (user != null) {
            System.out.println("Current User Information: " + user.toString());
    
            // Update the user's information based on the specified field
            switch (updateType.toLowerCase()) {
                case "address":
                    System.out.println("Enter new address:");
                    String newAddress = scanner.nextLine().trim();
                    user.setAddress(newAddress);
                    break;
                case "email":
                    System.out.println("Enter new email:");
                    String newEmail = scanner.nextLine().trim();
                    user.setEmail(newEmail);
                    break;
                case "password":
                    System.out.println("Enter new password:");
                    String newPassword = scanner.nextLine().trim();
                    user.setPassword(encryptString(newPassword)); // Ensure encryption method is correct
                    break;
                case "age":
                    System.out.println("Enter new age:");
                    String newAge = scanner.nextLine().trim();
                    user.setAge(newAge);
                    break;
                default:
                    System.out.println("Invalid update type.");
                    scanner.close();
                    return;
            }
    
            // Save the updated user information back to the file
            try {
                updateUserInFile(user, userFilePath);
                System.out.println("Updated User Information: " + user.toString());
            } catch (IOException e) {
                System.out.println("Error updating user: " + e.getMessage());
            }
        } else {
            System.out.println("User not found.");
        }
    
        scanner.close();
    }
    
    

    private boolean userExists(String userId, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                User user = User.fromString(line);
                if (user != null && user.getUser_id().equals(userId)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void deleteUserFromFile(String userId, String userFilePath) throws IOException {
        File inputFile = new File(userFilePath);
        File tempFile = new File("tempFile.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                User existingUser = User.fromString(currentLine);
                if (existingUser != null && !existingUser.getUser_id().equals(userId)) {
                    writer.write(currentLine);
                    writer.newLine();
                }
            }
        }

        inputFile.delete();
        tempFile.renameTo(inputFile);
    }

    private static void updateUserInFile(User user, String userFilePath) throws IOException {
        File inputFile = new File(userFilePath);
        File tempFile = new File("tempFile.txt"); // Temporary file to write updated data

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            boolean userFound = false;

            // Read each line and either copy it or update the user details
            while ((currentLine = reader.readLine()) != null) {
                User existingUser = User.fromString(currentLine);
                if (existingUser != null && existingUser.getUser_id().equals(user.getUser_id())) {
                    // Write the updated user information
                    writer.write(user.toString()); // Convert updated user object to string format
                    userFound = true;
                } else {
                    // Copy the existing user data
                    writer.write(currentLine);
                }
                writer.newLine();
            }

            // Check if user was found for update
            if (!userFound) {
                System.out.println("User with ID " + user.getUser_id() + " not found.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Replace the original file with the updated file
        if (!inputFile.delete()) {
            System.out.println("Could not delete original file.");
        }
        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename temp file to original file.");
        }
    }

    private void logAction(String message) {
        try {
            fileManagement.writeToFile(message, "C:\\Users\\user\\Desktop\\Training-Project\\src\\log.txt", true);
            System.out.println("Logged action: " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
