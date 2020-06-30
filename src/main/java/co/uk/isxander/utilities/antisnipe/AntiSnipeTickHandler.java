package co.uk.isxander.utilities.antisnipe;

import club.sk1er.mods.core.util.MinecraftUtils;
import co.uk.isxander.Reference;
import me.kbrewster.exceptions.APIException;
import me.kbrewster.mojangapi.MojangAPI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class AntiSnipeTickHandler {

    public Minecraft mcClient() {
        return Minecraft.getMinecraft();
    }

    @SubscribeEvent
    public void onTick(TickEvent event) {
        if (Minecraft.getMinecraft().theWorld == null) return;

        for (EntityPlayer player : mcClient().theWorld.playerEntities) {
            if (AntiSnipeCMD.getHiddenPlayers().contains(player.getUniqueID())) {
                if ((Math.random() * ((2 - 1) + 1)) + 1 == 1) {
                    mcClient().thePlayer.playSound("minecraft:mob.endermen.portal", 5.0F, 1.0F);
                }
                else {
                    mcClient().thePlayer.playSound("minecraft:mob.endermen.portal2", 100, 1);
                }
                MinecraftUtils.sendMessage(Reference.getPrefix(), EnumChatFormatting.RED + "A sniper is in your lobby.");
            }
        }

        MinecraftForge.EVENT_BUS.unregister(this);
    }
}
