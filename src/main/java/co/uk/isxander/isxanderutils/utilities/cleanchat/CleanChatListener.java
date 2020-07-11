package co.uk.isxander.isxanderutils.utilities.cleanchat;

import club.sk1er.mods.core.util.MinecraftUtils;
import co.uk.isxander.isxanderutils.Messages;
import co.uk.isxander.isxanderutils.config.Config;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CleanChatListener {
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if (Config.isCleanChatEnabled()) {
            String rawText = EnumChatFormatting.getTextWithoutFormattingCodes(event.message.getUnformattedText());
            String formattedText = event.message.getFormattedText();
            ChatStyle style = event.message.getChatStyle();

            if (Config.doReplaceDividers()) {
                if (rawText.startsWith("▬") || rawText.startsWith("-")) {
                    event.setCanceled(true);
                    String message = formattedText;
                    message = formattedText.replace("-", "");
                    message = message.replace("▬", "");
                    Messages.sendMessage(message, false);
                }
            }

            if (Config.doHideGameTag()) {
                if (formattedText.startsWith("§r§a[GAME] ")) {
                    event.setCanceled(true);
                    String message = formattedText.replace("§r§a[GAME] ", "");
                    Messages.sendMessage(message, false);
                }
            }

            if (Config.doHideSpecTag()) {
                if (formattedText.startsWith("§r§7[SPECTATOR] ")) {
                    event.setCanceled(true);
                    String message = formattedText.replace("§r§7[SPECTATOR] ", "");
                    Messages.sendMessage(message, false);
                }
            }

            if (Config.doNoTip()) {
                if (formattedText.startsWith("§a§aYou tipped ")) {
                    event.setCanceled(true);
                }
            }

            if (Config.doNoCountdown()) {
                if (formattedText.startsWith("§r§eThe game starts in §r§")) {
                    event.setCanceled(true);
                }
            }

            if (Config.doHideJoin()) {
                if (formattedText.contains("§r§e has joined (§r")) {
                    event.setCanceled(true);
                }
            }
            if (Config.doHideLeave()) {
                if (formattedText.endsWith("§r§e has quit!§r")) {
                    event.setCanceled(true);
                }
            }
            if (Config.doNoK()) {
                if (formattedText.contains("§k")) {
                    event.setCanceled(true);
                    String message = formattedText.replace("§k", "");
                    Messages.sendMessage(message, false);
                }
            }
            if (MinecraftUtils.isDevelopment()) {
//                System.out.println(formattedText);
            }
        }
    }
}
