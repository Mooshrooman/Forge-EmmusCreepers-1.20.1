package net.emmu.emmuscreepers.item.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MeltyCheeseItem extends Item {
    public MeltyCheeseItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        pLivingEntity.setSecondsOnFire(5);
        return this.isEdible() ? pLivingEntity.eat(pLevel, pStack) : pStack;
    }
}
