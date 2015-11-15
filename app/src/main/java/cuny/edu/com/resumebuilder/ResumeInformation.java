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
    private String educationLine1;
    private String educationLine2;
    private String educationLine3;
    private String educationLine4;
    private String educationLine5;
    private String educationLine6;
    private String educationLine7;

    private String employmentLine1;
    private String employmentLine2;
    private String employmentLine3;
    private String employmentLine4;
    private String employmentLine5;
    private String employmentLine6;
    private String employmentLine7;


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

    public String getEducationLine1() {
        return educationLine1;
    }

    public void setEducationLine1(String educationLine1) {
        this.educationLine1 = educationLine1;
    }

    public String getEducationLine2() {
        return educationLine2;
    }

    public void setEducationLine2(String educationLine2) {
        this.educationLine2 = educationLine2;
    }

    public String getEducationLine3() {
        return educationLine3;
    }

    public void setEducationLine3(String educationLine3) {
        this.educationLine3 = educationLine3;
    }

    public String getEducationLine4() {
        return educationLine4;
    }

    public void setEducationLine4(String educationLine4) {
        this.educationLine4 = educationLine4;
    }

    public String getEducationLine5() {
        return educationLine5;
    }

    public void setEducationLine5(String educationLine5) {
        this.educationLine5 = educationLine5;
    }

    public String getEducationLine6() {
        return educationLine6;
    }

    public void setEducationLine6(String educationLine6) {
        this.educationLine6 = educationLine6;
    }

    public String getEducationLine7() {
        return educationLine7;
    }

    public void setEducationLine7(String educationLine7) {
        this.educationLine7 = educationLine7;
    }

    public String getEmploymentLine1() {
        return employmentLine1;
    }

    public void setEmploymentLine1(String employmentLine1) {
        this.employmentLine1 = employmentLine1;
    }

    public String getEmploymentLine2() {
        return employmentLine2;
    }

    public void setEmploymentLine2(String employmentLine2) {
        this.employmentLine2 = employmentLine2;
    }

    public String getEmploymentLine3() {
        return employmentLine3;
    }

    public void setEmploymentLine3(String employmentLine3) {
        this.employmentLine3 = employmentLine3;
    }

    public String getEmploymentLine4() {
        return employmentLine4;
    }

    public void setEmploymentLine4(String employmentLine4) {
        this.employmentLine4 = employmentLine4;
    }

    public String getEmploymentLine5() {
        return employmentLine5;
    }

    public void setEmploymentLine5(String employmentLine5) {
        this.employmentLine5 = employmentLine5;
    }

    public String getEmploymentLine6() {
        return employmentLine6;
    }

    public void setEmploymentLine6(String employmentLine6) {
        this.employmentLine6 = employmentLine6;
    }

    public String getEmploymentLine7() {
        return employmentLine7;
    }

    public void setEmploymentLine7(String employmentLine7) {
        this.employmentLine7 = employmentLine7;
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
        sb.append("education1: ").append(getEducationLine1());
        sb.append("education2: ").append(getEducationLine2());
        sb.append("education3: ").append(getEducationLine3());
        sb.append("education4: ").append(getEducationLine4());
        sb.append("education5: ").append(getEducationLine5());
        sb.append("education6: ").append(getEducationLine6());
        sb.append("education7: ").append(getEducationLine7());

        sb.append("employment1: ").append(getEmploymentLine1());
        sb.append("employment2: ").append(getEmploymentLine2());
        sb.append("employment3: ").append(getEmploymentLine3());
        sb.append("employment4: ").append(getEmploymentLine4());
        sb.append("employment5: ").append(getEmploymentLine5());
        sb.append("employment6: ").append(getEmploymentLine6());
        sb.append("employment7: ").append(getEmploymentLine7());

        return sb.toString();
    }
}
