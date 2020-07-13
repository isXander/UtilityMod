package co.uk.isxander.isxanderutils.mods.antisnipe;

import club.sk1er.mods.core.ModCore;
import club.sk1er.mods.core.util.Multithreading;
import co.uk.isxander.isxanderutils.Initializer;
import co.uk.isxander.isxanderutils.util.Messages;
import co.uk.isxander.isxanderutils.Reference;
import me.kbrewster.exceptions.APIException;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import org.apache.logging.log4j.Level;

import me.kbrewster.mojangapi.MojangAPI;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AntiSnipeCMD extends CommandBase {

    private static boolean hideAll = false;

    public static boolean canHideAll() {
        return hideAll;
    }

    private static List<UUID> hiddenPlayers = new ArrayList<UUID>() {{
        String path = "./config/antiSnipeHiddenPlayers.list";
        List<String> temp = new ArrayList<>();
        Multithreading.runAsync(() -> {
            try {
                File file = new File(path);
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);

                UUID uuidLine = null;
                String line;
                while((line = br.readLine()) != null) {
                    add(UUID.fromString(line));
                }
            } catch (IOException e) {
                e.printStackTrace();
            };
        });

    }};

    public static List<UUID> getHiddenPlayers() {
        return hiddenPlayers;
    }

    @Override
    public String getCommandName() {
        return "antisnipe";
    }

    @Override
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

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + getCommandName() + " [add|delete|list|options]";
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return args.length == 1 ? getListOfStringsMatchingLastWord(args, "add", "delete", "list", "options") : null;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        UUID arg1UUID;
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("add")) {
                try {
                    arg1UUID = MojangAPI.getUUID(args[1]);
                } catch (APIException | NullPointerException e) {
                    Initializer.LOGGER.log(Level.ERROR, "Inputted username could not be found in the mojang database.");
                    Messages.sendMessage("§cUsername probably doesn't exist! Otherwise, something went really wrong.");
                    return;
                } catch (IOException e) {
                    Initializer.LOGGER.log(Level.ERROR, "A severe error has occurred which prevented the " + getCommandName() + " command from functioning properly.");
                    e.printStackTrace();
                    Messages.sendMessage("§cA severe error has occurred which prevented the " + getCommandName() + " command from functioning properly. Please check logs for more details.");
                    return;
                }
                if(!getHiddenPlayers().contains(arg1UUID)) {
                    if (!Minecraft.getMinecraft().thePlayer.getUniqueID().equals(arg1UUID)) {
                        addHiddenPlayer(arg1UUID);
                        try {
                            Messages.sendMessage("§aAdded §2" + MojangAPI.getUsername(arg1UUID) + "§a to your antisnipe list.");
                        } catch (IOException | APIException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        Messages.sendMessage("§cYou can't add yourself!");
                    }

                }
                else {
                    Messages.sendMessage("§cThat player is already in your antisnipe list.");
                }
            }
            else if (args[0].equalsIgnoreCase("del") || args[0].equalsIgnoreCase("delete") || args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("rem")) {
                try {
                    arg1UUID = MojangAPI.getUUID(args[1]);
                } catch (APIException | NullPointerException e) {
                    Initializer.LOGGER.log(Level.ERROR, "Inputted username could not be found in the mojang database.");
                    Messages.sendMessage("§cUsername probably doesn't exist! Otherwise, something went really wrong.");
                    return;
                } catch (IOException e) {
                    Initializer.LOGGER.log(Level.ERROR, "A severe error has occurred which prevented the " + getCommandName() + " command from functioning properly.");
                    e.printStackTrace();
                    Messages.sendMessage("§cA severe error has occurred which prevented the " + getCommandName() + " command from functioning properly. Please check logs for more details.");
                    return;
                }
                if(getHiddenPlayers().contains(arg1UUID)) {
                    removeHiddenPlayer(arg1UUID);
                    try {
                        Messages.sendMessage("§2" + MojangAPI.getUsername(arg1UUID) + "§a was removed from your antisnipe list.");
                    } catch (IOException | APIException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Messages.sendMessage("§cUnknown argument!");
                }
            }

        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("help")) {
                Messages.sendMessage(Reference.getEPHelpMessage());
            }
            else if (args[0].equalsIgnoreCase("list")){
                if (!getHiddenPlayers().isEmpty()) {
                    Messages.sendMessage("§2§lYour AntiSnipe List", false);
                    for (int i = 0; i < getHiddenPlayers().size(); i++) {
                        try {
                            Messages.sendMessage("§a" + MojangAPI.getUsername(getHiddenPlayers().get(i)), false);
                        } catch (IOException | APIException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else {
                    Messages.sendMessage("§cNobody is on your antisnipe list!");
                }

            }
            else if (args[0].equalsIgnoreCase("config") || args[0].equalsIgnoreCase("options") || args[0].equalsIgnoreCase("menu")){
                ModCore.getInstance().getGuiHandler().open(Initializer.instance.getUtilCFG().gui());
            }
            else if (args[0].equalsIgnoreCase("addall")) {
                hideAll = true;
                Messages.sendMessage("§aNow hiding all other players.");
            }
            else if (args[0].equalsIgnoreCase("delall")) {
                hideAll = false;
                Messages.sendMessage("§aNow showing all other players.");
            }
            else if (args[0].equalsIgnoreCase("clear")) {
                for (int i = 0; i < getHiddenPlayers().size(); i++) {
                    removeHiddenPlayer(getHiddenPlayers().get(i));
                }
                Messages.sendMessage("§aCleared you antisnipe list.");
            }
            else {
                Messages.sendMessage(Reference.getEPIncorrectUsage());
            }

        }
        else if (args.length > 2) {
            Messages.sendMessage("§cToo many arguments!");
        }
        else {
            ModCore.getInstance().getGuiHandler().open(Initializer.instance.getUtilCFG().gui());
        }
    }


    public static void addHiddenPlayer(UUID uuid) {
        String path = "./config/antiSnipeHiddenPlayers.list";

        Multithreading.runAsync(() -> {
            try(FileWriter fileWriter = new FileWriter(path)) {
                for (int i = 0; i < getHiddenPlayers().size(); i++) {
                    fileWriter.write(getHiddenPlayers().get(i) + System.getProperty("line.separator"));
                }
                fileWriter.write(uuid + System.getProperty("line.separator"));
                getHiddenPlayers().add(uuid);
            } catch (IOException e) {
                Messages.sendMessage("§cAn §4IOEXCEPTION §cerror to do with username to uuid conversion occurred.");
            }
        });
    }

    public static void removeHiddenPlayer(UUID uuid) {
        String path = "./config/antiSnipeHiddenPlayers.list";
        List<UUID> allPlayerUUIDS = new ArrayList<>();
        Multithreading.runAsync(() -> {
            try {
                File file = new File(path);
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);

                String line;
                while((line = br.readLine()) != null) {
                    allPlayerUUIDS.add(UUID.fromString(line));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            allPlayerUUIDS.remove(uuid);
            hiddenPlayers = allPlayerUUIDS;
            try(FileWriter fileWriter = new FileWriter(path)) {
                for (int i = 0; i < allPlayerUUIDS.size(); i++) {
                    fileWriter.write(allPlayerUUIDS.get(i) + System.getProperty("line.separator"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

}
