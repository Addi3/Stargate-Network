package com.addie.mixin;


import com.addie.core.StargateNetworkItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.explosion.ExplosionBehavior;
import net.minecraft.world.World.ExplosionSourceType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    private void explodeOverchargedZPM(CallbackInfo ci) {
        ItemEntity self = (ItemEntity) (Object) this;
        World world = self.getWorld();
        ItemStack stack = self.getStack();

        if (!world.isClient) {
            if (stack.getItem() == StargateNetworkItems.OVERCHARGED_ZPM) {

                world.createExplosion(
                        null,
                        (DamageSource) null,
                        (ExplosionBehavior) null,
                        self.getX(),
                        self.getY(),
                        self.getZ(),
                        8.0f,                              // explosion power
                        false,                              // allow fire
                        ExplosionSourceType.TNT
                );

                self.remove(Entity.RemovalReason.DISCARDED);
            }
        }
    }
}
