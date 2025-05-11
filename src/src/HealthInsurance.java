package src;

public class HealthInsurance extends InsurancePolicy {
    protected String health_condition;
    protected String age ;
    


    public void setHealth_condition(String health_condition) {
        this.health_condition = health_condition;
    }
    public String getHealth_condition() {
        return health_condition;
    }
    @Override
    public String policy_type(){
        return "Health Insurance";
    }
    @Override
    public int calculate_premium() {
        int basePremium = 500;
        int ageAdjustment = 0;
        int healthAdjustment = 0;
    
        // Parse age and handle possible NumberFormatException
        int age;
        try {
            age = Integer.parseInt(this.age);
        } catch (NumberFormatException e) {
            System.out.println("Invalid age format");
            return -1; // Return an error value or handle it as appropriate
        }
    
        // Calculate age adjustment
        if (age <= 30) {
            ageAdjustment = 0;
        } else if (age > 30 && age <= 50) {
            ageAdjustment = 50;
        } else {
            ageAdjustment = 100;
        }
    
        // Calculate health adjustment
        if (this.health_condition.equalsIgnoreCase("excellent")) {
            healthAdjustment = 0;
        } else if (this.health_condition.equalsIgnoreCase("good")) {
            healthAdjustment = 50;
        } else if (this.health_condition.equalsIgnoreCase("poor")) {
            healthAdjustment = 100;
        } else {
            System.out.println("Invalid health condition");
            return -1; // Return an error value or handle it as appropriate
        }
    
        return basePremium + ageAdjustment + healthAdjustment;
    }
    
}
