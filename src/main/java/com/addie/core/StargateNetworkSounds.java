package com.addie.core;

import com.addie.StargateNetwork;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class StargateNetworkSounds {

    public static final SoundEvent OBELISK_TELEPORT = register("obelisk_teleport");


    // Register a SoundEvent
    private static SoundEvent register(String name) {
        Identifier id = StargateNetwork.id(name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    // Initialize all sounds
    public static void init() {
        // This ensures the static fields are loaded and registered
    }
}
