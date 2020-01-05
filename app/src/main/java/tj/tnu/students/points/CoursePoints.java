package tj.tnu.students.points;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import org.w3c.dom.Text;

import java.util.List;

import tj.tnu.students.Data;
import tj.tnu.students.R;
import tj.tnu.students.data.model.Course;
import tj.tnu.students.data.model.WeekPoint;

public class CoursePoints extends AppCompatActivity {

    Course course;
    public static InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_points);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.course_points_detail);

        setSupportActionBar(myToolbar);
        //android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        //actionBar.setHomeButtonEnabled(true);
        //actionBar.setDisplayHomeAsUpEnabled(true);
        //actionBar.setTitle("");
        //actionBar.setHomeAsUpIndicator(R.drawable.back_button);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_button);
        myToolbar.setTitle("");

        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //onBackPressed();// возврат на предыдущий activity
                onBackPressed();
            }
        });


        MobileAds.initialize(this,
                "ca-app-pub-9215215947095346~9160437829");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-9215215947095346/4264809146");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


        System.out.println(mInterstitialAd.isLoaded());
        if (mInterstitialAd.isLoaded()){
            System.out.println("YES-------------");
            mInterstitialAd.show();
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
        }
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                System.out.println("Code to be executed when an ad finishes loading.");
                if (mInterstitialAd.isLoaded()){
                    System.out.println("YES-------------");
                    mInterstitialAd.show();
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                }
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                System.out.println("ERROR CODE = " + errorCode);
                System.out.println("Code to be executed when an ad request fails.");
                startTestMetod();
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
            }
        });

        int position = getIntent().getIntExtra("CoursePosition", 0);
        course = Data.getCourses().get(position);
        System.out.println(course.toString());

        TextView ratingOnePoint = findViewById(R.id.rating_one_point);
        LinearLayout ratingOneWeekPoints = findViewById(R.id.rating_one_week_points);
        TextView ratingTwoPoint = findViewById(R.id.rating_two_point);
        LinearLayout ratingTwoWeekPoints = findViewById(R.id.rating_two_week_points);
        TextView examePoint = findViewById(R.id.exam_point);
        TextView courseName = findViewById(R.id.course_name);
        TextView totalPoints = findViewById(R.id.total_points_and_mark);

        courseName.setText(course.getSubjectName().getTjText());

        ratingOnePoint.setText(String.valueOf(course.getFirstRatingPoint()));
        Resources r = this.getResources();

        for (WeekPoint weekPoint : course.getFirstWeekPoints()){
            RelativeLayout relativeLayout = new RelativeLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            int margin = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    10,
                    r.getDisplayMetrics()
            );
            params.setMargins(0, margin,0, margin);
            relativeLayout.setLayoutParams(params);


            TextView weekName = new TextView(this);
            RelativeLayout.LayoutParams weekNameParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            weekNameParams.addRule(RelativeLayout.ALIGN_PARENT_START);
            int px = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    20,
                    r.getDisplayMetrics()
            );
            weekNameParams.setMarginStart(px);

            weekName.setLayoutParams(weekNameParams);
            weekName.setText("Ҳафтаи " + weekPoint.getWeekNumber());
            weekName.setTextColor(getResources().getColor(R.color.white));
            weekName.setTypeface(ResourcesCompat.getFont(this, R.font.roboto_regular));
            weekName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
            relativeLayout.addView(weekName);

            TextView weekPointText = new TextView(this);
            RelativeLayout.LayoutParams weekPointParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            weekPointParams.addRule(RelativeLayout.ALIGN_PARENT_END);

            weekPointParams.setMarginEnd(px);
            weekPointText.setLayoutParams(weekPointParams);
            weekPointText.setText(weekPoint.getPoint() + "/" + weekPoint.getMaxPoint());
            weekPointText.setTextColor(getResources().getColor(R.color.points_color));
            weekPointText.setTypeface(ResourcesCompat.getFont(this, R.font.roboto_regular));
            weekPointText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            relativeLayout.addView(weekPointText);

            ratingOneWeekPoints.addView(relativeLayout);

            View view = new View(this);
            int lineHeight = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    1,
                    r.getDisplayMetrics()
            );
            LinearLayout.LayoutParams viewParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    lineHeight
            );
            view.setLayoutParams(viewParams);
            view.setBackgroundColor(getResources().getColor(R.color.line_color));
            view.setAlpha((float) 0.3);
            ratingOneWeekPoints.addView(view);

        }

        ratingTwoPoint.setText(String.valueOf(course.getSecondRatingPoint()));
        for (WeekPoint weekPoint : course.getSecondWeekPoints()){
            RelativeLayout relativeLayout = new RelativeLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            int margin = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    10,
                    r.getDisplayMetrics()
            );
            params.setMargins(0, margin,0, margin);
            relativeLayout.setLayoutParams(params);


            TextView weekName = new TextView(this);
            RelativeLayout.LayoutParams weekNameParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            weekNameParams.addRule(RelativeLayout.ALIGN_PARENT_START);
            int px = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    20,
                    r.getDisplayMetrics()
            );
            weekNameParams.setMarginStart(px);

            weekName.setLayoutParams(weekNameParams);
            weekName.setText("Ҳафтаи " + weekPoint.getWeekNumber());
            weekName.setTextColor(getResources().getColor(R.color.white));
            weekName.setTypeface(ResourcesCompat.getFont(this, R.font.roboto_regular));
            weekName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
            relativeLayout.addView(weekName);

            TextView weekPointText = new TextView(this);
            RelativeLayout.LayoutParams weekPointParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            weekPointParams.addRule(RelativeLayout.ALIGN_PARENT_END);

            weekPointParams.setMarginEnd(px);
            weekPointText.setLayoutParams(weekPointParams);
            weekPointText.setText(weekPoint.getPoint() + "/" + weekPoint.getMaxPoint());
            weekPointText.setTextColor(getResources().getColor(R.color.points_color));
            weekPointText.setTypeface(ResourcesCompat.getFont(this, R.font.roboto_regular));
            weekPointText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            relativeLayout.addView(weekPointText);

            ratingTwoWeekPoints.addView(relativeLayout);

            View view = new View(this);
            int lineHeight = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    1,
                    r.getDisplayMetrics()
            );
            LinearLayout.LayoutParams viewParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    lineHeight
            );
            view.setLayoutParams(viewParams);
            view.setBackgroundColor(getResources().getColor(R.color.line_color));
            view.setAlpha((float) 0.3);
            ratingTwoWeekPoints.addView(view);

        }
        examePoint.setText(course.getExamPoint() + " / 100");

        totalPoints.setText(course.getTotalPoints() + " / " + course.getMark());
    }

    private void startTestMetod() {
        TextView textView = new TextView(this);
        findViewById(R.id.rating_one_week_points).setVisibility(View.GONE);
    }
}
