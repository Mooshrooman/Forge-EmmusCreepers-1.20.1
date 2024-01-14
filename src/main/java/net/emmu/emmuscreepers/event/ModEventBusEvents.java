package net.emmu.emmuscreepers.event;

import net.emmu.emmuscreepers.EmmusCreepers;
import net.emmu.emmuscreepers.entity.ModEntities;
import net.emmu.emmuscreepers.entity.custom.CheeseCreeperEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EmmusCreepers.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
@SubscribeEvent
    public static void  registerAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntities.CHEESECREEPER.get(), CheeseCreeperEntity.createAttributes().build());
    }
}
