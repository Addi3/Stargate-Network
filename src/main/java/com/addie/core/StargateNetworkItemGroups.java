package com.addie.core;

import com.addie.StargateNetwork;
import dev.amble.lib.container.impl.ItemGroupContainer;
import dev.amble.lib.itemgroup.AItemGroup;
import net.minecraft.item.ItemStack;

public class StargateNetworkItemGroups implements ItemGroupContainer {

    public static final AItemGroup TAURI = AItemGroup.builder(StargateNetwork.id("tauri"))
            .icon(() -> new ItemStack(StargateNetworkBlocks.TERMINAL_BLOCK.asItem()))
            .build();
    public static final AItemGroup MISC = AItemGroup.builder(StargateNetwork.id("misc"))
            .icon(() -> new ItemStack(StargateNetworkItems.STEEL_INGOT))
            .build();
}