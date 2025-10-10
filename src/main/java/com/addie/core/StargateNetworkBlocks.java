package com.addie.core;

import com.addie.core.blocks.TerminalBlock;
import dev.amble.lib.block.ABlockSettings;
import dev.amble.lib.container.impl.BlockContainer;
import dev.amble.lib.datagen.util.AutomaticModel;
import dev.amble.lib.datagen.util.NoEnglish;
import dev.amble.lib.datagen.util.PickaxeMineable;
import dev.amble.lib.item.AItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.sound.BlockSoundGroup;

public class StargateNetworkBlocks extends BlockContainer {

    @PickaxeMineable(tool = PickaxeMineable.Tool.IRON)
    @NoEnglish
    @AutomaticModel
    public static final Block CAUTION_BLOCK = new Block(ABlockSettings.create()
            .itemSettings(new AItemSettings().group(StargateNetworkItemGroups.MAIN)).requiresTool()
            .strength(1.0F, 3.0F).pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.CALCITE));


    @PickaxeMineable(tool = PickaxeMineable.Tool.IRON)
    @NoEnglish
    public static final Block TERMINAL_BLOCK = new TerminalBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings().group(StargateNetworkItemGroups.MAIN)).requiresTool()
            .strength(1.5F, 6.0F).pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.METAL));
}
