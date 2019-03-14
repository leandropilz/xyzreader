package com.example.xyzreader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xyzreader.extras.Constants;
import com.example.xyzreader.extras.ImageLoader;
import com.example.xyzreader.pojos.Item;

public class ItemActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        /*Recebe o item selecionado pelo usuário.*/
        Item item = getIntent().getParcelableExtra(Constants.ITEM_BUNDLE);

        /*Imagem.*/
        ImageLoader.load(this, item.getPhoto(), (ImageView) findViewById(R.id.item_image));

        /*Título.*/
        ((TextView) findViewById(R.id.item_header_title)).setText(item.getTitle());

        /*Autor.*/
        ((TextView) findViewById(R.id.item_header_author)).setText(item.getAuthor());

        /*Body.*/
        TextView tv = findViewById(R.id.item_web_data);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.setText(Html.fromHtml(item.getBody()));

        /*Configura o listener ao fab.*/
        findViewById(R.id.item_fab).setOnClickListener(this);

        /*Configura a toolbar.*/
        Toolbar toolbar = findViewById(R.id.item_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_fab:
                BottomSheetFragment sheet = new BottomSheetFragment();
                sheet.show(getSupportFragmentManager(), sheet.getTag());
                break;
        }
    }
}