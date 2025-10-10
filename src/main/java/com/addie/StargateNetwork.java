package com.addie;

import com.addie.core.StargateNetworkBlockEntityTypes;
import com.addie.core.StargateNetworkBlocks;
import com.addie.core.StargateNetworkItemGroups;
import com.addie.core.StargateNetworkItems;
import dev.amble.lib.container.RegistryContainer;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	}
}