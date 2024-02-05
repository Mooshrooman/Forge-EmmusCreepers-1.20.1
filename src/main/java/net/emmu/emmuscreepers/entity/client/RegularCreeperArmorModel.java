package net.emmu.emmuscreepers.entity.client;// Made with Blockbench 4.9.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.emmu.emmuscreepers.entity.animations.ModAnimationsDefinitions;
import net.emmu.emmuscreepers.entity.custom.CheeseCreeperEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class RegularCreeperArmorModel<T extends Entity> extends HierarchicalModel<CheeseCreeperEntity> {
	private final ModelPart creeperarmormodel;
	private final ModelPart head;

	public RegularCreeperArmorModel(ModelPart root) {
		this.creeperarmormodel = root.getChild("creeperarmormodel");
		this.head = root.getChild("creeperarmormodel").getChild("body").getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition creeperarmormodel = partdefinition.addOrReplaceChild("creeperarmormodel", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = creeperarmormodel.addOrReplaceChild("body", CubeListBuilder.create().texOffs(12, 12).addBox(-6.0F, -20.0F, -4.0F, 12.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(-4, -4).addBox(-6.0F, -10.0F, -6.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -18.0F, 0.0F));

		PartDefinition leg0and1 = creeperarmormodel.addOrReplaceChild("leg0and1", CubeListBuilder.create(), PartPose.offset(2.0F, -6.0F, 4.0F));

		PartDefinition leg0 = leg0and1.addOrReplaceChild("leg0", CubeListBuilder.create().texOffs(-4, 12).addBox(-3.0F, -3.0F, -4.0F, 7.0F, 9.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leg1 = leg0and1.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(-4, 12).addBox(-4.0F, -3.0F, -4.0F, 7.0F, 9.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 0.0F, 0.0F));

		PartDefinition leg2and3 = creeperarmormodel.addOrReplaceChild("leg2and3", CubeListBuilder.create(), PartPose.offset(-2.0F, -6.0F, 4.0F));

		PartDefinition leg2 = leg2and3.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(-4, 12).addBox(-4.0F, -3.0F, -4.0F, 8.0F, 9.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 0.0F, -8.0F));

		PartDefinition leg3 = leg2and3.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(-4, 12).addBox(-4.0F, -3.0F, -4.0F, 8.0F, 9.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -8.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		creeperarmormodel.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return creeperarmormodel;
	}

	@Override
	public void setupAnim(@NotNull CheeseCreeperEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		root().getAllParts().forEach(ModelPart::resetPose);
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch * ((float)Math.PI / 180F);
		animate(entity.idleAnimationState, ModAnimationsDefinitions.IDLE, ageInTicks, 1.6F);
		animate(entity.swellAnimationState, ModAnimationsDefinitions.SWELL, ageInTicks, 0.95F);

		if(entity.isAggressive() && entity.isPathFinding()) {
			animateWalk(ModAnimationsDefinitions.RUN, limbSwing, limbSwingAmount,
					4.0f, 2.5f);
		}

		if(!entity.isInWaterOrBubble() && !entity.isAggressive()) {
			animateWalk(ModAnimationsDefinitions.WALK, limbSwing, limbSwingAmount,
					4.0f,2.5f);
		}else{
			animateWalk(ModAnimationsDefinitions.RUN, limbSwing, limbSwingAmount,
					4.0f, 2.5f);
		}

	}
}