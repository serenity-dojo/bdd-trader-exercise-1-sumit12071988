package net.bddtrader.pojo.asClass;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class C_POJO_JFWay {
    // ---------------------------------------------------------------------------------------------------
    // Step1: Create private final variables for Json KEYS
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String email;

    // ---------------------------------------------------------------------------------------------------
    // Step2: Create parameterized constructor which acts as SETTER
    //      This SETTER will get values from Builder class
    @JsonCreator
    public C_POJO_JFWay(@JsonProperty("id") Long id,
                        @JsonProperty("firstName") String firstName,
                        @JsonProperty("lastName") String lastName,
                        @JsonProperty("email") String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // ---------------------------------------------------------------------------------------------------
    // Step3: Create public GETTERS
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    // ---------------------------------------------------------------------------------------------------
    // Step4: Create static method which is the origination point for Builder inner class
    public static AndLastName withFirstName(String firstName) {
        return new ClientBuilder(firstName);
    }

    // ---------------------------------------------------------------------------------------------------
    // Step5: Create interfaces for 2nd key onwards WITH same name as abstract method inside
    public interface AndLastName {
        // Create abstract method so that overriding method will have its definition
        // Abstract Method's Datatype/ return type should be the next Key interface
        AndEmail andLastName(String lastName);
    }

    public interface AndEmail {
        // Create abstract method of main Class type so that overriding method needs to create
        //      object of that class
        C_POJO_JFWay andEmail(String email);
    }

    // ---------------------------------------------------------------------------------------------------
    // Step6: Make builder class implement all the above interfaces so that builder class must
    //  Override the abstract methods
    private static class ClientBuilder implements AndLastName, AndEmail {
        private final String firstName;
        private String lastName;

        public ClientBuilder(String firstName) {
            this.firstName = firstName;
        }

        @Override
        public AndEmail andLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        @Override
        public C_POJO_JFWay andEmail(String email) {
            return new C_POJO_JFWay(null, firstName, lastName, email);
        }
    }
}
