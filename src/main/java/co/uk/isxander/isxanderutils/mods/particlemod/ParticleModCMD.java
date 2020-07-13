package co.uk.isxander.isxanderutils.mods.particlemod;

import club.sk1er.mods.core.ModCore;
import co.uk.isxander.isxanderutils.Config;
import co.uk.isxander.isxanderutils.Initializer;
import co.uk.isxander.isxanderutils.util.Messages;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

import java.util.List;

public class ParticleModCMD extends CommandBase {

    @Override
    public String getCommandName() {
        return "particlemod";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + getCommandName() + " [toggle|sharp|crit] <multiplier>";
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, "toggle", "sharp", "crit");
        }
        else if (args.length == 2) {
            return getListOfStringsMatchingLastWord(args, "<multiplier>");
        }
        return null;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0) {
            ModCore.getInstance().getGuiHandler().open(Initializer.instance.getUtilCFG().gui());
        }
        else if (args[0].equalsIgnoreCase("toggle")) {
            Config.setMyChestEnabled(!Config.isMyChestEnabled());
            Messages.sendMessage("MyChest is now " + Config.isMyChestEnabled());
        }
        else if (args[0].equalsIgnoreCase("crit")) {
            if (args.length == 2) {
                if (isNumeric(args[1])) {
                    Config.setCritMultiplier(Integer.parseInt(args[1]));
                    Messages.sendMessage("Critical particle multiplier has been set to " + args[1]);
                }
                else {
                    Messages.sendMessage("Multiplier is not numeric.");
                }
            }
            else {
                Messages.sendMessage("Please give a multiplier!");
            }
        }
        else if (args[0].equalsIgnoreCase("sharp")) {
            if (args.length == 2) {
                if (isNumeric(args[1])) {
                    Config.setSharpMultiplier(Integer.parseInt(args[1]));
                    Messages.sendMessage("Sharpness particle multiplier has been set to " + args[1]);
                }
                else {
                    Messages.sendMessage("Multiplier is not numeric.");
                }
            }
            else {
                Messages.sendMessage("Please give a multiplier!");
            }
        }
        else ModCore.getInstance().getGuiHandler().open(Initializer.instance.getUtilCFG().gui());
    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) return false;
        try {
            Integer.parseInt(strNum);
        }
        catch (NumberFormatException ignored) {
            return false;
        }
        return true;
    }
}
