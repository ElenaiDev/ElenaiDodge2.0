package com.elenai.elenaidodge.item;
import java.util.List;

import javax.annotation.Nullable;

import com.elenai.elenaidodge.ElenaiDodge;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBase extends Item {
	
	public ItemBase(String name){
		super();
		setRegistryName(name);
		setUnlocalizedName(ElenaiDodge.MODID + "." + name);
		GameRegistry.findRegistry(Item.class).register(this);
	}
	
	public void initModel(){
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName().toString()));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		super.addInformation(stack, worldIn, tooltip, flagIn);
    }

}
