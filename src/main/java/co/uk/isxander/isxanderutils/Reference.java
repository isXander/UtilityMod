package co.uk.isxander.isxanderutils;


public class Reference {

    public static final String MOD_ID = "isxanderutils";
    public static final String MOD_NAME = "isXander's Utility Mod";
    public static final String VERSION = "0.7.0";
    public static final String AUTHOR = "isXander";

    public static String getGradleVersion() {
        return Reference.class.getPackage().getImplementationVersion();
    }

    public static String getPrefix() {
//        return I18n.format("command.prefix");
        return "§c[isXander's Utility Mod] §r";
    }

    public static String getEPIncorrectUsage() {
//        return I18n.format("easyplay.command.incorrectusage");
        return "§cIncorrect Usage! Use §7§o'/easyplay help' §r§cfor help.";
    }

    public static String getEPHelpMessage() {
//        return I18n.format("easyplay.command.helpmessage");
        return "§cCurrently there is no help message.";
    }

}
