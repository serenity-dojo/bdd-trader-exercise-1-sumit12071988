package net.bddtrader.pojo;

/**
 * NOTE: WHEN CREATING POJO CLASS, THE FOLLOWING ARE MANDATORY EVEN IF NOT USED:
 * 1. JSON KEYS AS PRIVATE VARIABLES
 * 2. GETTERS
 * 3. SETTERS
 */
public class POJOClass {
    // STEP1: CREATE JSON KEYS AS PRIVATE VARIABLES
    private String firstName, lastName, email;

    // STEP2: GENERATE GETTERS AND SETTERS
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
