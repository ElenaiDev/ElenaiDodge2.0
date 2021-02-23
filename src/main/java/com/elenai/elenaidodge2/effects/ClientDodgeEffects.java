package com.elenai.elenaidodge2.effects;

import com.elenai.elenaidodge2.gui.DodgeStep;
import com.elenai.elenaidodge2.util.ClientStorage;

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
