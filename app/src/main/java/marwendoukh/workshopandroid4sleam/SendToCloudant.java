package marwendoukh.workshopandroid4sleam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class SendToCloudant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_to_cloudant);


        String url = "https://username.cloudant.com/DBname";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("twitter_feeds", "Your Data");
        map.put("gender", "ok");


        JsonObjectRequest jar1 = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(map), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    System.out.println("rev " + jsonObject.getString("rev"));
                } catch (JSONException e) {
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Json Error Res: ", "" + error);
            }
        });
        requestQueue.add(jar1);


    }
}