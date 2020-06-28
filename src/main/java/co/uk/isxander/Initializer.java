package co.uk.isxander;

import co.uk.isxander.Utilities.antisnipe.AntiSnipeListener;
import co.uk.isxander.Utilities.antisnipe.AntiSnipeCMD;
import co.uk.isxander.Utilities.easyplay.easyPlayCMD;
import co.uk.isxander.Utilities.easyplay.easyPlayListner;

import co.uk.isxander.Config.Config;
import co.uk.isxander.Modcore.ModCoreInstaller;
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

        //antiSnipe
        LOGGER.log(Level.INFO, "Initializing AntiSnipe...");
        ClientCommandHandler.instance.registerCommand(new AntiSnipeCMD());
        MinecraftForge.EVENT_BUS.register(new AntiSnipeListener());
        //easyPlay
        LOGGER.log(Level.INFO, "Initializing EasyPlay...");
        ClientCommandHandler.instance.registerCommand(new easyPlayCMD());
        MinecraftForge.EVENT_BUS.register(new easyPlayListner());
        LOGGER.log(Level.INFO, "Finished Initialization Stage");

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        LOGGER.log(Level.INFO, "Starting Post Initialization Stage...");
        LOGGER.log(Level.INFO, "Couldn't find any post-initializers.");
        LOGGER.log(Level.INFO, "Finished Post Initialization Stage");
    }
}
