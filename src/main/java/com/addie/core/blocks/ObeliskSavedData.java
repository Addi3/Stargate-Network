package com.addie.core.blocks;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.PersistentState;

import java.util.ArrayList;
import java.util.List;

public class ObeliskSavedData extends PersistentState {

    private static final String DATA_NAME = "obelisks_data";
    private static final String TAG_OBELISKS = "Obelisks";

    private final List<BlockPos> obelisks = new ArrayList<>();

    public ObeliskSavedData() {}

    // Load from NBT
    public static ObeliskSavedData createFromNbt(NbtCompound nbt) {
        ObeliskSavedData data = new ObeliskSavedData();
        data.readNbt(nbt);
        return data;
    }

    // Retrieve or create the saved data for a world
    public static ObeliskSavedData get(ServerWorld world) {
        return world.getPersistentStateManager().getOrCreate(
                ObeliskSavedData::createFromNbt,
                ObeliskSavedData::new,
                DATA_NAME
        );
    }

    // Add an obelisk position
    public void add(BlockPos pos) {
        if (!obelisks.contains(pos)) {
            obelisks.add(pos);
            markDirty();
        }
    }

    // Remove an obelisk position
    public void remove(BlockPos pos) {
        if (obelisks.remove(pos)) {
            markDirty();
        }
    }

    // Get nearest obelisk (used by manager, but manager now filters by ACTIVE/POWERED)
    public BlockPos getNearest(BlockPos origin, double maxDistance) {
        BlockPos closest = null;
        double closestDist = maxDistance * maxDistance;

        for (BlockPos pos : obelisks) {
            double dist = pos.getSquaredDistance(origin);
            if (dist > 0 && dist < closestDist) {
                closest = pos;
                closestDist = dist;
            }
        }

        return closest;
    }

    // Return all obelisks
    public List<BlockPos> getObelisks() {
        return new ArrayList<>(obelisks);
    }

    // Read from NBT
    private void readNbt(NbtCompound nbt) {
        obelisks.clear();

        NbtList list = nbt.getList(TAG_OBELISKS, 10); // 10 = compound tag

        for (int i = 0; i < list.size(); i++) {
            NbtCompound posTag = list.getCompound(i);
            obelisks.add(new BlockPos(
                    posTag.getInt("x"),
                    posTag.getInt("y"),
                    posTag.getInt("z")
            ));
        }
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        NbtList list = new NbtList();

        for (BlockPos pos : obelisks) {
            NbtCompound posTag = new NbtCompound();
            posTag.putInt("x", pos.getX());
            posTag.putInt("y", pos.getY());
            posTag.putInt("z", pos.getZ());
            list.add(posTag);
        }

        nbt.put(TAG_OBELISKS, list);
        return nbt;
    }
}
