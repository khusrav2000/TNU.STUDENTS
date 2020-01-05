package tj.tnu.students;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import tj.tnu.students.data.NetworkClient;
import tj.tnu.students.data.apis.Studetns;
import tj.tnu.students.data.model.Course;
import tj.tnu.students.data.model.Semester;
import tj.tnu.students.points.CoursePoints;
import tj.tnu.students.points.LessonFragment;

public class MainActivity extends AppCompatActivity implements LessonFragment.OnListFragmentInteractionListener,
        ProfileFragment.OnFragmentInteractionListener{

    FrameLayout mainFragment;
    LessonFragment lessonFragment;
    ProfileFragment profileFragment = ProfileFragment.newInstance();

    public static final String APP_PREFERENCES = "SkipLoginPhone";
    public static final String APP_TOKEN = "getToken";
    SharedPreferences skipLoginPhone;
    String token = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainFragment = findViewById(R.id.main_fragment);

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
        skipLoginPhone  = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        token    = skipLoginPhone.getString(APP_TOKEN, "");

        /*Profile profile = new Profile("2018010294","Ашурзода Хусрав Абдусамад", "механикаю математика",
                "Математикаи амали", "31030302", "Рузона", "бакалавр",
                2, "ТАС", "25/12/2019", 4);
        Data.setProfile(profile);*/


    }


    private void downloadAndSetCoursesData() {

        //Data.setCours(coursesList);

    }


    @Override
    public void onListFragmentInteraction(int position) {
        System.out.println("CLICK");
        Intent intent = new Intent(this, CoursePoints.class);
        intent.putExtra("CoursePosition", position);
        startActivity(intent);
    }

    @Override
    public void onSelectSemester(int semesterId, int position) {
        findViewById(R.id.not_courses_information).setVisibility(View.GONE);
        findViewById(R.id.load_courses).setVisibility(View.VISIBLE);
        findViewById(R.id.list).setVisibility(View.GONE);

        downloadAndSetCoursesData();
        List<Semester> semestersList = Data.getSemesters();
        for (Semester semesters : semestersList){
            semesters.setActive(false);
        }
        semestersList.get(position).setActive(true);
        Data.setSemesters(semestersList);

        Retrofit retrofit = NetworkClient.getRetrofitClient();
        Studetns studetns = retrofit.create(Studetns.class);
        Call call = studetns.getCoursesBySemester(token, semesterId);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()){
                    List<Course> courseList = (List<Course>) response.body();
                    Data.setCourses(courseList);
                    updateCoursesAdapter();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });

        lessonFragment.setSemestersAdapter();
        lessonFragment.dismissSelectSemester();
    }

    @Override
    public void refreshActivity() {
        recreate();
    }

    private void updateCoursesAdapter() {
        lessonFragment.setCourseAdapter();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
