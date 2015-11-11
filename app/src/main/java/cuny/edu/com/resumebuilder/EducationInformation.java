package cuny.edu.com.resumebuilder;

import java.util.ArrayList;
import java.util.List;

public class EducationInformation {

    private long id;
    private String education;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder(super.toString());
        return sb.toString();
    }
}
