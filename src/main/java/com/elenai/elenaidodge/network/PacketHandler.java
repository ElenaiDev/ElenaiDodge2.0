package com.elenai.elenaidodge.network;

import com.elenai.elenaidodge.ElenaiDodge;
import com.elenai.elenaidodge.network.message.CDodgeEffectsMessage;
import com.elenai.elenaidodge.network.message.CInitPlayerMessage;
import com.elenai.elenaidodge.network.message.CParticleMessage;
import com.elenai.elenaidodge.network.message.CUpdateConfigMessage;
import com.elenai.elenaidodge.network.message.CUpdateStatsMessage;
import com.elenai.elenaidodge.network.message.CVelocityMessage;
import com.elenai.elenaidodge.network.message.IMessage;
import com.elenai.elenaidodge.network.message.SDodgeMessage;
import com.elenai.elenaidodge.network.message.SDodgeRegenMessage;
import com.elenai.elenaidodge.network.message.SWeightMessage;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    private static int nextId = 0;
    public static SimpleChannel instance;

    public static void init()
    {
        instance = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(ElenaiDodge.MODID, "network"))
                .networkProtocolVersion(() -> PROTOCOL_VERSION)
                .clientAcceptedVersions(PROTOCOL_VERSION::equals)
                .serverAcceptedVersions(PROTOCOL_VERSION::equals)
                .simpleChannel();

        register(SDodgeMessage.class, new SDodgeMessage());
        register(SDodgeRegenMessage.class, new SDodgeRegenMessage());
        register(SWeightMessage.class, new SWeightMessage());
        
        register(CVelocityMessage.class, new CVelocityMessage());
        register(CDodgeEffectsMessage.class, new CDodgeEffectsMessage());
        register(CUpdateConfigMessage.class, new CUpdateConfigMessage());
        register(CInitPlayerMessage.class, new CInitPlayerMessage());
        register(CUpdateStatsMessage.class, new CUpdateStatsMessage());
        register(CParticleMessage.class, new CParticleMessage());

    }

    private static <T> void register(Class<T> clazz, IMessage<T> message)
    {
        instance.registerMessage(nextId++, clazz, message::encode, message::decode, message::handle);
    }
}
