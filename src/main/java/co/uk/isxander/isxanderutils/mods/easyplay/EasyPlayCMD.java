package co.uk.isxander.isxanderutils.mods.easyplay;

import club.sk1er.mods.core.ModCore;
import club.sk1er.mods.core.util.MinecraftUtils;
import co.uk.isxander.isxanderutils.Initializer;
import co.uk.isxander.isxanderutils.Reference;
import co.uk.isxander.isxanderutils.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.util.ArrayList;
import java.util.List;

public class EasyPlayCMD extends CommandBase {

    @Override
    public String getCommandName() {
        return "easyplay";
    }

    @Override
    public List getCommandAliases() {
        ArrayList al = new ArrayList<String>();
        al.add("ep");
        al.add("easyplay");
        al.add("eplay");
        al.add("ezplay");
        al.add("easyp");
        al.add("ezp");
        return al;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + getCommandName() + " [(gamemode)|options] <(gamemode players)> <(gamemode type)>> ";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        //Checks if there are any arguments.
        if (args.length >= 1) {
            //Checks the first arguments
            if (args[0].equalsIgnoreCase("help")) {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(Reference.getPrefix() + Reference.getEPHelpMessage()));
                return;
            }

            if (args[0].equalsIgnoreCase("bedwars")) {
                if (args.length >= 2) {
                    if (args[1].equalsIgnoreCase("solo") || args[1].equalsIgnoreCase("solos") || args[1].equalsIgnoreCase("s") || args[1].equalsIgnoreCase("1") || args[1].equalsIgnoreCase("1s")) {
                        Config.currentGamemode = Gamemodes.bedwarsSLO;
                        EasyPlayListener.sendPlayer(Config.currentGamemode);
                        return;
                    }
                    if (args[1].equalsIgnoreCase("doubles") || args[1].equalsIgnoreCase("double") || args[1].equalsIgnoreCase("d") || args[1].equalsIgnoreCase("2") || args[1].equalsIgnoreCase("2s")) {
                        Config.currentGamemode = Gamemodes.bedwarsDBL;
                        EasyPlayListener.sendPlayer(Config.currentGamemode);
                        return;
                    }
                    if (args[1].equalsIgnoreCase("3v3v3v3") || args[1].equalsIgnoreCase("3s") || args[1].equalsIgnoreCase("triples") || args[1].equalsIgnoreCase("3") || args[1].equalsIgnoreCase("triple") || args[1].equalsIgnoreCase("3333")) {
                        Config.currentGamemode = Gamemodes.bedwars43S;
                        EasyPlayListener.sendPlayer(Config.currentGamemode);
                        return;
                    }
                    if (args[1].equalsIgnoreCase("4v4v4v4") || args[1].equalsIgnoreCase("4s") || args[1].equalsIgnoreCase("fours") || args[1].equalsIgnoreCase("4") || args[1].equalsIgnoreCase("four") || args[1].equalsIgnoreCase("4444")) {
                        Config.currentGamemode = Gamemodes.bedwars44S;
                        EasyPlayListener.sendPlayer(Config.currentGamemode);
                        return;
                    }
                    if (args[1].equalsIgnoreCase("4v4") || args[1].equalsIgnoreCase("44") || args[1].equalsIgnoreCase("24") || args[1].equalsIgnoreCase("2fours") || args[1].equalsIgnoreCase("twofours")) {
                        Config.currentGamemode = Gamemodes.bedwars4V4;
                        EasyPlayListener.sendPlayer(Config.currentGamemode);
                        return;
                    }
                }

                MinecraftUtils.sendMessage(Reference.getPrefix(), Reference.getEPIncorrectUsage());
                return;
            }
            if (args[0].equalsIgnoreCase("skywars")) {
                if (args.length >= 3) {
                    if (args[1].equalsIgnoreCase("solo")) {
                        if (args[2].equalsIgnoreCase("insane") || args[2].equalsIgnoreCase("i")) {
                            Config.currentGamemode = Gamemodes.skywarsSLI;
                            EasyPlayListener.sendPlayer(Config.currentGamemode);
                            return;
                        }
                        if (args[2].equalsIgnoreCase("normal") || args[2].equalsIgnoreCase("n")) {
                            Config.currentGamemode = Gamemodes.skywarsSLN;
                            EasyPlayListener.sendPlayer(Config.currentGamemode);
                            return;
                        }
                        if (args[2].equalsIgnoreCase("mega")) {
                            Config.currentGamemode = Gamemodes.skywarsMGN;
                            EasyPlayListener.sendPlayer(Config.currentGamemode);
                        }
                    }
                    if (args[1].equalsIgnoreCase("teams") || args[1].equalsIgnoreCase("doubles") || args[1].equalsIgnoreCase("double")) {
                        if (args[2].equalsIgnoreCase("insane") || args[2].equalsIgnoreCase("i")) {
                            Config.currentGamemode = Gamemodes.skywarsTMI;
                            EasyPlayListener.sendPlayer(Config.currentGamemode);
                            return;
                        }
                        if (args[2].equalsIgnoreCase("normal") || args[2].equalsIgnoreCase("n")) {
                            Config.currentGamemode = Gamemodes.skywarsTMN;
                            EasyPlayListener.sendPlayer(Config.currentGamemode);
                            return;
                        }
                        if (args[2].equalsIgnoreCase("mega")) {
                            Config.currentGamemode = Gamemodes.skywarsMGD;
                            EasyPlayListener.sendPlayer(Config.currentGamemode);
                        }
                    }
                }

                if (args.length == 2) {
                    if (args[1].equalsIgnoreCase("ranked") || args[1].equalsIgnoreCase("rank")) {
                        Config.currentGamemode = Gamemodes.skywarsRNK;
                        EasyPlayListener.sendPlayer(Config.currentGamemode);
                        return;
                    }
                }
            }

            //Doesn't match any known arguments.
            MinecraftUtils.sendMessage(Reference.getPrefix(), Reference.getEPIncorrectUsage());

        }
        //Doesn't have any arguments.
        else {
            ModCore.getInstance().getGuiHandler().open(Initializer.instance.getUtilCFG().gui());
        }

    }

}
