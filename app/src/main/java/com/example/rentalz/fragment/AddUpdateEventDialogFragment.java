package com.example.rentalz.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.rentalz.R;
import com.example.rentalz.database.DatabaseHelper;
import com.example.rentalz.listener.UpdateEventListener;
import com.example.rentalz.model.Event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddUpdateEventDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddUpdateEventDialogFragment extends DialogFragment implements NumberPicker.OnValueChangeListener {

    public static String TAG = "UpdateEventDialogFragment";

    public enum EventType {
        Add,
        Update
    }

    private DatabaseHelper databaseHelper;

    private TextView tvEventType;
    private TextView tvEventId;
    private EditText etActivityName;
    private EditText etType;
    private TextView tvEventDate;
    //private TextView tvAttendingTime;
    private EditText etReporterName;
    private TextView etFurnitureType;
    private EditText etMonthlyRentPrice;
    private EditText etNotes;
    private EditText etBedroom;
    private EditText etAddress;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_EVENT = "param_event";
    private static final String ARG_EVENT_LISTENER = "param_event_listener";
    private static final String ARG_EVENT_TYPE = "param_event_type";

    // TODO: Rename and change types of parameters
    private Event event;
    private UpdateEventListener listener;
    private EventType eventType;

    public AddUpdateEventDialogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment UpdateEventDialogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddUpdateEventDialogFragment newInstance(
            Event event,
            UpdateEventListener listener,
            EventType eventType
    ) {
        AddUpdateEventDialogFragment fragment = new AddUpdateEventDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_EVENT, event);
        args.putSerializable(ARG_EVENT_LISTENER, listener);
        args.putSerializable(ARG_EVENT_TYPE, eventType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            event = (Event) getArguments().getSerializable(ARG_EVENT);
            listener = (UpdateEventListener) getArguments().getSerializable(ARG_EVENT_LISTENER);
            eventType = (EventType) getArguments().getSerializable(ARG_EVENT_TYPE);
        }

        databaseHelper = new DatabaseHelper(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Drawable drawable = ContextCompat.getDrawable(requireContext(), android.R.color.transparent);
        InsetDrawable inset = new InsetDrawable(drawable, 20);

        getDialog().getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);
        getDialog().getWindow().setBackgroundDrawable(inset);
        getDialog().setCancelable(true);
        setCancelable(true);
        return inflater.inflate(R.layout.fragment_update_event_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

    }

    @SuppressLint("SetTextI18n")
    private void initView(View view) {
        tvEventType = view.findViewById(R.id.tvEventType);
        tvEventId = view.findViewById(R.id.tvEventId);
        etActivityName = view.findViewById(R.id.etActivityName);
        etType = view.findViewById(R.id.etType);
        tvEventDate = view.findViewById(R.id.tvEventDateUpdateEvent);
        //tvAttendingTime = view.findViewById(R.id.tvAttendingTimeUpdateEvent);
        etReporterName = view.findViewById(R.id.etReporterNameUpdateEvent);
        etAddress = view.findViewById(R.id.etAddress);
        etFurnitureType = view.findViewById(R.id.etFurniture);
        etMonthlyRentPrice = view.findViewById(R.id.etMonthlyRentPrice);
        etNotes = view.findViewById(R.id.etNotes);
        etBedroom = view.findViewById(R.id.etBedroom);

        // get and set current date
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date());
        tvEventDate.setText(date);


        Button btnUpdateEvent = view.findViewById(R.id.btnUpdateEventUpdateEventFragment);
        final Button btnCloseButton = view.findViewById(R.id.btnCloseBottomUpdateEventFragment);
        final ImageButton btnClose = view.findViewById(R.id.btnCloseUpdateEvent);
        final Button btnSaveEvent = view.findViewById(R.id.btnSaveEventUpdateEventFragment);
        final LinearLayout lnEventId = view.findViewById(R.id.lnEventId);


        if (this.eventType == EventType.Add) {
            tvEventType.setText("Add");
            btnUpdateEvent.setVisibility(View.INVISIBLE);
            btnSaveEvent.setVisibility(View.VISIBLE);

           // lnEventId.setVisibility(View.GONE);
        } else {
            tvEventType.setText("Update");
            btnUpdateEvent.setVisibility(View.VISIBLE);
            btnSaveEvent.setVisibility(View.INVISIBLE);

            if (this.event != null) {
                tvEventId.setText(Integer.toString(event.getEventId()));
                etActivityName.setText(event.getActivityName());
                etType.setText(event.getLocation());
                tvEventDate.setText(event.getEventDate());
                etMonthlyRentPrice.setText(event.getMonthlyRentPrice());
                etReporterName.setText(event.getReporterName());
                etFurnitureType.setText(event.getFurnitureType());
                etNotes.setText(event.getNotes());
                etBedroom.setText(event.getBedroom());
                etAddress.setText(event.getAddress());
            }
        }

//        tvEventDate.setOnClickListener((v) -> {
//            showDatePicker();
//        });

//        tvAttendingTime.setOnClickListener((v) -> {
//            showTimePicker();
//        });

        etFurnitureType.setOnClickListener((v) -> {
            showDropdownFurnitureType();
        });

        btnUpdateEvent.setOnClickListener((v) -> {
            updateEvent();
        });

        btnSaveEvent.setOnClickListener((v) -> {
            addEvent();
        });

        btnClose.setOnClickListener((v) -> {
            dismiss();
        });

        btnCloseButton.setOnClickListener((v) -> {
            dismiss();
        });


    }

    private void updateEvent() {
        String activityName = "";
        String location = "";
        String eventDate = "";
        String attendingTime = "";
        String reporterName = "";
        String furnitureType = "";
        String monthlyRentPrice = "";
        String notes = "";
        String bedroom = "";
        String address = "";


        if (tvEventDate.getText().toString().isEmpty()) {
            showAlert("Date is required!");
            return;
        } else {
            eventDate = tvEventDate.getText().toString();
        }


        if (etReporterName.getText().toString().isEmpty()) {
            showAlert("Name of the reporter is required!");
            return;
        } else {
            reporterName = etReporterName.getText().toString();
        }

        if (etMonthlyRentPrice.getText().toString().isEmpty()) {
            showAlert("Monthly rent price is required!");
            return;
        } else {
            monthlyRentPrice = etMonthlyRentPrice.getText().toString();
        }

        if (etBedroom.getText().toString().isEmpty()) {
            showAlert("Monthly rent price is required!");
            return;
        } else {
            bedroom = etBedroom.getText().toString();
        }

        activityName = etActivityName.getText().toString();
        address = etAddress.getText().toString();
        furnitureType = etFurnitureType.getText().toString();
        notes = etNotes.getText().toString();

        this.event = new Event(
                this.event.getEventId(),
                activityName,
                location,
                eventDate,
                reporterName,
                furnitureType,
                monthlyRentPrice,
                notes,
                bedroom,
                address
        );

        if (databaseHelper.updateEvent(this.event)) {
            Toast.makeText(requireContext(), "Update succeed!", Toast.LENGTH_SHORT).show();
            // gọi listener để hứng sự kiện đã cập nhật thành công
            // va2 gọi lại nơi @Override
            listener.onUpdated(this.event);
            dismiss();
        } else {
            Toast.makeText(requireContext(), "Update failed!", Toast.LENGTH_SHORT).show();
        }
    }

    private void addEvent() {
        String activityName = "";
        String type = "";
        String eventDate = "";
        String reporterName = "";
        String furnitureType = "";
        String monthlyRentPrice = "";
        String notes = "";
        String bedroom = "";
        String address = "";

        if (etType.getText().toString().isEmpty()) {
            showAlert("Type is required!");
            return;
        } else {
            type = etType.getText().toString();
        }

        if (tvEventDate.getText().toString().isEmpty()) {
            showAlert("Date is required!");
            return;
        } else {
            eventDate = tvEventDate.getText().toString();
        }

        if (etReporterName.getText().toString().isEmpty()) {
            showAlert("Name of the reporter is required!");
            return;
        } else {
            reporterName = etReporterName.getText().toString();
        }

        if (etMonthlyRentPrice.getText().toString().isEmpty()) {
            showAlert("Monthly rent price is required!");
            return;
        } else {
            monthlyRentPrice = etMonthlyRentPrice.getText().toString();
        }

        if (etBedroom.getText().toString().isEmpty()) {
            showAlert("Bedrooms is required!");
            return;
        } else {
            bedroom = etBedroom.getText().toString();
        }

        activityName = etActivityName.getText().toString();
        address = etAddress.getText().toString();
        furnitureType = etFurnitureType.getText().toString();
        notes = etNotes.getText().toString();

        Event event = new Event(
                activityName,
                type,
                eventDate,
                reporterName,
                furnitureType,
                monthlyRentPrice,
                notes,
                bedroom,
                address
        );

        if (databaseHelper.addEvent(event)) {
            Toast.makeText(requireContext(), "Add succeed!", Toast.LENGTH_SHORT).show();
            listener.onAdded(event);
            dismiss();
        } else {
            Toast.makeText(requireContext(), "Add failed!", Toast.LENGTH_SHORT).show();
        }
    }

    private void showDropdownFurnitureType(){
        final Dialog d = new Dialog(this.getContext());
        d.setTitle("NumberPicker");
        d.setContentView(R.layout.dialog);
        Button b1 = (Button) d.findViewById(R.id.buttonOK);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        final String[] furnitures = new String[]{"Furnished", "Unfurnished", "Part Furnished"};
        np.setMaxValue(furnitures.length - 1);
        np.setMinValue(0);
        np.setDisplayedValues(furnitures);

        np.setDescendantFocusability( NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);
        b1.setOnClickListener(v -> {
            etFurnitureType.setText(furnitures[np.getValue()]);
            d.dismiss();
        });
        b2.setOnClickListener(v -> d.dismiss());
        d.show();
    }

    private void showDatePicker() {
        Calendar calendarDatePicker = Calendar.getInstance();
        @SuppressLint("SetTextI18n") final DatePickerDialog.OnDateSetListener listener = (v, year, monthOfYear, dayOfMonth) -> {
            String month = normalizeNumber(monthOfYear + 1);
            String day = normalizeNumber(dayOfMonth);

            tvEventDate.setText(month + "/" + day + "/" + year);
        };

        new DatePickerDialog(requireContext(), listener, calendarDatePicker
                .get(Calendar.YEAR), calendarDatePicker.get(Calendar.MONTH),
                calendarDatePicker.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void showTimePicker() {
        Calendar calendarTimePicker = Calendar.getInstance();
        final TimePickerDialog.OnTimeSetListener timePickerListener = (v, hourOfDay, minute) -> {
            String am_pm;
            int hour = hourOfDay;
            if (hour < 12) {
                am_pm = "AM";
            } else {
                am_pm = "PM";
                hour %= 12;
                if (hour == 0)
                    hour = 12;
            }
            String time = normalizeNumber(hour) + ":" + normalizeNumber(minute) + " " + am_pm;
            //tvAttendingTime.setText(time);
        };


        int hour = calendarTimePicker.get(Calendar.HOUR);
        int minute = calendarTimePicker.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(
                requireContext(),
                timePickerListener,
                hour,
                minute,
                false);
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    private void showAlert(String message) {
        new AlertDialog.Builder(requireContext())
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                    }
                })
                .show();
    }

    private String normalizeNumber(int number) {
        if (number / 10 == 0)
            return "0" + number;
        return Integer.toString(number);
    }
}