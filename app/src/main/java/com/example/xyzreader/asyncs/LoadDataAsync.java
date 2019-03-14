package com.example.xyzreader.asyncs;

import android.os.AsyncTask;
import com.example.xyzreader.BuildConfig;
import com.example.xyzreader.R;
import com.example.xyzreader.pojos.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoadDataAsync extends AsyncTask<Void, Void, List<Item>> {

    private LoadDataListener mListener;
    private int mErr = 0;

    public LoadDataAsync(LoadDataListener listener) {
        mListener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mListener.onPreExecuteLoad();
    }

    @Override
    protected List<Item> doInBackground(Void... voids) {
        List<Item> list = new ArrayList<>();

        /*Cria a instância de client.*/
        OkHttpClient client = new OkHttpClient();

        /*Cria a instância de request.*/
        Request request = new Request.Builder()
                .url(BuildConfig.BASE_URL)
                .build();

        try {
            /*Configura o objeto response.*/
            Response response = client.newCall(request).execute();

            /*Valida se o retorno ocorreu de forma OK.*/
            if (response.body() != null && response.isSuccessful()) {
                String json = response.body().string();
                try {
                    JSONArray array = new JSONArray(json);
                    ObjectMapper mapper = new ObjectMapper();

                    for (int i = 0; i < array.length(); i++) {
                        list.add(mapper.readValue(array.get(i).toString(), Item.class));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                mErr = R.string.no_data;
            }
        } catch (Exception e) {
            mErr = R.string.unexpected_error;
        }
        return list;
    }

    @Override
    protected void onPostExecute(List<Item> items) {
        super.onPostExecute(items);
        mListener.onFinishLoad(items, mErr);
    }

    public interface LoadDataListener {
        void onPreExecuteLoad();

        void onFinishLoad(List<Item> items, int errMsg);
    }
}
