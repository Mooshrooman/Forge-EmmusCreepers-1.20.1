package net.emmu.emmuscreepers.item;

import net.emmu.emmuscreepers.EmmusCreepers;
import net.emmu.emmuscreepers.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EmmusCreepers.MOD_ID);

public static final RegistryObject<CreativeModeTab> EMMUS_CREEPERS_TAB = CREATIVE_MODE_TABS.register("emmus_creepers_tab",
        () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.CHEESE.get()))
                .title(Component.translatable("creativetab.emmus_creepers_tab"))
                .displayItems((pParameters, pOutput) -> {
                    pOutput.accept(ModItems.CHEESECREEPERSPAWNEGG.get());
                    pOutput.accept(ModItems.CHEESE.get());

                    pOutput.accept(ModBlocks.CHEESE_BLOCK.get());
                    pOutput.accept(ModBlocks.MOLDY_CHEESE_BLOCK.get());

                })
                .build());
    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
