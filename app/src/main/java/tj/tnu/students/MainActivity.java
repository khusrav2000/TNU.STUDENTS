package tj.tnu.students;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import tj.tnu.students.data.model.Courses;
import tj.tnu.students.data.model.Profile;
import tj.tnu.students.data.model.Semesters;
import tj.tnu.students.points.CoursePoints;
import tj.tnu.students.points.LessonFragment;

public class MainActivity extends AppCompatActivity implements LessonFragment.OnListFragmentInteractionListener,
        ProfileFragment.OnFragmentInteractionListener{

    FrameLayout mainFragment;
    LessonFragment lessonFragment;
    ProfileFragment profileFragment = ProfileFragment.newInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainFragment = findViewById(R.id.main_fragment);

        downloadAndSetSemestersData();
        downloadAndSetCoursesData();

        lessonFragment = new LessonFragment();
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if (menuItem.getItemId() == R.id.navigation_points){
                    transaction.replace(R.id.main_fragment, lessonFragment);
                    transaction.commit();
                }
                if (menuItem.getItemId() == R.id.navigation_profile){
                    transaction.replace(R.id.main_fragment, profileFragment);
                    transaction.commit();
                }
                return true;
            }
        });

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_fragment, lessonFragment);
        ft.commit();

        Profile profile = new Profile("2018010294","Ашурзода Хусрав Абдусамад", "механикаю математика",
                "Математикаи амали", "31030302", "Рузона", "бакалавр",
                2, "ТАС", "25/12/2019", 4);
        Data.setProfile(profile);

    }

    private void downloadAndSetSemestersData() {
        List<Semesters> semestersList = new ArrayList<>();
        Semesters semesters = new Semesters( "2015/2016 Нимосолаи 1", 1, false);
        semestersList.add(semesters);
        semesters = new Semesters( "2015/2016 Нимосолаи 2", 2, false);
        semestersList.add(semesters);
        semesters = new Semesters( "2016/2017 Нимосолаи 1", 3, true);
        semestersList.add(semesters);
        semesters = new Semesters( "2016/2017 Нимосолаи 2", 4, false);
        semestersList.add(semesters);
        Data.setSemesters(semestersList);
    }

    private void downloadAndSetCoursesData() {
        List<Courses> coursesList = new ArrayList<>();
        Courses courses;
        courses = new Courses("Таҳлили математика", "Юсупов", 20.2, 0);
        coursesList.add(courses);
        courses = new Courses("Диншиноси", "Амирбекова И.", 40.5, 0);
        coursesList.add(courses);
        courses = new Courses("Ҳуқуқ", "Сафарзода Н", 80.2, 0);
        coursesList.add(courses);
        courses = new Courses("Географияи Тоҷикистон бо асосҳои демографии он.", "Юнусов Талабшо.", 2.0, 0);
        coursesList.add(courses);
        Data.setCourses(coursesList);

    }


    @Override
    public void onListFragmentInteraction(Courses item) {
        System.out.println("CLICK");
        Intent intent = new Intent(this, CoursePoints.class);
        intent.putExtra("CourseId", item.getCourseId());
        startActivity(intent);
    }

    @Override
    public void onSelectSemester(int semesterId, int position) {
        downloadAndSetCoursesData();
        List<Semesters> semestersList = Data.getSemesters();
        for (Semesters semesters : semestersList){
            semesters.setActive(false);
        }
        semestersList.get(position).setActive(true);
        Data.setSemesters(semestersList);
        System.out.println(" == " + semesterId + position);

        lessonFragment.setCourseAdapter();
        lessonFragment.setSemestersAdapter();
        lessonFragment.dismissSelectSemester();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
