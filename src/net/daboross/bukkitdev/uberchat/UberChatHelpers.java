package net.daboross.bukkitdev.uberchat;

import java.util.Locale;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 *
 * @author daboross
 */
public class UberChatHelpers {

    public static String toggleCase(String input) {
        StringBuilder outputBuilder = new StringBuilder(input.length());
        String[] words = input.split(" ");
        boolean first = true;
        for (String word : words) {
            if (first) {
                first = false;
            } else {
                outputBuilder.append(" ");
            }
            if (word.length() == 0) {
                continue;
            }
            outputBuilder.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1, word.length()).toLowerCase());
        }
        return outputBuilder.toString();
    }

    public static String firstLetterCaps(String input) {
        if (input.length() == 0) {
            return input;
        }
        return new StringBuilder(String.valueOf(input.charAt(0)).toUpperCase(Locale.ENGLISH)).append(input.substring(1).toLowerCase(Locale.ENGLISH)).toString();
    }

    public static String translateColor(String input) {
        char[] array = input.toCharArray();
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] == '&' && "0123456789AaBbCcDdEeFfLlMmOo".indexOf(array[i + 1]) > -1) {
                array[i] = ChatColor.COLOR_CHAR;
                array[i + 1] = Character.toLowerCase(array[i + 1]);
            }
        }
        return String.valueOf(array);
    }

    public static String arrayToString(String[] array, String seperator) {
        if (array.length == 0) {
            return "";
        } else if (array.length == 1) {
            return array[0];
        } else {
            StringBuilder resultBuilder = new StringBuilder(array[0]);
            for (int i = 1; i < array.length; i++) {
                resultBuilder.append(seperator).append(array[i]);
            }
            return resultBuilder.toString();
        }
    }

    public static String arrayToString(int start, String[] array, String seperator) {
        if (array.length == 0) {
            return "";
        } else if (start >= array.length) {
            return "";
        } else if (array.length - 1 == start) {
            return array[start];
        } else {
            StringBuilder resultBuilder = new StringBuilder(array[start]);
            for (int i = start + 1; i < array.length; i++) {
                resultBuilder.append(seperator).append(array[i]);
            }
            return resultBuilder.toString();
        }
    }

    public static void formatPlayerDisplayname(Player p) {
        String newDisplayName = p.getDisplayName();
        if (newDisplayName.contains("_")) {
            newDisplayName = newDisplayName.replaceAll("_", " ");
        }
        String noColor = ChatColor.stripColor(newDisplayName);
        while (noColor.startsWith(" ")) {
            newDisplayName = newDisplayName.replaceFirst(" ", "");
            noColor = ChatColor.stripColor(newDisplayName);
        }
        if (noColor.length() > 16) {
            int lengthDiff = 15 + newDisplayName.length() - noColor.length();
            newDisplayName = newDisplayName.substring(0, lengthDiff);
            if (newDisplayName.endsWith(String.valueOf(ChatColor.COLOR_CHAR))) {
                newDisplayName = newDisplayName.substring(0, newDisplayName.length() - 1);
            }
            p.setDisplayName(newDisplayName);
        }
    }
}
