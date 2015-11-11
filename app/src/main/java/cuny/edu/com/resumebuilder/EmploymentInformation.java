package cuny.edu.com.resumebuilder;

public class EmploymentInformation {

    private long id;
    private String employment;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmployment() {
        return employment;
    }

    public void setEmployment(String employment) {
        this.employment = employment;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder(super.toString());
        return sb.toString();
    }
}
