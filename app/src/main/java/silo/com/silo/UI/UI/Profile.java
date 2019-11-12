package silo.com.silo.UI.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import silo.com.silo.R;
import silo.com.silo.UI.Controller.ApiClient;
import silo.com.silo.UI.Session.SessionManager;

public class Profile extends AppCompatActivity {

    TextView topName;
    EditText name,phoneNumber,oldPassword,newPassword;
    Button send;

    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
        session = new SessionManager(Profile.this);
        name.setText(session.getKeyName());
        topName.setText(session.getKeyName());
        phoneNumber.setText(session.getKeyPhone());
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
    }

    private void init(){
        name = findViewById(R.id.name);
        phoneNumber = findViewById(R.id.phoneNumber);
        topName = findViewById(R.id.topname);
        send = findViewById(R.id.btnSend);
        oldPassword = findViewById(R.id.oldPassword);
        newPassword = findViewById(R.id.newPassword);
    }

    public void update(){
        if(newPassword.getText().toString().isEmpty()){
            final ProgressDialog progressDialog = new ProgressDialog(Profile.this,R.style.AppTheme_Dark_Dialog);
            progressDialog.setMessage("Authenticating...");
            progressDialog.show();

            Retrofit retrofit= new retrofit2.Retrofit.Builder()
                    .baseUrl("http://siloselo.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiClient apiClient = retrofit.create(ApiClient.class);
//            Call<ResponseUser> call = apiUser.GetLogin(txtUsername.getText().toString(),txtPassword.getText().toString());
            Call<ResponseBody> call = apiClient.updateProfile2(Integer.parseInt(session.getKeyId()),name.getText().toString(),phoneNumber.getText().toString());
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        JSONObject jsonRes = new JSONObject(response.body().string());
                        session.createLoginSessions(
                                jsonRes.getJSONObject("data").getString("phone_number"),
                                jsonRes.getJSONObject("data").getString("name"), jsonRes.getJSONObject("data").getString("id_user")
                                ,jsonRes.getJSONObject("data").getString("status"));
//                        final Intent intent = new Intent(Login.this, MainActivity.class);

                        Toast.makeText(getApplicationContext(), "Berhasil Ubah Data", Toast.LENGTH_SHORT).show();
//                        startActivity(intent);
                        progressDialog.dismiss();


                    } catch (JSONException e){
                        Toast.makeText(getApplicationContext(), "Cek1", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    } catch (IOException e) {
                        Toast.makeText(getApplicationContext(), "Cek2", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        e.printStackTrace();
                    } catch (Throwable e){
                        Toast.makeText(getApplicationContext(), "Cek3", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Internet err\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }else{

            final ProgressDialog progressDialog = new ProgressDialog(Profile.this,R.style.AppTheme_Dark_Dialog);
            progressDialog.setMessage("Authenticating...");
            progressDialog.show();

            Retrofit retrofit= new retrofit2.Retrofit.Builder()
                    .baseUrl("http://siloselo.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiClient apiClient = retrofit.create(ApiClient.class);
//            Call<ResponseUser> call = apiUser.GetLogin(txtUsername.getText().toString(),txtPassword.getText().toString());
            Call<ResponseBody> call = apiClient.updateProfile1(Integer.parseInt(session.getKeyId()),name.getText().toString(),phoneNumber.getText().toString(),newPassword.getText().toString(),oldPassword.getText().toString());
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        JSONObject jsonRes = new JSONObject(response.body().string());
                        session.createLoginSessions(
                                jsonRes.getJSONObject("data").getString("phone_number"),
                                jsonRes.getJSONObject("data").getString("name"), jsonRes.getJSONObject("data").getString("id_user")
                                ,jsonRes.getJSONObject("data").getString("status"));
//                        final Intent intent = new Intent(Login.this, MainActivity.class);

                        Toast.makeText(getApplicationContext(), "Berhasil Ubah Data", Toast.LENGTH_SHORT).show();
//                        startActivity(intent);
                        progressDialog.dismiss();


                    } catch (JSONException e){
                        Toast.makeText(getApplicationContext(), "Cek1", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    } catch (IOException e) {
                        Toast.makeText(getApplicationContext(), "Cek2", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        e.printStackTrace();
                    } catch (Throwable e){
                        Toast.makeText(getApplicationContext(), "Cek3", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Internet err\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
