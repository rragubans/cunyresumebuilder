package cuny.edu.com.resumebuilder;

public class ClassData {

    private String dept;
    private String name;
    private String instructor;
    private String time;
    private String endTime;
    private String days;
    private String room;
    private String req;
    private String desc;
    private String comp;
    private String prerequisite;
    private String credits;
    private boolean selected=false;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getCredits() {
        if (credits == null) {
            credits = "N/A";
        }
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getPrerequisite() {
        if (prerequisite == null) {
            prerequisite = "N/A";
        }
        return prerequisite;
    }

    public void setPrerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
    }

    public String getComp() {
        return comp;
    }

    public void setComp(String comp) {
        this.comp = comp;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getName() {
        if (name == null) {
            name = "UNK";
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructor() {
        if (instructor == null) {
            instructor = "Not Avail";
        }
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;

    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getReq() {
        return req;
    }

    public void setReq(String req) {
        this.req = req;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
