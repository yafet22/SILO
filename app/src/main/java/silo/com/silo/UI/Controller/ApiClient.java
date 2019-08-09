package silo.com.silo.UI.Controller;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiClient {

    @POST("send-sms")
    @FormUrlEncoded
    Call<ResponseBody> Register(
            @Field("name")String name,
            @Field("phone_number")String phone_number,
            @Field("password") String password);

    @POST("verify-user")
    @FormUrlEncoded
    Call<ResponseBody> Verification(
            @Field("code")String name,
            @Field("phone_number")String phone_number);

    @POST("login")
    @FormUrlEncoded
    Call<ResponseBody> GetLogin(
            @Field("phone_number")String phone_number,
            @Field("password")String password);

    @GET("posting")
    Call<PostList> getPosts();
}
