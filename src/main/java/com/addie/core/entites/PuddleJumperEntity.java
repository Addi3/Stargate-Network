package com.addie.core.entites;

import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PuddleJumperEntity extends LivingEntity {

    private static final int MAX_PASSENGERS = 4;
    private static final double MAX_SPEED = 2;

    public PuddleJumperEntity(EntityType<? extends PuddleJumperEntity> type, World world) {
        super(type, world);
        this.setNoGravity(true);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 50000.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.0);
    }


    @Override
    public void travel(Vec3d movementInput) {
        LivingEntity driver = this.getControllingPassenger();

        if (driver != null) {
            this.setYaw(driver.getYaw());
            this.setPitch(driver.getPitch());
            this.prevYaw = this.getYaw();
            this.prevPitch = this.getPitch();

            float forwardInput = driver.forwardSpeed;
            float sidewaysInput = driver.sidewaysSpeed;

            float yawRad = (float) Math.toRadians(driver.getYaw());
            float pitchRad = (float) Math.toRadians(driver.getPitch());

            Vec3d forwardVec = new Vec3d(-MathHelper.sin(yawRad), 0, MathHelper.cos(yawRad));
            Vec3d rightVec = new Vec3d(forwardVec.z, 0, -forwardVec.x);

            double verticalMotion = -MathHelper.sin(pitchRad) * forwardInput;

            Vec3d motion = forwardVec.multiply(forwardInput)
                    .add(rightVec.multiply(sidewaysInput))
                    .add(0, verticalMotion, 0);

            if (motion.length() > MAX_SPEED) {
                motion = motion.normalize().multiply(MAX_SPEED);
            }

            this.setVelocity(motion);
            this.move(MovementType.SELF, this.getVelocity());
        } else {
            super.travel(movementInput);
        }
    }


    @Override
    protected boolean canAddPassenger(Entity passenger) {
        return this.getPassengerList().size() < MAX_PASSENGERS;
    }

    @Override
    public @Nullable LivingEntity getControllingPassenger() {
        List<Entity> passengers = this.getPassengerList();
        if (passengers.isEmpty()) return null;
        Entity first = passengers.get(0);
        return first instanceof LivingEntity living ? living : null;
    }

    @Override
    public ActionResult interact(net.minecraft.entity.player.PlayerEntity player, Hand hand) {
        if (!this.getWorld().isClient) {
            player.startRiding(this);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    protected void updatePassengerPosition(Entity passenger, PositionUpdater updater) {
        if (!this.hasPassenger(passenger)) return;

        List<Entity> passengers = this.getPassengerList();
        int index = passengers.indexOf(passenger);

        double offsetX = 0;
        double offsetY = 0.5;
        double offsetZ = 0;

        switch (index) {
            case 0 -> { offsetX = 1; offsetZ = 0.1; }   // Driver FR
            case 1 -> { offsetX = -1; offsetZ = 0; } // Passenger 1 FL
            case 2 -> { offsetX = -0.6; offsetZ = 0; } // Passenger 2 BR
            case 3 -> { offsetX = -0.6; offsetZ = 0; } // Passenger 3 BL
        }

        float yawRad = -this.getYaw() * MathHelper.RADIANS_PER_DEGREE;
        Vec3d rotated = new Vec3d(offsetX, 0, offsetZ).rotateY(yawRad);

        updater.accept(
                passenger,
                this.getX() + rotated.x,
                this.getY() + offsetY,
                this.getZ() + rotated.z
        );
    }


    @Override
    public Arm getMainArm() {
        return Arm.RIGHT;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {}

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {}

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