package uk.co.polly.volleydemo;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class GsonObjectRequest<T> extends Request<T> {

    private static final String TAG = GsonObjectRequest.class.getName();

    /**
     * Gson parser
     */
    private final Gson mGson;

    /**
     * Class type for the response
     */
    private final Class<T> mClass;

    /**
     * Callback for response delivery
     */
    private final Response.Listener<T> mListener;

    /**
     * @param method
     *            Request type.. Method.GET etc
     * @param url
     *            path for the requests
     * @param objectClass
     *            expected class type for the response. Used by gson for
     *            serialization.
     * @param listener
     *            handler for the response
     * @param errorListener
     *            handler for errors
     */
    public GsonObjectRequest(int method, String url, Class<T> objectClass, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mClass = objectClass;
        this.mListener = listener;
        mGson = new Gson();
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(mGson.fromJson(jsonString, mClass), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    @Override
    public String getCacheKey() {
        String cacheKey = "";
        try {
            cacheKey = URLEncoder.encode(getUrl(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return cacheKey;
    }
}
