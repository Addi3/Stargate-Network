package com.addie.core.items;

import com.addie.core.StargateNetworkItems;
import dev.amble.stargate.service.Services;
import dev.amble.stargate.service.TooltipContextExt;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ZPMTooltipHandlerItem extends Item {

    private final boolean showTooltip;

    public ZPMTooltipHandlerItem(Settings settings, boolean showTooltip) {
        super(settings);
        this.showTooltip = showTooltip;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        this.handleTooltip(stack, tooltip, Services.TOOLTIP.create(context));
    }

    private void handleTooltip(ItemStack stack, List<Text> tooltip, TooltipContextExt ctx) {

        if (stack.getItem() == StargateNetworkItems.OVERCHARGED_ZPM) {
            tooltip.add(Text.translatable("item.overchargedzpm.caution")
                    .formatted(Formatting.DARK_RED));
        }

        if (!showTooltip)
            return;

        if (!ctx.isShiftDown())
            tooltip.add(Text.translatable("tooltip.zpm.hint").formatted(Formatting.WHITE)
                    .formatted(Formatting.ITALIC));

        if (ctx.isShiftDown()) {

            tooltip.add(
                    Text.translatable("item.zpm.status")
                            .setStyle(Style.EMPTY.withColor(Formatting.WHITE))
                            .append(Text.literal(" "))
            );

            if (stack.getItem() == StargateNetworkItems.ZPM) {
                tooltip.add(
                        Text.translatable("item.zpm.charged")
                                .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xf7ab00))) // gold
                );
            } else if (stack.getItem() == StargateNetworkItems.DEPLETED_ZPM) {
                tooltip.add(
                        Text.translatable("item.zpm.depleted")
                                .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x727272))) // gray
                );
            } else if (stack.getItem() == StargateNetworkItems.OVERCHARGED_ZPM) {
                String translated = Text.translatable("item.zpm.overcharged").getString();
                int len = translated.length();
                int part1End = len / 3;
                int part2End = 2 * len / 3;

                Text gradientText = Text.literal("")
                        .append(Text.literal(translated.substring(0, part1End))
                                .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x5e0a24)))) // dark red
                        .append(Text.literal(translated.substring(part1End, part2End))
                                .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x2d154f)))) // purple
                        .append(Text.literal(translated.substring(part2End))
                                .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x303b7a)))); // blue

                tooltip.add(gradientText);


            }
        }
    }
}
