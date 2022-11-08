package com.elenai.elenaidodge2.capability;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

public class PlayerInvincibilityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
	public static Capability<InvincibleCapability> PLAYER_INVINCIBILITY = CapabilityManager
			.get(new CapabilityToken<InvincibleCapability>() {
			});

	private InvincibleCapability invincible = null;
	private final LazyOptional<InvincibleCapability> optional = LazyOptional.of(this::createInvincibility);

	private InvincibleCapability createInvincibility() {
		if (this.invincible == null) {
			this.invincible = new InvincibleCapability();
		}

		return this.invincible;
	}

	public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
		if (cap == PLAYER_INVINCIBILITY) {
			return optional.cast();
		}

		return LazyOptional.empty();
	}

	public CompoundTag serializeNBT() {
		CompoundTag nbt = new CompoundTag();
		createInvincibility().saveNBTData(nbt);
		return nbt;
	}

	public void deserializeNBT(CompoundTag nbt) {
		createInvincibility().loadNBTData(nbt);
	}
}
