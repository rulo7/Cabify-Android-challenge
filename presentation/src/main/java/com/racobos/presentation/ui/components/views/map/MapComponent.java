package com.racobos.presentation.ui.components.views.map;

import android.Manifest;
import android.content.Context;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
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
import java.util.HashMap;
import java.util.List;

import lombok.Data;
import lombok.experimental.Builder;

/**
 * Created by rulo7 on 09/10/2016.
 */
@Trait
public class MapComponent implements ViewComponent, OnMapReadyCallback {

    private static final int CAMERA_CENTER_PADDING = 100;

    private AppCompatActivity appCompatActivity;
    private GoogleMap googleMap;
    private GoogleApiClient googleApiClient;
    private PlaceAutocompleteAdapter placeAutocompleteAdapter;
    private OnMapActionListener onMapActionListener;
    private HashMap<String, Marker> markers = new HashMap<>();
    private AutoCompleteTextView autocompleteTextSearch;
    private ImageView iconSearch;

    public MapComponent(AppCompatActivity appCompatActivity, OnMapActionListener onMapActionListener) {
        this.appCompatActivity = appCompatActivity;
        this.onMapActionListener = onMapActionListener;
    }

    @Override
    public void initialize() {
        if (appCompatActivity != null) {
            ViewGroup holderView = (ViewGroup) appCompatActivity.findViewById(R.id.map_component);
            if (holderView != null) {
                PermissionManager.requestMultiplePermissions(holderView, () -> {
                    View view = LayoutInflater.from(appCompatActivity).inflate(R.layout.map_component_view, holderView, false);
                    setupViews(view);
                    holderView.addView(view);
                    MapFragment mapFragment =
                            (MapFragment) appCompatActivity.getFragmentManager().findFragmentById(R.id.map_fragment);
                    mapFragment.getMapAsync(MapComponent.this);
                }, Manifest.permission.ACCESS_FINE_LOCATION);
            }
        }
    }

    private void setupViews(View view) {
        autocompleteTextSearch = (AutoCompleteTextView) view.findViewById(R.id.edittext_search);
        iconSearch = (ImageView) view.findViewById(R.id.imageview_icon_search);
        googleApiClient = new GoogleApiClient.Builder(appCompatActivity)
                .enableAutoManage(appCompatActivity, connectionResult -> {
                    autocompleteTextSearch.setVisibility(View.GONE);
                    Log.e(getClass().getSimpleName(), "Google api client connnection failed");
                })
                .addApi(Places.GEO_DATA_API)
                .build();
        placeAutocompleteAdapter = new PlaceAutocompleteAdapter(appCompatActivity, googleApiClient, null, null);
        autocompleteTextSearch.setAdapter(placeAutocompleteAdapter);
        autocompleteTextSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final AutocompletePrediction item = placeAutocompleteAdapter.getItem(position);
                final String placeId = item.getPlaceId();
                PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                        .getPlaceById(googleApiClient, placeId);
                placeResult.setResultCallback(new ResultCallback<PlaceBuffer>() {
                    @Override
                    public void onResult(@NonNull PlaceBuffer places) {
                        if (!places.getStatus().isSuccess()) {
                            Log.e(getClass().getSimpleName(), "Place query did not complete. Error: " + places.getStatus().toString());
                        } else if (places.getCount() > 0) {
                            onMapActionListener.onSearch(mapGoogleAddress(places.get(0)));
                        }
                        places.release();
                        autocompleteTextSearch.clearFocus();
                        hideKeyBoard();
                    }
                });
            }
        });
        autocompleteTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()) {
                    iconSearch.setImageResource(android.R.drawable.ic_menu_search);
                    iconSearch.setOnClickListener(null);
                } else {
                    iconSearch.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
                    iconSearch.setOnClickListener(v -> {
                        autocompleteTextSearch.setText(null);
                        autocompleteTextSearch.clearFocus();
                        hideKeyBoard();
                    });
                }
            }

            //<editor-fold desc="unused methods">
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
            //</editor-fold>
        });
    }

    private void hideKeyBoard() {
        View view = appCompatActivity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) appCompatActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private Address mapGoogleAddress(Place place) {
        if (place == null) {
            return null;
        }
        return Address.builder()
                .lat(place.getLatLng().latitude)
                .lon(place.getLatLng().longitude)
                .title(place.getName().toString())
                .address(place.getAddress().toString())
                .country(place.getLocale().getCountry())
                .build();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        if (onMapActionListener != null) {
            googleMap.setOnMapClickListener(
                    latLng -> onMapActionListener.onMapClick(latLng.latitude, latLng.longitude));
            googleMap.setOnMarkerClickListener(marker -> onMapActionListener.onMarkerClick(marker.getId()));
        }
    }

    /**
     * Add marker to the map
     *
     * @return the marker id added
     */
    public String addMarker(double lat, double lon, String title, String snippet) {
        if (googleMap != null) {
            if (snippet == null) {
                try {
                    List<android.location.Address> addresses = new Geocoder(appCompatActivity).getFromLocation(lat, lon, 1);
                    if (addresses.size() > 0) {
                        android.location.Address address = addresses.get(0);
                        if (address.getMaxAddressLineIndex() > 0) {
                            snippet = address.getAddressLine(0);
                            if (address.getCountryName() != null) {
                                snippet += ", " + address.getCountryName();
                            }
                            if (address.getLocality() != null) {
                                snippet += " (" + address.getLocality() + ")";
                            }
                        }
                    }
                } catch (IOException e) {
                    Log.e(getClass().getSimpleName(), e.getMessage());
                }
            }
            Marker marker = googleMap.addMarker(
                    new MarkerOptions().position(new LatLng(lat, lon)).snippet(snippet).title(title));
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
        int padding = ScreenSizeOperations.getPxFromDps(appCompatActivity,
                CAMERA_CENTER_PADDING); // offset from edges of the map in pixels
        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding));
    }

    public void removeMarker(String id) {
        markers.get(id).remove();
        markers.remove(id);
    }

    public interface OnMapActionListener {
        void onMapClick(double lat, double lng);

        boolean onMarkerClick(String id);

        void onSearch(MapComponent.Address addresses);
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
