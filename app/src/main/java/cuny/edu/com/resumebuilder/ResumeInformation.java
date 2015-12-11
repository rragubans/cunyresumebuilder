package cuny.edu.com.resumebuilder;

import java.util.ArrayList;
import java.util.List;

public class ResumeInformation {

    private long id = System.currentTimeMillis();
    private String name;
    private String address;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String email;
    private String languagesKnown;
    private String skills;
    private List<String> careerObjectives = new ArrayList<>();
    private List<Employment> employments  = new ArrayList<>();
    private String strength;
    private String hobbies;


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLanguagesKnown() {
        return languagesKnown;
    }

    public void setLanguagesKnown(String languagesKnown) {
        this.languagesKnown = languagesKnown;
    }

    public List<String> getCareerObjectives() {
        return careerObjectives;
    }

    public void setCareerObjectives(List<String> careerObjectives) {
        this.careerObjectives = careerObjectives;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public void addCareerObjectives(String objective) {
        careerObjectives.add(objective);
    }


    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public List<Employment> getEmployments() {
        return employments;
    }

    public void setEmployments(List<Employment> employments) {
        this.employments = employments;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("Name: ").append(getName());
        sb.append("Address: ").append(getAddress());
        sb.append("City: ").append(getCity());
        sb.append("State: ").append(getState());
        sb.append("Zip: ").append(getZipCode());
        sb.append("Email: ").append(getEmail());
        sb.append("languages: ").append(getLanguagesKnown());
        sb.append("skills: ").append(getSkills());
        sb.append("strength: ").append(getStrength());
        sb.append("hobbies: ").append(getHobbies());

        return sb.toString();
    }
}
