package tj.tnu.students.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import tj.tnu.students.data.ConnectivityHelper;
import tj.tnu.students.data.model.Course;
import tj.tnu.students.data.model.Message;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import tj.tnu.students.Data;
import tj.tnu.students.MainActivity;
import tj.tnu.students.R;
import tj.tnu.students.data.NetworkClient;
import tj.tnu.students.data.apis.Studetns;
import tj.tnu.students.data.model.Profile;
import tj.tnu.students.data.model.Semester;

public class LoginActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton;
    ProgressBar loadingProgressBar;
    public static final String APP_PREFERENCES = "SkipLoginPhone";
    public static final String APP_TOKEN = "getToken";
    SharedPreferences skipLoginPhone;
    String token = "";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        loadingProgressBar = findViewById(R.id.loading);

        skipLoginPhone  = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        token    = skipLoginPhone.getString(APP_TOKEN, "");
        if (token != ""){
            findViewById(R.id.login_layout).setVisibility(View.GONE);
            checkLogin();
        }

        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    checkLogin();
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Здесь все не впорядке.))
                checkLogin();
            }
        });
    }

    private void checkLogin() {
        loadingProgressBar.setVisibility(View.VISIBLE);
        String login = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        Retrofit retrofit = NetworkClient.getRetrofitClient();
        Studetns studetns = retrofit.create(Studetns.class);
        System.out.println("TOKE = " + token);
        Call call = studetns.login(token, login, password);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()){
                    Message message = (Message) response.body();
                    startLogin(message.getMessage());
                } else {
                    loadingProgressBar.setVisibility(View.GONE);
                    showLoginFailed(R.string.login_failed);
                    findViewById(R.id.login_layout).setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                t.printStackTrace();
                loadingProgressBar.setVisibility(View.GONE);
                if (!ConnectivityHelper.isConnectedToNetwork(getCtx())){
                    Toast.makeText(getApplicationContext(), getText(R.string.internet_not_available), Toast.LENGTH_LONG).show();
                } else {
                    showLoginFailed(R.string.server_not_work);
                }

            }
        });

    }

    private void startLogin(String token1) {
        SharedPreferences.Editor editor = skipLoginPhone.edit();
        editor.putString(APP_TOKEN, token1);
        editor.apply();
        token = token1;
        (new LoadData()).execute();
    }

    private void startMainActivity() {
        loadingProgressBar.setVisibility(View.GONE);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void updateUiWithUser() {
        String welcome = getString(R.string.welcome) + Data.getProfile().getFullName().getTjText();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    private class LoadData extends AsyncTask<String, Void,Boolean> {

        @Override
        protected Boolean doInBackground(String... param) {
            Retrofit retrofit = NetworkClient.getRetrofitClient();
            Studetns studetns = retrofit.create(Studetns.class);
            Call call = studetns.getStudentProfile(token);
            try {
                Response response = call.execute();
                if (response.isSuccessful()){
                    System.out.println("Profile =========");
                    System.out.println(((Profile) response.body()).toString());
                    Data.setProfile((Profile) response.body());
                } else {
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            call = studetns.getSemesters(token);
            try{
                Response response = call.execute();
                if (response.isSuccessful()){
                    List<Semester> semestersList = (List<Semester>) response.body();
                    for (Semester semesters : semestersList){
                        semesters.setActive(false);
                    }
                    semestersList.get(0).setActive(true);
                    Data.setSemesters(semestersList);
                } else {
                    return false;
                }
            } catch (IOException e){
                e.printStackTrace();
                return false;
            }

            call = studetns.getCoursesBySemester(token, Data.getSemesters().get(0).getSemesterId());
            try{
                Response response = call.execute();
                if (response.isSuccessful()){
                    List<Course> courseList = (List<Course>) response.body();
                    Data.setCourses(courseList);
                } else {
                    return false;
                }
            } catch (IOException e){
                e.printStackTrace();
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean){
                updateUiWithUser();
                startMainActivity();
            } else {
                loadingProgressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), getText(R.string.error_load_data), Toast.LENGTH_LONG).show();
            }
        }
    }

    public Context getCtx(){
        return  this;
    }

}
