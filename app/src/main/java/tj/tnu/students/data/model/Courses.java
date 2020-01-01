package tj.tnu.students.data.model;


public class Courses {

    private String courseName;
    private String teacherName;
    private double totalPoints;
    private int courseId;

    public Courses(String courseName, String teacherName, double totalPoints, int courseId) {
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.totalPoints = totalPoints;
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public double getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(double totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Courses{" +
                "courseName='" + courseName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", totalPoints=" + totalPoints +
                ", courseId=" + courseId +
                '}';
    }
}
