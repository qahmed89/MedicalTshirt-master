package com.example.ahmedabdelazeem.medicaltshirt;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ahmed.Abdelazeem on 5/9/2016.
 */
public class RegisterRequest extends StringRequest {
    private static final String Register_Request_URL = "http://www.egymarts.com/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String name,String email,String password,Response.Listener<String> listener){
        super(Method.POST,Register_Request_URL,listener,null);
        params = new HashMap<>();
        params.put("name",name);
        params.put("email",email);
        params.put("password",password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
