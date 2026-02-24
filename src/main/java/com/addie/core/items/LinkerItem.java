package com.addie.core.items;

import com.addie.core.blockentities.AncientObeliskBlockEntity;
import com.addie.core.blocks.ObeliskBlock;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LinkerItem extends Item {

    private BlockPos firstObelisk;

    public LinkerItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockEntity be = world.getBlockEntity(pos);

        if (!(be instanceof AncientObeliskBlockEntity obelisk)) return ActionResult.PASS;

        BlockState state = world.getBlockState(pos);
        if (!(state.getBlock() instanceof ObeliskBlock) || !state.get(ObeliskBlock.POWERED)) {
            world.playSound(null, pos, SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE,
                    SoundCategory.BLOCKS, 1f, 1f);
            if (context.getPlayer() != null) {
                context.getPlayer().sendMessage(
                        Text.translatable("item.linker.nopower")
                                .styled(style -> style.withColor(Formatting.RED)),
                        true
                );
            }
            return ActionResult.FAIL;
        }



        if (world.isClient) return ActionResult.SUCCESS;

        ItemStack stack = context.getStack();

        if (firstObelisk == null) {
            firstObelisk = obelisk.getPos();
            world.playSound(null, pos, SoundEvents.ITEM_LODESTONE_COMPASS_LOCK,
                    SoundCategory.BLOCKS, 1f, 1f);
            if (context.getPlayer() != null) {
                context.getPlayer().sendMessage(Text.translatable("item.linker.selected").styled(style -> style.withColor(Formatting.GREEN)),
                        true);
            }
        } else {
            BlockState firstState = world.getBlockState(firstObelisk);
            if (!(firstState.getBlock() instanceof ObeliskBlock) || !firstState.get(ObeliskBlock.POWERED)) {
                firstObelisk = null;
                return ActionResult.FAIL;
            }

            obelisk.linkTo(firstObelisk);
            BlockEntity firstBE = world.getBlockEntity(firstObelisk);
            if (firstBE instanceof AncientObeliskBlockEntity first) first.linkTo(pos);
            world.playSound(null, pos, SoundEvents.BLOCK_RESPAWN_ANCHOR_SET_SPAWN,
                    SoundCategory.BLOCKS, 1f, 1f);
            if (context.getPlayer() != null) {

                context.getPlayer().sendMessage(
                        Text.translatable("item.linker.linked").styled(style -> style.withColor(Formatting.GOLD)),
                        true
                );
            }

            if (context.getPlayer() != null && !context.getPlayer().isCreative()) {
                stack.decrement(1);
            }

            firstObelisk = null;
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return firstObelisk != null;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, net.minecraft.client.item.TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(Text.translatable("item.linker.tolink")
                .formatted(Formatting.AQUA));
    }
}
