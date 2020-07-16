package co.uk.isxander.isxanderutils.mods.particlemod;

import co.uk.isxander.isxanderutils.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ParticleModListener {

    private final Minecraft mcClient = Minecraft.getMinecraft();
    private WorldClient mcWorld;
    private EntityPlayerSP mcPlayer;

    @SubscribeEvent
    public void onAttack(final AttackEntityEvent event) {
        mcPlayer = mcClient.thePlayer;
        mcWorld = mcClient.theWorld;
        Entity entity = event.target;
        doRawCrit(entity);
        doRawSharp(entity);
        if (!Config.isAlwaysSharpEnabled()) return;
        doSharp(entity);
    }

    private void doRawCrit(Entity entity) {
        if (mcPlayer.onGround) return;
        attemptParticleSpawn(EnumParticleTypes.CRIT,
                entity.posX, entity.posY, entity.posZ,
                Config.getCritMultiplier() - 1);
    }

    private void doSharp(Entity entity) {
        attemptParticleSpawn(EnumParticleTypes.CRIT_MAGIC,
                entity.posX, entity.posY, entity.posZ,
                Config.getSharpMultiplier());
    }

    private void doRawSharp(Entity entity) {
        InventoryPlayer inv = Minecraft.getMinecraft().thePlayer.inventory;
        ItemStack stack = inv.mainInventory[inv.currentItem];
        try {
            stack.getItem();
        }
        catch (NullPointerException ignored) { return; }
        if (stack.getItem() instanceof ItemSword) {
            ItemSword sword = (ItemSword) stack.getItem();
            if (sword.hasEffect(stack)) {
                if (EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, stack) > 0) {
                    attemptParticleSpawn(EnumParticleTypes.CRIT_MAGIC,
                            entity.posX, entity.posY, entity.posZ,
                            Config.getSharpMultiplier() - 1);
                }
            }
        }
    }

    private void attemptParticleSpawn(EnumParticleTypes particleType, double rawX, double rawY, double rawZ, int multiplier) {
        final double x = rawX + 0.25;
        final double y = rawY + 0.4;
        final double z = rawZ + 0.25;
        for (int i = 0; i < 20 * multiplier; ++i) {
            if (mcWorld != null) {
                final double xOffset = Math.random() * 2.0 - 1.3;
                final double yOffset = Math.random() * 2.0;
                final double zOffset = Math.random() * 2.0 - 1.3;
                mcWorld.spawnParticle(particleType, x, y, z, xOffset, yOffset, zOffset);
            }
        }
    }
}
