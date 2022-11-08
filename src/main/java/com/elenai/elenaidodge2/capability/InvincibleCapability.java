package com.elenai.elenaidodge2.capability;

import com.elenai.elenaidodge2.config.ED2CommonConfig;

import net.minecraft.nbt.CompoundTag;

public class InvincibleCapability {
	private int invincibility = 0;
	private int maxInvincibility = ED2CommonConfig.INVINCIBILITY_TICKS.get();
	private final int MIN_INVINCIBILITY = 0;

	public int getInvincibility() {
		return invincibility;
	}

	public void setInvincibility(int invincibility) {
		this.invincibility = invincibility;
	}

	public void addInvincibility(int invincibility) {
		this.invincibility = Math.min(this.invincibility + invincibility, maxInvincibility);
	}

	public void subInvincibility(int invincibility) {
		this.invincibility = Math.max(this.invincibility - invincibility, MIN_INVINCIBILITY);
	}

	public void copyFrom(InvincibleCapability source) {
		this.invincibility = source.invincibility;
	}

	public void saveNBTData(CompoundTag nbt) {
		nbt.putInt("invincibility", this.invincibility);
	}

	public void loadNBTData(CompoundTag nbt) {
		this.invincibility = nbt.getInt("invincibility");
	}
}
