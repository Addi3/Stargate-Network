package com.addie;

import com.addie.core.*;
import com.addie.core.effect.RadiationEffect;
import dev.amble.lib.container.RegistryContainer;
import net.fabricmc.api.ModInitializer;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class StargateNetwork implements ModInitializer {
	public static final String MOD_ID = "stargate-network";

    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }

	@Override
	public void onInitialize() {

        RegistryContainer.register(StargateNetworkBlocks.class, MOD_ID);
        RegistryContainer.register(StargateNetworkItemGroups.class, MOD_ID);
        RegistryContainer.register(StargateNetworkItems.class, MOD_ID);
        RegistryContainer.register(StargateNetworkBlockEntityTypes.class, MOD_ID);
        StargateNetworkSounds.init();
	}

    public static final StatusEffect RADIATION =
            Registry.register(
                    Registries.STATUS_EFFECT,
                    new Identifier("stargate-network", "radiation"),
                    new RadiationEffect()
            );
}