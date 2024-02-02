package net.emmu.emmuscreepers.entity.custom;

import net.emmu.emmuscreepers.block.ModBlocks;
import net.emmu.emmuscreepers.entity.ai.CheeseCreepersSwellGoal;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.Random;

public class CheeseCreeperEntity extends Creeper implements PowerableMob {
    private int explosionRadius = 3;
    private int swell;
    private int swell2;
    private int maxSwell = 30;
    public static final EntityDataAccessor<Integer> SWELLNUMBER = SynchedEntityData.defineId(CheeseCreeperEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> DATA_IS_POWERED = SynchedEntityData.defineId(CheeseCreeperEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_IS_IGNITED = SynchedEntityData.defineId(CheeseCreeperEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> SWELLING = SynchedEntityData.defineId(CheeseCreeperEntity.class, EntityDataSerializers.BOOLEAN);
    public CheeseCreeperEntity(EntityType<? extends Creeper> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public final AnimationState idleAnimationState = new AnimationState();

    public final AnimationState swellAnimationState = new AnimationState();
    private int swellAnimationTimeout = 0;

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.25D);
    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new CheeseCreepersSwellGoal(this));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Ocelot.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Cat.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }

    @Override
    public void tick() {
        if (level().isClientSide) {
            this.idleAnimationState.animateWhen(!isInWaterOrBubble() && !this.walkAnimation.isMoving(), this.tickCount);
            setupAnimationStates();
        }
            if (this.isAlive()) {
                    if (this.isIgnited()) {
                        this.setSwellNumber(1);
                    }

                    int i = this.getSwellNumber();
                    if (i > 0 && this.swell == 0) {
                        this.playSound(SoundEvents.CREEPER_PRIMED, 1.0F, 0.5F);
                        this.gameEvent(GameEvent.PRIME_FUSE);
                    }

                    this.swell += i;
                    if (this.swell < 0) {
                        this.swell = 0;
                    }

                    if (this.swell >= this.maxSwell) {
                        this.swell = this.maxSwell;
                        this.explodeCreeper();
                        if (!level().isClientSide && !this.entityData.get(DATA_IS_POWERED)){
                            int numberOfIterations = 0;
                            while (numberOfIterations <= 7) {
                                level().setBlock(blockPosition().offset(randomXPos(), randomYPos(), randomZPos()), ModBlocks.CHEESE_BLOCK.get().defaultBlockState(), 3);
                                ++numberOfIterations;
                            }
                        }else if (!level().isClientSide && this.entityData.get(DATA_IS_POWERED)){
                            int numberOfIterations = 0;
                            while (numberOfIterations <= 25) {
                                level().setBlock(blockPosition().offset(randomChargedPos(), randomYPos(), randomChargedPos()), ModBlocks.CHEESE_BLOCK.get().defaultBlockState(), 3);
                                ++numberOfIterations;
                            }
                        }
                    }
            }
        super.tick();

    }

    public int randomXPos() {
        Random randomX = new Random();
        return 2 - randomX.nextInt(4);
    }

    public int randomYPos() {
        Random randomY = new Random();
        return (randomY.nextInt(3) + 1) * -1;
    }

    public int randomZPos() {
        Random randomZ = new Random();
        return 2 - randomZ.nextInt(4);
    }

    public int randomChargedPos() {
        Random randomX = new Random();
        return 4 - randomX.nextInt(8);
    }

    public int getSwellNumber() {
        return this.entityData.get(SWELLNUMBER);
    }


    private void explodeCreeper() {
        if (!level().isClientSide) {
            float f = this.isPowered() ? 2.0F : 1.0F;
            this.dead = true;
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), (float)this.explosionRadius * f, Level.ExplosionInteraction.MOB);
            this.discard();
        }

    }

    public void setSwellNumber(int swellNumber) {
        this.entityData.set(SWELLNUMBER, swellNumber);
    }

    private void setupAnimationStates() {
        if(this.isSwelling() && swellAnimationTimeout <= 0) {
            swellAnimationTimeout = 30; // Length in ticks of your animation
            swellAnimationState.start(this.tickCount);
        } else {
            --this.swellAnimationTimeout;
        }

        if(!this.isSwelling()) {
            swellAnimationState.stop();
        }
    }

    public float getSwelling(float pPartialTicks) {
        return Mth.lerp(pPartialTicks, (float)this.swell2, (float)this.swell) / (float)(this.maxSwell - 2);
    }

    public void setSwelling(boolean swelling) {
        this.entityData.set(SWELLING, swelling);
    }

    public boolean isSwelling() {
        return this.entityData.get(SWELLING);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SWELLING, false);
        this.entityData.define(DATA_IS_POWERED, false);
        this.entityData.define(DATA_IS_IGNITED, false);
        this.entityData.define(SWELLNUMBER, 0);
    }
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        if (this.entityData.get(DATA_IS_POWERED)) {
            pCompound.putBoolean("powered", true);
        }

        pCompound.putByte("ExplosionRadius", (byte)this.explosionRadius);
        pCompound.putBoolean("ignited", this.isIgnited());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.entityData.set(DATA_IS_POWERED, pCompound.getBoolean("powered"));

        if (pCompound.contains("ExplosionRadius", 99)) {
            this.explosionRadius = pCompound.getByte("ExplosionRadius");
        }

        if (pCompound.getBoolean("ignited")) {
            this.ignite();
        }

    }
    public boolean isPowered() {
        return this.entityData.get(DATA_IS_POWERED);
    }

    public void thunderHit(ServerLevel pLevel, LightningBolt pLightning) {
        super.thunderHit(pLevel, pLightning);
        this.entityData.set(DATA_IS_POWERED, true);
    }

    protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (itemstack.is(ItemTags.CREEPER_IGNITERS)) {
            SoundEvent soundevent = itemstack.is(Items.FIRE_CHARGE) ? SoundEvents.FIRECHARGE_USE : SoundEvents.FLINTANDSTEEL_USE;
            this.level().playSound(pPlayer, this.getX(), this.getY(), this.getZ(), soundevent, this.getSoundSource(), 1.0F, this.random.nextFloat() * 0.4F + 0.8F);
            if (!this.level().isClientSide) {
                this.ignite();
                if (!itemstack.isDamageableItem()) {
                    itemstack.shrink(1);
                } else {
                    itemstack.hurtAndBreak(1, pPlayer, (p_32290_) -> {
                        p_32290_.broadcastBreakEvent(pHand);
                    });
                }
            }

            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else {
            return super.mobInteract(pPlayer, pHand);
        }
    }
    public boolean isIgnited() {
        return this.entityData.get(DATA_IS_IGNITED);
    }

    public void ignite() {
        this.entityData.set(DATA_IS_IGNITED, true);
    }
}
