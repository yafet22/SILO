package silo.com.silo.UI.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.goodiebag.pinview.Pinview;

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

public class Verify extends AppCompatActivity {

    Button verif;
    Pinview pin;

    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
//        getIntent().getStringExtra("phoneNumber");
        session = new SessionManager(getApplicationContext());
        init();
        verif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verify();
            }
        });
    }

    private void init()
    {
        verif = findViewById(R.id.btnVerif);
        pin = findViewById(R.id.pinview);
    }

    public void verify()
    {
        final ProgressDialog progressDialog = new ProgressDialog(Verify.this,R.style.AppTheme_Dark_Dialog);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        Retrofit retrofit= new retrofit2.Retrofit.Builder()
                .baseUrl("http://silo.yafetrakan.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiClient apiClient = retrofit.create(ApiClient.class);
//            Call<ResponseUser> call = apiUser.GetLogin(txtUsername.getText().toString(),txtPassword.getText().toString());
        Call<ResponseBody> call = apiClient.Verification(pin.getValue(),getIntent().getStringExtra("phoneNumber") );
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonRes = new JSONObject(response.body().string());;
                    if(jsonRes.getString("message").equalsIgnoreCase("verified")) {
//                    Toast.makeText(getApplicationContext(), jsonRes.getJSONObject("data").getString("role"), Toast.LENGTH_SHORT).show();
                        session.createLoginSessions(
                            getIntent().getStringExtra("phoneNumber"),
                            getIntent().getStringExtra("name"), getIntent().getStringExtra("id"));
                        final Intent intent = new Intent(Verify.this, MainActivity.class);
                        progressDialog.dismiss();
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Kode Verifikasi Salah" , Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
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
