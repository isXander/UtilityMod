package co.uk.isxander.isxanderutils.mods.namehistory;

import club.sk1er.mods.core.ModCore;
import club.sk1er.mods.core.util.MinecraftUtils;
import co.uk.isxander.isxanderutils.Config;
import co.uk.isxander.isxanderutils.Initializer;
import co.uk.isxander.isxanderutils.util.Messages;
import io.github.reflxction.hypixelapi.HypixelAPI;
import io.github.reflxction.hypixelapi.player.HypixelPlayer;
import me.kbrewster.exceptions.APIException;
import me.kbrewster.mojangapi.MojangAPI;
import me.kbrewster.mojangapi.profile.Name;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NameHistoryCMD extends CommandBase {
//    private static final HypixelAPI hypixelAPI = HypixelAPI.create("cdc3c506-61b7-4c82-9e02-610208026ff4");
//    private static final HypixelPlayer userData = hypixelAPI.getPlayer(Minecraft.getMinecraft().thePlayer.getUniqueID());

    @Override
    public String getCommandName() {
        return "namehistory";
    }

    @Override
    public List getCommandAliases() {
        ArrayList al = new ArrayList<String>();
        al.add("nh");
        al.add("nhistory");
        al.add("nameh");
        return al;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + getCommandName() + " [username|uuid]";
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        List<String> names = new ArrayList<>();
        for (EntityPlayer entityPlayer : Minecraft.getMinecraft().theWorld.playerEntities) {
            names.add(entityPlayer.getName());
        }
        return args.length == 1 ? getListOfStringsMatchingLastWord(args, names) : null;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 1) {
            try {
                Messages.sendMessage("§c" + MojangAPI.getUsername(MojangAPI.getUUID(args[0])) + "'s name history:", false);
                for (Name name : MojangAPI.getNameHistory(MojangAPI.getUUID(args[0]))) {
                    Messages.sendMessage("§a" + name.getName() + name.getChangedToAt(), false);
                }
            }
            catch (APIException e) {
                e.printStackTrace();
                Messages.sendMessage("§cAPIException: Make sure to give a valid username!");
            }
            catch (IOException e) {
                e.printStackTrace();
                Messages.sendMessage("§cIOException: Unknown error!");
            }
        }
    }
}
