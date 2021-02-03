package com.elenai.elenaidodge.util;

import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;

public class ModAttributes {

    public static final IAttribute MAX_DODGES = (new RangedAttribute((IAttribute)null, "generic.maxDodges", 20.0D, Float.MIN_VALUE, 1024.0D)).setDescription("Max Dodges").setShouldWatch(true); 

	
}
