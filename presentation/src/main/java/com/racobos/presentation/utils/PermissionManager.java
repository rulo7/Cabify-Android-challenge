package com.racobos.presentation.utils;

import android.view.ViewGroup;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.CompositeMultiplePermissionsListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.multi.SnackbarOnAnyDeniedMultiplePermissionsListener;
import com.racobos.domain.R;

import java.util.List;

/**
 * Created by rulo7 on 08/10/2016.
 */

public final class PermissionManager {
    public static void requestMultiplePermissions(ViewGroup rootView,
                                                  final OnAllPermissionsGrantedListener onAllPermissionsGrantedListener, String... permissions) {
        MultiplePermissionsListener multiplePermissionsListener = new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                if (report.areAllPermissionsGranted()) {
                    onAllPermissionsGrantedListener.onAllPermissionsGranted();
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                token.continuePermissionRequest();
            }
        };
        MultiplePermissionsListener deniedMultiplePermissionsListener =
                SnackbarOnAnyDeniedMultiplePermissionsListener.Builder.
                        with(rootView, R.string.necessary_multiple_permissions)
                        .withOpenSettingsButton("Settings")
                        .build();
        Dexter.checkPermissions(new CompositeMultiplePermissionsListener(multiplePermissionsListener,
                deniedMultiplePermissionsListener), permissions);
    }

    public interface OnAllPermissionsGrantedListener {
        void onAllPermissionsGranted();
    }
}
