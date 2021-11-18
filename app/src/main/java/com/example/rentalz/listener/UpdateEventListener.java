package com.example.rentalz.listener;

import com.example.rentalz.model.Event;

import java.io.Serializable;

public interface UpdateEventListener extends Serializable {
    void onUpdated(Event event);
    void onAdded(Event event);
}
