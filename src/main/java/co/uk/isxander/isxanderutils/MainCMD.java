package co.uk.isxander.isxanderutils;

import club.sk1er.mods.core.ModCore;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

import java.util.ArrayList;
import java.util.List;

public class MainCMD extends CommandBase {
    public String getCommandName() {
        return "isxanderutils";
    }

    public List getCommandAliases() {
        ArrayList al = new ArrayList<String>();
        al.add("isxanderutils");
        al.add("isxanderutil");
        al.add("isxanderu");
        al.add("iutils");
        al.add("iutil");
        al.add("ixu");
        al.add("iu");
        return al;
    }

    public String getCommandUsage(ICommandSender sender) {
        return "/" + getCommandName();
    }

    public int getRequiredPermissionLevel() {
        return -1;
    }

    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length > 1) {
            Messages.sendMessage("There aren't any subcommands!");
        }
        ModCore.getInstance().getGuiHandler().open(Initializer.instance.getUtilCFG().gui());
    }
}
