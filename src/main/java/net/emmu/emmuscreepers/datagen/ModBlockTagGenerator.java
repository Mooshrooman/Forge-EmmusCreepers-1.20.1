package net.emmu.emmuscreepers.datagen;

import net.emmu.emmuscreepers.EmmusCreepers;
import net.emmu.emmuscreepers.block.ModBlocks;
import net.emmu.emmuscreepers.item.custom.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, EmmusCreepers.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.Blocks.CHEESE)
                .add(ModBlocks.CHEESE_BLOCK.get())
                .add(ModBlocks.MOLDY_CHEESE_BLOCK.get());

        this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.CHEESE_BLOCK.get())
                .add(ModBlocks.MOLDY_CHEESE_BLOCK.get());

        this.tag(BlockTags.NEEDS_STONE_TOOL);
        this.tag(BlockTags.NEEDS_IRON_TOOL);
        this.tag(BlockTags.NEEDS_DIAMOND_TOOL);
        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL);

    }
}
