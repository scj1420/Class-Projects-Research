package model;

/**
 * Created by Seong-EunCho on 2/17/17.
 */

/**
 * An object containing a Person's information
 *
 * Domain:
 * personID     : non-empty string
 * descendant   : non-empty User
 * firstName    : non-empty string
 * lastName     : non-empty string
 * gender       : Male or Female
 * father       : string (possibly null)
 * mother       : string (possibly null)
 * spouse       : string (possibly null)
 */
public class Person {
    private String personID;
    private User descendant;
    private String firstName;
    private String lastName;
    private String gender;
    private Person father;
    private Person mother;
    private Person spouse;

    /**
     * Creates a Person object
     *
     * @param personID -- Unique identifier for this person
     * @param descendant -- User to which this person belongs
     * @param firstName -- Person's first name
     * @param lastName -- Person's last name
     * @param gender -- Person's gender
     * @param father -- Person's father
     * @param mother -- Person's mother
     * @param spouse -- Person's spouse
     */
    public Person(String personID,
                  User descendant,
                  String firstName,
                  String lastName,
                  String gender,
                  Person father,
                  Person mother,
                  Person spouse){

        setPersonID(personID);
        setDescendant(descendant);
        setFirstName(firstName);
        setLastName(lastName);
        setGender(gender);
        setFather(father);
        setMother(mother);
        setSpouse(spouse);
    }

    public User getDescendant() {
        return descendant;
    }

    public void setDescendant(User descendant) {
        this.descendant = descendant;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
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

    public Person getFather() {
        return father;
    }

    public void setFather(Person father) {
        this.father = father;
    }

    public Person getMother() {
        return mother;
    }

    public void setMother(Person mother) {
        this.mother = mother;
    }

    public Person getSpouse() {
        return spouse;
    }

    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Person{" +
                "descendant=" + descendant +
                ", personID='" + personID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'');
        if (father != null){
            sb.append(", father=" + father.getPersonID());
        } else sb.append (", father=null");
        if (mother != null){
            sb.append(", mother=" + mother.getPersonID());
        } else sb.append (", mother=null");
        if (spouse != null){
            sb.append(", spouse=" + spouse.getPersonID());
        } else sb.append (", spouse=null");

        return sb.toString();
    }
}
