package net.emmu.emmuscreepers.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.emmu.emmuscreepers.EmmusCreepers;
import net.emmu.emmuscreepers.entity.custom.CheeseCreeperEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.Creeper;

public class CheeseCreeperRenderer extends MobRenderer<CheeseCreeperEntity, CheeseCreeperModel<CheeseCreeperEntity>> {

    public CheeseCreeperRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new CheeseCreeperModel<>(pContext.bakeLayer(ModModelLayers.CHEESECREEPER_LAYER)), 0.5f);
    }

    protected void scale(CheeseCreeperEntity pLivingEntity, PoseStack pPoseStack, float pPartialTickTime) {
        float f = pLivingEntity.getSwelling(pPartialTickTime);
        float f1 = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
        f = Mth.clamp(f, 0.0F, 1.0F);
        f *= f;
        f *= f;
        float f2 = (1.0F + f * 0.4F) * f1;
        float f3 = (1.0F + f * 0.1F) / f1;
        pPoseStack.scale(f2, f3, f2);
    }

    protected float getWhiteOverlayProgress(CheeseCreeperEntity pLivingEntity, float pPartialTicks) {
        float f = pLivingEntity.getSwelling(pPartialTicks);
        return (int)(f * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(f, 0.5F, 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(CheeseCreeperEntity pEntity) {
        return new ResourceLocation(EmmusCreepers.MOD_ID, "textures/entity/cheesecreeper.png");
    }
}
