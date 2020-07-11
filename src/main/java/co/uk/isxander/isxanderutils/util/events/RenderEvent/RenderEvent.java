package co.uk.isxander.isxanderutils.util.events.RenderEvent;

import co.uk.isxander.isxanderutils.util.tesselation.GeometryTessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.eventhandler.Event;

/**
 * Created on 5/5/2017 by fr1kin
 */
public class RenderEvent extends Event {

    private final GeometryTessellator tessellator;
    private final Vec3 renderPos;
    private final double partialTicks;

    public RenderEvent(GeometryTessellator tessellator, Vec3 renderPos, double partialTicks) {
        this.tessellator = tessellator;
        this.renderPos = renderPos;
        this.partialTicks = partialTicks;
    }

    public GeometryTessellator getTessellator() {
        return tessellator;
    }

    public WorldRenderer getBuffer() {
        return tessellator.getWorldRenderer();
    }

    public Vec3 getRenderPos() {
        return renderPos;
    }

    public void setTranslation(Vec3 translation) {
        getBuffer().setTranslation(-translation.xCoord, -translation.yCoord, -translation.zCoord);
    }

    public void resetTranslation() {
        setTranslation(renderPos);
    }

    public double getPartialTicks() {
        return partialTicks;
    }
}
