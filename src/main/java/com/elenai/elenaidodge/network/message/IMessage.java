package com.elenai.elenaidodge.network.message;

import java.util.function.Supplier;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public interface IMessage<T>
{
    void encode(T message, PacketBuffer buffer);

    T decode(PacketBuffer buffer);

    void handle(T message, Supplier<NetworkEvent.Context> supplier);
}
