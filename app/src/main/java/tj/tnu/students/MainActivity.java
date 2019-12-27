package tj.tnu.students;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import tj.tnu.students.data.model.Courses;
import tj.tnu.students.points.LessonFragment;

public class MainActivity extends AppCompatActivity implements LessonFragment.OnListFragmentInteractionListener{

    FrameLayout mainFragment;
    LessonFragment lessonFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainFragment = findViewById(R.id.main_fragment);
        lessonFragment = new LessonFragment();
        BottomNavigationView bottomNavigationView;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_fragment, lessonFragment);
        ft.commit();

    }

    @Override
    public void onListFragmentInteraction(Courses item) {

    }
}
