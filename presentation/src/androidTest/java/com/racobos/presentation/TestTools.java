package com.racobos.presentation;

import android.os.Build;
import android.os.ParcelFileDescriptor;

import java.io.IOException;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.InstrumentationRegistry.getTargetContext;

/**
 * Created by rulo7 on 18/10/2016.
 */

public class TestTools {
    public static void acceptPermissions(String permission) throws IOException {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ParcelFileDescriptor parcelFileDescriptor = getInstrumentation().getUiAutomation().executeShellCommand(
                    "pm grant " + getTargetContext().getPackageName()
                            + permission);
            parcelFileDescriptor.close();
        }
    }
}
