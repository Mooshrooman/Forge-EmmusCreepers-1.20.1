package net.emmu.emmuscreepers.entity;

import net.emmu.emmuscreepers.EmmusCreepers;
import net.emmu.emmuscreepers.entity.custom.CheeseCreeperEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, EmmusCreepers.MOD_ID);

    public static final RegistryObject<EntityType<CheeseCreeperEntity>> CHEESECREEPER =
            ENTITY_TYPES.register("cheesecreeper", () -> EntityType.Builder.of(CheeseCreeperEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 1.7f).build("cheesecreeper"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
