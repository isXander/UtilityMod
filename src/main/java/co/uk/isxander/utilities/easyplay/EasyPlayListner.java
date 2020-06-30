package co.uk.isxander.utilities.easyplay;

import club.sk1er.mods.core.util.MinecraftUtils;
import club.sk1er.mods.core.util.Multithreading;
import co.uk.isxander.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import co.uk.isxander.config.Config;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EasyPlayListner {

    public static String previousMode = "";
    public static boolean gameChecker = false;
    public static boolean worldLoaded = true;

    private static Config config;
    private static String username = "";
    private static Minecraft mc = Minecraft.getMinecraft();


    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String rawText = EnumChatFormatting.getTextWithoutFormattingCodes(event.message.getUnformattedText());

        if (Config.isEasyPlayEnabled()) {
            if (rawText.toLowerCase().contains("sending you to") || rawText.toLowerCase().contains("sending to server")) {
                if (Config.shouldHideSendMsgs()) {
                    event.setCanceled(true);
                    return;
                }
            }

            if (rawText.toLowerCase().contains("something went wrong trying to send you to that server! if this keeps happening please report it! (")) {
                if (Config.shouldRequeueIfFail()) {
                    if (Config.shouldReplaceErrors()) {
                        event.setCanceled(true);
                    } else {
                        MinecraftUtils.sendMessage(Reference.getPrefix(), "§cCouldn't send you to server. Re-queueing.");
                    }
                    sendPlayer(Config.currentGamemode);

                } else {
                    if (Config.shouldReplaceErrors()) {
                        event.setCanceled(true);
                    }
                    MinecraftUtils.sendMessage(Reference.getPrefix(), "§cCouldn't send you to server.");

                }

            }

            if (Config.shouldRequeueAfter()) {

                if (username.isEmpty())
                    username = mc.thePlayer.getName();


                String line = event.message.getUnformattedText();

                if (!Config.isEasyPlayEnabled() || rawText.split(" ").length == 0)
                    return;

                String killMessageRegex = "(\\w{1,16}).+ (by|of|to|for|with) (" + username + ")";
                String usernamePatternRegex = "^[a-zA-Z0-9_-]{3,16}$";

                Pattern killMessagePattern = Pattern.compile(killMessageRegex);
                Pattern usernamePattern = Pattern.compile(usernamePatternRegex);

                Matcher killMessageMatcher = killMessagePattern.matcher(rawText);
                Matcher usernameMatcher = usernamePattern.matcher(rawText.split(" ")[0]);

                if (line.toLowerCase().contains("you have been eliminated")) {
                    if (gameChecker) {
                        requeuePlayer(previousMode);
                    }
                }

                if (usernameMatcher.matches() && killMessageMatcher.find()) {

                    String killed = killMessageMatcher.group(1);
                    if (killed.equals(username)) {
                        if (gameChecker)
                            requeuePlayer(previousMode);
                    }
                    return;
                }
                for (Pattern trigger : winTriggers) {
                    if (trigger.matcher(rawText).matches()) {
                        if (gameChecker)
                            requeuePlayer(previousMode);

                        break;
                    }

                }
            }
        }
    }


    @SubscribeEvent
    public void LeaveServer(PlayerEvent.PlayerLoggedOutEvent event) {
        Config.currentGamemode = "";
    }

    @SubscribeEvent
    public void worldSwap(WorldEvent.Unload event) {
        Config.currentGamemode = "";
        gameChecker = false;
    }

    @SubscribeEvent
    public void worldLoad(WorldEvent.Load event) {
        worldLoaded = true;
    }


    private static List<Pattern> winTriggers = new ArrayList<Pattern>() {{
        add(Pattern.compile("^ +1st Killer - ?\\[?\\w*\\+*]? \\w+ - \\d+(?: Kills?)?$"));
        add(Pattern.compile("^ *1st (?:Place ?)?(?:-|:)? ?\\[?\\w*\\+*]? \\w+(?: : \\d+| - \\d+(?: Points?)?| - \\d+(?: x .)?| \\(\\w+ .{1,6}\\) - \\d+ Kills?|: \\d+:\\d+| - \\d+ (?:Zombie )?(?:Kills?|Blocks? Destroyed)| - \\[LINK])?$"));
        add(Pattern.compile("^ +Winn(?:er #1 \\(\\d+ Kills\\): \\w+ \\(\\w+\\)|er(?::| - )(?:Hiders|Seekers|Defenders|Attackers|PLAYERS?|MURDERERS?|Red|Blue|RED|BLU|\\w+)(?: Team)?|ers?: ?\\[?\\w*\\+*]? \\w+(?:, ?\\[?\\w*\\+*]? \\w+)?|TSeam ?[:-] (?:Animals|Hunters|Red|Green|Blue|Yellow|RED|BLU|Survivors|Vampires))$"));
        add(Pattern.compile("^ +Alpha Infected: \\w+ \\(\\d+ infections?\\)$"));
        add(Pattern.compile("^ +Murderer: \\w+ \\(\\d+ Kills?\\)$"));
        add(Pattern.compile("^ +You survived \\d+ rounds!$"));
        add(Pattern.compile("^ +(?:UHC|SkyWars|The Bridge|Sumo|Classic|OP|MegaWalls|Bow|NoDebuff|Blitz|Combo|Bow Spleef) (?:Duel|Doubles|Teams|Deathmatch|2v2v2v2|3v3v3v3)? - \\d+:\\d+$"));
        add(Pattern.compile("^ +They captured all wools!$"));
        add(Pattern.compile("^ +Game over!$"));
        add(Pattern.compile("^ +[\\d.]+k?/[\\d.]+k? \\w+$"));
        add(Pattern.compile("^ +(?:Criminal|Cop)s won the game!$"));
        add(Pattern.compile("^ +\\[?\\w*\\+*]? \\w+ - \\d+ Final Kills$"));
        add(Pattern.compile("^ +Zombies - \\d*:?\\d+:\\d+ \\(Round \\d+\\)$"));
        add(Pattern.compile("^ +. YOUR STATISTICS ."));
        add(Pattern.compile("^ *You have been eliminated!$"));
        add(Pattern.compile("^ *You died! Want to play again\\? Click here!$"));
        add(Pattern.compile("A player has killed you!"));
    }};


    public static void requeuePlayer(String gamemode) {
        MinecraftUtils.sendMessage(Reference.getPrefix(), "DEVLOG: Attempting to requeue player out of multithread.");
        Multithreading.schedule(() ->
        {
            MinecraftUtils.sendMessage(Reference.getPrefix(), "DEVLOG: Attempting to requeue player in multithread.");
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/play " + gamemode);
            previousMode = gamemode;
            if (!Config.shouldHideSendMsgs()) {
                return;
            }
            MinecraftUtils.sendMessage(Reference.getPrefix(), "§aSending you to " + gamemode);


            if (worldLoaded) {
                gameChecker = true;
                worldLoaded = false;
            }
            MinecraftUtils.sendMessage(Reference.getPrefix(), "DEVLOG: Finished in multithread.");
        }, Config.getRequeueDelay(), TimeUnit.SECONDS);
        MinecraftUtils.sendMessage(Reference.getPrefix(), "DEVLOG: Finished out of multithread.");

    }

    public static void sendPlayer(String gamemode) {

        Minecraft.getMinecraft().thePlayer.sendChatMessage("/play " + gamemode);
        previousMode = gamemode;
        if (!Config.shouldHideSendMsgs()) {
            return;
        }
        MinecraftUtils.sendMessage(Reference.getPrefix(), "§aSending you to " + gamemode);


        if (worldLoaded) {
            gameChecker = true;
            worldLoaded = false;
        }

    }

}
