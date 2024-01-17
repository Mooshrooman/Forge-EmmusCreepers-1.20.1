package net.emmu.emmuscreepers.entity.client;

import net.emmu.emmuscreepers.EmmusCreepers;
import net.emmu.emmuscreepers.entity.custom.CheeseCreeperEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CreeperPowerLayer;
import net.minecraft.resources.ResourceLocation;

public class CheeseCreeperRenderer extends MobRenderer<CheeseCreeperEntity, CheeseCreeperModel<CheeseCreeperEntity>> {

    public CheeseCreeperRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new CheeseCreeperModel<>(pContext.bakeLayer(ModModelLayers.CHEESECREEPER_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(CheeseCreeperEntity pEntity) {
        return new ResourceLocation(EmmusCreepers.MOD_ID, "textures/entity/cheesecreeper.png");
    }
}
