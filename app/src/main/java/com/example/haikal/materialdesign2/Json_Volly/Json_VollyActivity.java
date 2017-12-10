package com.example.haikal.materialdesign2.Json_Volly;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.haikal.materialdesign2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Json_VollyActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<HashMap<String, String>> list;
    private String tag_json = "tag_json";
    private ProgressDialog progressDialog;
    private String url = "sad.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json__volly);

        progressDialog = new ProgressDialog(this);

        list = new ArrayList<HashMap<String, String>>();
        showData();
    }

    private void showData() {
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        if (!progressDialog.isShowing())
            progressDialog.show();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("RESPONSE", response.toString());

                if(progressDialog.isShowing())
                    progressDialog.dismiss();

                try {
                    JSONArray jsonArray = response.getJSONArray("handphone");
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        HashMap<String, String> hashMap = new HashMap<>();
                        //put(key, value)
                        hashMap.put("id", jsonObject.getString("idhp"));
                        hashMap.put("merk", jsonObject.getString("merk"));
                        hashMap.put("tipe", jsonObject.getString("tipe"));
                        hashMap.put("gambar", jsonObject.getString("gambar"));
                        hashMap.put("keterangan", jsonObject.getString("keterangan"));

                        list.add(hashMap);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("ERROR",error.getMessage());
                progressDialog.dismiss();
            }
        });

    }
}
