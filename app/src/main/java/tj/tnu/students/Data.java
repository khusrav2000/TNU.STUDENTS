package tj.tnu.students;

import java.util.List;

import tj.tnu.students.data.model.Courses;
import tj.tnu.students.data.model.Profile;
import tj.tnu.students.data.model.Semesters;

public class Data {
    public static List<Courses> courses;
    public static List<Semesters> semesters;
    public static Profile profile;

    public static Profile getProfile() {
        return profile;
    }

    public static void setProfile(Profile profile) {
        Data.profile = profile;
    }

    public static List<Courses> getCourses() {
        return courses;
    }

    public static void setCourses(List<Courses> courses) {
        Data.courses = courses;
    }

    public static List<Semesters> getSemesters() {
        return semesters;
    }

    public static void setSemesters(List<Semesters> semesters) {
        Data.semesters = semesters;
    }
}
