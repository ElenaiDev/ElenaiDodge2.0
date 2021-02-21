package com.elenai.elenaidodge.network;

import com.elenai.elenaidodge.network.message.CDodgeEffectsMessage;
import com.elenai.elenaidodge.network.message.CInitPlayerMessage;
import com.elenai.elenaidodge.network.message.CParticleMessage;
import com.elenai.elenaidodge.network.message.CPatronMessage;
import com.elenai.elenaidodge.network.message.CUpdateAbsorptionMessage;
import com.elenai.elenaidodge.network.message.CUpdateConfigMessage;
import com.elenai.elenaidodge.network.message.CUpdateDodgeMessage;
import com.elenai.elenaidodge.network.message.CUpdateWeightMessage;
import com.elenai.elenaidodge.network.message.CVelocityMessage;
import com.elenai.elenaidodge.network.message.SDodgeMessage;
import com.elenai.elenaidodge.network.message.SDodgeRegenMessage;
import com.elenai.elenaidodge.network.message.SThirstMessage;
import com.elenai.elenaidodge.network.message.SWeightMessage;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {

	private static int nextId = 0;
	public static SimpleNetworkWrapper instance;

	public static void registerMessages(String channelName) {
		instance = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);
		
		// Server Side Logic
		instance.registerMessage(SDodgeMessage.Handler.class, SDodgeMessage.class, nextId++, Side.SERVER);
		instance.registerMessage(SDodgeRegenMessage.Handler.class, SDodgeRegenMessage.class, nextId++, Side.SERVER);
		instance.registerMessage(SWeightMessage.Handler.class, SWeightMessage.class, nextId++, Side.SERVER);
		instance.registerMessage(SThirstMessage.Handler.class, SThirstMessage.class, nextId++, Side.SERVER);

		
		// Client Side Logic
		instance.registerMessage(CVelocityMessage.Handler.class, CVelocityMessage.class, nextId++, Side.CLIENT);
		instance.registerMessage(CDodgeEffectsMessage.Handler.class, CDodgeEffectsMessage.class, nextId++, Side.CLIENT);
		instance.registerMessage(CUpdateConfigMessage.Handler.class, CUpdateConfigMessage.class, nextId++, Side.CLIENT);
		instance.registerMessage(CInitPlayerMessage.Handler.class, CInitPlayerMessage.class, nextId++, Side.CLIENT);
		instance.registerMessage(CUpdateAbsorptionMessage.Handler.class, CUpdateAbsorptionMessage.class, nextId++, Side.CLIENT);
		instance.registerMessage(CUpdateWeightMessage.Handler.class, CUpdateWeightMessage.class, nextId++, Side.CLIENT);
		instance.registerMessage(CUpdateDodgeMessage.Handler.class, CUpdateDodgeMessage.class, nextId++, Side.CLIENT);
		instance.registerMessage(CParticleMessage.Handler.class, CParticleMessage.class, nextId++, Side.CLIENT);
		instance.registerMessage(CPatronMessage.Handler.class, CPatronMessage.class, nextId++, Side.CLIENT);



	}	
}
