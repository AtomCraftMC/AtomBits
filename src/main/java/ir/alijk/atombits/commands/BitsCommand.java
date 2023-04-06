package ir.alijk.atombits.commands;

import ir.alijk.atombits.config.Messages;
import ir.alijk.atombits.models.AtomPlayer;
import ir.alijk.atombits.models.Transaction;
import ir.alijk.megacore.utils.Common;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class BitsCommand implements CommandExecutor {
    public final String ADMIN_PERMISSION = "bitssystem.admin";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        boolean wrongUsage = false;

        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                wrongUsage = true;
            } else {
                Common.send(sender, Messages.BALANCE.replace("%bits%", Long.toString(AtomPlayer.findPlayer(sender.getName()).getBits())));
            }
        } else if (sender.hasPermission(ADMIN_PERMISSION)) {
            if (args.length >= 2) {
                String action = args[0];
                AtomPlayer atomPlayer = AtomPlayer.findPlayer(args[1]);

                if (atomPlayer == null) {
                    Common.send(sender, Messages.PLAYER_DOESNT_EXIST_ERROR.replace("%player_name%", args[1]));
                    return true;
                }

                if (action.equalsIgnoreCase("get")) {
                    Common.send(
                            sender,
                            Messages.ADMIN_BALANCE
                                    .replace("%player_name%", atomPlayer.getUserName())
                                    .replace("%bits%", Long.toString(atomPlayer.getBits()))
                    );
                } else if (args.length > 3) {
                    long newBalance = -1;
                    long amount;
                    String reason = String.join(" ", Arrays.copyOfRange(args, 3, args.length));
                    Transaction transaction = new Transaction();

                    try {
                        amount = Long.parseLong(args[2]);

                        transaction.setSender((sender instanceof Player) ? sender.getName() : "CONSOLE");
                        transaction.setReceiver(atomPlayer.getUserName());
                        transaction.setReason(reason);
                        transaction.setAmount(amount);

                        if (action.equalsIgnoreCase("add")) {
                            newBalance = atomPlayer.getBits() + amount;
                            transaction.setAction("add");
                        } else if (action.equalsIgnoreCase("remove")) {
                            newBalance = atomPlayer.getBits() - amount;
                            transaction.setAction("remove");
                        } else if (action.equalsIgnoreCase("set")) {
                            newBalance = amount;
                            transaction.setAction("set");
                        }

                        if (newBalance < 0) {
                            Common.send(
                                    sender,
                                    Messages.NEGATIVE_BALANCE_ERROR
                                            .replace("%player_name%", atomPlayer.getUserName())
                                            .replace("%amount%", Long.toString(amount))
                            );
                        } else {
                            atomPlayer.setBits(newBalance);
                            atomPlayer.save();

                            transaction.create();

                            Common.send(
                                    sender,
                                    Messages.ADMIN_UPDATE
                                            .replace("%player_name%", atomPlayer.getUserName())
                                            .replace("%bits%", Long.toString(newBalance))
                            );

                            if (Bukkit.getPlayerExact(atomPlayer.getUserName()) != null) {
                                Common.send(
                                        Bukkit.getPlayer(atomPlayer.getUserName()),
                                        Messages.UPDATE
                                                .replace("%bits%", Long.toString(atomPlayer.getBits()))
                                                .replace("%reason%", reason)
                                );
                            }
                        }
                    } catch (NumberFormatException exception) {
                        wrongUsage = true;
                    }
                } else {
                    wrongUsage = true;
                }
            }
        } else {
            Common.send(sender, Messages.PERMISSION_ERROR);
        }

        if (wrongUsage) {
            Common.send(sender, Messages.USAGE_ERROR);
        }

        return true;
    }
}
