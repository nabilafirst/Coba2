package id.sch.smktelkom_mlg.privateassignment.xirpl425.bismillahcoba2;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl425.bismillahcoba2.adapter.MovieAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl425.bismillahcoba2.model.MovieModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class PopulerFragment extends Fragment {
    public MovieAdapter movieAdapter;
    public RecyclerView recyclerView;
    public ArrayList<MovieModel> movieModelArrayList = new ArrayList<MovieModel>();
    private ProgressDialog pDialog;
    private String url = "https://api.themoviedb.org/3/movie/popular?api_key=fdcdbf0470f07fda93c90e98fe6c0a64";

    public PopulerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_populer, container, false);
        pDialog = new ProgressDialog(this.getActivity());
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        movieAdapter = new MovieAdapter(movieModelArrayList);
        recyclerView.setAdapter(movieAdapter);

        if (movieModelArrayList.isEmpty()) makeJsonjReq(url);

        return view;
    }

    private void makeJsonjReq(String url) {
        pDialog.show();
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.equals("1")) {

                                JSONArray dataArray = response.getJSONArray("results");
                                for (int j = 0; j < dataArray.length(); j++) {
                                    MovieModel modelMovie = new MovieModel();
                                    JSONObject dataObject = dataArray.getJSONObject(j);
                                    modelMovie.setTitle(dataObject.getString("title"));
                                    modelMovie.setOverview(dataObject.getString("overview"));
                                    movieModelArrayList.add(modelMovie);
                                }
                                movieAdapter.notifyDataSetChanged();
                            } else {
                                pDialog.dismiss();
                                Toast.makeText(getActivity(), "Error page", Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Response Error", Toast.LENGTH_LONG).show();
                        }
                        pDialog.dismiss();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();
                Toast.makeText(getActivity(), "Koneksi Error", Toast.LENGTH_LONG).show();

            }
        });

        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);

        queue.add(stringRequest);
    }
}
