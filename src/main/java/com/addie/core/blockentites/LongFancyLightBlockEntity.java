package com.addie.core.blockentites;

import com.addie.core.StargateNetworkBlockEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class LongFancyLightBlockEntity extends BlockEntity {

    public LongFancyLightBlockEntity(BlockPos pos, BlockState state) {
        super(StargateNetworkBlockEntityTypes.LONG_FANCY_LIGHT_BLOCK_ENTITY_TYPE, pos, state);
    }

    public float getScale() {
        return 1;
    }
}

