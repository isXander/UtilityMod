package co.uk.isxander.config;

import club.sk1er.mods.core.util.MinecraftUtils;
import club.sk1er.vigilance.Vigilant;
import club.sk1er.vigilance.data.Property;
import club.sk1er.vigilance.data.PropertyType;

import java.io.File;

public class Config extends Vigilant {
    public static String currentGamemode = "";

    //Easyplay
    @Property(
            type = PropertyType.SWITCH,
            name = "EasyPlay",
            category = "EasyPlay",
            subcategory = "Functionality",
            description = "Toggle the mod completely"
    )
    public static boolean easyPlayEnabled = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Requeue if an error occurs",
            category = "EasyPlay",
            subcategory = "Functionality",
            description = "Requeues the specified gamemode if it fails for whatever reason."
    )
    private static boolean requeueIfFail = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Requeue once the game ends",
            category = "EasyPlay",
            subcategory = "Functionality",
            description = "Requeues the previous game once it has finished."
    )
    private static boolean requeueAfterGame = false;

    @Property(
            type = PropertyType.SLIDER,
            name = "Requeue delay",
            category = "EasyPlay",
            subcategory = "Functionality",
            description = "Time in seconds. Adds a delay to the requeuing to be compatible with mods such as AutoGG.",
            max = 10
    )
    private static int requeueDelay = 5;

    @Property(
            type = PropertyType.SWITCH,
            name = "Hide Sending to Server Messages",
            category = "EasyPlay",
            subcategory = "Language",
            description = "Hides the server messages that tell you that you are being sent to a server."
    )
    private static boolean hideSendMessages = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Replace Server Errors",
            category = "EasyPlay",
            subcategory = "Language",
            description = "Replaces the server messages that tell you about errors about sending you to servers."
    )
    private static boolean hideErrors = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "AntiSnipe",
            category = "AntiSnipe",
            subcategory = "Functionality",
            description = "Toggles the AntiSnipe mod"
    )
    private static boolean antiSnipeEnabled = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Hide players",
            category = "AntiSnipe",
            subcategory = "Functionality",
            description = "Prevents rendering players if they are on your AntiSnipe list."
    )
    private static boolean hidePlayers = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Warns if a sniper is in your lobby",
            category = "AntiSnipe",
            subcategory = "Functionality",
            description = "Warns you in chat if someone in your sniper list is in your lobby."
    )
    private static boolean warnSnipers = true;


    public static boolean isEasyPlayEnabled() {
        if (MinecraftUtils.isHypixel()) {
            return easyPlayEnabled;
        } else {
            return false;
        }
    }

    public static boolean isAntiSnipeEnabled() {
        return antiSnipeEnabled;
    }

    public static boolean doHidePlayers() {
        return hidePlayers;
    }

    public static boolean doWarnSnipers() {
        return warnSnipers;
    }

    public static boolean shouldRequeueIfFail() {
        return requeueIfFail;
    }

    public static boolean shouldRequeueAfter() {
        return requeueAfterGame;
    }


    public static boolean shouldHideSendMsgs() {
        if (MinecraftUtils.isHypixel()) {
            return hideSendMessages;
        } else {
            return false;
        }
    }

    public static boolean shouldReplaceErrors() {
        if (MinecraftUtils.isHypixel()) {
            return hideErrors;
        } else {
            return false;
        }
    }

    public static int getRequeueDelay() {
        return requeueDelay;
    }


    public Config() {
        super(new File("./config/isxanderutils.toml"));
        initialize();
    }
}

