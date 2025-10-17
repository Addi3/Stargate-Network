package com.addie.core.blockentites;

import com.addie.core.StargateNetworkBlockEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class WallLightBlockEntity extends BlockEntity {

    public WallLightBlockEntity(BlockPos pos, BlockState state) {
        super(StargateNetworkBlockEntityTypes.WALL_LIGHT_BLOCK_ENTITY_TYPE, pos, state);
    }

    public float getScale() {
        return 1;
    }
}

