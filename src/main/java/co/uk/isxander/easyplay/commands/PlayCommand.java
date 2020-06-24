package co.uk.isxander.easyplay.commands;

import club.sk1er.mods.core.ModCore;
import club.sk1er.mods.core.util.MinecraftUtils;
import co.uk.isxander.easyplay.EasyPlay;
import co.uk.isxander.easyplay.Reference;
import co.uk.isxander.easyplay.config.EasyPlayConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.util.ArrayList;
import java.util.List;

import static co.uk.isxander.easyplay.Listeners.sendPlayer;

public class PlayCommand extends CommandBase
{

    public String getCommandName()
    {
        return "easyplay";
    }

    public List getCommandAliases()
    {
        ArrayList al = new ArrayList<String>();
        al.add("ep");
        al.add("easyplay");
        al.add("eplay");
        al.add("ezplay");
        al.add("easyp");
        al.add("ezp");
        return al;
    }

    public String getCommandUsage(ICommandSender sender)
    {
        return "";
    }

    public int getRequiredPermissionLevel() {
        return -1;
    }


    public void processCommand(ICommandSender sender, String[] args) throws CommandException
    {
        //Checks if there are any arguments.
        if(args.length >= 1)
        {
            //Checks the first arguments
            if(args[0].equalsIgnoreCase("help"))
            {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(Reference.getPrefix() + Reference.getHelpMessage()));
                return;
            }

            if(args[0].equalsIgnoreCase("bedwars"))
            {
                if(args.length >= 2)
                {
                    if(args[1].equalsIgnoreCase("solo") || args[1].equalsIgnoreCase("solos") || args[1].equalsIgnoreCase("s") || args[1].equalsIgnoreCase("1") || args[1].equalsIgnoreCase("1s"))
                    {
                        EasyPlayConfig.currentGamemode = Reference.bedwarsSLO;
                        sendPlayer(EasyPlayConfig.currentGamemode);
                        return;
                    }
                    if(args[1].equalsIgnoreCase("doubles") || args[1].equalsIgnoreCase("double") || args[1].equalsIgnoreCase("d") || args[1].equalsIgnoreCase("2") || args[1].equalsIgnoreCase("2s"))
                    {
                        EasyPlayConfig.currentGamemode = Reference.bedwarsDBL;
                        sendPlayer(EasyPlayConfig.currentGamemode);
                        return;
                    }
                    if(args[1].equalsIgnoreCase("3v3v3v3") || args[1].equalsIgnoreCase("3s") || args[1].equalsIgnoreCase("triples") || args[1].equalsIgnoreCase("3") || args[1].equalsIgnoreCase("triple") || args[1].equalsIgnoreCase("3333"))
                    {
                        EasyPlayConfig.currentGamemode = Reference.bedwars43S;
                        sendPlayer(EasyPlayConfig.currentGamemode);
                        return;
                    }
                    if(args[1].equalsIgnoreCase("4v4v4v4") || args[1].equalsIgnoreCase("4s") || args[1].equalsIgnoreCase("fours") || args[1].equalsIgnoreCase("4") || args[1].equalsIgnoreCase("four") || args[1].equalsIgnoreCase("4444"))
                    {
                        EasyPlayConfig.currentGamemode = Reference.bedwars44S;
                        sendPlayer(EasyPlayConfig.currentGamemode);
                        return;
                    }
                    if(args[1].equalsIgnoreCase("4v4") || args[1].equalsIgnoreCase("44")  || args[1].equalsIgnoreCase("24") || args[1].equalsIgnoreCase("2fours") || args[1].equalsIgnoreCase("twofours"))
                    {
                        EasyPlayConfig.currentGamemode = Reference.bedwars4V4;
                        sendPlayer(EasyPlayConfig.currentGamemode);
                        return;
                    }
                }

                MinecraftUtils.sendMessage(Reference.getPrefix(), Reference.getIncorrectUsage());
                return;
            }
            if(args[0].equalsIgnoreCase("skywars"))
            {
                if(args.length >= 3)
                {
                   if(args[1].equalsIgnoreCase("solo"))
                   {
                       if(args[2].equalsIgnoreCase("insane") || args[2].equalsIgnoreCase("i"))
                       {
                           EasyPlayConfig.currentGamemode = Reference.skywarsSLI;
                           sendPlayer(EasyPlayConfig.currentGamemode);
                           return;
                       }
                       if(args[2].equalsIgnoreCase("normal") || args[2].equalsIgnoreCase("n"))
                       {
                           EasyPlayConfig.currentGamemode = Reference.skywarsSLN;
                           sendPlayer(EasyPlayConfig.currentGamemode);
                           return;
                       }
                       if(args[2].equalsIgnoreCase("mega"))
                       {
                           EasyPlayConfig.currentGamemode = Reference.skywarsMGN;
                           sendPlayer(EasyPlayConfig.currentGamemode);
                       }
                   }
                   if(args[1].equalsIgnoreCase("teams") || args[1].equalsIgnoreCase("doubles") || args[1].equalsIgnoreCase("double"))
                   {
                       if(args[2].equalsIgnoreCase("insane") || args[2].equalsIgnoreCase("i"))
                       {
                            EasyPlayConfig.currentGamemode = Reference.skywarsTMI;
                            sendPlayer(EasyPlayConfig.currentGamemode);
                            return;
                       }
                       if(args[2].equalsIgnoreCase("normal") || args[2].equalsIgnoreCase("n"))
                       {
                            EasyPlayConfig.currentGamemode = Reference.skywarsTMN;
                            sendPlayer(EasyPlayConfig.currentGamemode);
                            return;
                       }
                       if(args[2].equalsIgnoreCase("mega"))
                       {
                           EasyPlayConfig.currentGamemode = Reference.skywarsMGD;
                           sendPlayer(EasyPlayConfig.currentGamemode);
                       }
                   }

                }

                if(args.length == 2)
                {
                    if(args[1].equalsIgnoreCase("ranked") || args[1].equalsIgnoreCase("rank"))
                    {
                        EasyPlayConfig.currentGamemode = Reference.skywarsRNK;
                        sendPlayer(EasyPlayConfig.currentGamemode);
                        return;
                    }
                }

            }

            //Doesn't match any known arguments.
            MinecraftUtils.sendMessage(Reference.getPrefix(), Reference.getIncorrectUsage());
            return;

        }
        //Doesn't have any arguments.
        else
        {
            ModCore.getInstance().getGuiHandler().open(EasyPlay.instance.getEasyPlayCFG().gui());
        }

    }

}
