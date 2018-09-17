package model;

/**
 * Created by Seong-EunCho on 2/17/17.
 */

/**
 * Object that contains information about a user.
 *
 * Domain:
 * username     : non-empty string
 * password     : non-empty string
 * email        : non-empty string
 * firstName    : non-empty string
 * lastName     : non-empty string
 * gender       : Male or Female
 * Person ID    : non-empty string
 */
public class User {
    private String userName;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String personID;

    /**
     * Creates a User object
     *
     * @param username -- Unique user name
     * @param password -- User's password
     * @param email -- User's email address
     * @param firstName -- User's first name
     * @param lastName -- User's last name
     * @param gender -- User's gender
     * @param personID -- Unique Person ID assigned to this user's generated Person object
     */
    public User(String username,
                String password,
                String email,
                String firstName,
                String lastName,
                String gender,
                String personID){
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setFirstName(firstName);
        setLastName(lastName);
        setGender(gender);
        setPersonID(personID);

    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
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

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", personID='" + personID + '\'' +
                '}';
    }
}
