package co.uk.isxander.isxanderutils.utilities.mychest;

import co.uk.isxander.isxanderutils.Initializer;
import co.uk.isxander.isxanderutils.config.Config;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Level;

public class MyChestListener {

    @SubscribeEvent
    public void playerInteractEvent(PlayerInteractEvent event) {
        if (!Config.isMyChestEnabled()) return;
        if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
            if (!event.entityPlayer.isSneaking()) {
                TileEntity tile = event.world.getTileEntity(event.pos);
                if (tile instanceof TileEntityChest) {
                    Initializer.LOGGER.log(Level.INFO, "Player opened chest.");
                    MyChestHighlighter.addBlockPos(event.pos);
                }
            }
        }
        if (event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK) {
            TileEntity tile = event.world.getTileEntity(event.pos);
            if (tile instanceof TileEntityChest) {
                Initializer.LOGGER.log(Level.INFO, "Player broke chest.");
                MyChestHighlighter.removeBlockPos(event.pos);
            }
        }
    }

    @SubscribeEvent
    public void changeWorld(WorldEvent.Unload event) {
        MyChestHighlighter.clearBlockPos();
    }
}
