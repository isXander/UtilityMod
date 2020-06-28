package co.uk.isxander;

import net.minecraft.client.resources.I18n;


public class Reference {

    public static final String MOD_ID = "isxanderutils";
    public static final String MOD_NAME = "isXander's Utility Mod";
    public static final String VERSION = "0.5";
    public static final String AUTHOR = "isXander";


    public static String getPrefix() {
        return I18n.format("command.prefix");
    }

    public static String getEPIncorrectUsage() {
        return I18n.format("easyplay.command.incorrectusage");
    }

    public static String getEPHelpMessage() {
        return I18n.format("easyplay.command.helpmessage");
    }

}
