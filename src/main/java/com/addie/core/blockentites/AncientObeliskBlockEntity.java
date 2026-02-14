package com.addie.core.blockentites;

import com.addie.core.StargateNetworkBlockEntityTypes;
import com.addie.core.blocks.ObeliskBlock;
import lombok.Getter;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

@Getter
public class AncientObeliskBlockEntity extends BlockEntity {

    private BlockPos linkedObelisk;

    public AncientObeliskBlockEntity(BlockPos pos, BlockState state) {
        super(StargateNetworkBlockEntityTypes.ANCIENT_OBELISK_BLOCK_ENTITY_TYPE, pos, state);
    }

    public float getScale() {
        return 1f;
    }

    public void linkTo(BlockPos other) {
        this.linkedObelisk = other;
        markDirty();
    }

    public void unlink() {
        this.linkedObelisk = null;
        markDirty();
    }

    public boolean isPowered() {
        return hasWorld() && getCachedState().get(ObeliskBlock.POWERED);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("linkedX") && nbt.contains("linkedY") && nbt.contains("linkedZ")) {
            this.linkedObelisk = new BlockPos(
                    nbt.getInt("linkedX"),
                    nbt.getInt("linkedY"),
                    nbt.getInt("linkedZ")
            );
        }
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (linkedObelisk != null) {
            nbt.putInt("linkedX", linkedObelisk.getX());
            nbt.putInt("linkedY", linkedObelisk.getY());
            nbt.putInt("linkedZ", linkedObelisk.getZ());
        }
    }
}
