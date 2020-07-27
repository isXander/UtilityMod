package co.uk.isxander.isxanderutils.mods.cleanchat;

import club.sk1er.mods.core.ModCore;
import co.uk.isxander.isxanderutils.Initializer;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class CleanChatCMD extends CommandBase {

    @Override
    public String getCommandName() {
        return "antisnipe";
    }

    @Override
    public List getCommandAliases() {
        ArrayList al = new ArrayList<String>();
        al.add("cleanchat");
        al.add("cleanc");
        al.add("cchat");
        return al;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + getCommandName() + " [add|delete|list|options]";
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return args.length == 1 ? getListOfStringsMatchingLastWord(args, "options") : null;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("config")) {
                ModCore.getInstance().getGuiHandler().open(Initializer.instance.getUtilCFG().gui());
            }
        }
        else if (args.length == 0) {
            ModCore.getInstance().getGuiHandler().open(Initializer.instance.getUtilCFG().gui());
        }
    }
}
