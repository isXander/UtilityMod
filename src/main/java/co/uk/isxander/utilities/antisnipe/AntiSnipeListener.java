package co.uk.isxander.utilities.antisnipe;

import co.uk.isxander.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class AntiSnipeListener {

    @SubscribeEvent
    public void prePlayerRender(RenderPlayerEvent.Pre event) {
        if (Config.isAntiSnipeEnabled()) {
            for (int i = 0; i < AntiSnipeCMD.getHiddenPlayers().size(); i++) {
                if (AntiSnipeCMD.getHiddenPlayers().get(i).equals(EntityPlayer.getUUID(event.entityPlayer.getGameProfile()).toString())) {
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

//    @SubscribeEvent
//    public void renderGameOverlay(RenderGameOverlayEvent event) {
//        System.out.println(Minecraft.getMinecraft().theWorld.getScoreboard().func96539_a);
//    }
}
