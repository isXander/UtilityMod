package co.uk.isxander.isxanderutils.mods.mychest;

import co.uk.isxander.isxanderutils.Config;
import co.uk.isxander.isxanderutils.util.colors.Color;
import co.uk.isxander.isxanderutils.util.colors.Colors;
import co.uk.isxander.isxanderutils.util.events.RenderEvent.RenderEvent;
import co.uk.isxander.isxanderutils.util.tesselation.GeometryMasks;
import co.uk.isxander.isxanderutils.util.tesselation.GeometryTessellator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class MyChestHighlighter {

    private final static List<BlockPos> blockPositions = new ArrayList<>();
    public WorldRenderer renderer;
    private static final Color[] colors =
            new Color[]{ Colors.WHITE, Colors.BLACK, Colors.RED, Colors.GREEN, Colors.BLUE, Colors.ORANGE, Colors.PURPLE, Colors.GRAY, Colors.DARK_RED, Colors.YELLOW };

    @SubscribeEvent
    public void onRender(final RenderEvent event) {
        if (!Config.isMyChestEnabled()) return;
        renderer = event.getBuffer();
        event.getBuffer().begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_COLOR);
        for (TileEntity tile : Minecraft.getMinecraft().theWorld.loadedTileEntityList) {
            if (tile instanceof TileEntityChest) {
                BlockPos pos = tile.getPos();
                if (!getBlockPositions().contains(pos)) continue;
                if (!Config.doEsp()) {
                    GlStateManager.enableDepth();
                    GlStateManager.depthFunc(GL11.GL_LEQUAL);
                }
                GeometryTessellator.drawCuboid(event.getBuffer(), pos, GeometryMasks.Line.ALL, colors[Config.getChestColor()].toBuffer());
            }
        }
        event.getTessellator().draw();
    }

    public static List<BlockPos> getBlockPositions() {
        return blockPositions;
    }
    public static BlockPos getBlockPos(int index) {
        return blockPositions.get(index);
    }
    public static void addBlockPos(BlockPos pos) {
        blockPositions.add(pos);
    }
    public static void removeBlockPos(BlockPos pos) {
        blockPositions.remove(pos);
    }
    public static void clearBlockPos() {
        blockPositions.clear();
    }
}
