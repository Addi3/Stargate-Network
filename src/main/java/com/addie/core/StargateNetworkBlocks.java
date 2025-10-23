package com.addie.core;

import com.addie.core.blocks.*;
import dev.amble.lib.block.ABlockSettings;
import dev.amble.lib.container.impl.BlockContainer;
import dev.amble.lib.datagen.util.AutomaticModel;
import dev.amble.lib.datagen.util.AxeMineable;
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
            .itemSettings(new AItemSettings().group(StargateNetworkItemGroups.TAURI)).requiresTool()
            .strength(1.0F, 3.0F).pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.CALCITE));

    @PickaxeMineable(tool = PickaxeMineable.Tool.IRON)
    @NoEnglish
    public static final Block LIGHT_BLOCK = new LightBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings().group(StargateNetworkItemGroups.TAURI)).requiresTool()
            .strength(1.0F, 2.0F).pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.COPPER));


    @PickaxeMineable(tool = PickaxeMineable.Tool.IRON)
    @NoEnglish
    public static final Block TERMINAL_BLOCK = new TerminalBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings().group(StargateNetworkItemGroups.TAURI)).requiresTool()
            .strength(1.5F, 6.0F).pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.COPPER));


    @PickaxeMineable(tool = PickaxeMineable.Tool.IRON)
    @NoEnglish
    public static final Block ELEVATOR_DOOR_BLOCK = new ElevatorDoorBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings().group(StargateNetworkItemGroups.TAURI)).requiresTool().nonOpaque()
            .strength(1.5F, 6.0F).pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.COPPER));

    @PickaxeMineable(tool = PickaxeMineable.Tool.IRON)
    @NoEnglish
    public static final Block FANCY_LIGHT_BLOCK = new FancyLightBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings().group(StargateNetworkItemGroups.TAURI)).requiresTool()
            .strength(1.0F, 3.0F).pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.COPPER).luminance(state -> 13));

    @PickaxeMineable(tool = PickaxeMineable.Tool.IRON)
    @NoEnglish
    public static final Block LONG_FANCY_LIGHT_BLOCK = new LongFancyLightBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings().group(StargateNetworkItemGroups.TAURI)).requiresTool()
            .strength(1.0F, 3.0F).pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.COPPER).luminance(state -> 13));

    @PickaxeMineable(tool = PickaxeMineable.Tool.IRON)
    @NoEnglish
    public static final Block WALL_LIGHT_BLOCK = new WallLightBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings().group(StargateNetworkItemGroups.TAURI)).requiresTool()
            .strength(1.0F, 3.0F).pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.COPPER).luminance(state -> 13));

    @PickaxeMineable(tool = PickaxeMineable.Tool.IRON)
    @NoEnglish
    public static final Block FLOOD_LIGHT_BLOCK = new FloodLightBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings().group(StargateNetworkItemGroups.TAURI)).requiresTool()
            .strength(1.0F, 3.0F).pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.COPPER));

    @PickaxeMineable(tool = PickaxeMineable.Tool.IRON)
    @NoEnglish
    public static final Block STEEL_STAIRS_BLOCK = new SteelStairsBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings().group(StargateNetworkItemGroups.TAURI)).requiresTool()
            .strength(1.0F, 3.0F).pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.COPPER));

    @PickaxeMineable(tool = PickaxeMineable.Tool.IRON)
    @NoEnglish
    public static final Block STEEL_SLAB_BLOCK = new SteelSlabBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings().group(StargateNetworkItemGroups.TAURI)).requiresTool()
            .strength(1.0F, 3.0F).pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.COPPER));

    @PickaxeMineable(tool = PickaxeMineable.Tool.IRON)
    @NoEnglish
    public static final Block SLANTED_STEEL_RAILING_BLOCK = new SteelRailingBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings().group(StargateNetworkItemGroups.TAURI)).requiresTool()
            .strength(1.0F, 3.0F).pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.COPPER));

    @PickaxeMineable(tool = PickaxeMineable.Tool.IRON)
    @NoEnglish
    public static final Block STEEL_RAILING_BLOCK = new SteelRailingBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings().group(StargateNetworkItemGroups.TAURI)).requiresTool()
            .strength(1.0F, 3.0F).pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.COPPER));

    @PickaxeMineable(tool = PickaxeMineable.Tool.IRON)
    @NoEnglish
    @AutomaticModel
    public static final Block STEEL_BLOCK = new Block(ABlockSettings.create()
            .itemSettings(new AItemSettings().group(StargateNetworkItemGroups.MISC)).requiresTool()
            .strength(1.0F, 3.0F).pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.COPPER));

    @PickaxeMineable(tool = PickaxeMineable.Tool.IRON)
    @NoEnglish
    public static final Block GRATED_STEEL_STAIRS_BLOCK = new SteelStairsBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings().group(StargateNetworkItemGroups.TAURI)).requiresTool()
            .strength(1.0F, 3.0F).pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.COPPER));

    @AxeMineable(tool= AxeMineable.Tool.IRON)
    @NoEnglish
    public static final Block TABLE_BLOCK = new TableBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings().group(StargateNetworkItemGroups.TAURI)).requiresTool()
            .strength(1.0F, 3.0F).pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.WOOD));
}
