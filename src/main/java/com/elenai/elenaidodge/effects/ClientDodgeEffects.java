package com.elenai.elenaidodge.effects;

import com.elenai.elenaidodge.gui.DodgeStep;
import com.elenai.elenaidodge.util.ClientStorage;

public class ClientDodgeEffects {
	
	/**
	 * Runs the Client Dodge Effects
	 * @param dodges
	 * @param dodgeCost
	 * @side Client
	 */
	public static void run(int dodges, int absorption) {
		ClientStorage.absorption = absorption;
		ClientStorage.dodges = dodges;
		if(ClientStorage.tutorialDodges < 1) {
		ClientStorage.tutorialDodges+=0.25;
		DodgeStep.moveToast.setProgress((float)ClientStorage.tutorialDodges);
		}
	}
	
}
