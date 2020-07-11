package co.uk.isxander.isxanderutils.util.events.RenderEvent;

import co.uk.isxander.isxanderutils.Initializer;
import co.uk.isxander.isxanderutils.util.tesselation.GeometryTessellator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import net.minecraft.util.Vec3;

public class EventTriggers {
    private static final GeometryTessellator TESSELLATOR = new GeometryTessellator(Initializer.myChestHighlighter.renderer);
    private Minecraft mcClient = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent event) {
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
        GlStateManager.shadeModel(GL11.GL_SMOOTH);
        GlStateManager.disableDepth();

        Vec3 renderPos = getInterpolatedPos(mcClient.getRenderViewEntity(), event.partialTicks);

        RenderEvent e = new RenderEvent(TESSELLATOR, renderPos, event.partialTicks);
        e.resetTranslation();
        MinecraftForge.EVENT_BUS.post(e);

        GlStateManager.shadeModel(GL11.GL_FLAT);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.enableCull();
        GlStateManager.popMatrix();
    }

    public static Vec3 getInterpolatedAmount(Entity entity, double x, double y, double z) {
        return new Vec3(
                (entity.posX - entity.lastTickPosX) * x,
                (entity.posY - entity.lastTickPosY) * y,
                (entity.posZ - entity.lastTickPosZ) * z);
    }

    public static Vec3 getInterpolatedAmount(Entity entity, double ticks) {
        return getInterpolatedAmount(entity, ticks, ticks, ticks);
    }

    public static Vec3 getInterpolatedPos(Entity entity, double ticks) {
        return new Vec3(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ)
                .add(getInterpolatedAmount(entity, ticks));
    }
}
