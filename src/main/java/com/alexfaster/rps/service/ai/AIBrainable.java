package com.alexfaster.rps.service.ai;

import com.alexfaster.rps.model.Choice;
import com.alexfaster.rps.model.Player;

public interface AIBrainable {

    Choice makeTurn(final Player player);

}
