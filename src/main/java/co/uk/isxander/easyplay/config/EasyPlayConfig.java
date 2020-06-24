package co.uk.isxander.easyplay.config;

import club.sk1er.mods.core.util.MinecraftUtils;
import club.sk1er.vigilance.Vigilant;
import club.sk1er.vigilance.data.Property;
import club.sk1er.vigilance.data.PropertyType;

import java.io.File;

public class EasyPlayConfig extends Vigilant
{
    public static String currentGamemode = "";

    //General
    @Property (
        type = PropertyType.SWITCH,
        name = "EasyPlay",
        category = "Functionality",
        subcategory = "General",
        description = "Toggle the mod completely"
    )
    public static boolean enabled = true;

    @Property (
        type = PropertyType.SWITCH,
        name = "Requeue if an error occurs",
        category = "Functionality",
        subcategory = "General",
        description = "Requeues the specified gamemode if it fails for whatever reason."
    )
    private static boolean requeueIfFail = true;

    @Property (
        type = PropertyType.SWITCH,
        name = "Requeue once the game ends",
        category = "Functionality",
        subcategory = "General",
        description = "Requeues the previous game once it has finished."
    )
    private static boolean requeueAfterGame = false;

    @Property(
        type = PropertyType.SLIDER,
        name = "Requeue delay",
        category = "Functionality",
        subcategory = "General",
        description = "Time in seconds. Adds a delay to the requeuing to be compatible with mods such as AutoGG",
        max = 10
    )
    private static int requeueDelay = 5;

    //Messages
    @Property (
        type = PropertyType.SWITCH,
        name = "Hide Sending to Server Messages",
        category = "Functionality",
        subcategory = "Messages",
        description = "Hides the server messages that tell you that you are being sent to a server."
    )
    private static boolean hideSendMessages = true;

    @Property (
        type = PropertyType.SWITCH,
        name = "Replace Server Errors",
        category = "Functionality",
        subcategory = "Messages",
        description = "Replaces the server messages that tell you about errors about sending you to servers."
    )
    private static boolean hideErrors = true;

    @Property (
        type = PropertyType.TEXT,
        name = "Prefix",
        category = "Language",
        subcategory = "Texts",
        description = "Changes the prefix that the mod uses."
    )
    private static String prefix = "§7§l[§9§lE§e§lP§7§l] §r";

    @Property (
        type = PropertyType.TEXT,
        name = "Incorrect Usage",
        category = "Language",
        subcategory = "Texts",
        description = "Changes the incorrect usage error message that the mod uses."
    )
    private static String incorrectUsage = "§cIncorrect Usage! Use §7§o\'/easyplay help\' §r§cfor help.";

    @Property (
        type = PropertyType.TEXT,
        name = "Help Message Title",
        category = "Language",
        subcategory = "Texts",
        description = "Changes the help message title that the mod uses."
    )
    private static String helpMessageTitle = "§9§lEasy§e§lPlay §b§lHelp Menu";


    public static boolean isEasyPlayEnabled() {
        if(MinecraftUtils.isHypixel()) {
            return enabled;
        }
        else {
            return false;
        }
    }

    public static boolean shouldRequeueIfFail() {
        return requeueIfFail;
    }

    public static boolean shouldRequeueAfter() {
        return requeueAfterGame;
    }


    public static boolean shouldHideSendMsgs() {
        if(MinecraftUtils.isHypixel()) {
            return hideSendMessages;
        }
        else {
            return false;
        }
    }

    public static boolean shouldReplaceErrors() {
        if(MinecraftUtils.isHypixel()) {
            return hideErrors;
        }
        else {
            return false;
        }
    }

    public static int getRequeueDelay() {
        return requeueDelay;
    }

    public static String prefix() {
        return prefix;
    }

    public static String incorrectUsage() {
        return incorrectUsage;
    }

    public static String helpMenu() {
        return helpMessageTitle;
    }

    public EasyPlayConfig() {
        super(new File("./config/easyplay.toml"));
        initialize();
    }
}

