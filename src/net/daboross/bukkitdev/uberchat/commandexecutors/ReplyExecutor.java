/*
 * Author: Dabo Ross
 * Website: www.daboross.net
 * Email: daboross@daboross.net
 */
package net.daboross.bukkitdev.uberchat.commandexecutors;

import java.util.List;
import net.daboross.bukkitdev.uberchat.PlayerInfoTracker;
import net.daboross.bukkitdev.uberchat.UberChatHelpers;
import net.daboross.bukkitdev.uberchat.UberChatMessageHandler;
import net.daboross.bukkitdev.uberchat.UberChatUserFinder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author daboross
 */
public class ReplyExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Please specify a message to send.");
            sender.sendMessage("Usage: /" + label + " <message> (Sends <message> to the last person who messaged you.)");
        } else {
            List<CommandSender> replyTo = UberChatUserFinder.findCommandSenders(PlayerInfoTracker.getReplyto(sender.getName()));
            if (replyTo.isEmpty()) {
                sender.sendMessage("No users found to reply to.");
            } else {
                String message = UberChatHelpers.arrayToString(args, " ");
                UberChatMessageHandler.sendMessage(sender, replyTo.get(0), message);
            }
        }
        return true;
    }
}