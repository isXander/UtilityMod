package co.uk.isxander.isxanderutils.mods.mychest;

import co.uk.isxander.isxanderutils.Config;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MyChestListener {

    @SubscribeEvent
    public void playerInteractEvent(final PlayerInteractEvent event) {
        if (!Config.isMyChestEnabled()) return;
        if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
            if (!event.entityPlayer.isSneaking()) {
                TileEntity tile = event.world.getTileEntity(event.pos);
                if (tile instanceof TileEntityChest) {
                    MyChestHighlighter.addBlockPos(event.pos);
                }
            }
        }
        if (event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK) {
            TileEntity tile = event.world.getTileEntity(event.pos);
            if (tile instanceof TileEntityChest) {
                MyChestHighlighter.removeBlockPos(event.pos);
            }
        }
    }

    @SubscribeEvent
    public void changeWorld(final WorldEvent.Unload event) {
        MyChestHighlighter.clearBlockPos();
    }
}
