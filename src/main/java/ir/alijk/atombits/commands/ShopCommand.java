package ir.alijk.atombits.commands;

import ir.alijk.atombits.config.Messages;
import ir.alijk.atombits.gui.ShopGUI;
import ir.alijk.atombits.models.AtomPlayer;
import ir.alijk.atombits.models.Transaction;
import ir.alijk.megacore.utils.Common;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class ShopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        new ShopGUI().open((Player) sender);
        Common.send(sender, Messages.SHOP_GUI_OPENED);

        return true;
    }
}
