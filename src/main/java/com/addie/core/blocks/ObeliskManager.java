package com.addie.core.blocks;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;

import java.util.ArrayList;
import java.util.List;

public class ObeliskManager {

    private final ObeliskSavedData data;
    private final ServerWorld world;

    public ObeliskManager(ServerWorld world) {
        this.world = world;
        this.data = ObeliskSavedData.get(world);
    }

    public void add(BlockPos pos) {
        data.add(pos);
    }

    public void remove(BlockPos pos) {
        data.remove(pos);
    }

    public BlockPos getNearest(BlockPos origin, double maxDistance) {
        BlockPos closest = null;
        double closestDist = maxDistance * maxDistance;

        for (BlockPos pos : data.getObelisks()) {
            BlockState state = world.getBlockState(pos);
            if (state.getBlock() instanceof ObeliskBlock obelisk) {
                if (state.get(ObeliskBlock.POWERED) && state.get(ObeliskBlock.ACTIVE)) {
                    double dist = pos.getSquaredDistance(origin);
                    if (dist > 0 && dist < closestDist) {
                        closest = pos;
                        closestDist = dist;
                    }
                }
            }
        }

        return closest;
    }

    public List<BlockPos> getAll() {
        return new ArrayList<>(data.getObelisks());
    }
}
