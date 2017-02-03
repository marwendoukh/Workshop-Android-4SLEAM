package marwendoukh.workshopandroid4sleam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    // le lien du Webservice
    String weatherWebserviceURL = "http://api.openweathermap.org/data/2.5/weather?q=ariana,tn&appid=2156e2dd5b92590ab69c0ae1b2d24586&units=metric";
    JSONObject jsonObj;


    // textview (from layout)

    TextView temp, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // link to java var
        temp = (TextView) findViewById(R.id.temp);
        description = (TextView) findViewById(R.id.description);


        JsonObjectRequest RequestToWebService = new JsonObjectRequest(Request.Method.GET,
                weatherWebserviceURL, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    // Parsing json object response
                    // response will be a json object

                    // print all the data we got from the web service
                    System.out.println("response from WS " + response.toString());

                    jsonObj = (JSONObject) response.getJSONArray("weather").get(0);
                    // display weather description into the "description textview"
                    System.out.println("description " + jsonObj.getString("description"));
                    description.setText(jsonObj.getString("description"));
                    // display the temperature
                    System.out.println("temperature " + response.getJSONObject("main").getString("temp"));
                    temp.setText(response.getJSONObject("main").getString("temp"));

                    /*String backgroundImage = "";

                    //choose the image to set as background according to weather condition
                    if (jsonObj.getString("main").equals("Clouds")) {
                        backgroundImage = "https://marwendoukh.files.wordpress.com/2017/01/clouds-wallpaper2.jpg";
                    } else if (jsonObj.getString("main").equals("Rain")) {
                        backgroundImage = "https://marwendoukh.files.wordpress.com/2017/01/rainy-wallpaper1.jpg";
                    } else if (jsonObj.getString("main").equals("Snow")) {
                        backgroundImage = "https://marwendoukh.files.wordpress.com/2017/01/snow-wallpaper1.jpg";
                    }
*/


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error , try again ! ", Toast.LENGTH_LONG).show();

                }


            }


        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error in the request !!!! ");
            }
        });

        // Adding request to request queue
        // (singleton)
        AppController.getInstance(this).addToRequestQueue(RequestToWebService);


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "Haya bye !  a++", Toast.LENGTH_LONG).show();
    }
}
