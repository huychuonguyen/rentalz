package com.example.rentalz.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentalz.R;
import com.example.rentalz.listener.EventListAdapterListener;
import com.example.rentalz.model.Event;

public class EventListAdapter extends ListAdapter<Event, EventListAdapter.EventHolder> {

    private final EventListAdapterListener mListener;

    public EventListAdapter(EventListAdapterListener mListener) {
        super(Event.DIFF_CALLBACK);
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.apartment_item, parent, false);
        return new EventHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {

        Event event = getItem(position);
        if (event != null) holder.bind(event, this.mListener);
    }


    static class EventHolder extends RecyclerView.ViewHolder {

        private TextView etActivityName;
        private TextView etType;
        private TextView tvEventDate;
        private TextView etReporterName;
        private TextView etFurnitureType;
        private TextView etMonthlyRentPrice;
        private TextView etNotes;
        private TextView etBedroom;
        private TextView etAddress;
        private final Button btnUpdateEvent;
        private final Button btnDeleteEvent;


        public EventHolder(@NonNull View itemView) {
            super(itemView);

            etActivityName = itemView.findViewById(R.id.etActivityName);
            etType = itemView.findViewById(R.id.etType);
            tvEventDate = itemView.findViewById(R.id.tvEventDateUpdateEvent);
            etReporterName = itemView.findViewById(R.id.etReporterNameUpdateEvent);
            etFurnitureType = itemView.findViewById(R.id.etFurniture);
            etMonthlyRentPrice = itemView.findViewById(R.id.etMonthlyRentPrice);
            etNotes = itemView.findViewById(R.id.etNotes);
            etBedroom = itemView.findViewById(R.id.etBedroom);
            etAddress = itemView.findViewById(R.id.etAddress);

            btnUpdateEvent = itemView.findViewById(R.id.btnUpdateEventUpdateEventFragment);
            btnDeleteEvent = itemView.findViewById(R.id.btnDeleteBottomUpdateEventFragment);
        }

        public void bind(Event event, EventListAdapterListener listener) {
            etActivityName.setText(event.getActivityName());
            etAddress.setText(event.getAddress());
            tvEventDate.setText(event.getEventDate());
            etReporterName.setText(event.getReporterName());
            etType.setText(event.getLocation());
            etFurnitureType.setText(event.getFurnitureType());
            etMonthlyRentPrice.setText(event.getMonthlyRentPrice());
            etNotes.setText(event.getNotes());
            etBedroom.setText(event.getBedroom());

            btnUpdateEvent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onUpdateClicked(event, getAdapterPosition());
                }
            });
            btnDeleteEvent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDeleteClicked(event, getAdapterPosition());
                }
            });
        }
    }
}





