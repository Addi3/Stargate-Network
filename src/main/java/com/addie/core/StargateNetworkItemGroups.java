package com.addie.core;

import com.addie.StargateNetwork;
import dev.amble.lib.container.impl.ItemGroupContainer;
import dev.amble.lib.itemgroup.AItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class StargateNetworkItemGroups implements ItemGroupContainer {

    public static final AItemGroup TAURI = AItemGroup.builder(StargateNetwork.id("tauri"))
            .icon(() -> new ItemStack(Items.DIAMOND.asItem()))
            .build();

}