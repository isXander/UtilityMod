package co.uk.isxander.isxanderutils;

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
    public static boolean easyPlayEnabled = false;

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
    private static boolean antiSnipeEnabled = false;

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
            name = "Sniper Warning",
            category = "AntiSnipe",
            subcategory = "Functionality",
            description = "Warns you in chat if someone in your sniper list is in your lobby."
    )
    private static boolean warnSnipers = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "CleanChat",
            category = "CleanChat",
            subcategory = "Functionality",
            description = "Toggles CleanChat."
    )
    private static boolean cleanChat = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Replace dividers",
            category = "CleanChat",
            subcategory = "Functionality",
            description = "Hides the dividers the hypixel puts in chat."
    )
    private static boolean replaceDividers = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Hide game tag",
            category = "CleanChat",
            subcategory = "Functionality",
            description = "Hides the game tag hypixel adds to your name when in a game."
    )
    private static boolean hideGameTag = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Hide spectator tag",
            category = "CleanChat",
            subcategory = "Functionality",
            description = "Hides the spectator tag hypixel adds to your name when you are spectating."
    )
    private static boolean hideSpecTag = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "NoTip",
            category = "CleanChat",
            subcategory = "Functionality",
            description = "Hides tipping messages."
    )
    private static boolean noTip = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Hide game countdown",
            category = "CleanChat",
            subcategory = "Functionality",
            description = "Hides the game countdown at the start of games."
    )
    private static boolean noCountdown = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Hide join messages",
            category = "CleanChat",
            subcategory = "Functionality",
            description = "Hides players joining games. (NOT LOBBIES!)"
    )
    private static boolean hideJoin = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Hide leave messages",
            category = "CleanChat",
            subcategory = "Functionality",
            description = "Hides players leaving games. (NOT LOBBIES!)"
    )
    private static boolean hideLeave = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "NoK",
            category = "CleanChat",
            subcategory = "Functionality",
            description = "Hides the changing letters."
    )
    private static boolean noK = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "MyChest",
            category = "MyChest",
            subcategory = "Functionality",
            description = "Highlights chests that you have opened."
    )
    private static boolean myChest = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Clear on chest refill",
            category = "MyChest",
            subcategory = "Functionality",
            description = "Clears all highlighted chests when they refill."
    )
    private static boolean clearOnRefill = true;

    @Property(
            type = PropertyType.SELECTOR,
            name = "Color",
            category = "MyChest",
            subcategory = "Personalization",
            description = "Sets the color for highlighting chests.",
            options = { "White", "Black", "Red", "Green", "Blue", "Orange", "Purple", "Gray", "Dark Red", "Yellow" }
    )
    private static int color = 2;

    @Property(
            type = PropertyType.SWITCH,
            name = "ESP Mode",
            category = "MyChest",
            subcategory = "Functionality",
            description = "Renders the highlighted box through walls. (Disabled on Hypixel)"
    )
    private static boolean espMode = false;

    @Property(
            type = PropertyType.SLIDER,
            name = "Critical Multiplier",
            category = "Particle Mod",
            subcategory = "Criticals",
            description = "Changes how many critical particles are spawned.",
            min = 1,
            max = 100
    )
    private static int critMultiplier = 1;

    @Property(
            type = PropertyType.SWITCH,
            name = "Always Sharp",
            category = "Particle Mod",
            subcategory = "Sharpness",
            description = "Toggles always showing sharp particles."
    )
    private static boolean alwaysSharp = false;

    @Property(
            type = PropertyType.SLIDER,
            name = "Sharp Multiplier",
            category = "Particle Mod",
            subcategory = "Sharpness",
            description = "Changes how many sharp particles are spawned.",
            min = 1,
            max = 100
    )
    private static int sharpMultiplier = 1;

    public static boolean isEasyPlayEnabled() {
        return easyPlayEnabled && MinecraftUtils.isHypixel();
    }

    public static boolean isCleanChatEnabled() {
        return cleanChat && MinecraftUtils.isHypixel();
    }

    public static boolean doEsp() {
        return espMode && !MinecraftUtils.isHypixel();
    }

    public static boolean isAlwaysSharpEnabled() {
        return alwaysSharp;
    }

    public static int getSharpMultiplier() {
        return sharpMultiplier;
    }

    public static void setSharpMultiplier(int i) {
        if (i > 0 && i <= 20) {
            sharpMultiplier = i;
        }
        else {
            throw new IllegalArgumentException("Out of bounds.");
        }
    }

    public static int getCritMultiplier() {
        return critMultiplier;
    }

    public static void setCritMultiplier(int i) {
        if (i > 0 && i <= 20) {
            critMultiplier = i;
        }
        else {
            throw new IllegalArgumentException("Out of bounds.");
        }
    }

    public static boolean isMyChestEnabled() {
        return myChest;
    }

    public static int getChestColor() {
        return color;
    }

    public static void setMyChestEnabled(boolean bool) {
        myChest = bool;
    }

    public static boolean doClearOnRefill() {
        return clearOnRefill;
    }

    public static boolean doNoTip() {
            return noTip;
    }

    public static boolean doHideJoin() {
        return hideJoin;
    }

    public static boolean doHideLeave() {
        return hideLeave;
    }

    public static boolean doNoK() {
        return noK;
    }

    public static boolean doReplaceDividers() {
        return replaceDividers;
    }

    public static boolean doNoCountdown() {
        return noCountdown;
    }

    public static boolean doHideSpecTag() {
        return hideSpecTag;
    }

    public static boolean doHideGameTag() {
        return hideGameTag;
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
        }
        return false;
    }

    public static boolean shouldReplaceErrors() {
        if (MinecraftUtils.isHypixel()) {
            return hideErrors;
        }
        return false;
    }

    public static int getRequeueDelay() {
        return requeueDelay;
    }


    public Config() {
        super(new File("./config/isxanderutils.toml"));
        initialize();
    }
}

