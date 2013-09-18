package uk.co.polly.volleydemo;

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
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import uk.co.bbc.polly.volleydemo.R;

public class MainActivity extends Activity {

    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRequestQueue = Volley.newRequestQueue(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        final String url = "http://www.bbc.co.uk/iplayer/ion/featured/masterbrand/bbc_radio_one/clips/exclusive/discoverable/1/media_set/android-phone-rtmp-high/recipe/http%3A%2F%2Fextdev.bbc.co.uk%2Fmobile%2Fiplayerradio%2Fiplayerradio.yaml/format/json";
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
                        Toast.makeText(MainActivity.this, "Flagrant system error", Toast.LENGTH_LONG).show();
                    }
                }
        );
        gsonObjectRequest.setTag(this);
        mRequestQueue.add(gsonObjectRequest);
    }

    private void showPromotions(List<Promotion> promotions) {
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new PromotionListAdapter(promotions, mRequestQueue));
    }

    @Override
    protected void onStop() {
        mRequestQueue.cancelAll(this);
        super.onStop();
    }
}
