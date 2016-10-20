package com.racobos.presentation.ui.journeyslist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.racobos.domain.R;
import com.racobos.domain.models.Journey;
import com.racobos.presentation.utils.Renderer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by raulcobos on 13/10/16.
 */
public class JourneysListAdapter extends RecyclerView.Adapter<JourneysListAdapter.JourneysListViewHolder> {

    private List<Journey> journeys;

    public JourneysListAdapter(List<Journey> journeys) {
        this.journeys = journeys;
    }

    @Override
    public JourneysListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new JourneysListViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rate, parent, false));
    }

    @Override
    public void onBindViewHolder(JourneysListViewHolder holder, int position) {
        Journey journey = journeys.get(position);
        Renderer.print(journey.getVehicle().getImage(), holder.imageViewVehicule, android.R.drawable.ic_menu_report_image, 90, 90, true);
        holder.textviewName.setText(journey.getVehicle().getName());
        holder.textviewDescription.setText(journey.getVehicle().getDescription());
        holder.textviewPrice.setText(journey.getFormattedPrice());
        holder.textviewTimeEstimation.setText(journey.getVehicle().getTimeEstimation());
    }

    @Override
    public int getItemCount() {
        return journeys.size();
    }

    protected class JourneysListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageview_vehicle)
        ImageView imageViewVehicule;
        @BindView(R.id.textview_name)
        TextView textviewName;
        @BindView(R.id.textview_description)
        TextView textviewDescription;
        @BindView(R.id.textview_time_estimation)
        TextView textviewTimeEstimation;
        @BindView(R.id.textview_price)
        TextView textviewPrice;

        public JourneysListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
