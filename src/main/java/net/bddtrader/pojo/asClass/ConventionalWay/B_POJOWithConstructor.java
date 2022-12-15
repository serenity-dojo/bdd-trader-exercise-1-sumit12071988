package net.bddtrader.pojo.asClass.ConventionalWay;

public class B_POJOWithConstructor {
    //------ STEP1: PRIVATE VARIABLES -----------------------------------------------
    private final String firstName;
    private final String lastName;
    private final String email;

    //------ STEP2: GETTERS -----------------------------------------------
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }

    //------ STEP3: PARAMETERIZED CONSTRUCTORS AS SETTERS --------------------
    public B_POJOWithConstructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
