package co.uk.isxander.isxanderutils.utilities.mychest;

import club.sk1er.mods.core.ModCore;
import co.uk.isxander.isxanderutils.Initializer;
import co.uk.isxander.isxanderutils.Messages;
import co.uk.isxander.isxanderutils.config.Config;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

import java.util.List;

public class MyChestCMD extends CommandBase {
    @Override
    public String getCommandName() {
        return "mychest";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + getCommandName() + " [toggle]";
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return args.length == 1 ? getListOfStringsMatchingLastWord(args, "toggle") : null;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0) {
            ModCore.getInstance().getGuiHandler().open(Initializer.instance.getUtilCFG().gui());
            return;
        }
        if (args[0].equalsIgnoreCase("toggle")) {
            Config.setMyChestEnabled(!Config.isMyChestEnabled());
            Messages.sendMessage("MyChest is now " + Config.isMyChestEnabled());
        }
        else ModCore.getInstance().getGuiHandler().open(Initializer.instance.getUtilCFG().gui());
    }
}
