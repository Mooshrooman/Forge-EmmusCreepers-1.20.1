package net.emmu.emmuscreepers.entity.ai;

import net.emmu.emmuscreepers.entity.custom.CheeseCreeperEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

public class CheeseCreepersSwellGoal extends Goal {

    private LivingEntity target;
    private final CheeseCreeperEntity creeper;

    public CheeseCreepersSwellGoal(CheeseCreeperEntity pCheeseCreeper){
        this.creeper = pCheeseCreeper;
    }

    @Override
    public boolean canUse() {
        LivingEntity livingentity = this.creeper.getTarget();
        return this.creeper.isSwelling() || livingentity != null && this.creeper.distanceToSqr(livingentity) < 9.0D;
    }

    public void start() {
        this.creeper.getNavigation().stop();
        this.target = this.creeper.getTarget();
    }

    public void stop() {
        this.target = null;
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        if (this.target == null) {
            this.creeper.setSwelling(false);
            this.creeper.setSwellNumber(-1);
        } else if (this.creeper.distanceToSqr(this.target) > 49.0D) {
            this.creeper.setSwelling(false);
            this.creeper.setSwellNumber(-1);
        } else if (!this.creeper.getSensing().hasLineOfSight(this.target)) {
            this.creeper.setSwelling(false);
            this.creeper.setSwellNumber(-1);
        } else {
            this.creeper.setSwelling(true);
            this.creeper.setSwellNumber(1);
        }
    }
}



