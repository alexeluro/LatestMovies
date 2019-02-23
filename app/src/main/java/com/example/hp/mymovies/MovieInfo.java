package com.example.hp.mymovies;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MovieInfo {
    RequestQueue requestQueue;
    final static String URL =
            "https://api.themoviedb.org/3/movie/upcoming?api_key=f9a0e9ea6ce23bc519099f12249694ee&language=en-US&page=1";
    Context mContext;
//    List<String> mTitle = Movies.title;
//    List<String> mReleaseNote = Movies.release_date;
//    List<String> mVoteCount = Movies.vote_count;
//    List<String> mOverview = Movies.overview;
//    List<String> mPosterPath = Movies.poster_path;

     CustomLayout customLayout = new CustomLayout(Movies.title, Movies.release_date,
            Movies.vote_count, Movies.overview, Movies.poster_path);

    public static class Movies{
        static List<String> title;
        static List<String> release_date;
        static List<String> vote_count;
        static List<String> overview;
        static List<String> poster_path;

        public Movies(int i, String title, String release_date, String vote_count, String overview, String poster_path) {
            this.title.add(i, title);
            this.release_date.add(i, release_date);
            this.vote_count.add(i, vote_count);
            this.overview.add(i, overview);
            this.poster_path.add(i, poster_path);
        }

        public List<String> getTitle() {
            return title;
        }

        public List<String> getRelease_date() {
            return release_date;
        }

        public List<String> getVote_count() {
            return vote_count;
        }

        public List<String> getOverview() {
            return overview;
        }

        public List<String> getPoster_path() {
            return poster_path;
        }
    }

    public MovieInfo(Context context) {
    mContext = context;
    }

    public void movieLoader(){

        requestQueue = Volley.newRequestQueue(mContext);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        JSONObject movieCrap = new JSONObject();
                        try {
                            for(int i = 0; i<movieCrap.length(); i++) {
                                String title = movieCrap.getString("title");
                                String release_date = movieCrap.getString("release_date");
                                String vote_count = movieCrap.getString("vote_count");
                                String overview = movieCrap.getString("overview");
                                String poster_path = movieCrap.getString("poster_path");


                                new Movies(i, title, release_date, vote_count, overview, poster_path);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            JSONArray genre = response.getJSONArray("genre");

                            JSONObject genrePref = genre.getJSONObject(0);
                            String genreResult = genrePref.getString("name");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },///// END OF POSITIVE RESONPSE
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }///// END OF ERROR RESPONSE

        );///// END OF JsonObjectRequest
        requestQueue.add(jsonObjectRequest);
    }
}
