package src;

public class HomeInsurance  extends InsurancePolicy{
    protected String home_value;
    protected String location;


    public String getHome_value() {
        return home_value;
    }
    public void setHome_value(String home_value) {
        this.home_value = home_value;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public String getLocation() {
        return location;
    }
    @Override
    public String policy_type(){
        return "Home Insurance";
    }

    @Override
    public int calculate_premium(){
        int basePremium = 250;
        int home_valueAdjustment = 0;
        int locationAdjustment = 0;
    
        // Parse age and handle possible NumberFormatException
        int value;
        try {
            value = Integer.parseInt(this.home_value);
        } catch (NumberFormatException e) {
            System.out.println("Invalid age format");
            return -1; // Return an error value or handle it as appropriate
        }
    
        // Calculate homevalue adjustment
        if (value <= 300000) {
            home_valueAdjustment = 0;
        } else if (value > 300000 && value <= 50000) {
            home_valueAdjustment = 50;
        } else {
            home_valueAdjustment = 100;
        }
        // calculate location adjustment
        if (this.location.equalsIgnoreCase("rural")) {
            locationAdjustment = 0;
        } else if (this.location.equalsIgnoreCase("suburban")) {
            locationAdjustment = 30;
        } else if (this.location.equalsIgnoreCase("urban")) {
            locationAdjustment = 50;
        }
    
        return basePremium + home_valueAdjustment + locationAdjustment;
    }


}
