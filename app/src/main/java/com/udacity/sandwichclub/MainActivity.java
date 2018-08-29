package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String[] sandwichesDetails = getResources().getStringArray(R.array.sandwich_details);
        List<Sandwich> sandwichesList = new ArrayList();

        for (String sandwichJson : sandwichesDetails) {
            sandwichesList.add(JsonUtils.parseSandwichJson(sandwichJson));
        }

        ListView listView = findViewById(R.id.sandwiches_listview);
        ListAdapter customAdapter = new ListAdapter(this, sandwichesList);
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                launchDetailActivity(position,sandwichesDetails);
            }
        });
    }

    private void launchDetailActivity(int position, String [] sandwichesDetails) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_POSITION, position);
        intent.putExtra(DetailActivity.SANDWICH_DETAILS, sandwichesDetails);
        startActivity(intent);
    }
}
