package request;

/**
 * Created by Seong-EunCho on 2/17/17.
 */

import result.RegisterResult;

/**
 * An object that contains all the information necessary to register when register method is called
 *
 * Domain:
 * userName     : Non-empty String
 * password     : Non-empty String
 * email        : Non-empty String
 * firstName    : Non-empty String
 * lastName     : Non-empty String
 * gender       : "f" or "m"
 */
public class RegisterRequest{
    private String userName;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;

    /**
     * Creates the return object for the register command
     *
     * @param userName -- the user's unique username
     * @param password -- the user's password
     * @param email -- the user's email address
     * @param firstName -- the user's first name
     * @param lastName -- the user's last name
     * @param gender -- the user's gender
     */
    public RegisterRequest (String userName,
                            String password,
                            String email,
                            String firstName,
                            String lastName,
                            String gender){
        setUserName(userName);
        setPassword(password);
        setEmail(email);
        setFirstName(firstName);
        setLastName(lastName);
        setGender(gender);
    }

    public RegisterRequest(){};

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "RegisterRequest{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
