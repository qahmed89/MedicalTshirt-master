package com.example.ahmedabdelazeem.medicaltshirt;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ahmed.Abdelazeem on 5/10/2016.
 */
public class RequestDoctorList extends StringRequest {
    private static final String FetchDocterList_URL = "http://www.egymarts.com/FetchDocterList.php";
    private Map<String, String> params;

    public RequestDoctorList(Response.Listener<String> listener){
        super(Method.GET, FetchDocterList_URL, listener, null);
        params = new HashMap<>();
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
