package net.emmu.emmuscreepers.entity.client;

import net.emmu.emmuscreepers.EmmusCreepers;
import net.emmu.emmuscreepers.entity.custom.CheeseCreeperEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CheeseCreeperPowerLayer extends EnergySwirlLayer{
        private static final ResourceLocation CHEESE_CREEPER_ARMOR_LOCATION = new ResourceLocation (EmmusCreepers.MOD_ID, "textures/entity/cheesecreeperarmor.png");
        private final RegularCreeperArmorModel<CheeseCreeperEntity> model;

        public CheeseCreeperPowerLayer(RenderLayerParent<CheeseCreeperEntity, CheeseCreeperModel<CheeseCreeperEntity>> pRenderer, EntityModelSet pModelSet) {
                super(pRenderer);
                this.model = new RegularCreeperArmorModel<>(pModelSet.bakeLayer(ModModelLayers.REGULAR_CREEPER_ARMOR_LAYER));
        }

        @Override
        protected float xOffset(float pTickCount) {
                return pTickCount * 0.02F;
        }

        @Override
        protected ResourceLocation getTextureLocation() {
                return CHEESE_CREEPER_ARMOR_LOCATION;
        }

        @Override
        protected EntityModel model() {
                return this.model;
        }
}
