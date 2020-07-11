package co.uk.isxander.isxanderutils.utilities.antisnipe;

import co.uk.isxander.isxanderutils.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class AntiSnipeListener {

    private int antiSpamWarn = 0;

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
            if (antiSpamWarn == 0) {
                MinecraftForge.EVENT_BUS.register(new AntiSnipeTickHandler());
            }
            else if (antiSpamWarn >= 2) antiSpamWarn = 0;
            else antiSpamWarn++;
        }
    }

//    @SubscribeEvent
//    public void renderGameOverlay(RenderGameOverlayEvent event) {
//        System.out.println(Minecraft.getMinecraft().theWorld.getScoreboard().func96539_a);
//    }
}
