package com.addie.core;

import com.addie.core.entites.PuddleJumperEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import com.addie.StargateNetwork;

public class StargateNetworkEntities {

    public static final EntityType<PuddleJumperEntity> PUDDLE_JUMPER =
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, PuddleJumperEntity::new)
                    .dimensions(EntityDimensions.changing(3f, 2f))
                    .fireImmune()
                    .build();

    public static void register() {
     Registry.register(Registries.ENTITY_TYPE, new Identifier(StargateNetwork.MOD_ID, "puddle_jumper"), PUDDLE_JUMPER);
        FabricDefaultAttributeRegistry.register(PUDDLE_JUMPER, PuddleJumperEntity.createAttributes());
    }
}