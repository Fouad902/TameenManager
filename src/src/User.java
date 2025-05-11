package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.io.FileNotFoundException;

public class User {
  protected String user_id;
  protected String age;
  protected String email;
  protected String password;

  public User(String user_id, String age, String email, String password) {
    this.user_id = user_id;
    this.age = age;
    this.email = email;
    this.password = password;
  }

  public User() {
  }

  @Override
  public String toString() {
    return "Customer [user_id=" + user_id + ", address=" + "notfound" + ", email=" + email
        + ", password=" + password + ", age=" + age + "]";
  }

  public String getUser_id() {
    return user_id;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getAge() {
    return age;
  }

  public void setUser_id(String user_id) {
    this.user_id = user_id;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public void display_user_info() {
    System.out.println("User_id:" + getUser_id());
    System.out.println("Email:" + getEmail());
    System.out.println("Password:" + getPassword());
    System.out.println("Age:" + getAge());
  }

  public boolean login(String userId, String password, String userFilePath) {
    StringBuilder content = new StringBuilder();

    // Read file content
    try (FileReader fileReader = new FileReader(userFilePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader)) {

      String line;
      while ((line = bufferedReader.readLine()) != null) {
        content.append(line).append("\n"); // Append line with newline for separation
      }

    } catch (FileNotFoundException e) {
      System.out.println("File not found: " + e.getMessage());
      return false;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
    // Encrypt the entered password
    String encryptedPassword = encryptString(password);
    // System.out.println("Entered Password: " + password); // Debugging
    // System.out.println("Encrypted Entered Password: " + encryptedPassword); //
    // Debugging

    // Check if the user ID and encrypted password exist in the file content
    String fileContent = content.toString();
    // System.out.println("File Content: " + fileContent); // Debugging

    // Look for the user ID and corresponding encrypted password in the file content
    String userIdPattern = "user_id=" + userId;
    String passwordPattern = "password=" + encryptedPassword;
    // System.out.println("userid"+userId.strip().length()); see the length of
    // userid
    // System.out.println("password"+encryptedPassword.strip().length()); length of
    // password
    boolean userIdFound = fileContent.contains(userIdPattern.strip());
    boolean passwordMatches = fileContent.contains(passwordPattern.strip());
    if (userId.length() != 0 && encryptedPassword.length() != 0) {
      if (userIdFound && passwordMatches) {
        return true; // Authentication successful
      }
      System.out.println("fail");
      return false; // Authentication failed
    } else {
      System.out.println("the user id not found ");
      return false;
    }

  }
  public static User fromString(String userData) {
    // // Debugging output
    // System.out.println("Parsing data: " + userData);

    // Clean up and prepare the string for splitting
    userData = userData.trim();
    if (userData.startsWith("Customer") || userData.startsWith("Admin") || userData.startsWith("User") || userData.startsWith("Agent")) {
        userData = userData.substring(userData.indexOf('[') + 1, userData.indexOf(']')).trim();
    }

    // Split the data by commas and trim extra spaces
    String[] userDetails = userData.split(",\\s*");

    // // Debugging output
    // System.out.println("Split data: " + Arrays.toString(userDetails));

    try {
        if (userDetails.length >= 4) { // Adjust length check based on your data structure
            String userId = getValue(userDetails[0]);
            String address = getValue(userDetails[1]);
            String email = getValue(userDetails[2]);
            String password = getValue(userDetails[3]);
            String age = getValue(userDetails[4]); // Ensure your data contains age

            // Assuming User class and relevant subclasses have appropriate constructors
            return new User(userId, email, password, age); // or return specific subclass based on data
        } else {
            System.out.println("Data format is incorrect.");
            return null; // Return null if data format is incorrect
        }
    } catch (Exception e) {
        e.printStackTrace();
        return null; // Handle parsing errors
    }
}

private static String getValue(String detail) {
    int index = detail.indexOf('=');
    if (index != -1 && index + 1 < detail.length()) {
        return detail.substring(index + 1).trim();
    }
    return ""; // Return empty string if '=' is not found or value is missing
}
  public static User getUserById(String userId, String filePath) {
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = br.readLine()) != null) {
            // // Debugging output
            // System.out.println("Checking line: " + line);

            User user = User.fromString(line);
            if (user != null && user.getUser_id().equals(userId)) {
                return user; // This could be an instance of User, Admin, Agent, or Customer
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return null; // User not found
}


  public static String encryptString(String str) {
    char encryptedChar;
    StringBuilder encryptedPass = new StringBuilder();
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      encryptedChar = (char) (c + 10);
      encryptedPass.append(encryptedChar);
    }
    return encryptedPass.toString();
  }

  public void setAddress(String newAddress) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setAddress'");
  }

}
