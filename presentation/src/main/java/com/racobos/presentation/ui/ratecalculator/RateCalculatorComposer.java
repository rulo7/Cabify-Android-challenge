package com.racobos.presentation.ui.ratecalculator;

import com.racobos.presentation.ui.components.views.map.MapComponent;
import com.racobos.presentation.ui.components.views.progressbar.ProgressBarComponent;
import com.racobos.presentation.ui.components.views.toolbar.ToolbarComponent;
import com.txusballesteros.mara.TraitComposer;

/**
 * Created by rulo7 on 08/10/2016.
 */

@TraitComposer(traits = {ToolbarComponent.class, ProgressBarComponent.class, MapComponent.class})
public interface RateCalculatorComposer {
}
