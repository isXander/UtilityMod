package co.uk.isxander.isxanderutils.util;

import club.sk1er.mods.core.util.MinecraftUtils;
import co.uk.isxander.isxanderutils.Reference;

public class Messages {
    public static void sendMessage(String prefix, String message) {
        MinecraftUtils.sendMessage(prefix, message);
    }

    public static void sendMessage(String message, boolean prefix) {
        if (prefix) {
            MinecraftUtils.sendMessage(Reference.getPrefix(), message);
        }
        else {
            MinecraftUtils.sendMessage("", message);
        }
    }

    public static void sendMessage(String message) {
        MinecraftUtils.sendMessage(Reference.getPrefix(), message);
    }
}
