package src;

public class VechileInsurance extends InsurancePolicy {
    protected String vechile_type;
    protected String vechile_age;

    public void setVechile_age(String vechile_age) {
        this.vechile_age = vechile_age;
    }
    public String getVechile_age() {
        return vechile_age;
    }
    public void setVechile_type(String vechile_type) {
        this.vechile_type = vechile_type;
    }
    public String getVechile_type() {
        return vechile_type;
    }
    @Override
    public String policy_type(){
        return "Vechile Insurance";
    }
    @Override
    public int calculate_premium(){
        int basePremium = 300;
        int ageAdjustment = 0;
        int typeAdjustment = 0;
    
        // Parse age and handle possible NumberFormatException
        int Vechile_age;
        try {
            Vechile_age = Integer.parseInt(this.vechile_age);
        } catch (NumberFormatException e) {
            System.out.println("Invalid age format");
            return -1; // Return an error value or handle it as appropriate
        }
    
        // Calculate age adjustment
        if (Vechile_age <= 3) {
            ageAdjustment = 0;
        } else if (Vechile_age > 3 && Vechile_age <= 5) {
            ageAdjustment = 30;
        } else {
            ageAdjustment = 50;
        }
    
        // Calculate type adjustment
        if (this.vechile_type.equalsIgnoreCase("car")) {
            typeAdjustment = 0;
        } else if (this.vechile_type.equalsIgnoreCase("motorcycle")) {
            typeAdjustment = 30;
        } else if (this.vechile_type.equalsIgnoreCase("truck")) {
            typeAdjustment = 50;
        } else {
            System.out.println("Invalid  vechile type");
            return -1; // Return an error value or handle it as appropriate
        }
    
        return basePremium + ageAdjustment + typeAdjustment;
    }

}
