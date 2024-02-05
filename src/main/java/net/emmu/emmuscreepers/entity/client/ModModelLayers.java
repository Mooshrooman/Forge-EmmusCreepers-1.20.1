package net.emmu.emmuscreepers.entity.client;

import net.emmu.emmuscreepers.EmmusCreepers;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static final ModelLayerLocation CHEESECREEPER_LAYER = new ModelLayerLocation(
            new ResourceLocation(EmmusCreepers.MOD_ID, "cheesecreeper_layer"), "main");
    public static final ModelLayerLocation REGULAR_CREEPER_ARMOR_LAYER = new ModelLayerLocation(
            new ResourceLocation(EmmusCreepers.MOD_ID, "reg_creeper_armor_layer"), "main");
}
