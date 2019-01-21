package databasemanagerapp;

import java.util.Random;

public class Person {

    private int personID;
    private String personFirstName;
    private String personMidName;
    private String personLastName;
    private String personEmail;
    private String personPhoneNumber;

    public Person() {
        personID = 0;
        personFirstName = "";
        personMidName = "";
        personLastName = "";
        personEmail = "";
        personPhoneNumber = "";
    }

    public Person(String personFirstName, String personMidName, String personLastName, String personEmail, String personPhoneNumber) {
        this.personID = new Random().nextInt((int) 10e9);
        this.personFirstName = personFirstName;
        this.personMidName = personMidName;
        this.personLastName = personLastName;
        this.personEmail = personEmail;
        this.personPhoneNumber = personPhoneNumber;
    }
    
    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public String getPersonFirstName() {
        return personFirstName;
    }

    public void setPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
    }

    public String getPersonMidName() {
        return personMidName;
    }

    public void setPersonMidName(String personMidName) {
        this.personMidName = personMidName;
    }

    public String getPersonLastName() {
        return personLastName;
    }

    public void setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getPersonPhoneNumber() {
        return personPhoneNumber;
    }

    public void setPersonPhoneNumber(String personPhoneNumber) {
        this.personPhoneNumber = personPhoneNumber;
    }

}
