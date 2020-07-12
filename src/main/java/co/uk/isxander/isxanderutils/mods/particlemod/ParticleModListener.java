package co.uk.isxander.isxanderutils.mods.particlemod;

import co.uk.isxander.isxanderutils.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ParticleModListener {

    private final Minecraft mcClient = Minecraft.getMinecraft();
    private WorldClient mcWorld;
    private EntityPlayerSP mcPlayer;

    @SubscribeEvent
    public void onAttack(AttackEntityEvent event) {
        mcPlayer = mcClient.thePlayer;
        mcWorld = mcClient.theWorld;
        Entity entity = event.target;
        if (!(entity instanceof EntityPlayer)) return;
        EntityPlayer entityPlayer = (EntityPlayer) entity;
        doCrit(entityPlayer);
//        try {
//            if (EnchantmentHelper.getEnchantments(mcPlayer.inventory.getCurrentItem()).get(16) > 1) doRawSharp(entityPlayer);
//        }
//        catch (NullPointerException ignored) {}
        if (!Config.isAlwaysSharpEnabled()) return;
        doSharp(entityPlayer);
    }

    private void doCrit(EntityPlayer entityPlayer) {
        if (mcPlayer.onGround) return;
        if (!shouldSpawnParticles(entityPlayer)) return;
        attemptParticleSpawn(EnumParticleTypes.CRIT, entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, Config.getCritMultiplier() - 1);
    }

    private void doSharp(EntityPlayer entityPlayer) {
        System.out.println("raw sharp");
        if (!shouldSpawnParticles(entityPlayer)) return;
        attemptParticleSpawn(EnumParticleTypes.CRIT_MAGIC, entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, Config.getSharpMultiplier());
    }

    private void doRawSharp(EntityPlayer entityPlayer) {
        if (!shouldSpawnParticles(entityPlayer)) return;
        attemptParticleSpawn(EnumParticleTypes.CRIT_MAGIC, entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, Config.getSharpMultiplier() - 1);
    }

    private boolean shouldSpawnParticles(EntityPlayer entityAsPlayer) {
        return (mcPlayer.canAttackPlayer(entityAsPlayer) && entityAsPlayer.canAttackPlayer(mcPlayer));
    }

    private void attemptParticleSpawn(EnumParticleTypes particleType, double rawX, double rawY, double rawZ, int multiplier) {
        for (int i = 0; i < 20 * multiplier; ++i) {
            final double x = rawX;
            final double y = rawY + 0.8;
            final double z = rawZ;
            final double xOffset = Math.random() * 2.0 - 1.3;
            final double yOffset = Math.random() * 0.8;
            final double zOffset = Math.random() * 2.0 - 1.3;
            mcWorld.spawnParticle(particleType, x, y, z, xOffset, yOffset, zOffset);
        }
    }
}
