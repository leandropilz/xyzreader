package com.example.xyzreader;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.xyzreader.adapters.AdapterData;
import com.example.xyzreader.asyncs.LoadDataAsync;
import com.example.xyzreader.extras.AdapterListener;
import com.example.xyzreader.extras.Constants;
import com.example.xyzreader.extras.RecyclerInitialConf;
import com.example.xyzreader.pojos.Item;

import java.util.List;

public class MainActivity extends AppCompatActivity implements
        LoadDataAsync.LoadDataListener,
        SwipeRefreshLayout.OnRefreshListener,
        AdapterListener {

    private AdapterData mAdapter;
    private SwipeRefreshLayout mRefresh;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRefresh = findViewById(R.id.main_list);
        RecyclerView recyclerView = mRefresh.findViewById(R.id.default_recycler_list);

        /*Configura as cores do refesh.*/
        mRefresh.setColorSchemeColors(ContextCompat.getColor(this, R.color.light_blue_500),
                ContextCompat.getColor(this, R.color.cyan_500),
                ContextCompat.getColor(this, R.color.teal_500));

        /*Configura o listener.*/
        mRefresh.setOnRefreshListener(this);

        /*Cria a instância do adapter.*/
        mAdapter = new AdapterData(this);

        /*Configura o adapter com o recycler view.*/
        new RecyclerInitialConf(this, recyclerView, true, mAdapter);

        /*Carrega os dados.*/
        loadData();
    }

    /**
     * Carrega os dados da Api e atualiza a tela.
     */
    private void loadData() {
        new LoadDataAsync(this).execute();
    }

    @Override
    public void onPreExecuteLoad() {

    }

    @Override
    public void onFinishLoad(List<Item> items, int errMsg) {
        mRefresh.setRefreshing(false);
        mAdapter.addItems(items);
    }

    @Override
    public void onRefresh() {
        /*Carrega os dados.*/
        loadData();
    }

    @Override
    public void onItemAdapterClick(Object object) {
        Intent intent = new Intent(this, ItemActivity.class);
        intent.putExtra(Constants.ITEM_BUNDLE, (Item) object);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}