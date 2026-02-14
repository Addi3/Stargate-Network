package com.addie.core;


import com.addie.core.items.LinkerItem;
import dev.amble.lib.container.impl.ItemContainer;
import dev.amble.lib.datagen.util.AutomaticModel;
import dev.amble.lib.datagen.util.NoEnglish;
import dev.amble.lib.item.AItemSettings;
import net.minecraft.item.Item;

public class StargateNetworkItems extends ItemContainer {

    ///  TODO- make obtainable, maybe some new structure in the end (ancient outpost?)??
    @NoEnglish
    public static final Item ZPM = new Item (new AItemSettings().group(StargateNetworkItemGroups.MAIN));

    @AutomaticModel
    @NoEnglish
    public static final Item OBELISK_LINKER = new LinkerItem(new AItemSettings().group(StargateNetworkItemGroups.MAIN).maxCount(1));

    /// no recipe, found in ancient outpost?
    @NoEnglish
    @AutomaticModel
    public static final Item ANCIENT_GLASS_CRYSTAL = new Item(new AItemSettings().group(StargateNetworkItemGroups.MAIN));
}
