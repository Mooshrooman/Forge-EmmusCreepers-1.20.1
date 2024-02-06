package net.emmu.emmuscreepers.block.custom;

import net.emmu.emmuscreepers.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;


public class CheeseBlock extends Block {
    int ageInTicks = 0;

    public CheeseBlock(Properties pProperties) {
        super(pProperties);
        ageInTicks = 0;
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource rand) {
       ageInTicks++;
       if (ageInTicks >= 45){
           world.setBlock(pos, ModBlocks.MOLDY_CHEESE_BLOCK.get().defaultBlockState(), 3);
       }
    }
}



