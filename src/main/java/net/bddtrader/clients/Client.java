package net.bddtrader.clients;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Client {
// ---------------------------------------------------------------------------------------------------------------------
    // Step 1: Create Private variables for KEYS
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String email;
// ---------------------------------------------------------------------------------------------------------------------
    // Step 2: Create constructor to act as SETTERS
    @JsonCreator
    public Client(@JsonProperty("id") Long id,
                  @JsonProperty("firstName") String firstName,
                  @JsonProperty("lastName") String lastName,
                  @JsonProperty("email") String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
// ---------------------------------------------------------------------------------------------------------------------
    // Step 3: Create Public GETTERS
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
// ------- INTERFACES --------------------------------------------------------------------------------------------------
    public static AndLastName withFirstName(String firstName) {
        return new ClientBuilder(firstName);    // returning object of ClientBuilder class for method chaining
    }

    public interface AndLastName {
        AndEmail andLastName(String lastName);  // abstract method
    }
    public interface AndEmail {
        Client andEmail(String email);  // abstract method
    }
// ---------------------------------------------------------------------------------------------------------------------

    public static class ClientBuilder implements AndLastName, AndEmail {
        private final String firstName;
        private String lastName;

        // Constructor - SETTER
        public ClientBuilder(String firstName) {
            this.firstName = firstName;
        }

        // Public SETTER
        // Return type is object for method chaining
        public AndEmail andLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        // Final Key in JSON is Email, thus return type is the object of main class
        public Client andEmail(String email) {
            return new Client(null, firstName, lastName, email);
        }
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
