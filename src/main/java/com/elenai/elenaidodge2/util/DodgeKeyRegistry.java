package com.elenai.elenaidodge2.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;

@OnlyIn(Dist.CLIENT)
public class DodgeKeyRegistry {
	List<KeyBinding> keys;

	public DodgeKeyRegistry()
	{
		this.keys = new ArrayList<KeyBinding>();
	}

	public DodgeKeyRegistry register(KeyBinding keyBinding)
	{
		if (!keys.contains(keyBinding))
		{
			this.keys.add(keyBinding);
			ClientRegistry.registerKeyBinding(keyBinding);
		}
		return this;
	}


}
