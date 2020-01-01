package tj.tnu.students.data.apis;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import tj.tnu.students.data.model.Course;
import tj.tnu.students.data.model.Message;
import tj.tnu.students.data.model.Profile;
import tj.tnu.students.data.model.Semester;

public interface Studetns {

    @FormUrlEncoded
    @POST("/api/v1/auth")
    Call<Message> login(@Header ("token") String token, @Field("login") String username, @Field("password") String password);

    @GET("/api/v1/student/profile")
    Call<Profile> getStudentProfile(@Header("token") String token);

    @GET("api/v1/student/academic_years")
    Call<List<Semester>> getSemesters(@Header("token") String token);

    @GET("api/v1/student/grades/{academic_year_id}")
    Call<List<Course> > getCoursesBySemester(@Header("token") String token, @Path("academic_year_id") int academicYearId);

}
