package net.emmu.emmuscreepers.datagen;

import net.emmu.emmuscreepers.EmmusCreepers;
import net.emmu.emmuscreepers.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, EmmusCreepers.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.CHEESE);
        simpleItem(ModItems.MELTY_CHEESE);
        eggItem(ModItems.CHEESE_CREEPER_SPAWN_EGG);
    }
    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
        new ResourceLocation("item/generated")).texture("layer0",
        new ResourceLocation(EmmusCreepers.MOD_ID, "item/" + item.getId().getPath()));
    }
    private ItemModelBuilder eggItem(RegistryObject<ForgeSpawnEggItem> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/template_spawn_egg"));
    }
}
