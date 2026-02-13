package com.addie.core.blocks;

import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class WaystoneManager {

    private static final List<BlockPos> WAYSTONES = new ArrayList<>();

    public static void add(BlockPos pos) {
        if (!WAYSTONES.contains(pos)) {
            WAYSTONES.add(pos);
        }
    }

    public static void remove(BlockPos pos) {
        WAYSTONES.remove(pos);
    }

    public static BlockPos getNearest(BlockPos origin, double maxDistance) {
        BlockPos closest = null;
        double closestDist = maxDistance * maxDistance;

        for (BlockPos pos : WAYSTONES) {
            double dist = pos.getSquaredDistance(origin);
            if (dist > 0 && dist < closestDist) {
                closestDist = dist;
                closest = pos;
            }
        }

        return closest;
    }
}
