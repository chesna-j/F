package com.ches.friendssapp;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
     EditText e1,e2,e3,e4;
     AppCompatButton b1;
    @SuppressLint("MissingInflatedId")
    String apiurl="https://friendsapi-re5a.onrender.com/adddata";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        e1=(EditText) findViewById(R.id.name);
        e2=(EditText) findViewById(R.id.fname);
        e3=(EditText) findViewById(R.id.nickname);
        e4=(EditText) findViewById(R.id.describe);
        b1=(AppCompatButton)findViewById(R.id.submitbtn);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getName=e1.getText().toString();
                String getFname=e2.getText().toString();
                String getNickame=e3.getText().toString();
                String getDescribe=e4.getText().toString();

                //JSON object creation

                JSONObject friend =new JSONObject();
                try {
                    friend.put("name",getName);
                    friend.put("friendName", getFname);
                    friend.put("friendNickName", getNickame);
                    friend.put("DescribeYourFriend",  getDescribe);
                    //json request
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(
                        Request.Method.POST,
                        apiurl,
                        friend,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(getApplicationContext(), "submitted", Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        }

                );
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jsonObjectRequest);

                //Toast.makeText(getApplicationContext(),getName+getFname+getNickame+getDescribe,Toast.LENGTH_SHORT).show();

            }
        });
    }
}