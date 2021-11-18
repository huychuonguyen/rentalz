package com.example.rentalz.listener;

import com.example.rentalz.model.Event;

public interface EventListAdapterListener {
    void onUpdateClicked(Event event, int position);
    void onDeleteClicked(Event event, int position);
}