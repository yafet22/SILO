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

public class Register extends AppCompatActivity {

    ImageView back;
    EditText name,phoneNumber,password;
    Button login,register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

    }

    private void init(){
        back = findViewById(R.id.btnBack);
        login = findViewById(R.id.btnSudah);
        name = findViewById(R.id.name);
        phoneNumber = findViewById(R.id.phoneNumber);
        password = findViewById(R.id.password);
        register = findViewById(R.id.btnAdd);
    }

    public void register(){
        final ProgressDialog progressDialog = new ProgressDialog(Register.this,R.style.AppTheme_Dark_Dialog);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        Retrofit retrofit= new retrofit2.Retrofit.Builder()
                .baseUrl("http://silo.yafetrakan.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiClient apiClient = retrofit.create(ApiClient.class);
//            Call<ResponseUser> call = apiUser.GetLogin(txtUsername.getText().toString(),txtPassword.getText().toString());
        Call<ResponseBody> call = apiClient.Register(name.getText().toString(), phoneNumber.getText().toString(),password.getText().toString());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonRes = new JSONObject(response.body().string());
//                    Toast.makeText(getApplicationContext(), jsonRes.getJSONObject("data").getString("role"), Toast.LENGTH_SHORT).show();
//                    session.createLoginSessions(
//                            jsonRes.getJSONObject("data").getString("role"),
//                            jsonRes.getJSONObject("data").getString("username"), jsonRes.getJSONObject("data").getString("id"));
                    Toast.makeText(getApplicationContext(), "id:" + jsonRes.getString("id_user"), Toast.LENGTH_SHORT).show();
                    final Intent intent = new Intent(Register.this, Verify.class);
                    intent.putExtra("phoneNumber", phoneNumber.getText().toString());
                    intent.putExtra("name", name.getText().toString());
                    Toast.makeText(getApplicationContext(), "id" + jsonRes.getString("id_user"), Toast.LENGTH_SHORT).show();
                    intent.putExtra("id", jsonRes.getString("id_user"));
                    progressDialog.dismiss();
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "cek", Toast.LENGTH_SHORT).show();
//                    new android.os.Handler().postDelayed(
//                            new Runnable() {
//                                @Override
//                                public void run() {
//                                    Toast.makeText(getApplicationContext(), "Berhasil Login", Toast.LENGTH_SHORT).show();
//                                    startActivity(intent);
//                                    progressDialog.dismiss();
//                                }
//                            },3000);

                } catch (JSONException e){
                    progressDialog.dismiss();
                    e.printStackTrace();
                } catch (IOException e) {
                    progressDialog.dismiss();
                    e.printStackTrace();
                } catch (Throwable e){
                    progressDialog.dismiss();
//                    tError.setText("Username dan Password tidak cocok");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                tError.setText("Koneksi Internet Tidak Stabil");
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Internet err\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
