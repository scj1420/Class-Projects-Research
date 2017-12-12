package service;

import java.io.*;
import java.util.*;

import coder.Decoder;
import model.*;

/**
 * Created by Seong-EunCho on 3/7/17.
 */

public class Generator {
    Names mNames;
    Names fNames;
    Names sNames;
    Locations eventLocations;
    int CURRENT_YEAR = 2017;
    Random rand = new Random();
    Decoder d = new Decoder();

    public void importData(){
        importfNames();
        importmNames();
        importsNames();
        importEventLocations();
    }
    private void importfNames(){
        try {
            InputStream is = new FileInputStream("json/fnames.json");
            BufferedInputStream bis = new BufferedInputStream(is);
            Scanner s = new Scanner(bis);

            StringBuilder sb = new StringBuilder();
            while (s.hasNext()){
                sb.append(s.next());
            }
            String jsonstr = sb.toString();
            fNames = d.decodeNames(jsonstr);

            s.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void importmNames(){
        try {
            InputStream is = new FileInputStream("json/mnames.json");
            BufferedInputStream bis = new BufferedInputStream(is);
            Scanner s = new Scanner(bis);

            StringBuilder sb = new StringBuilder();
            while (s.hasNext()){
                sb.append(s.next());
            }
            String jsonstr = sb.toString();
            mNames = d.decodeNames(jsonstr);

            s.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }
    private void importsNames(){
        try {
            InputStream is = new FileInputStream("json/snames.json");
            BufferedInputStream bis = new BufferedInputStream(is);
            Scanner s = new Scanner(bis);

            StringBuilder sb = new StringBuilder();
            while (s.hasNext()){
                sb.append(s.next());
            }
            String jsonstr = sb.toString();
            sNames = d.decodeNames(jsonstr);

            s.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }
    private void importEventLocations(){
        try {
            InputStream is = new FileInputStream("json/locations.json");
            BufferedInputStream bis = new BufferedInputStream(is);
            Scanner s = new Scanner(bis);

            StringBuilder sb = new StringBuilder();
            while (s.hasNext()){
                sb.append(s.next());
            }
            String jsonstr = sb.toString();
            eventLocations = d.decodeLocations(jsonstr);

            s.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    public Tree<Person> generatePeople(User u, int generations){
        Person descendant = new Person(u.getPersonID(), u, u.getFirstName(), u.getLastName(), u.getGender(), null, null, null);
        Tree<Person> personTree = new Tree<Person>(descendant);
        recursiveGeneratePeople(personTree.getRoot(), u, generations, 1);
        linkRelation(personTree.getRoot());

        return personTree;
    }
    public void recursiveGeneratePeople(Node<Person> temp, User user, int generation, int count){
        if (count <= generation){
            if (temp.getLeft() == null){
                Person father = randPerson(user, "m", temp.getData().getLastName());
                Node<Person> n = new Node<Person>();
                n.setData(father);
                temp.setLeft(n);
                n.setParent(temp);
                int newcount = count + 1;
                recursiveGeneratePeople(n, user, generation, newcount);
            }
            if (temp.getRight() == null){
                Person mother = randPerson(user, "f");
                Node<Person> n = new Node<Person>();
                n.setData(mother);
                temp.setRight(n);
                n.setParent(temp);
                int newcount = count + 1;
                recursiveGeneratePeople(n, user, generation, newcount);
            }
            return;

        } else return;
    }
    public void showmethetree(Node<Person> temp){
        if (temp.getLeft() != null && temp.getRight() != null){
            System.out.println(temp.getLeft().getData().toString());
            showmethetree(temp.getLeft());
            System.out.println(temp.getRight().getData().toString());
            showmethetree(temp.getRight());
            return;
        } else return;
    }
    public void linkRelation(Node<Person> temp){
        if (temp.getLeft() != null && temp.getRight() != null){
            temp.getData().setFather(temp.getLeft().getData());
            temp.getLeft().getData().setSpouse(temp.getRight().getData());
            linkRelation(temp.getLeft());
            temp.getData().setMother(temp.getRight().getData());
            temp.getRight().getData().setSpouse(temp.getLeft().getData());
            linkRelation(temp.getRight());
            return;
        } else return;
    }
    public Person randPerson(User user, String gender){
        String firstName;
        if (gender.equals("m")){
            firstName = mNames.randName();
        } else firstName = fNames.randName();

        String lastName = sNames.randName();

        Person p = new Person(generateID(), user, firstName, lastName, gender, null, null, null);

        return p;
    }
    public Person randPerson(User user, String gender, String lastName){
        String firstName;
        if (gender.equals("m")){
            firstName = mNames.randName();
        } else firstName = fNames.randName();

        Person p = new Person(generateID(), user, firstName, lastName, gender, null, null, null);

        return p;
    }

    public ArrayList<Event> generateEvents(Tree<Person> tree){
        ArrayList<Event> allEvents = new ArrayList<Event>();
        int userAge = rand.nextInt(40) + 20;
        int userBirthYear = CURRENT_YEAR - userAge;

        //Birth
        Location birthLocation = eventLocations.randLocation();
        Event userBirth = new Event(generateID(), tree.getRoot().getData().getDescendant(),
                                    tree.getRoot().getData(), birthLocation.getLatitude(),
                                    birthLocation.getLongitude(), birthLocation.getCountry(),
                                    birthLocation.getCity(), "Birth", userBirthYear);
        allEvents.add(userBirth);

        //Baptism
        if (rand.nextInt(3) != 0){
            int userBaptAge = rand.nextInt(userAge - 8) + 8;
            int userBaptYear = userBirthYear + userBaptAge;
            Location baptLocation = eventLocations.randLocation();
            Event userBaptism = new Event(generateID(), tree.getRoot().getData().getDescendant(),
                                        tree.getRoot().getData(), baptLocation.getLatitude(),
                                        baptLocation.getLongitude(), baptLocation.getCountry(),
                                        baptLocation.getCity(), "Baptism", userBaptYear);
            allEvents.add(userBaptism);
        }

        recursiveGenerateEvents(tree.getRoot(), userBirthYear, allEvents);
        for (int i = 0; i < allEvents.size(); i++){
            //System.out.println(allEvents.get(i).toString());
        }

        return allEvents;
    }
    public void recursiveGenerateEvents(Node<Person> n, int desBY, ArrayList<Event> al){
        if (n.getLeft() != null && n.getRight() != null){
            //Add events for the father
            int fatherAge = rand.nextInt(40) + 50;
            Event fatherBirth = generateBirth(n.getLeft().getData(), desBY);
            al.add(fatherBirth);
            Event fatherBaptism = generateBaptism(n.getLeft().getData(), fatherBirth.getYear(), fatherAge);
            if (fatherBaptism != null){
                al.add(fatherBaptism);
            }
            int marriageYear = rand.nextInt(desBY - (fatherBirth.getYear() + 18)) + (fatherBirth.getYear() + 18);
            Event fatherMarriage = generateMarriage(n.getLeft().getData(), marriageYear);
            al.add(fatherMarriage);
            Event fatherDeath = generateDeath(n.getLeft().getData(), fatherBirth.getYear(), fatherAge);
            if (fatherDeath != null){
                al.add(fatherDeath);
            }
            recursiveGenerateEvents(n.getLeft(), fatherBirth.getYear(), al);

            //Add events for the mother;
            int motherAge = rand.nextInt(40) + 50;
            Event motherBirth = generateBirth(n.getRight().getData(), desBY);
            al.add(motherBirth);
            Event motherBaptism = generateBaptism(n.getRight().getData(), motherBirth.getYear(), motherAge);
            if (motherBaptism != null){
                al.add(motherBaptism);
            }
            Event motherMarriage = new Event(generateID(), n.getData().getDescendant(), n.getRight().getData(),
                                            fatherMarriage.getLatitude(), fatherMarriage.getLongitude(),
                                            fatherMarriage.getCountry(), fatherMarriage.getCity(),
                                            "Marriage", marriageYear);
            al.add(motherMarriage);
            Event motherDeath = generateDeath(n.getRight().getData(), motherBirth.getYear(), motherAge);
            if (motherDeath != null){
                al.add(motherDeath);
            }
            recursiveGenerateEvents(n.getRight(), motherBirth.getYear(), al);

            return;
        } else return;
    }
    public Event generateBirth(Person p, int desBY){
        Location birthLocation = eventLocations.randLocation();
        int ageWhenConceived = rand.nextInt(15) + 20;
        int pBirthYear = desBY - ageWhenConceived;
        Event pBirth = new Event(generateID(), p.getDescendant(), p, birthLocation.getLatitude(),
                                birthLocation.getLongitude(), birthLocation.getCountry(),
                                birthLocation.getCity(), "Birth", pBirthYear);
        return pBirth;
    }
    public Event generateBaptism(Person p, int pBY, int age){
        if (rand.nextInt(3) != 0){
            int pBaptAge = rand.nextInt(age - 8) + 8;
            int pBaptYear = pBY +pBaptAge;
            if (pBaptYear <= CURRENT_YEAR) {
                Location baptLocation = eventLocations.randLocation();
                Event pBaptism = new Event(generateID(), p.getDescendant(), p, baptLocation.getLatitude(),
                        baptLocation.getLongitude(), baptLocation.getCountry(),
                        baptLocation.getCity(), "Baptism", pBaptYear);
                return pBaptism;
            } else return null;
        } else return null;
    }
    public Event generateMarriage(Person p, int pMY){
        Location marriageLocation = eventLocations.randLocation();
        Event pMarriage = new Event(generateID(), p.getDescendant(), p, marriageLocation.getLatitude(),
                                    marriageLocation.getLongitude(), marriageLocation.getCountry(),
                                    marriageLocation.getCity(), "Marriage", pMY);
        return pMarriage;
    }
    public Event generateDeath(Person p, int pBY, int age){
        int pDeathYear = pBY + age;
        if (pDeathYear <= CURRENT_YEAR){
            Location deathLocation = eventLocations.randLocation();
            Event pDeath = new Event(generateID(), p.getDescendant(), p, deathLocation.getLatitude(),
                                    deathLocation.getLongitude(), deathLocation.getCountry(),
                                    deathLocation.getCity(), "Death", pDeathYear);
            return pDeath;
        } else return null;
    }


    public ArrayList<User> randUserList(int size){
        ArrayList<User> userList = new ArrayList<User>();
        for (int i = 0; i < size; i ++){
            String gender;
            if (rand.nextInt(2) == 0){
                gender = "m";
            } else gender = "f";
            User u = new User("user" + i, "password" + i, "mail" + i + "@gmail.com", "firstName" + i, "lastName" + i, gender, generateID());
            userList.add(u);
        }

        return userList;
    }
    public ArrayList<Person> randPeopleList(User u){
        ArrayList<Person> personList = new ArrayList<Person>();
        Tree<Person> tree = generatePeople(u, 1);
        personList.add(tree.getRoot().getData());
        recursivelyAddPeople(tree.getRoot(), personList);
        return personList;
    }
    public void recursivelyAddPeople(Node<Person> n, ArrayList<Person> al){
        if (n.getLeft() != null && n.getRight() != null){
            al.add(n.getLeft().getData());
            recursivelyAddPeople(n.getLeft(), al);
            al.add(n.getRight().getData());
            recursivelyAddPeople(n.getRight(), al);
            return;
        } else return;
    }

    public String generateID(){
        UUID id = UUID.randomUUID();
        return id.toString();
    }

    public Names getmNames() {
        return mNames;
    }

    public void setmNames(Names mNames) {
        this.mNames = mNames;
    }

    public Names getfNames() {
        return fNames;
    }

    public void setfNames(Names fNames) {
        this.fNames = fNames;
    }

    public Names getsNames() {
        return sNames;
    }

    public void setsNames(Names sNames) {
        this.sNames = sNames;
    }

    public Locations getEventLocations() {
        return eventLocations;
    }

    public void setEventLocations(Locations eventLocations) {
        this.eventLocations = eventLocations;
    }

    public Decoder getD() {
        return d;
    }

    public void setD(Decoder d) {
        this.d = d;
    }
}
