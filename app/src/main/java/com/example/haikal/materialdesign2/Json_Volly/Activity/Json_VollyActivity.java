package com.example.haikal.materialdesign2.Json_Volly.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.haikal.materialdesign2.Json_Volly.Adapter.Adapter_Json_Volly;
import com.example.haikal.materialdesign2.Json_Volly.App.AppController;
import com.example.haikal.materialdesign2.Json_Volly.helper.helper;
import com.example.haikal.materialdesign2.R;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Json_VollyActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<HashMap<String, String>> list;
    //listView
    private ListView listView;
    private String tag_json = "tag_json";
    //progress dialog
    private ProgressDialog progressDialog;
    //url to get all product list
    private static String url = "http://www.json-generator.com/api/json/get/cppvjpnxCG?indent=2";

    private Adapter_Json_Volly adapter_json_volly;
    //Context
    private Context context;
    //Create JSON parser object
    //JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS = "products";
    private static final String TAG_PID = "pid";
    private static final String TAG_NAME = "name";

    private RequestQueue requestQueue;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json__volly);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        adapter_json_volly = new Adapter_Json_Volly(this, list);

        //get listview
        listView = (ListView) findViewById(R.id.listView_json);

        //get Recycler View
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_Json_volly);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        //recyclerView.setAdapter(adapter_json_volly);

        progressDialog = new ProgressDialog(this);

        //Hashmap for listview
        list = new ArrayList<HashMap<String, String>>();

        //loading product inbackground thread
        new LoadAllproduct();


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

                if (progressDialog.isShowing())
                    progressDialog.dismiss();

                try {
                    JSONArray jsonArray = response.getJSONArray("handphone");
                    for (int i = 0; i < jsonArray.length(); i++) {
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
                VolleyLog.d("ERROR", error.getMessage());
                progressDialog.dismiss();
            }
        });
        AppController.getAppController().addToRequestQueue(jsonObjectRequest, tag_json);
    }

    private class LoadAllproduct extends AsyncTask<String, String, String> {
        /**
         * Before starting background thead show progress dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Loading Products,  please wait...");
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        /**
         * getting All product from url
         */
        @Override
        protected String doInBackground(String... strings) {
            //Build Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            //getting Jason String from Url
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            //check log cat for json response
                            Log.d("RESPONSE", response.toString());
                            //checking for success TAG
                            try {
                                int success = response.getInt(TAG_SUCCESS);
                                if (success == 1) {
                                    //product found
                                    //getArray of product
                                    JSONArray jsonArray = response.getJSONArray("handphone");

                                    //looping for through All product
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        //getting jsonObject of jsonArray
                                        JSONObject c = jsonArray.getJSONObject(i);
                                        //sorting each json item in array listview Hashmap
                                        HashMap<String, String> hashMap = new HashMap<String, String>();
                                        hashMap.put("id", c.getString("idhp"));
                                        hashMap.put("merk", c.getString("merk"));
                                        hashMap.put("tipe", c.getString("tipe"));
                                        hashMap.put("gambar", c.getString("gambar"));
                                        hashMap.put("keterangan", c.getString("keterangan"));
                                        list.add(hashMap);
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context,error.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
            );
            requestQueue.add(jsonObjectRequest);
            return null;
        }

        /**
         * After completing background theard dismiss the progress dialog
         */
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //dismiss the dialog after gettting all product
            progressDialog.dismiss();
            //update ui from background thread
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    /**
                     * updating parsed JSON data into Listvew
                     */
                    ListAdapter adapter = new SimpleAdapter(
                            context,
                            list,
                            R.layout.list_item_phone,
                            new String[]{TAG_PID, TAG_NAME},
                            new int[]{R.id.txthape, R.id.txtharga}
                    );
                    listView.setAdapter(adapter);
                }
            });
        }
    }
}
