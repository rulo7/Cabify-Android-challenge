package com.racobos.presentation.ui.components.views.map;

import android.Manifest;
import android.content.Context;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.racobos.domain.R;
import com.racobos.presentation.ui.components.views.ViewComponent;
import com.racobos.presentation.utils.PermissionManager;
import com.racobos.presentation.utils.ScreenSizeOperations;
import com.txusballesteros.mara.Trait;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lombok.Data;
import lombok.experimental.Builder;

/**
 * Created by rulo7 on 09/10/2016.
 */
@Trait
public class MapComponent implements ViewComponent, OnMapReadyCallback {

    private static final int CAMERA_CENTER_PADDING = 24;
    private static final int ADDRESS_SERACH_MAX_RESULT = 12;

    private Context context;
    private GoogleMap googleMap;
    private OnMapActionListener onMapActionListener;
    private HashMap<String, Marker> markers = new HashMap<>();
    private EditText editTextSearch;

    public MapComponent(Context context, OnMapActionListener onMapActionListener) {
        this.context = context;
        this.onMapActionListener = onMapActionListener;
    }

    @Override
    public void initialize() {
        if (context instanceof AppCompatActivity) {
            AppCompatActivity rootActivity = (AppCompatActivity) context;
            ViewGroup holderView = (ViewGroup) rootActivity.findViewById(R.id.map_component);
            if (holderView != null) {
                PermissionManager.requestMultiplePermissions(holderView, () -> {
                    View view = LayoutInflater.from(context).inflate(R.layout.map_component_view, holderView, false);
                    setupViews(view);
                    holderView.addView(view);
                    SupportMapFragment mapFragment = (SupportMapFragment) rootActivity.getSupportFragmentManager().findFragmentById(R.id.map_fragment);
                    mapFragment.getMapAsync(MapComponent.this);
                }, Manifest.permission.ACCESS_FINE_LOCATION);
            }
        }
    }

    private void setupViews(View view) {
        editTextSearch = (EditText) view.findViewById(R.id.edittext_search);
        editTextSearch.setOnEditorActionListener((v, actionId, event) ->
                {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH && !editTextSearch.getText().toString().isEmpty()) {
                        searchAddress(editTextSearch.getText().toString());
                    }
                    return false;
                }
        );
    }

    private void searchAddress(String search) {
        Toast.makeText(context, "Searching", Toast.LENGTH_SHORT).show();
        Geocoder geocoder = new Geocoder(context);
        try {
            List<android.location.Address> addresses = geocoder.getFromLocationName(search, ADDRESS_SERACH_MAX_RESULT);
            List<Address> result = new ArrayList<>();
            for (android.location.Address address : addresses) {
                result.add(mapGoogleAddress(address));
            }
            onMapActionListener.onSearch(result);
            editTextSearch.setText(null);
        } catch (IOException e) {
            Toast.makeText(context, "There are not matches with the description", Toast.LENGTH_SHORT).show();
        }
    }

    private Address mapGoogleAddress(android.location.Address address) {
        if (address.getMaxAddressLineIndex() <= 0) {
            return null;
        }
        return Address.builder()
                .lat(address.getLatitude())
                .lon(address.getLongitude())
                .title(address.getFeatureName())
                .address(address.getAddressLine(0))
                .city(address.getLocality())
                .country(address.getCountryName())
                .build();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        if (onMapActionListener != null) {
            googleMap.setOnMapClickListener(latLng -> onMapActionListener.onMapClick(latLng.latitude, latLng.longitude));
            googleMap.setOnMarkerClickListener(marker -> onMapActionListener.onMarkerClick(marker.getId()));
        }
    }

    /**
     * Add marker to the map
     *
     * @param lat
     * @param lon
     * @param title
     * @return the marker id added
     */
    public String addMarker(double lat, double lon, String title) {
        if (googleMap != null) {
            String snippet = lat + ", " + lon;
            try {
                android.location.Address address = new Geocoder(context).getFromLocation(lat, lon, 1).get(0);
                if (address.getMaxAddressLineIndex() > 0)
                    snippet = address.getAddressLine(0) + ", " + address.getCountryName() + " (" + address.getLocality() + ")";
            } catch (IOException e) {
                Log.e(getClass().getSimpleName(), e.getMessage());
            }
            Marker marker = googleMap.addMarker(new MarkerOptions().position(new LatLng(lat, lon)).snippet(snippet).title(title));
            markers.put(marker.getId(), marker);
            updateCameraPosition();
            return marker.getId();
        }
        return null;
    }

    private void updateCameraPosition() {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Marker marker : markers.values()) {
            builder.include(marker.getPosition());
        }
        LatLngBounds bounds = builder.build();
        int padding = ScreenSizeOperations.getPxFromDps(context, CAMERA_CENTER_PADDING); // offset from edges of the map in pixels
        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding));
    }

    public void removeMarker(String id) {
        markers.get(id).remove();
        markers.remove(id);
    }

    public interface OnMapActionListener {
        void onMapClick(double lat, double lng);

        boolean onMarkerClick(String id);

        void onSearch(List<MapComponent.Address> addresses);
    }

    @Data
    @Builder
    public static class Address {
        private double lat;
        private double lon;
        private String title;
        private String address;
        private String number = "s/n";
        private String city;
        private String country;
    }
}
