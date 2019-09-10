package com.padcmyanmar.padc9.housebuyingapp.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.padcmyanmar.padc9.housebuyingapp.R;
import com.padcmyanmar.padc9.housebuyingapp.adapters.HouseDetailsImageAdapter;
import com.padcmyanmar.padc9.housebuyingapp.data.vos.HouseRentingVO;

import org.mmtextview.components.MMTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity {
    public static final String EXTERNAL_HOUSE_ID = "houseExtra";

    public static Intent newIntent(Context context, int eventExtra){
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTERNAL_HOUSE_ID,eventExtra);
        return intent;
    }

    @BindView(R.id.tv_price)
    MMTextView tvPrice;

    @BindView(R.id.tv_location_west_minister_details)
    MMTextView tvAddress;

    @BindView(R.id.tv_single_family_house_detail)
    MMTextView tvDescription;

    @BindView(R.id.tv_3200_sqft_details)
    MMTextView tvSqFt;

    @BindView(R.id.vp_details_images)
    ImageView vpDetailsImage;

    @BindView(R.id.fab_near_me)
    FloatingActionButton favNearMe;

    @BindView(R.id.tv_no_of_bed_rooms)
    MMTextView noOfBedRooms;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        //HouseDetailsImageAdapter adapter = new HouseDetailsImageAdapter();
        //vpDetailsImage.setAdapter(adapter);

        final ImageView backBtn = findViewById(R.id.iv_back_button);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        int houseId = getIntent().getIntExtra(EXTERNAL_HOUSE_ID,0);

        final HouseRentingVO houseVO = houseModel.findHouseById(houseId);
        bindData(houseVO);

        favNearMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location = Uri.encode(String.valueOf(houseVO.getLatitude())+","+String.valueOf(houseVO.getLongitude()));

                Intent intent = new Intent(Intent.ACTION_VIEW,  Uri.parse("https://www.google.com/maps/dir/?api=1&destination=" + location +"&travelmode=driving"));
                startActivity(intent);
            }
        });

    }

    private void bindData(HouseRentingVO data){
        Glide.with(vpDetailsImage.getContext())
                .load(data.getHouseImageUrl()).into(vpDetailsImage);
        tvAddress.setText(data.getAddress());
        tvPrice.setText(String.valueOf(data.getPrice()));
        tvSqFt.setText(String.valueOf(data.getSquareFeet()));
        tvDescription.setText(data.getDescription());
    }


}
