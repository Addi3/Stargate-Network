package com.addie.core.entites;

import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PuddleJumperEntity extends LivingEntity {

    private static final int MAX_PASSENGERS = 4;
    private static final double SPEED = 0.6;

    public PuddleJumperEntity(EntityType<? extends PuddleJumperEntity> type, World world) {
        super(type, world);
        this.setNoGravity(true);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10000.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.0);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
    }

    @Override
    public Arm getMainArm() {
        return Arm.RIGHT;
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {}
    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {}

    @Override
    protected boolean canAddPassenger(Entity passenger) {
        return this.getPassengerList().size() < MAX_PASSENGERS;
    }

    @Override
    public @Nullable LivingEntity getControllingPassenger() {
        Entity first = this.getFirstPassenger();
        return first instanceof LivingEntity ? (LivingEntity) first : null;
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        if (!this.getWorld().isClient) {
            player.startRiding(this);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void tick() {
        super.tick();
        this.setNoGravity(true);

        if (!this.getWorld().isClient) {
            LivingEntity pilot = this.getControllingPassenger();

            if (pilot instanceof PlayerEntity player) {
                float targetYaw = player.getYaw();
                float yawDiff = normalizeAngle(targetYaw - this.getYaw());
                this.setYaw(this.getYaw() + yawDiff * 0.5f);
                this.prevYaw = this.getYaw();

                this.setPitch(player.getPitch() * 0.5f);

                float forward = player.forwardSpeed;
                float sideways = player.sidewaysSpeed;

                Vec3d movement = Vec3d.ZERO;

                if (forward != 0 || sideways != 0) {
                    movement = new Vec3d(sideways, 0, forward)
                            .rotateY(-this.getYaw() * ((float)Math.PI / 180F))
                            .multiply(SPEED);
                }

                this.setVelocity(movement);
                this.move(MovementType.SELF, this.getVelocity());
            }
        }
    }

    private float normalizeAngle(float angle) {
        while (angle > 180F) angle -= 360F;
        while (angle < -180F) angle += 360F;
        return angle;
    }

    @Override
    protected void updatePassengerPosition(Entity passenger, PositionUpdater updater) {
        if (!this.hasPassenger(passenger)) return;

        List<Entity> passengers = this.getPassengerList();
        int index = passengers.indexOf(passenger);

        double offsetX = 0, offsetZ = 0;

        switch (index) {
            case 1 -> { offsetX = 0.8; offsetZ = 0.5; }
            case 2 -> { offsetX = -0.8; offsetZ = 0.5; }
            case 3 -> { offsetX = 0; offsetZ = -0.8; }
        }

        updater.accept(passenger, this.getX() + offsetX, this.getY() + 0.5, this.getZ() + offsetZ);
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        return super.damage(source, amount);
    }

    @Override
    public Iterable<ItemStack> getArmorItems() {
        return List.of();
    }

    @Override
    public ItemStack getEquippedStack(EquipmentSlot slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public void equipStack(EquipmentSlot slot, ItemStack stack) {}
}