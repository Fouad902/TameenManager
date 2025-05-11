package src;

public class LifeInsurance extends InsurancePolicy {
    protected String age;
    protected boolean smoker;

    public void setSmoker(boolean smoker) {
        this.smoker = smoker;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getAge() {
        return age;
    }
    @Override
    public String policy_type(){
        return "Life Insurance";
    }
    @Override
    public int calculate_premium(){
        int basePremium = 400;
        int ageAdjustment = 0;
        int smokerAdjustment = 0;
    
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
            ageAdjustment = 100;
        } else {
            ageAdjustment = 150;
        }
    
        // Calculate smpker adjustment
        if (smoker) {
            smokerAdjustment = 200;
        } else {
            smokerAdjustment=0;
        }
    
        return basePremium + ageAdjustment + smokerAdjustment;
    }

}
