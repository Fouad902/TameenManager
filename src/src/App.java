package src;

import java.io.File;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        User use = new User();

        Scanner input = new Scanner(System.in);
        System.out.println("Enter role:");
        String role = input.nextLine();
        System.out.println("Login OR SignUp");
        String action = input.nextLine();
        switch (action) {
            case "login":
                switch (role) {
                    case "admin":
                        System.out.println("enter User_id ");
                        String userIdInput = input.nextLine();
                        System.out.println("Enter Password");
                        String passworddInput = input.nextLine();
                        Admin admin = new Admin(null, null, null, null, null);
                        // Instantiate without using constructor arguments
                        String userFilePath = "C:\\Users\\user\\Desktop\\Training-Project\\src\\admin.txt";

                        if (admin.login(userIdInput, passworddInput, userFilePath)) {
                            // String fileContent = fileManagement.readFile(userFilePath); // Pass the
                            // correct file path
                            System.out.println("Login successful!");
                            // System.out.println("File Content:\n" + fileContent);
                            // File path = new File("F:\\VSCode-java\\Training-Project\\src\\admin.txt");

                            System.out.println("customers or agents  ? ");
                            String role1 = input.nextLine();
                            // Customer customer = new Customer("66", "20", "hbkdjh", "123456", "hbldhb");
                            switch (role1.toLowerCase()) {
                                case "customers":
                                    String link = "C:\\Users\\user\\Desktop\\Training-Project\\src\\customer.txt";
                                    System.out.println("what you want to do ADD || UPDATE || DELELTE ?");
                                    String manageAction = input.nextLine();
                                    switch (manageAction) {
                                        case "add":
                                            System.out.println("enter customer details :\n userid :");
                                            String user_id = input.nextLine();
                                            System.out.println(" email :  ");
                                            String email = input.nextLine();
                                            System.out.println(" adress :");
                                            String adress = input.nextLine();
                                            System.out.println("password :");
                                            String password = input.nextLine();
                                            System.out.println("age :");
                                            String age = input.nextLine();
                                            Customer customer = new Customer(user_id, age, email,
                                                    use.encryptString(password), adress);
                                            admin.manageUser(customer, manageAction, link);
                                        case "update":
                                            System.out.println("enter user id ");
                                            String input5 = input.nextLine();
                                            User customer1 = use.getUserById(input5, link);
                                            admin.manageUser(customer1, manageAction, link);

                                            break;

                                        case "delete":
                                            System.out.println("Enter the user ID of the customer to update: ");
                                            String userid = input.nextLine();
                                            Customer customer3 = new Customer(userid, null, null, null, null);
                                            admin.manageUser(customer3, manageAction, link);
                                            break;
                                    }
                                case "agents":
                                    String link2 = "C:\\Users\\user\\Desktop\\Training-Project\\src\\agent.txt";
                                    System.out.println("what you want to do ADD || UPDATE || DELELTE ?");
                                    String manageAction2 = input.nextLine();
                                    switch (manageAction2) {
                                        case "add":
                                            System.out.println("enter agent details :\n Agent id :");
                                            String user_id = input.nextLine();
                                            System.out.println(" agent code :  ");
                                            String agentCode = input.nextLine();
                                            System.out.println(" email :");
                                            String email = input.nextLine();
                                            System.out.println("password :");
                                            String password = input.nextLine();
                                            Agent agent = new Agent(user_id, email, use.encryptString(password),
                                                    agentCode);
                                            admin.manageUser(agent, manageAction2, link2);
                                        case "update":
                                            System.out.println("enter user id ");
                                            String input5 = input.nextLine();
                                            User agent1 = use.getUserById(input5, link2);
                                            admin.manageUser(agent1, manageAction2, link2);

                                            break;

                                        case "delete":
                                            System.out.println("Enter the user ID of the agent to delete: ");
                                            String userid = input.nextLine();
                                            Agent agent3 = new Agent(userid, null, null, null);
                                            admin.manageUser(agent3, manageAction2, link2);
                                            break;
                                    }

                                    break;
                            }
                        } else {
                            System.out.println("Login failed. User ID or password is incorrect.");
                        }
                        break;
                    case "customer":
                        System.out.println("enter User_id ");
                        String userId1Input = input.nextLine();
                        System.out.println("Enter Password");
                        String passwordd1Input = input.nextLine();
                        Customer customer = new Customer(null, null, null, null, null);

                        String path2 = "C:\\Users\\user\\Desktop\\Training-Project\\src\\Customer.txt";
                        // Pass the correct file path
                        if (customer.login(userId1Input, passwordd1Input, path2)) {
                            String fileContent = fileManagement.readFile(path2); // Pass the correct file path
                            System.out.println("Login successful!");
                            // System.out.println("File Content:\n" + fileContent);
                            // File path = new File("F:\\VSCode-java\\Training-Project\\src\\admin.txt");
                            // Fetch the user by ID
                            User user = User.getUserById(userId1Input, path2);
                            if (user != null) {
                                System.out.println("::::::::::::::your info:::::::::::::");
                                user.display_user_info();
                            } else {
                                System.out.println("User not found.");
                            }
                            System.out.println("what type of insurance do you want ? \n (Home | Life | Health | vechile)");
                            String type =input.nextLine();
                            if(type.toLowerCase().equals("Home")){
                                HomeInsurance policy =new HomeInsurance();
                            } else if (type.toLowerCase().equals("Life")){
                                LifeInsurance policy =new LifeInsurance();
                            }else if (type.toLowerCase().equals("Health")){
                                HealthInsurance policy = new HealthInsurance();
                            } else if (type.toLowerCase().equals("Vechile")){
                                VechileInsurance policy = new VechileInsurance();
                            } else{
                                System.out.println("invalid insurance type ");
                            }

                        } else {
                            System.out.println("Login failed. User ID or password is incorrect.");
                        }
                        break;
                    case "agent":
                        System.out.println("enter User_id ");
                        String userId2Input = input.nextLine();
                        System.out.println("Enter Password");
                        String passwordd2Input = input.nextLine();
                        Agent agent = new Agent(userId2Input, null, passwordd2Input, "1");

                        String path3 = "C:\\Users\\user\\Desktop\\Training-Project\\src\\agent.txt";
                        // Pass the correct file path
                        if (agent.login(userId2Input, passwordd2Input, path3)) {
                            String fileContent = fileManagement.readFile(path3); // Pass the correct file path
                            System.out.println("Login successful!");
                            System.out.println(
                                    "------------------------------------------------------------------------");
                            System.out.println("(1)Add/delete customer ? || (2)show your customers ?");
                            String path5 = "C:\\Users\\user\\Desktop\\Training-Project\\src\\Customer.txt";
                            String input3 = input.nextLine();
                            switch (input3.toLowerCase()) {
                                case "1":
                                    System.out.println("add || delete customer");
                                    String input4 = input.nextLine();
                                    switch (input4) {
                                        case "add":
                                            System.out.println("enter customer details :\n userid :");
                                            String user_id = input.nextLine();
                                            System.out.println(" email :  ");
                                            String email = input.nextLine();
                                            System.out.println(" adress :");
                                            String adress = input.nextLine();
                                            System.out.println("password :");
                                            String password = input.nextLine();
                                            System.out.println("age :");
                                            String age = input.nextLine();
                                            System.out.println("agent code :");
                                            String agentCode = input.nextLine();
                                            Customer customer8 = new Customer(user_id, age, email,
                                                    use.encryptString(password), adress, agentCode);
                                            agent.manageUser(customer8, input4, path5);
                                            break;

                                        case "delete":
                                            System.out.println("Enter the user ID of the customer to delete: ");
                                            String userid = input.nextLine();
                                            Customer customer3 = new Customer(userid, null, null, null, null);
                                            agent.manageUser(customer3, input4, path5);
                                            break;
                                    }
                                    break;
                                case "2":
                                    agent.displayCustomersByAgentCode(Agent.getAllCustomers(
                                            "C:\\Users\\user\\Desktop\\Training-Project\\src\\Customer.txt"));

                                    break;
                            }

                        } else {
                            System.out.println("Login failed. User ID or password is incorrect.");
                        }

                        break;
                    default:
                }
                break;

            case "signup":
                switch (role) {
                    case "admin":
                        System.out.println("enter User_id ");
                        String userInput = input.nextLine();
                        System.out.println("Enter AdminLevel");
                        String adminLevelInput = input.nextLine();
                        System.out.println("Enter Email");
                        String emailInput = input.nextLine();
                        System.out.println("Enter Password");
                        String passwordInput = input.nextLine();
                        System.out.println("Enter Age");
                        String AgeInput = input.nextLine();
                        Admin admin1 = new Admin(userInput, adminLevelInput, emailInput,
                                use.encryptString(passwordInput),
                                AgeInput);

                        File path = new File("C:\\Users\\user\\Desktop\\Training-Project\\src\\admin.txt");
                        fileManagement.writeToFile(admin1.toString(), path, true);
                        System.out.println("signed up successfully :)");
                        break;
                    case "agent":
                        System.out.println("enter User_id ");
                        String userInput1 = input.nextLine();
                        System.out.println("Enter AgentCode");
                        String agentCodeInput = input.nextLine();
                        System.out.println("Enter Email");
                        String emailInput1 = input.nextLine();
                        System.out.println("Enter Password");
                        String passwordInput1 = input.nextLine();
                        Agent agent = new Agent(userInput1, emailInput1, use.encryptString(passwordInput1),
                                agentCodeInput);
                        File path1 = new File("C:\\Users\\user\\Desktop\\Training-Project\\src\\agent.txt");
                        fileManagement.writeToFile(agent.toString(), path1, true);
                        System.out.println("signed up successfully :)");
                        // Customer[] assCustomerInput = new Customer[5];
                        break;
                    case "customer":
                        System.out.println("enter User_id ");
                        String userInput2 = input.nextLine();
                        System.out.println("Enter Address");
                        String addressInput = input.nextLine();
                        System.out.println("Enter Email");
                        String emailInput2 = input.nextLine();
                        System.out.println("Enter Password");
                        String passwordInput2 = input.nextLine();
                        System.out.println("Enter Age");
                        String AgeInput2 = input.nextLine();
                        Customer customer = new Customer(userInput2, addressInput, emailInput2,
                                use.encryptString(passwordInput2), AgeInput2);
                        File path2 = new File("C:\\Users\\user\\Desktop\\Training-Project\\src\\Customer.txt");
                        fileManagement.writeToFile(customer.toString(), path2, true);
                        System.out.println("signed up successfully :)");
                        break;
                    default:
                        System.out.println("Unknown role. Please contact support.");
                        break;
                }

                break;

            default:
                System.out.println("Invalid choice. Please enter 'login' or 'signup'.");
                break;
        }

    }

    public static String decryptString(String str) {
        char decryptedChar;
        StringBuilder decryptedPass = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char p = str.charAt(i);
            decryptedChar = (char) (p - 10);
            decryptedPass.append(decryptedChar);

        }
        return (decryptedPass.toString());
    }

}