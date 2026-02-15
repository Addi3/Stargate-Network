package com.addie.core.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.world.World;

import java.util.UUID;

import static com.addie.core.effect.RadiationDamage.getRadiationDamageSource;

public class RadiationEffect extends StatusEffect {

    private static final UUID RADIATION_HEALTH_UUID =
            UUID.fromString("5e734d7c-0000-0000-0000-000000000001");

    private static final float DAMAGE_PER_TICK = 0.5f;

    private static final float MAX_HEALTH_LOSS_PER_TICK = 0.5f;

    public RadiationEffect() {
        super(StatusEffectCategory.HARMFUL, 0x55FF55);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % 80 == 0;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        World world = entity.getWorld();

        if (entity.getHealth() > 0.5f) {
            entity.damage(getRadiationDamageSource(world), DAMAGE_PER_TICK * (amplifier + 1));
        }

        EntityAttributeInstance maxHealth = entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
        if (maxHealth == null) return;

        EntityAttributeModifier existing = maxHealth.getModifier(RADIATION_HEALTH_UUID);
        double currentReduction = existing != null ? existing.getValue() : 0.0;

        double newReduction = currentReduction - MAX_HEALTH_LOSS_PER_TICK * (amplifier + 1);

        if (existing != null) {
            maxHealth.removeModifier(existing);
        }

        maxHealth.addPersistentModifier(new EntityAttributeModifier(
                RADIATION_HEALTH_UUID,
                "radiation_health_decay",
                newReduction,
                EntityAttributeModifier.Operation.ADDITION
        ));


        if (entity.getHealth() > entity.getMaxHealth()) {
            entity.setHealth(entity.getMaxHealth());
        }

        if (entity.getMaxHealth() <= 1f) {
            entity.kill();
        }
    }

    @Override
    public void onRemoved(LivingEntity entity,
                          net.minecraft.entity.attribute.AttributeContainer attributes,
                          int amplifier) {
        super.onRemoved(entity, attributes, amplifier);

        EntityAttributeInstance maxHealth = entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
        if (maxHealth != null) {
            maxHealth.removeModifier(RADIATION_HEALTH_UUID);
        }
    }
}
