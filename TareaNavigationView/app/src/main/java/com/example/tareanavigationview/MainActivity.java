package com.example.tareanavigationview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    RequestQueue requestQueue;//eguarangao/Json/blob/main/MyJson.json
    private String Ruta="https://raw.githubusercontent.com/eguarangao/Json/master/MyJson.json";
    TextView txtNombre;
    TextView txtPassword;
    Button btnIniciar;
    TextView txtUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUser=findViewById(R.id.txtUsuario);
        txtPassword=findViewById(R.id.txtContraseña);
        txtNombre=findViewById(R.id.textView_userName);
        requestQueue= Volley.newRequestQueue(this);




    }
    public void setBtnIniciar (View view){
        jsonArrayRequest(txtUser.getText().toString(),txtPassword.getText().toString());
    }

    private void jsonArrayRequest(String usuario, String contrasenia){
        JsonArrayRequest json=new JsonArrayRequest(Request.Method.GET,
                Ruta, null,
                new com.android.volley.Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int size=response.length();
                        String userId="";
                        String user="";
                        String password="";
                        String photo="";
                        for(int i=0;i<size;i++){
                            try {
                                JSONObject objeto = new JSONObject(response.get(i).toString());
                                userId=objeto.getString("userId");
                                user=objeto.getString("user");
                                password=objeto.getString("password");
                                photo=objeto.getString("photo");
                                if (usuario.equals(user)){
                                    if (contrasenia.equals(password)){
                                        Intent intent=new Intent(MainActivity.this, Controlador.class);
                                        Bundle b=new Bundle();
                                        b.putString("UserId",userId);
                                        b.putString("Usuario",user);
                                        b.putString("Password",password);
                                        b.putString("Photo",photo);
                                        intent.putExtras(b);
                                        startActivity(intent);
                                    }
                                }
                            }catch (JSONException e){
                                e.printStackTrace();
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(json);

    }
}