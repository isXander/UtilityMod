package co.uk.isxander.isxanderutils.mods.autowarp;

import club.sk1er.mods.core.util.MinecraftUtils;
import co.uk.isxander.isxanderutils.Reference;
import co.uk.isxander.isxanderutils.mods.antisnipe.AntiSnipeCMD;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class AutoWarpTickHandler {

    @SubscribeEvent
    public void onTick(TickEvent event) {
        if (Minecraft.getMinecraft().theWorld == null) return;

        

        for (EntityPlayer player : Minecraft.getMinecraft().theWorld.playerEntities) {
            if (AntiSnipeCMD.getHiddenPlayers().contains(player.getUniqueID())) {
                MinecraftUtils.sendMessage(Reference.getPrefix(), EnumChatFormatting.RED + "A sniper is in your lobby.");
            }
        }

        MinecraftForge.EVENT_BUS.unregister(this);
    }
}
