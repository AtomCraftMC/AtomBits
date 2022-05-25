package ir.alijk.atombits.events;

import ir.alijk.atombits.AtomBits;
import ir.alijk.atombits.config.Config;
import ir.alijk.atombits.models.AtomPlayer;
import ir.alijk.megacore.utils.Common;
import me.neznamy.tab.api.team.TeamManager;
import me.neznamy.tab.api.team.UnlimitedNametagManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class TabHook implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (!Config.NAMETAG_ENABLED) return;

        Bukkit.getScheduler().runTaskLaterAsynchronously(AtomBits.getInstance(), () -> {
            TeamManager manager = AtomBits.getTabManager().getTeamManager();

            if (manager instanceof UnlimitedNametagManager) {
                UnlimitedNametagManager unlimitedNametagManager = (UnlimitedNametagManager) manager;
                unlimitedNametagManager.setLine(
                        AtomBits.getTabManager().getPlayer(e.getPlayer().getUniqueId()),
                        "belowname",
                        Common.colorize(
                                "&b" + AtomPlayer.findPlayer(e.getPlayer().getName()).getBits() + " &a&lBits")
                );
            }
        }, 20L);
    }
}
