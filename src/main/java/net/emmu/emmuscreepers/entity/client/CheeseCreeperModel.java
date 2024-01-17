package net.emmu.emmuscreepers.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.emmu.emmuscreepers.entity.animations.ModAnimationsDefinitions;
import net.emmu.emmuscreepers.entity.custom.CheeseCreeperEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class CheeseCreeperModel<T extends Entity> extends HierarchicalModel<CheeseCreeperEntity> {
	private final ModelPart cheesecreeper;
	private final ModelPart head;

	public CheeseCreeperModel(ModelPart root) {
		this.cheesecreeper = root.getChild("cheesecreeper");
		this.head = root.getChild("cheesecreeper").getChild("body").getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition cheesecreeper = partdefinition.addOrReplaceChild("cheesecreeper", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = cheesecreeper.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 0.0F));

		PartDefinition leg0and1 = cheesecreeper.addOrReplaceChild("leg0and1", CubeListBuilder.create(), PartPose.offset(-2.0F, -6.0F, -2.0F));

		PartDefinition leg0 = leg0and1.addOrReplaceChild("leg0", CubeListBuilder.create().texOffs(32, 0).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 0.0F, 4.0F));

		PartDefinition leg1 = leg0and1.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(24, 16).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

		PartDefinition leg2and3 = cheesecreeper.addOrReplaceChild("leg2and3", CubeListBuilder.create(), PartPose.offset(2.0F, -6.0F, -2.0F));

		PartDefinition leg2 = leg2and3.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, 0.0F, -4.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leg3 = leg2and3.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(24, 26).addBox(-2.0F, 0.0F, -4.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(@NotNull CheeseCreeperEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		root().getAllParts().forEach(ModelPart::resetPose);
		animate(entity.idleAnimationState, ModAnimationsDefinitions.IDLE, ageInTicks, 1.0F);

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

	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		cheesecreeper.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return cheesecreeper;
	}

}