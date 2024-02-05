package net.emmu.emmuscreepers.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {

    public static final FoodProperties CHEESE = new FoodProperties.Builder().nutrition(6)
            .saturationMod(0.7F).build();

    public static final FoodProperties MELTY_CHEESE = new FoodProperties.Builder().nutrition(8)
            .saturationMod(0.9F).build();
}
