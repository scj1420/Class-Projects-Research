package result;

import java.util.ArrayList;

import model.Item;
import request.LoadRequest;

/**
 * Created by Seong-EunCho on 2/17/17.
 */

/**
 * Returns the result of the person method
 *
 * Domain:
 * data     : Array of person objects
 */
public class PeopleResult {
    private SuccessResponseBody srb = new SuccessResponseBody();
    private ErrorResponseBody erb = new ErrorResponseBody();
    boolean success = true;

    public String toString(){
        if (success){
            return srb.toString();
        } else return erb.toString();
    }

    public SuccessResponseBody getSrb() {
        return srb;
    }

    public void setSrb(SuccessResponseBody srb) {
        this.srb = srb;
    }

    public ErrorResponseBody getErb() {
        return erb;
    }

    public void setErb(ErrorResponseBody erb) {
        this.erb = erb;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class SuccessResponseBody {
        private ArrayList<Person> data;

        public ArrayList<Person> getData() {
            return data;
        }

        public void setData(ArrayList<Person> data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "SuccessResponseBody{" +
                    "data=" + data +
                    '}';
        }
    }
    public static class ErrorResponseBody {
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "ErrorResponseBody{" +
                    "message='" + message + '\'' +
                    '}';
        }
    }

    public static class Person implements Item {
        private String personID;
        private String descendant;
        private String firstName;
        private String lastName;
        private String gender;
        private String father;
        private String mother;
        private String spouse;

        public Person(String personID, String descendant, String firstName, String lastName,
                      String gender, String father, String mother, String spouse) {
            this.personID = personID;
            this.descendant = descendant;
            this.firstName = firstName;
            this.lastName = lastName;
            this.gender = gender;
            this.father = father;
            this.mother = mother;
            this.spouse = spouse;
        }

        public String getPersonID() {
            return personID;
        }

        public void setPersonID(String personID) {
            this.personID = personID;
        }

        public String getDescendant() {
            return descendant;
        }

        public void setDescendant(String descendant) {
            this.descendant = descendant;
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

        public String getFather() {
            return father;
        }

        public void setFather(String father) {
            this.father = father;
        }

        public String getMother() {
            return mother;
        }

        public void setMother(String mother) {
            this.mother = mother;
        }

        public String getSpouse() {
            return spouse;
        }

        public void setSpouse(String spouse) {
            this.spouse = spouse;
        }

        @Override
        public boolean contains(String search) {
            if (firstName.toLowerCase().contains(search.toLowerCase()))
                return true;
            return lastName.toLowerCase().contains(search.toLowerCase());
        }

        @Override
        public String toString() {
            return "Person{" +
                    "personID='" + personID + '\'' +
                    ", descendant='" + descendant + '\'' +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", gender='" + gender + '\'' +
                    ", father='" + father + '\'' +
                    ", mother='" + mother + '\'' +
                    ", spouse='" + spouse + '\'' +
                    '}';
        }
    }
}
