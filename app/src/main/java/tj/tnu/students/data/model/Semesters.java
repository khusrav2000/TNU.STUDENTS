package tj.tnu.students.data.model;

public class Semesters {
    String semesterName;
    int semesterId;
    boolean isActive;

    public Semesters(String semesterName, int semesterId, boolean isActive) {
        this.semesterName = semesterName;
        this.semesterId = semesterId;
        this.isActive = isActive;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Semesters{" +
                "semesterName='" + semesterName + '\'' +
                ", semesterId=" + semesterId +
                ", isActive=" + isActive +
                '}';
    }
}
