package silo.com.silo.UI.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class Login extends AppCompatActivity {

    Button register,login;
    EditText phoneNumber,password;
    ImageView back;

    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        session = new SessionManager(getApplicationContext());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final Intent intent = new Intent(Login.this, Start.class);
//                startActivity(intent);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }

    private void init(){
        back = findViewById(R.id.btnBack);
        login = findViewById(R.id.btnLogin);
        register = findViewById(R.id.btnNew);
        phoneNumber = findViewById(R.id.phoneNumber);
        password = findViewById(R.id.password);
    }

    public void login(){
        if(phoneNumber.getText().toString().isEmpty()||
                password.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Inputan tidak boleh kosong" , Toast.LENGTH_SHORT).show();
        }else{

            final ProgressDialog progressDialog = new ProgressDialog(Login.this,R.style.AppTheme_Dark_Dialog);
            progressDialog.setMessage("Authenticating...");
            progressDialog.show();

            Retrofit retrofit= new retrofit2.Retrofit.Builder()
                    .baseUrl("http://siloselo.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiClient apiClient = retrofit.create(ApiClient.class);
//            Call<ResponseUser> call = apiUser.GetLogin(txtUsername.getText().toString(),txtPassword.getText().toString());
            Call<ResponseBody> call = apiClient.GetLogin(phoneNumber.getText().toString(), password.getText().toString());
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        JSONObject jsonRes = new JSONObject(response.body().string());
                        session.createLoginSessions(
                                jsonRes.getJSONObject("data").getString("phone_number"),
                                jsonRes.getJSONObject("data").getString("name"), jsonRes.getJSONObject("data").getString("id_user")
                                , jsonRes.getJSONObject("data").getString("status"));
                        final Intent intent = new Intent(Login.this, MainActivity.class);

                        Toast.makeText(getApplicationContext(), "Berhasil Login", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
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
