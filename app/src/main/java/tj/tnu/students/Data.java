package tj.tnu.students;

import com.google.android.gms.ads.InterstitialAd;

import java.util.List;

import tj.tnu.students.data.model.Course;
import tj.tnu.students.data.model.Profile;
import tj.tnu.students.data.model.Semester;

public class Data {
    public static List<Course> cours;
    public static List<Semester> semesters;
    public static Profile profile;
    public static InterstitialAd mInterstitialAd;

    public static Profile getProfile() {
        return profile;
    }

    public static void setProfile(Profile profile) {
        Data.profile = profile;
    }

    public static List<Course> getCourses() {
        return cours;
    }

    public static void setCourses(List<Course> cours) {
        Data.cours = cours;
    }

    public static List<Semester> getSemesters() {
        return semesters;
    }

    public static void setSemesters(List<Semester> semesters) {
        Data.semesters = semesters;
    }
}
