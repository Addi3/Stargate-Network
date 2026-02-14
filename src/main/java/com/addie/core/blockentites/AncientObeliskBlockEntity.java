package com.addie.core.blockentites;

import com.addie.core.StargateNetworkBlockEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class AncientObeliskBlockEntity extends BlockEntity {

    public AncientObeliskBlockEntity(BlockPos pos, BlockState state) {
        super(StargateNetworkBlockEntityTypes.ANCIENT_OBELISK_BLOCK_ENTITY_TYPE, pos, state);
    }

    public float getScale() {
        return 1;
    }
}


