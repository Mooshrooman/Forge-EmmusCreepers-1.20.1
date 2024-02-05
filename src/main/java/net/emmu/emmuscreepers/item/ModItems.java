package net.emmu.emmuscreepers.item;

import net.emmu.emmuscreepers.EmmusCreepers;
import net.emmu.emmuscreepers.entity.ModEntities;
import net.emmu.emmuscreepers.item.custom.MeltyCheeseItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, EmmusCreepers.MOD_ID);

    public static final RegistryObject<Item> CHEESE = ITEMS.register("cheese",
            () -> new Item(new Item.Properties().food(ModFoods.CHEESE)));
    public static final RegistryObject<Item> MELTY_CHEESE = ITEMS.register("melty_cheese",
            () -> new MeltyCheeseItem(new Item.Properties().food(ModFoods.MELTY_CHEESE)));

    public static final RegistryObject<ForgeSpawnEggItem> CHEESE_CREEPER_SPAWN_EGG = ITEMS.register("cheese_creeper_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.CHEESECREEPER, 0xFFCC20,0xC69E18,
                    new Item.Properties().stacksTo(16)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
