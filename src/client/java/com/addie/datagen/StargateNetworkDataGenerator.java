package com.addie.datagen;

import com.addie.core.StargateNetworkBlocks;
import com.addie.core.StargateNetworkItems;
import com.addie.datagen.providers.StargateNetworkItemTagProvider;
import com.addie.datagen.providers.StargateNetworkModelGen;
import com.addie.datagen.providers.StargateNetworkRecipeProvider;
import dev.amble.lib.datagen.lang.AmbleLanguageProvider;
import dev.amble.lib.datagen.lang.LanguageType;
import dev.amble.lib.datagen.loot.AmbleBlockLootTable;
import dev.amble.lib.datagen.sound.AmbleSoundProvider;
import dev.amble.lib.datagen.tag.AmbleBlockTagProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import static net.minecraft.data.server.recipe.RecipeProvider.conditionsFromItem;
import static net.minecraft.data.server.recipe.RecipeProvider.hasItem;

public class StargateNetworkDataGenerator  implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator gen) {
        FabricDataGenerator.Pack pack = gen.createPack();

        genLang(pack);
        generateRecipes(pack);
        genLoot(pack);
        genTags(pack);
        genModels(pack);
        generateItemTags(pack);
        generateSoundData(pack);
    }


    private void genModels(FabricDataGenerator.Pack pack) {
        pack.addProvider(((output, registriesFuture) -> {
           StargateNetworkModelGen provider = new StargateNetworkModelGen(output);
            provider.withBlocks(StargateNetworkBlocks.class);
            provider.withItems(StargateNetworkItems.class);
            return provider;
        }));

    }

    public void generateSoundData(FabricDataGenerator.Pack pack) {
        pack.addProvider((((output, registriesFuture) -> new AmbleSoundProvider(output))));
    }

    public void generateItemTags(FabricDataGenerator.Pack pack) {
        pack.addProvider(StargateNetworkItemTagProvider::new);
    }

    private void genLang(FabricDataGenerator.Pack pack) {
        genEnglish(pack);
    }

    private void genTags(FabricDataGenerator.Pack pack) {
        pack.addProvider((((output, registriesFuture) -> new AmbleBlockTagProvider(output, registriesFuture).withBlocks(StargateNetworkBlocks.class))));
    }

    private void genLoot(FabricDataGenerator.Pack pack) {
        pack.addProvider((((output, registriesFuture) -> new AmbleBlockLootTable(output).withBlocks(StargateNetworkBlocks.class))));
    }
    public void generateRecipes(FabricDataGenerator.Pack pack) {
        pack.addProvider((((output, registriesFuture) -> {
            StargateNetworkRecipeProvider provider = new StargateNetworkRecipeProvider(output);

            ;provider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, StargateNetworkBlocks.TERMINAL_BLOCK, 1)
                    .pattern("BII")
                    .pattern("RII")
                    .pattern("CRI")
                    .input('I',StargateNetworkItems.STEEL_INGOT)
                    .input('R',Items.REDSTONE)
                    .input('B',Blocks.BLACK_CONCRETE)
                    .input('C',Blocks.COMPARATOR)
                    .criterion(hasItem(StargateNetworkItems.STEEL_INGOT), conditionsFromItem(StargateNetworkItems.STEEL_INGOT))
                    .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                    .criterion(hasItem(Blocks.BLACK_CONCRETE), conditionsFromItem(Blocks.BLACK_CONCRETE))
                    .criterion(hasItem(Blocks.COMPARATOR), conditionsFromItem(Blocks.COMPARATOR)));

            ;provider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, StargateNetworkBlocks.LIGHT_BLOCK, 1)
                    .pattern("R")
                    .pattern("I")
                    .input('I',StargateNetworkItems.STEEL_INGOT)
                    .input('R',Blocks.REDSTONE_LAMP)
                    .criterion(hasItem(Blocks.REDSTONE_LAMP), conditionsFromItem(Blocks.REDSTONE_LAMP))
                    .criterion(hasItem(StargateNetworkItems.STEEL_INGOT), conditionsFromItem(StargateNetworkItems.STEEL_INGOT)));

            ;provider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, StargateNetworkBlocks.FANCY_LIGHT_BLOCK, 1)
                            .group("lights")
                    .pattern("I I")
                    .pattern("BIB")
                    .pattern("F F")
                    .input('I',StargateNetworkItems.STEEL_INGOT)
                    .input('B',StargateNetworkBlocks.STEEL_BLOCK)
                    .input('F',Blocks.OCHRE_FROGLIGHT)
                    .criterion(hasItem(Blocks.OCHRE_FROGLIGHT), conditionsFromItem(Blocks.OCHRE_FROGLIGHT))
                    .criterion(hasItem(StargateNetworkItems.STEEL_INGOT), conditionsFromItem(StargateNetworkItems.STEEL_INGOT))
                    .criterion(hasItem(StargateNetworkBlocks.STEEL_BLOCK), conditionsFromItem(StargateNetworkBlocks.STEEL_BLOCK)));

            ;provider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, StargateNetworkBlocks.LONG_FANCY_LIGHT_BLOCK, 1)
                    .group("lights")
                    .pattern("FFF")
                    .input('F',StargateNetworkBlocks.FANCY_LIGHT_BLOCK)
                    .criterion(hasItem(StargateNetworkBlocks.FANCY_LIGHT_BLOCK), conditionsFromItem(StargateNetworkBlocks.FANCY_LIGHT_BLOCK)));

            ;provider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, StargateNetworkBlocks.WALL_LIGHT_BLOCK, 1)
                    .group("lights")
                    .pattern("SS ")
                    .pattern("FFS")
                    .input('F',Blocks.OCHRE_FROGLIGHT)
                    .input('S',Blocks.POLISHED_DEEPSLATE_SLAB)
                    .criterion(hasItem(Blocks.OCHRE_FROGLIGHT), conditionsFromItem(Blocks.OCHRE_FROGLIGHT))
                    .criterion(hasItem(Blocks.POLISHED_DEEPSLATE_SLAB), conditionsFromItem(Blocks.POLISHED_DEEPSLATE_SLAB)));

            ;provider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, StargateNetworkBlocks.FLOOD_LIGHT_BLOCK, 1)
                    .group("lights")
                    .pattern("T T")
                    .pattern("III")
                    .input('I',StargateNetworkItems.STEEL_INGOT)
                    .input('T',Blocks.TORCH)
                    .criterion(hasItem(StargateNetworkItems.STEEL_INGOT), conditionsFromItem(StargateNetworkItems.STEEL_INGOT))
                    .criterion(hasItem(Blocks.TORCH), conditionsFromItem(Blocks.TORCH)));


            provider.addStonecutting(Blocks.YELLOW_CONCRETE, StargateNetworkBlocks.CAUTION_BLOCK,1);

            provider.addBlastFurnaceRecipe(CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(Items.RAW_IRON),
                                    RecipeCategory.MISC, StargateNetworkItems.RAW_STEEL, 0.2f, 200)
                            .criterion(hasItem(Items.RAW_IRON), conditionsFromItem(Items.RAW_IRON)));


            provider.addBlastFurnaceRecipe(CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(StargateNetworkItems.RAW_STEEL),
                            RecipeCategory.MISC, StargateNetworkItems.STEEL_INGOT, 0.2f, 200)
                    .criterion(hasItem(StargateNetworkItems.RAW_STEEL), conditionsFromItem(StargateNetworkItems.RAW_STEEL)));


            ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, StargateNetworkItems.STEEL_NUGGET, 9);
            provider.addShapelessRecipe(ShapelessRecipeJsonBuilder
                    .create(RecipeCategory.MISC, StargateNetworkItems.STEEL_NUGGET, 9)
                            .group("steel")
                    .input(StargateNetworkItems.STEEL_INGOT)
                    .criterion(hasItem(StargateNetworkItems.STEEL_INGOT), conditionsFromItem(StargateNetworkItems.STEEL_INGOT)),
                    new Identifier("stargate-network","steel_nugget_from_ingot"));

            ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, StargateNetworkItems.STEEL_INGOT, 9);
            provider.addShapelessRecipe(ShapelessRecipeJsonBuilder
                            .create(RecipeCategory.MISC, StargateNetworkItems.STEEL_INGOT, 9)
                            .group("steel")
                            .input(StargateNetworkBlocks.STEEL_BLOCK)
                            .criterion(hasItem(StargateNetworkBlocks.STEEL_BLOCK), conditionsFromItem(StargateNetworkBlocks.STEEL_BLOCK)),
                    new Identifier("stargate-network","steel_ingot_from_steel_block"));

            ;provider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, StargateNetworkBlocks.STEEL_BLOCK, 1)
                    .group("steel")
                    .pattern("III")
                    .pattern("III")
                    .pattern("III")
                    .input('I',StargateNetworkItems.STEEL_INGOT)
                    .criterion(hasItem(StargateNetworkItems.STEEL_INGOT), conditionsFromItem(StargateNetworkItems.STEEL_INGOT)));

            ;provider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, StargateNetworkItems.STEEL_INGOT, 1)
                    .group("steel")
                    .pattern("NNN")
                    .pattern("NNN")
                    .pattern("NNN")
                    .input('N',StargateNetworkItems.STEEL_NUGGET)
                    .criterion(hasItem(StargateNetworkItems.STEEL_NUGGET), conditionsFromItem(StargateNetworkItems.STEEL_NUGGET)),
                    new Identifier("stargate-network","steel_ingot_from_steel_nuggets"));

            ;provider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, StargateNetworkBlocks.STEEL_SLAB_BLOCK, 1)
                    .group("steel")
                    .pattern("III")
                    .input('I',StargateNetworkItems.STEEL_INGOT)
                    .criterion(hasItem(StargateNetworkItems.STEEL_INGOT), conditionsFromItem(StargateNetworkItems.STEEL_INGOT)));

            ;provider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, StargateNetworkBlocks.STEEL_STAIRS_BLOCK, 1)
                    .group("steel")
                    .pattern("I  ")
                    .pattern("II ")
                    .pattern("III")
                    .input('I',StargateNetworkItems.STEEL_INGOT)
                    .criterion(hasItem(StargateNetworkItems.STEEL_INGOT), conditionsFromItem(StargateNetworkItems.STEEL_INGOT)));

            ;provider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, StargateNetworkBlocks.STEEL_RAILING_BLOCK, 1)
                    .group("steel")
                    .pattern("III")
                    .pattern("N N")
                    .input('I',StargateNetworkItems.STEEL_INGOT)
                    .input('N',StargateNetworkItems.STEEL_NUGGET)
                    .criterion(hasItem(StargateNetworkItems.STEEL_INGOT), conditionsFromItem(StargateNetworkItems.STEEL_INGOT))
                    .criterion(hasItem(StargateNetworkItems.STEEL_NUGGET), conditionsFromItem(StargateNetworkItems.STEEL_NUGGET)));

            ;provider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, StargateNetworkBlocks.SLANTED_STEEL_RAILING_BLOCK, 1)
                    .group("steel")
                    .pattern("I  ")
                    .pattern("NII")
                    .pattern("  N")
                    .input('I',StargateNetworkItems.STEEL_INGOT)
                    .input('N',StargateNetworkItems.STEEL_NUGGET)
                    .criterion(hasItem(StargateNetworkItems.STEEL_INGOT), conditionsFromItem(StargateNetworkItems.STEEL_INGOT))
                    .criterion(hasItem(StargateNetworkItems.STEEL_NUGGET), conditionsFromItem(StargateNetworkItems.STEEL_NUGGET)));

            ;provider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, StargateNetworkBlocks.GRATED_STEEL_STAIRS_BLOCK, 1)
                    .group("steel")
                    .pattern("N  ")
                    .pattern("NN ")
                    .pattern("NNN")
                    .input('N',StargateNetworkItems.STEEL_NUGGET)
                    .criterion(hasItem(StargateNetworkItems.STEEL_NUGGET), conditionsFromItem(StargateNetworkItems.STEEL_NUGGET)));

            ;provider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, StargateNetworkBlocks.TABLE_BLOCK, 2)
                    .group("")
                    .pattern("MM")
                    .pattern("DD")
                    .input('M',Blocks.MANGROVE_SLAB)
                    .input('D',Blocks.POLISHED_DEEPSLATE)
                    .criterion(hasItem(Blocks.MANGROVE_SLAB), conditionsFromItem(Blocks.MANGROVE_SLAB))
                    .criterion(hasItem(Blocks.POLISHED_DEEPSLATE), conditionsFromItem(Blocks.POLISHED_DEEPSLATE)));

            return provider;

        })));
    }

    private void genEnglish(FabricDataGenerator.Pack pack) {
        pack.addProvider((((output, registriesFuture) -> {
            AmbleLanguageProvider provider = new AmbleLanguageProvider(output, LanguageType.EN_US);

        // Misc
            provider.addTranslation("itemGroup.stargate-network.tauri",  "SGN: Tau'ri");
            provider.addTranslation("itemGroup.stargate-network.misc",  "SGN: Misc");

        // Blocks

            // TAURI
            provider.addTranslation(StargateNetworkBlocks.CAUTION_BLOCK,"Caution Block");
            provider.addTranslation(StargateNetworkBlocks.TERMINAL_BLOCK,"Terminal");
            provider.addTranslation(StargateNetworkBlocks.LIGHT_BLOCK,"Emergancy Light");
            provider.addTranslation(StargateNetworkBlocks.FANCY_LIGHT_BLOCK,"Fancy Light");
            provider.addTranslation(StargateNetworkBlocks.LONG_FANCY_LIGHT_BLOCK,"Long Fancy Light");
            provider.addTranslation(StargateNetworkBlocks.WALL_LIGHT_BLOCK,"Wall Light");
            provider.addTranslation(StargateNetworkBlocks.FLOOD_LIGHT_BLOCK,"Flood Light");
            provider.addTranslation(StargateNetworkBlocks.STEEL_BLOCK,"Steel Block");
            provider.addTranslation(StargateNetworkBlocks.STEEL_STAIRS_BLOCK,"Steel Stairs");
            provider.addTranslation(StargateNetworkBlocks.STEEL_SLAB_BLOCK,"Steel Slab");
            provider.addTranslation(StargateNetworkBlocks.SLANTED_STEEL_RAILING_BLOCK,"Steel Railing (Slanted)");
            provider.addTranslation(StargateNetworkBlocks.STEEL_RAILING_BLOCK,"Steel Railing");
            provider.addTranslation(StargateNetworkBlocks.GRATED_STEEL_STAIRS_BLOCK,"Grated Steel Stairs");
            provider.addTranslation(StargateNetworkBlocks.TABLE_BLOCK,"Table");

        // Items
            provider.addTranslation(StargateNetworkItems.STEEL_INGOT,"Steel Ingot");
            provider.addTranslation(StargateNetworkItems.STEEL_NUGGET,"Steel Nugget");
            provider.addTranslation(StargateNetworkItems.RAW_STEEL,"Raw Steel");

            return provider;
        })));
    }
}