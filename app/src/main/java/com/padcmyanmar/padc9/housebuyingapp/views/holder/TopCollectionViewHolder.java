package com.padcmyanmar.padc9.housebuyingapp.views.holder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.padcmyanmar.padc9.housebuyingapp.data.vos.HouseRentingVO;
import com.padcmyanmar.padc9.housebuyingapp.delegates.EventItemDelegate;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.padcmyanmar.padc9.housebuyingapp.R;

import org.mmtextview.components.MMTextView;

public class TopCollectionViewHolder extends BaseViewHolder<HouseRentingVO> {
    private EventItemDelegate mEventItemDelegate;

    @BindView(R.id.tv_3200_sqft)
    MMTextView tvSquareFeet;

    @BindView(R.id.tv_location_west_minister)
    MMTextView tvLocationAddress;

    @BindView(R.id.btn_price)
    MMTextView btnPrice;

    @BindView(R.id.iv_west_minster_london)
    ImageView ivHouse;

    public TopCollectionViewHolder(@NonNull View itemView, EventItemDelegate delegate) {
        super(itemView);
        mEventItemDelegate = delegate;
        ButterKnife.bind(this,itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEventItemDelegate.onTapEventItem(mData.getId());
            }
        });

    }


    @Override
    public void bindData(HouseRentingVO data) {
        mData = data;
        Glide.with(itemView).load(data.getHouseImageUrl()).into(ivHouse);
        tvLocationAddress.setText(data.getAddress());
        tvSquareFeet.setText(String.valueOf(data.getSquareFeet()));
        btnPrice.setText(String.valueOf(data.getPrice()));
    }
}

