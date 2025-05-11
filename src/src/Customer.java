package src;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private String address;
    private String agentCode;
    private List<InsurancePolicy> policies;

    String userFilePath = "C:\\Users\\user\\Desktop\\Training-Project\\src\\Customer.txt";

    public Customer(String user_id, String age, String email, String password, String address) {
        super(user_id, age, email, password);
        this.address = address;
        this.policies = new ArrayList<>();
    }

    public Customer(String user_id, String age, String email, String password, String address, String agentCode) {
        super(user_id, age, email, password);
        this.address = address;
        this.agentCode = agentCode;
        this.policies = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Customer [user_id=" + user_id + ", address=" + address + ", email=" + email
                + ", password=" + password + ", age=" + age + ", agent code =" + agentCode + "]";
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode() {
        this.agentCode = agentCode;
    }

    public List<InsurancePolicy> getPolicies() {
        return policies;
    }

    public void getTotalPremium() {

    }

    public void displayInfo() {
        super.display_user_info();
        System.out.println("Address: " + getAddress() /* + ", Policies: " + policies */);
    }

    @Override
    public boolean login(String userId, String password, String userFilePath) {
        // Customer-specific login implementation
        // For example, checking a different file or format
        return super.login(userId, password, userFilePath); // Assuming customer data is in a different file
    }

    public void addPolicy(InsurancePolicy policy) {
        this.policies.add(policy);
    }

    public void updatePolicy(InsurancePolicy policy) {
        for (int i = 0; i < policies.size(); i++) {
            if (policies.get(i).getPolicy_number().equals(policy.getPolicy_number())) {
                policies.set(i, policy);
                return;
            }
        }
        // If the policy is not found, add it
        this.policies.add(policy);
    }

    public void displayPolicies() {
        for (InsurancePolicy policy : policies) {
            System.out.println("Policy ID: " + policy.getPolicy_number() +
                    ", Type: " + policy.policy_type() +
                    ", Amount: " + policy.getCoverage_amount());
        }
    }

}
