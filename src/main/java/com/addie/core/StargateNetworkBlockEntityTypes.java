package com.addie.core;

import com.addie.core.blockentites.ElevatorDoorBlockEntity;
import com.addie.core.blockentites.FancyLightBlockEntity;
import com.addie.core.blockentites.LongFancyLightBlockEntity;
import com.addie.core.blockentites.TerminalBlockEntity;
import dev.amble.lib.animation.HasBedrockModel;
import dev.amble.lib.container.impl.BlockEntityContainer;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;

public class StargateNetworkBlockEntityTypes implements BlockEntityContainer {

    public static BlockEntityType<TerminalBlockEntity> TERMINAL_BLOCK_ENTITY_TYPE = FabricBlockEntityTypeBuilder
            .create(TerminalBlockEntity::new, StargateNetworkBlocks.TERMINAL_BLOCK).build();

    public static BlockEntityType<FancyLightBlockEntity> FANCY_LIGHT_BLOCK_ENTITY_TYPE = FabricBlockEntityTypeBuilder
            .create(FancyLightBlockEntity::new, StargateNetworkBlocks.FANCY_LIGHT_BLOCK).build();

    public static BlockEntityType<LongFancyLightBlockEntity> LONG_FANCY_LIGHT_BLOCK_ENTITY_TYPE = FabricBlockEntityTypeBuilder
            .create(LongFancyLightBlockEntity::new, StargateNetworkBlocks.LONG_FANCY_LIGHT_BLOCK).build();

    @HasBedrockModel
    public static BlockEntityType<ElevatorDoorBlockEntity> ELEVATOR_DOOR_BLOCK = FabricBlockEntityTypeBuilder.create(ElevatorDoorBlockEntity::new, StargateNetworkBlocks.ELEVATOR_DOOR_BLOCK).build();
}
