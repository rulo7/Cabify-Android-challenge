package com.racobos.presentation.ui.components.views.map;

import android.Manifest;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.racobos.domain.R;
import com.racobos.presentation.ui.components.views.ViewComponent;
import com.racobos.presentation.utils.PermissionManager;
import com.txusballesteros.mara.Trait;

import java.util.HashMap;

/**
 * Created by rulo7 on 09/10/2016.
 */
@Trait
public class MapComponent implements ViewComponent, OnMapReadyCallback {

    private Context context;
    private GoogleMap googleMap;
    private OnMapClickListener onMapClickListener;
    private OnMarkersClickListener onMarkersClickListener;
    private HashMap<String, Marker> markers = new HashMap<>();

    public MapComponent(Context context, OnMapClickListener onMapClickListener, OnMarkersClickListener onMarkersClickListener) {
        this.context = context;
        this.onMapClickListener = onMapClickListener;
        this.onMarkersClickListener = onMarkersClickListener;
    }

    @Override
    public void initialize() {
        if (context instanceof AppCompatActivity) {
            AppCompatActivity rootActivity = (AppCompatActivity) context;
            ViewGroup holderView = (ViewGroup) rootActivity.findViewById(R.id.map_component);
            if (holderView != null) {
                PermissionManager.requestMultiplePermissions(holderView, () -> {
                    holderView.addView(LayoutInflater.from(context).inflate(R.layout.map_component_view, holderView, false));
                    MapFragment mapFragment = (MapFragment) rootActivity.getFragmentManager().findFragmentById(R.id.map_fragment);
                    mapFragment.getMapAsync(MapComponent.this);
                }, Manifest.permission.ACCESS_FINE_LOCATION);
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        if (onMapClickListener != null) {
            googleMap.setOnMapClickListener(latLng -> onMapClickListener.onMapClick(latLng.latitude, latLng.longitude));
        }
        if (onMarkersClickListener != null) {
            googleMap.setOnMarkerClickListener(marker -> onMarkersClickListener.onMarkerClick(marker.getId()));
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
            Marker marker = googleMap.addMarker(new MarkerOptions().position(new LatLng(lat, lon)).title(title));
            markers.put(marker.getId(), marker);
            return marker.getId();
        }
        return null;
    }

    public void removeMarker(String id) {
        markers.get(id).remove();
        markers.remove(id);
    }


    public interface OnMapClickListener {
        void onMapClick(double lat, double lng);
    }

    public interface OnMarkersClickListener {
        boolean onMarkerClick(String id);
    }
}
