package com.racobos.presentation.ui.ratecalculator;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.racobos.domain.R;
import com.racobos.presentation.ui.bases.android.BaseActivity;
import com.racobos.presentation.ui.bases.android.Presenter;
import com.racobos.presentation.ui.components.views.map.MapComponent;

import java.util.List;

import javax.inject.Inject;

import icepick.State;

/**
 * Created by rulo7 on 08/10/2016.
 */

public class RateCalculatorActivity extends BaseActivity implements RateCalculatorPresenter.RateCalculatorView, MapComponent.OnMapActionListener {

    @Inject
    @Presenter
    RateCalculatorPresenter rateCalculatorPresenter;

    @State
    String originMarkerId;
    @State
    String destinationMarkerId;

    private Mara_RateCalculatorComposer composer;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, RateCalculatorActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_calculator);
        if (savedInstanceState == null) {
            setupComponents();
            showInformationDialog();
        }
    }

    private void setupComponents() {
        composer = new Mara_RateCalculatorComposer.Builder().setContext(this).setOnMapActionListener(this).build();
        composer.initialize();
        composer.enableHomeAsUp();
        composer.hideProgress();
    }

    private void showInformationDialog() {
        new AlertDialog.Builder(this).setTitle(R.string.welcome_cabify_android_challenge)
                .setMessage(R.string.calculate_rate_route_instructions)
                .setPositiveButton(R.string.undestood, (dialog, which) -> dialog.dismiss()).setCancelable(false).show();
    }

    @Override
    public void onMapClick(double lat, double lng) {
        pickORiginOrDestination(lat, lng);
    }

    private void pickORiginOrDestination(double lat, double lng) {
        new AlertDialog.Builder(this).setTitle(R.string.choose_position).setItems(R.array.position_selection, (dialog, which) -> {
            String[] items = getResources().getStringArray(R.array.position_selection);
            if (items[which].equals(getString(R.string.origin))) {
                rateCalculatorPresenter.setOrigin(lat, lng);
                if (originMarkerId != null) {
                    composer.removeMarker(originMarkerId);
                }
                originMarkerId = composer.addMarker(lat, lng, getString(R.string.origin));

            } else if (items[which].equals(getString(R.string.destination))) {
                rateCalculatorPresenter.setDestination(lat, lng);
                if (destinationMarkerId != null) {
                    composer.removeMarker(destinationMarkerId);
                }
                destinationMarkerId = composer.addMarker(lat, lng, getString(R.string.destination));
            }
        }).show();
    }

    @Override
    public boolean onMarkerClick(String id) {
        return false;
    }

    @Override
    public void onSearch(List<MapComponent.Address> addresses) {
        String[] items = new String[addresses.size()];
        for (int i = 0; i < addresses.size(); i++) {
            MapComponent.Address address = addresses.get(i);
            items[i] = address.getAddress() + ", " + address.getCountry() + " (" + address.getCity() + ")";
        }
        new AlertDialog.Builder(this).setItems(items, (dialog, which) -> {
            pickORiginOrDestination(addresses.get(which).getLat(), addresses.get(which).getLon());
            dialog.dismiss();
        }).setCancelable(true).show();
    }

    @Override
    public void showErrorEmptyOriginDialog() {
        Toast.makeText(this, R.string.origin_can_not_be_empty, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorEmptyDestinationDialog() {
        Toast.makeText(this, R.string.destination_can_not_be_empty, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        composer.showProgress();
    }

    @Override
    public void hideProgress() {
        composer.hideProgress();
    }
}