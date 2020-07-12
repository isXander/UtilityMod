package co.uk.isxander.isxanderutils.mods.antisnipe;

import co.uk.isxander.isxanderutils.Messages;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class AntiSnipeTickHandler {

    public Minecraft mcClient() {
        return Minecraft.getMinecraft();
    }

    @SubscribeEvent
    public void onTick(TickEvent event) {
        if (mcClient().theWorld == null) return;

        for (EntityPlayer player : mcClient().theWorld.playerEntities) {
            if (AntiSnipeCMD.getHiddenPlayers().contains(player.getUniqueID())) {
                if ((Math.random() * ((2 - 1) + 1)) + 1 == 1) {
//                    mcClient().thePlayer.playSound("minecraft:mob.endermen.portal", 5.0F, 1.0F);
                } else {
//                    mcClient().thePlayer.playSound("minecraft:mob.endermen.portal2", 100, 1);
                }
                Messages.sendMessage(EnumChatFormatting.RED + "A sniper is in your lobby.");
                break;
            }
        }
        MinecraftForge.EVENT_BUS.unregister(this);
    }
}
