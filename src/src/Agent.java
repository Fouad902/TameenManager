package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Agent extends User {

    private String agentCode;
    String userFilePath = "C:\\Users\\user\\Desktop\\Training-Project\\src\\agent.txt";

    // private Customer[] assignedCustomer;

    public Agent(String userInput, String email, String password, String agentCode) {
        super(userInput, "28", email, password);
        this.agentCode = agentCode;

    }

    @Override
    public String toString() {
        return "Agent [user_id=" + user_id + ", agentCode=" + agentCode + ", email=" + email
                + ", password=" + password + ", age=" + age + "]";
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }
    /*
     * public Customer[] getAssignedCustomer() {
     * return assignedCustomer;
     * }
     */

    /*
     * public void setAssignedCustomer(Customer[] assignedCustomer) {
     * this.assignedCustomer = assignedCustomer;
     * }
     */

    // public void displayAssignedCustInfo() {
    // System.out.println("Agent Code: " + agentCode);
    // /*
    // * System.out.println("Assigned Customers:");
    // * for (Customer customer : assignedCustomer)
    // */ {
    // // customer.displayInfo();
    // System.out.println("------------------");
    // }

    // }

    @Override
    public void display_user_info() {
        super.display_user_info(); // Call parent method
        System.out.println("Agent Code: " + getAgentCode());
    }

    @Override
    public boolean login(String userId, String password, String userFilePath) {
        // Agent-specific login implementation
        // For example, checking a different file or format
        return super.login(userId, password, userFilePath); // Assuming agent data is in a different file
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

    private void logAction(String message) {
        try {
            fileManagement.writeToFile(message, "C:\\Users\\user\\Desktop\\Training-Project\\src\\log.txt", true);
            System.out.println("Logged action: " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Customer> getAllCustomers(String filePath) {
        List<Customer> customers = new ArrayList<>();
        String content = fileManagement.readFile(filePath);

        String[] lines = content.split("\n");  // Split content by new lines

        for (String line : lines) {
            String[] customerData = line.split(","); // Assuming data is comma-separated

            if (customerData.length == 6) { // Validate number of fields
                // Create Customer object
                Customer customer = new Customer(
                        customerData[0],  // user_id
                        customerData[1],  // address
                        customerData[2],  // email
                        customerData[3],  // password
                        customerData[4],  // age
                        customerData[5]   // agentCode
                );
                customers.add(customer);
            }
        }
        return customers;
    }
    
    public void displayCustomersByAgentCode(List<Customer> customers) {
        boolean found = false;
        for (Customer customer : customers) {
            // Ensure non-null and correct comparison
            if (this.agentCode != null && this.agentCode.equals(customer.getAgentCode())) {
                System.out.println(customer);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No customers found for agent code: " + agentCode);
        }
    }
    
}
