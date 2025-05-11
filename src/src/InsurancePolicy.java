package src;

public class InsurancePolicy {
    protected String policy_number;
    protected int premium ;
    protected int coverage_amount;
    protected String holder_name;
    protected String policy_type="";

    public InsurancePolicy(String policy_number ,int premium,int coverage_amount,String holder_name,String policy_type){
        this.policy_number=policy_number;
        this.premium=premium;
        this.coverage_amount=coverage_amount;
        this.holder_name=holder_name;
        this.policy_type=policy_type;
    }

    public void setCoverage_amount(int coverage_amount) {
        this.coverage_amount = coverage_amount;
    }
    public int getCoverage_amount() {
        return coverage_amount;
    }

    public void setPremium(int premium) {
        this.premium = premium;
    }
    public int getPremium() {
        return premium;
    }
    public void setHolder_name(String holder_name) {
        this.holder_name = holder_name;
    }
    public String getHolder_name() {
        return holder_name;
    }
    public void setPolicy_number(String policy_number) {
        this.policy_number = policy_number;
    }
    public String getPolicy_number() {
        return policy_number;
    }
    public String policy_type(){
        return policy_type;
    }
    public int calculate_premium(){
        return 0;
    }
    public String display_policy_info(){
        return" inf0o0o00o0o";
    }
}