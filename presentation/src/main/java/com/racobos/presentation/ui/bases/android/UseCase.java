package com.racobos.presentation.ui.bases.android;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by rulo7 on 07/10/2016.
 * based on https://github.com/Karumi/Rosie/blob/master/rosie/src/main/java/com/karumi/rosie/view/Presenter.java
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UseCase {
}
