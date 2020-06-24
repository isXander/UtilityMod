package co.uk.isxander.easyplay;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;

import akka.dispatch.sysmsg.EarliestFirstSystemMessageList;
import club.sk1er.mods.core.util.MinecraftUtils;
import com.google.gson.JsonParser;

import club.sk1er.mods.core.ModCore;
import co.uk.isxander.easyplay.config.EasyPlayConfig;
import co.uk.isxander.easyplay.modcore.ModCoreInstaller;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static co.uk.isxander.easyplay.Listeners.sendPlayer;
import co.uk.isxander.easyplay.commands.*;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class EasyPlay {

	public EasyPlayConfig easyPlayCFG;
	@Mod.Instance("easyplay")
	public static EasyPlay instance;

	public EasyPlayConfig getEasyPlayCFG() {
		return easyPlayCFG;
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) 
	{
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		ModCoreInstaller.initializeModCore(Minecraft.getMinecraft().mcDataDir);

		easyPlayCFG = new EasyPlayConfig();
		easyPlayCFG.preload();

		ClientCommandHandler.instance.registerCommand(new PlayCommand());
		MinecraftForge.EVENT_BUS.register(new Listeners());

	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) 
	{

	}
}
