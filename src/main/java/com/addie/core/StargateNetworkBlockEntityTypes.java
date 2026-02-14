package com.addie.core;

import com.addie.core.blockentites.AncientObeliskBlockEntity;
import dev.amble.lib.container.impl.BlockEntityContainer;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;

public class StargateNetworkBlockEntityTypes implements BlockEntityContainer {

    public static BlockEntityType<AncientObeliskBlockEntity> ANCIENT_OBELISK_BLOCK_ENTITY_TYPE = FabricBlockEntityTypeBuilder
            .create(AncientObeliskBlockEntity::new, StargateNetworkBlocks.ANCIENT_OBELISK).build();

}
