package com.elenai.elenaidodge2.integration;

import static codersafterdark.reskillable.lib.LibMisc.MOD_ID;

import com.elenai.elenaidodge2.ElenaiDodge2;
import com.elenai.elenaidodge2.ModConfig;
import com.elenai.elenaidodge2.util.ClientStorage;

import codersafterdark.reskillable.api.unlockable.Trait;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class ReskillableTraitDodge extends Trait {
	
    public ReskillableTraitDodge() {
    	super(new ResourceLocation(ElenaiDodge2.MODID, "dodge"), 4, 2, new ResourceLocation(MOD_ID, "agility"), 0, "");
    	 if (FMLCommonHandler.instance().getSide().isClient()) {
             MinecraftForge.EVENT_BUS.register(this);
         }
    }

    @Override
    public void onUnlock(EntityPlayer player) {
    	if(ModConfig.client.hud.tutorial) {
    	ClientStorage.shownTutorial = false;
		ClientStorage.tutorialDodges = 0;
    	}
    }
    
}
