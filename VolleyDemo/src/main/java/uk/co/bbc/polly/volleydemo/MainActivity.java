package uk.co.bbc.polly.volleydemo;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.List;

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getName();
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "http://www.bbc.co.uk/iplayer/ion/featured/masterbrand/bbc_radio_one/clips/exclusive/discoverable/1/media_set/android-phone-rtmp-high/recipe/http%3A%2F%2Fextdev.bbc.co.uk%2Fmobile%2Fiplayerradio%2Fiplayerradio.yaml/format/json";

        mRequestQueue = Volley.newRequestQueue(this);

        final GsonObjectRequest<PromotionList> gsonObjectRequest = new GsonObjectRequest<PromotionList>(Request.Method.GET,
                url,
                PromotionList.class,
                new Response.Listener<PromotionList>() {

                    @Override
                    public void onResponse(PromotionList response) {
                        showPromotions(response.getPromotions());
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "volley error", error);
                        Toast.makeText(MainActivity.this, "Oops! Flagrant system error", Toast.LENGTH_LONG).show();
                    }
                }
        );
        mRequestQueue.add(gsonObjectRequest);

    }

    private void showPromotions(List<Promotion> promotions) {
        Toast.makeText(MainActivity.this, "Got "+promotions.size()+" promotions", Toast.LENGTH_LONG).show();
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new PromotionListAdapter(promotions));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
