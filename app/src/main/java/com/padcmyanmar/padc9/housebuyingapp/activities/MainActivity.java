package com.padcmyanmar.padc9.housebuyingapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;


import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.padcmyanmar.padc9.housebuyingapp.R;
import com.padcmyanmar.padc9.housebuyingapp.adapters.TopCollectionAdapter;
import com.padcmyanmar.padc9.housebuyingapp.data.model.EventModel;
import com.padcmyanmar.padc9.housebuyingapp.data.vos.HouseRentingVO;
import com.padcmyanmar.padc9.housebuyingapp.delegates.EventItemDelegate;
import com.padcmyanmar.padc9.housebuyingapp.delegates.TopCollectionItemViewDelegate;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements EventItemDelegate, TopCollectionItemViewDelegate, View.OnClickListener {

    @BindView(R.id.rv_top_collection)
    RecyclerView recyclerView;
    private ImageButton hori,ver;
    TopCollectionAdapter adapter;

    private static boolean isGridView = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hori = findViewById(R.id.iv_view_stream_horizontal);
        ver = findViewById(R.id.iv_view_stream_vertical);
        hori.setOnClickListener(this);
        ver.setOnClickListener(this);


        ButterKnife.bind(this);

        adapter = new TopCollectionAdapter(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(adapter);

        final EditText textSearch = findViewById(R.id.et_location);
        ImageView search = findViewById(R.id.ic_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchWord = textSearch.getText().toString();
                onSearchFilter(searchWord);
            }
        });

        houseModel.getEvent(new EventModel.GetEventFromDataLayerDelegate() {
            @Override
            public void onSuccess(List<HouseRentingVO> event) {
                adapter.setNewData(event);
            }

            @Override
            public void onFailure(String errorMessage) {
                showIndefiniteSnackBar(errorMessage);
            }
        });

    }

    @Override
    public void onTapEventItem(int houseId) {
        Intent intent = DetailActivity.newIntent(getApplicationContext(),houseId);
        startActivity(intent);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_view_stream_horizontal:onTabGridView();
                break;
            case R.id.iv_view_stream_vertical : onTabListView();
                break;
        }
    }

    @Override
    public void onSearchFilter(String searchWord) {
        List<HouseRentingVO> resultList = houseModel.findHouseByName(searchWord);
        if(isGridView){
            showAsGrid(resultList);
        }else {
            showAsList(resultList);
        }
    }

    @Override
    public void onTabGridView() {
        isGridView = true;
        List<HouseRentingVO> houseRentingVOList = houseModel.getDataRepository();
        showAsGrid(houseRentingVOList);
    }

    private void showAsGrid(List<HouseRentingVO> houseRentingVOList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
        adapter.setNewData(houseRentingVOList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onTabListView() {
        isGridView = false;
        List<HouseRentingVO> houseRentingVOList = houseModel.getDataRepository();
        showAsList(houseRentingVOList);
    }

    private void showAsList(List<HouseRentingVO> houseRentingVOList) {
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        adapter.setNewData(houseRentingVOList);
        recyclerView.setAdapter(adapter);
    }
}
