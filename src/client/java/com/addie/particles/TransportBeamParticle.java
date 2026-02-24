package com.addie.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class TransportBeamParticle extends SpriteBillboardParticle {

    private final SpriteProvider spriteProvider;

    protected TransportBeamParticle(ClientWorld world,
                                    double x, double y, double z,
                                    double velocityX, double velocityY, double velocityZ,
                                    SpriteProvider spriteProvider) {
        super(world, x, y, z, velocityX, velocityY, velocityZ);

        this.spriteProvider = spriteProvider;

        this.scale = 0.4f + random.nextFloat() * 0.3f;
        this.maxAge = 20 + random.nextInt(10);

        this.velocityX *= 0.05;
        this.velocityZ *= 0.05;
        this.velocityY = 0.05 + random.nextDouble() * 0.05;

        this.setSpriteForAge(spriteProvider);

        this.red = 1.0f;
        this.green = 1.0f;
        this.blue = 1.0f;

        this.alpha = 0.9f;
    }

    @Override
    public void tick() {
        super.tick();

        this.setSpriteForAge(spriteProvider);

        this.velocityY += 0.002;

        this.alpha = 1.0f - ((float) this.age / this.maxAge);

        this.scale = MathHelper.lerp(
                (float) age / maxAge,
                0.5f,
                0.1f
        );

        if (this.age >= this.maxAge) {
            this.markDead();
        }
    }

    @Override
    protected int getBrightness(float tint) {
        return 0xF000F0;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {

        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(DefaultParticleType type,
                                       ClientWorld world,
                                       double x, double y, double z,
                                       double velocityX, double velocityY, double velocityZ) {
            return new TransportBeamParticle(world, x, y, z, velocityX, velocityY, velocityZ, spriteProvider);
        }
    }
}