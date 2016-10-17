package com.racobos.presentation.ui.ratecalculator;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.racobos.domain.R;
import com.racobos.domain.models.Journey;
import com.racobos.presentation.idlingresource.SimpleIdlingResource;
import com.racobos.presentation.navigation.Navigator;
import com.racobos.presentation.ui.bases.android.BaseActivity;
import com.racobos.presentation.ui.bases.android.Presenter;
import com.racobos.presentation.ui.components.views.map.MapComponent;
import icepick.State;
import java.util.Calendar;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by rulo7 on 08/10/2016.
 */

public class RateCalculatorActivity extends BaseActivity
        implements RateCalculatorPresenter.RateCalculatorView, MapComponent.OnMapActionListener {

    @Inject
    @Presenter
    RateCalculatorPresenter rateCalculatorPresenter;

    @Inject
    Navigator navigator;

    @State
    String originMarkerId;
    @State
    String destinationMarkerId;

    @BindView(R.id.submit)
    TextView submit;

    private Mara_RateCalculatorComposer composer;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, RateCalculatorActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_calculator);
        ButterKnife.bind(this);
        setupComponents();
        if (savedInstanceState == null) {
            showInformationDialog();
        }
    }

    private void setupComponents() {
        composer = new Mara_RateCalculatorComposer.Builder().setAppCompatActivity(this)
                .setRootView(findViewById(android.R.id.content))
                .setOnMapActionListener(this)
                .build();
        composer.initialize();
        composer.hideProgress();
    }

    private void showInformationDialog() {
        new AlertDialog.Builder(this).setTitle(R.string.welcome_cabify_android_challenge)
                .setMessage(R.string.calculate_rate_route_instructions)
                .setPositiveButton(R.string.undestood, (dialog, which) -> dialog.dismiss())
                .setCancelable(false)
                .show();
    }

    @Override
    public void onMapClick(double lat, double lng) {
        pickOriginOrDestination(lat, lng);
    }

    private void pickOriginOrDestination(double lat, double lng) {
        new AlertDialog.Builder(this).setTitle(R.string.choose_position)
                .setItems(R.array.position_selection, (dialog, which) -> {
                    String[] items = getResources().getStringArray(R.array.position_selection);
                    if (items[which].equals(getString(R.string.origin))) {
                        if (originMarkerId != null) {
                            composer.removeMarker(originMarkerId);
                        }
                        originMarkerId = composer.addMarker(lat, lng, getString(R.string.origin), null);
                        rateCalculatorPresenter.setOrigin(lat, lng);
                    } else if (items[which].equals(getString(R.string.destination))) {
                        if (destinationMarkerId != null) {
                            composer.removeMarker(destinationMarkerId);
                        }
                        destinationMarkerId = composer.addMarker(lat, lng, getString(R.string.destination), null);
                        rateCalculatorPresenter.setDestination(lat, lng);
                    }
                })
                .show();
    }

    @Override
    public boolean onMarkerClick(String id) {
        return false;
    }

    @Override
    public void onSearch(MapComponent.Address address) {
        if (address != null) {
            pickOriginOrDestination(address.getLat(), address.getLon());
        }
    }

    @Override
    public void showProgress() {
        composer.showProgress();
    }

    @Override
    public void hideProgress() {
        composer.hideProgress();
    }

    @Override
    public void showSubmitMessage() {
        submit.setText(R.string.estimate);
    }

    @Override
    public void showSubmitOriginRequiredMessage() {
        submit.setText(R.string.origin_can_not_be_empty);
    }

    @Override
    public void showSubmitDestinationRequiredMessage() {
        submit.setText(R.string.destination_can_not_be_empty);
    }

    @Override
    public void requestStartMoment(OnRequestDateTimeListener onRequestDateTimeListener) {
        new AlertDialog.Builder(this).setTitle(R.string.set_start_time)
                .setNegativeButton(R.string.asap, (dialog, which) -> onRequestDateTimeListener.onDateTimeResponse(null))
                .setPositiveButton(getString(R.string.set_moment),
                        (dialog, which) -> requestDateTime(onRequestDateTimeListener))
                .show();
    }

    private void requestDateTime(OnRequestDateTimeListener onRequestDateTimeListener) {
        Calendar currentCalendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view1, hourOfDay, minute) -> {
                Calendar selectedDateCalendar = Calendar.getInstance();
                selectedDateCalendar.set(Calendar.YEAR, year);
                selectedDateCalendar.set(Calendar.MONTH, month);
                selectedDateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                selectedDateCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                selectedDateCalendar.set(Calendar.MINUTE, minute);
                onRequestDateTimeListener.onDateTimeResponse(selectedDateCalendar.getTimeInMillis());
            }, currentCalendar.get(Calendar.HOUR_OF_DAY), currentCalendar.get(Calendar.MINUTE), true);
            timePickerDialog.setTitle(R.string.starts_at);
            timePickerDialog.show();
        }, currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH),
                currentCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.setTitle(R.string.starts_at);
        datePickerDialog.show();
    }

    @Override
    public void showErrorDateSelected() {
        Toast.makeText(this, R.string.invalid_selected_time, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setDestination(Double lat, Double lon) {
        if (destinationMarkerId != null) {
            composer.removeMarker(destinationMarkerId);
        }
        destinationMarkerId = composer.addMarker(lat, lon, getString(R.string.destination), null);
    }

    @Override
    public void setOrigin(Double lat, Double lon) {
        if (originMarkerId != null) {
            composer.removeMarker(originMarkerId);
        }
        originMarkerId = composer.addMarker(lat, lon, getString(R.string.origin), null);
    }

    @Override
    public void notifySomethingWentWrong() {
        Toast.makeText(this, R.string.default_error_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToJourneyRatesList(List<Journey> journeys) {
        navigator.navigateToRateList(journeys, this);
    }

    @Override
    public void notifyErrorNetwork() {
        Toast.makeText(this, R.string.network_error_default_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void notifyErrorSelectedPlaces() {
        Toast.makeText(this, R.string.unavalaible_service_under_selected_places, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.submit)
    public void submit() {
        rateCalculatorPresenter.submit();
    }

    //<editor-fold desc="testing methods">
    @VisibleForTesting
    @NonNull
    @Override
    public SimpleIdlingResource getIdlingResource() {
        SimpleIdlingResource idlingResource = super.getIdlingResource();
        composer.setIdlingResource(idlingResource);
        return idlingResource;
    }
    //</editor-fold>
}