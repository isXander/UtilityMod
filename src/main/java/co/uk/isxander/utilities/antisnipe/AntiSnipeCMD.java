package co.uk.isxander.utilities.antisnipe;

import club.sk1er.mods.core.ModCore;
import club.sk1er.mods.core.util.MinecraftUtils;
import co.uk.isxander.Initializer;
import co.uk.isxander.Reference;
import me.kbrewster.exceptions.APIException;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import org.apache.logging.log4j.Level;

import me.kbrewster.mojangapi.MojangAPI;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AntiSnipeCMD extends CommandBase {

    private static List<String> hiddenPlayers = new ArrayList<String>() {{
        add("invalid");
    }};

    public static List<String> getHiddenPlayers() {
        return hiddenPlayers;
    }

    public String getCommandName() {
        return "antisnipe";
    }

    public List getCommandAliases() {
        ArrayList al = new ArrayList<String>();
        al.add("as");
        al.add("antisnipe");
        al.add("nosnipe");
        al.add("snipe");
        al.add("nsnipe");
        al.add("asnipe");
        al.add("ns");
        return al;
    }

    public String getCommandUsage(ICommandSender sender) {
        return "";
    }

    public int getRequiredPermissionLevel() {
        return -1;
    }

    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 2) {
            if (args[0].equals("add")) {
                try {
                    addHiddenPlayer(MojangAPI.getUUID(args[1]).toString());
                } catch (APIException e) {
                    Initializer.LOGGER.log(Level.ERROR, "Inputted username could not be found in the mojang database.");
                    MinecraftUtils.sendMessage(Reference.getPrefix(), "§cUsername probably doesn't exist! Otherwise, something went really wrong.");
                    return;
                } catch (IOException e) {
                    Initializer.LOGGER.log(Level.ERROR, "A severe error has occurred which prevented the " + getCommandName() + " command from functioning properly.");
                    e.printStackTrace();
                    MinecraftUtils.sendMessage(Reference.getPrefix(), "§cA severe error has occurred which prevented the " + getCommandName() + " command from functioning properly. Please check logs for more details.");
                    return;
                }

            }
            MinecraftUtils.sendMessage(Reference.getPrefix(), "§aAdded §2" + args[1] + "§a to your antisnipe list.");

        } else if (args.length == 1) {
            if (args[0].equals("help")) {
                MinecraftUtils.sendMessage(Reference.getPrefix(), Reference.getEPHelpMessage());
            } else {
                MinecraftUtils.sendMessage(Reference.getPrefix(), Reference.getEPIncorrectUsage());
            }

        } else if (args.length > 2) {
            MinecraftUtils.sendMessage(Reference.getPrefix(), "§cToo many arguments!");
        } else {
            ModCore.getInstance().getGuiHandler().open(Initializer.instance.getUtilCFG().gui());
        }
    }


    public static void addHiddenPlayer(String uuid) {
        String path = "./config/isXanderUtilHidden.list";
        
        try(FileWriter fileWriter = new FileWriter(path)) {
            for (int i = 0; i < getHiddenPlayers().size(); i++) {
                fileWriter.write(getHiddenPlayers().get(i) + System.getProperty("line.separator"));
            }
            fileWriter.write(uuid + System.getProperty("line.separator"));
            getHiddenPlayers().add(uuid);
        } catch (IOException e) {
            MinecraftUtils.sendMessage(Reference.getPrefix(), "§cAn §4IOEXCEPTION §cerror to do with username to uuid conversion occurred.");
        }

    }

}
