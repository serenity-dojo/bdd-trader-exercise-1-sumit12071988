package net.bddtrader.pojo.asClass.ConventionalWay;

public class C_POJOUsingMethodChaining {

    // STEP1: Private Variables for JSON KEYS ----------------------
    private String firstName, lastName, email;

    // STEP2: Public GETTERS --------------------------------------
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }

    // STEP3: STATIC METHOD for object creation --------------------------------------
    public static C_POJOUsingMethodChaining getInstance(){
        return new C_POJOUsingMethodChaining();
    }

    // STEP4: METHOD CHAINING for SETTERS --------------------------------------
    public C_POJOUsingMethodChaining withFirstName(String firstName){
        this.firstName = firstName;
        return this;
    }
    public C_POJOUsingMethodChaining andLastName(String lastName){
        this.lastName = lastName;
        return this;
    }
    public C_POJOUsingMethodChaining andEmail(String email){
        this.email = email;
        return this;
    }


}
