package com.addie.puddlejumper;

import com.addie.core.entites.PuddleJumperEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.Vec3d;

public class PuddleJumperInputHandler {

    private static final double VERTICAL_SPEED = 0.1;

    public static void tick() {
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayerEntity player = client.player;
        if (player == null) return;

        if (player.getVehicle() instanceof PuddleJumperEntity puddleJumper) {

            Vec3d verticalMovement = Vec3d.ZERO;

            if (PuddleJumperKeybinds.MOVE_UP.isPressed()) {
                verticalMovement = verticalMovement.add(0, VERTICAL_SPEED, 0);
            }
            if (PuddleJumperKeybinds.MOVE_DOWN.isPressed()) {
                verticalMovement = verticalMovement.add(0, -VERTICAL_SPEED, 0);
            }

            puddleJumper.setVelocity(puddleJumper.getVelocity().add(verticalMovement));
        }
    }
}