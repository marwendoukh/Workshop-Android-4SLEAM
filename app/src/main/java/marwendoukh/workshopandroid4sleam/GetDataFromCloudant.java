package marwendoukh.workshopandroid4sleam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class GetDataFromCloudant extends AppCompatActivity {

    // le lien du Webservice
    String CloudantURL = "https://aa7976c5-0434-406c-a1ec-28efb22f8c08-bluemix.cloudant.com/facialemotions/_design/facialemotion/_view/facialemotion?limit=20&reduce=false";
    JSONObject jsonObj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data_from_cloudant);


        JsonObjectRequest RequestToWebService = new JsonObjectRequest(Request.Method.GET,
                CloudantURL, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {


                System.out.println("response from Cloudant " + response.toString());


            }


        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error in the request !!!! " + error.toString());
            }
        });

        // Adding request to request queue
        // (singleton)
        AppController.getInstance(this).addToRequestQueue(RequestToWebService);


    }


}

