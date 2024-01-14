package net.emmu.emmuscreepers.event;

import net.emmu.emmuscreepers.EmmusCreepers;
import net.emmu.emmuscreepers.entity.client.CheeseCreeperModel;
import net.emmu.emmuscreepers.entity.client.ModModelLayers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EmmusCreepers.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
@SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.CHEESECREEPER_LAYER, CheeseCreeperModel::createBodyLayer);
    }

}
