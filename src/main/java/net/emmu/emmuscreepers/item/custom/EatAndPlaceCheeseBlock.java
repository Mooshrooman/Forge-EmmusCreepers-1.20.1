package net.emmu.emmuscreepers.item.custom;

import net.emmu.emmuscreepers.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EatAndPlaceCheeseBlock extends Item {
    public EatAndPlaceCheeseBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        pLevel.setBlock(BlockPos.containing(pLivingEntity.getX(),pLivingEntity.getY()+3,pLivingEntity.getZ()), ModBlocks.CHEESE_BLOCK.get().defaultBlockState(), 3);
        return this.isEdible() ? pLivingEntity.eat(pLevel, pStack) : pStack;
    }
}
