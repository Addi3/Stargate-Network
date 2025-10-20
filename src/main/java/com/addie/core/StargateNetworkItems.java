package com.addie.core;

import dev.amble.lib.container.impl.ItemContainer;
import dev.amble.lib.datagen.util.AutomaticModel;
import dev.amble.lib.datagen.util.NoEnglish;
import dev.amble.lib.item.AItemSettings;
import net.minecraft.item.Item;

public class StargateNetworkItems extends ItemContainer {

    @AutomaticModel
    @NoEnglish
    public static final Item STEEL_INGOT = new Item(new AItemSettings().group(StargateNetworkItemGroups.MISC));

    @AutomaticModel
    @NoEnglish
    public static final Item STEEL_NUGGET = new Item(new AItemSettings().group(StargateNetworkItemGroups.MISC));

    @AutomaticModel
    @NoEnglish
    public static final Item RAW_STEEL = new Item(new AItemSettings().group(StargateNetworkItemGroups.MISC));


}
