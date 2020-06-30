package co.uk.isxander.utilities.antisnipe;

import club.sk1er.mods.core.util.MinecraftUtils;
import club.sk1er.mods.core.util.Multithreading;
import co.uk.isxander.Reference;
import co.uk.isxander.config.Config;
import me.kbrewster.exceptions.APIException;
import me.kbrewster.mojangapi.MojangAPI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import co.uk.isxander.utilities.antisnipe.AntiSnipeCMD;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;


public class AntiSnipeListener {

    public MinecraftServer mcServer() {
        return MinecraftServer.getServer();
    }

    public Minecraft mcClient() {
        return Minecraft.getMinecraft();
    }

    @SubscribeEvent
    public void prePlayerRender(RenderPlayerEvent.Pre event) {
        if (Config.isAntiSnipeEnabled()) {
            if (Config.doHidePlayers()) {
                if (AntiSnipeCMD.canHideAll()) {
                    event.entityPlayer.setInvisible(true);
                    event.setCanceled(true);
                }
                else {
                    for (int i = 0; i < AntiSnipeCMD.getHiddenPlayers().size(); i++) {
                        if (AntiSnipeCMD.getHiddenPlayers().get(i).equals(EntityPlayer.getUUID(event.entityPlayer.getGameProfile()))) {
                            event.entityPlayer.setInvisible(true);
                            event.setCanceled(true);
                            break;
                        }
                        else {
                            event.entityPlayer.setInvisible(false);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void worldLoad(WorldEvent.Load event) {
        if (Config.doWarnSnipers()) {
            MinecraftForge.EVENT_BUS.register(new AntiSnipeTickHandler());
        }
    }

//    @SubscribeEvent
//    public void renderGameOverlay(RenderGameOverlayEvent event) {
//        System.out.println(Minecraft.getMinecraft().theWorld.getScoreboard().func96539_a);
//    }
}
