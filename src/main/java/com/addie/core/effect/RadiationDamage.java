package com.addie.core.effect;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class RadiationDamage {

    public static final RegistryKey<DamageType> RADIATION_DAMAGE_TYPE =
            RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier("stargate-network", "radiation"));

    public static DamageSource getRadiationDamageSource(World world) {
        RegistryEntry<DamageType> typeEntry =
                world.getRegistryManager()
                        .get(RegistryKeys.DAMAGE_TYPE)
                        .entryOf(RADIATION_DAMAGE_TYPE);

        return new DamageSource(typeEntry);
    }
}
