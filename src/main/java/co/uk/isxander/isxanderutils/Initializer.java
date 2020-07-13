package co.uk.isxander.isxanderutils;

import co.uk.isxander.isxanderutils.mods.namehistory.NameHistoryCMD;
import co.uk.isxander.isxanderutils.util.events.RenderEvent.EventTriggers;
import co.uk.isxander.isxanderutils.mods.particlemod.ParticleModCMD;
import co.uk.isxander.isxanderutils.mods.particlemod.ParticleModListener;
import co.uk.isxander.isxanderutils.mods.antisnipe.AntiSnipeListener;
import co.uk.isxander.isxanderutils.mods.antisnipe.AntiSnipeCMD;
import co.uk.isxander.isxanderutils.mods.cleanchat.CleanChatCMD;
import co.uk.isxander.isxanderutils.mods.cleanchat.CleanChatListener;
import co.uk.isxander.isxanderutils.mods.easyplay.EasyPlayCMD;
import co.uk.isxander.isxanderutils.mods.easyplay.EasyPlayListener;

import co.uk.isxander.isxanderutils.mods.mychest.MyChestCMD;
import co.uk.isxander.isxanderutils.mods.mychest.MyChestHighlighter;
import co.uk.isxander.isxanderutils.mods.mychest.MyChestListener;
import co.uk.isxander.launch.modcore.ModCoreInstaller;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class Initializer {
    public static final Logger LOGGER = LogManager.getLogger(Reference.MOD_NAME);

    public Config utilCFG;
    @Instance(Reference.MOD_ID)
    public static Initializer instance;

    public static MyChestHighlighter myChestHighlighter = new MyChestHighlighter();

    public Config getUtilCFG() {
        return utilCFG;
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER.log(Level.INFO, "Starting Pre Initialization Stage...");
        LOGGER.log(Level.INFO, "Finished Pre Initialization Stage");
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        LOGGER.log(Level.INFO, "Starting Initialization Stage...");
        LOGGER.log(Level.INFO, "Found initializers.");
        ModCoreInstaller.initializeModCore(Minecraft.getMinecraft().mcDataDir);

        LOGGER.log(Level.INFO, "Loading configuration.");
        utilCFG = new Config();
        utilCFG.preload();

        // Main
        LOGGER.log(Level.INFO, "Initializing isXander's Utility Mod...");
        ClientCommandHandler.instance.registerCommand(new MainCMD());
        // Anti Snipe
        LOGGER.log(Level.INFO, "Initializing AntiSnipe...");
        ClientCommandHandler.instance.registerCommand(new AntiSnipeCMD());
        MinecraftForge.EVENT_BUS.register(new AntiSnipeListener());
        // Easy Play
        LOGGER.log(Level.INFO, "Initializing EasyPlay...");
        ClientCommandHandler.instance.registerCommand(new EasyPlayCMD());
        MinecraftForge.EVENT_BUS.register(new EasyPlayListener());
        // Clean Chat
        LOGGER.log(Level.INFO, "Initializing CleanChat...");
        ClientCommandHandler.instance.registerCommand(new CleanChatCMD());
        MinecraftForge.EVENT_BUS.register(new CleanChatListener());
        // My Chest
        LOGGER.log(Level.INFO, "Initializing MyChest...");
        ClientCommandHandler.instance.registerCommand(new MyChestCMD());
        MinecraftForge.EVENT_BUS.register(new MyChestListener());
        MinecraftForge.EVENT_BUS.register(myChestHighlighter);
        MinecraftForge.EVENT_BUS.register(new EventTriggers());
        // Particle Mod
        LOGGER.log(Level.INFO, "Initializing Particle Mod...");
        ClientCommandHandler.instance.registerCommand(new ParticleModCMD());
        MinecraftForge.EVENT_BUS.register(new ParticleModListener());
        LOGGER.log(Level.INFO, "Initializing Name History Mod...");
        ClientCommandHandler.instance.registerCommand(new NameHistoryCMD());
        LOGGER.log(Level.INFO, "Finished Initialization Stage");

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        LOGGER.log(Level.INFO, "Starting Post Initialization Stage...");
        LOGGER.log(Level.INFO, "Couldn't find any post-initializers.");
        LOGGER.log(Level.INFO, "Finished Post Initialization Stage");
    }
}
