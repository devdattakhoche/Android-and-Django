package com.example.dante1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private Button btnRequest;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private TextView Text ;
    private String url = "https://jsonplaceholder.typicode.com/posts/1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        btnRequest = (Button) findViewById(R.id.buttonRequest);
        btnRequest = (Button) findViewById(R.id.button1);
        Text = (TextView) findViewById(R.id.textView);
        btnRequest.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v){

                                              sendAndRequestResponse();

                                          }
                                      }

        );
    }
    private void sendAndRequestResponse() {

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

//                Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
                Log.i(TAG,"My Response :" + response.toString());
                try {
                    JSONObject obj = new JSONObject(response.toString());
                    Text.setText("User_id :\t"+ obj.getString("userId")+ "\ntitle :\t" +  obj.getString("title"));
                    Log.i(TAG,"My title :" + obj.getString("title"));
                }
                catch (Exception e){
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i(TAG,"Error :" + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);
    }
}
