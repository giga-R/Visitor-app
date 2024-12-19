package com.example.visitorapp;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ViewActivity extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view);
        tv=(TextView) findViewById(R.id.t1);
        callApi();
    }

    private void callApi() {
        String apiUrl="https://log-app-demo-api.onrender.com/getvistors";
        JsonArrayRequest request=new JsonArrayRequest(
                Request.Method.GET,
                apiUrl,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        StringBuilder result=new StringBuilder();
                        for(int i=0;i< response.length();i++)
                        {
                            try {
                                JSONObject ob=response.getJSONObject(i);
                                String s1=ob.getString("firstname");
                                String s2=ob.getString("lastname");
                                String s3=ob.getString("purpose");
                                String s4=ob.getString("whomToMeet");
                                result.append("First Name: ").append(s1).append("\n");
                                result.append("Last Name: ").append(s2).append("\n");
                                result.append("Purpose: ").append(s3).append("\n");
                                result.append("Whom to meet: ").append(s4).append("\n");
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }

                        }
                        tv.setText(result.toString());

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Something Went Wrong",Toast.LENGTH_LONG).show();
                    }
                }
        );
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request);

    }
}