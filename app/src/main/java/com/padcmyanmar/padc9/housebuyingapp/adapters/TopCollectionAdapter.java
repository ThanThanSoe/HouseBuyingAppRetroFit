package com.padcmyanmar.padc9.housebuyingapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.padcmyanmar.padc9.housebuyingapp.R;
import com.padcmyanmar.padc9.housebuyingapp.data.vos.HouseRentingVO;
import com.padcmyanmar.padc9.housebuyingapp.delegates.EventItemDelegate;
import com.padcmyanmar.padc9.housebuyingapp.views.holder.TopCollectionViewHolder;

public class TopCollectionAdapter extends BaseRecyclerAdapter<TopCollectionViewHolder, HouseRentingVO> {
    private EventItemDelegate mEventItemDelegate;

    public TopCollectionAdapter(EventItemDelegate mEventItemDelegate) {
        this.mEventItemDelegate = mEventItemDelegate;
    }

    @NonNull
    @Override
    public TopCollectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_collection_item_view, parent, false);
        return new TopCollectionViewHolder(itemView, mEventItemDelegate);
    }

}