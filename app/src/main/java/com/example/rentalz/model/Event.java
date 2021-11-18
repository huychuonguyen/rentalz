package com.example.rentalz.model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.io.Serializable;

public class Event implements Serializable {
    private int eventId;
    private String activityName;
    /**
     * it mean Type
     */
    private String location; // it mean Type
    private String eventDate;
    private String attendingTime;
    private String reporterName;

    private String furnitureType;
    private String monthlyRentPrice;
    private String notes;
    private String bedroom;
    private String address;

    public Event(int eventId,
                 String activityName,
                 String location,
                 String eventDate,
                 String reporterName,
                 String furnitureType,
                 String monthlyRentPrice,
                 String notes,
                 String bedroom,
                 String address
    ) {
        this.eventId = eventId;
        this.activityName = activityName;
        this.location = location;
        this.eventDate = eventDate;
        this.reporterName = reporterName;
        this.furnitureType = furnitureType;
        this.monthlyRentPrice = monthlyRentPrice;
        this.notes = notes;
        this.bedroom = bedroom;
        this.address = address;
    }

    public Event(
                 String activityName,
                 String location,
                 String eventDate,
                 String reporterName,
                 String furnitureType,
                 String monthlyRentPrice,
                 String notes,
                 String bedroom,
                 String address
    ) {
        this.activityName = activityName;
        this.location = location;
        this.eventDate = eventDate;
        this.reporterName = reporterName;
        this.furnitureType = furnitureType;
        this.monthlyRentPrice = monthlyRentPrice;
        this.notes = notes;
        this.bedroom = bedroom;
        this.address = address;
    }


    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getAttendingTime() {
        return attendingTime;
    }

    public void setAttendingTime(String attendingTime) {
        this.attendingTime = attendingTime;
    }

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }


    // dùng cho list adapter
    public static DiffUtil.ItemCallback<Event> DIFF_CALLBACK = new DiffUtil.ItemCallback<Event>() {

        @Override
        public boolean areItemsTheSame(@NonNull Event oldItem, @NonNull Event newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Event oldItem, @NonNull Event newItem) {
            return oldItem.eventId == newItem.eventId;
        }

    };

    public String getFurnitureType() {
        return furnitureType;
    }

    public void setFurnitureType(String furnitureType) {
        this.furnitureType = furnitureType;
    }

    public String getMonthlyRentPrice() {
        return monthlyRentPrice;
    }

    public void setMonthlyRentPrice(String monthlyRentPrice) {
        this.monthlyRentPrice = monthlyRentPrice;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getBedroom() {
        return bedroom;
    }

    public void setBedroom(String bedroom) {
        this.bedroom = bedroom;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
