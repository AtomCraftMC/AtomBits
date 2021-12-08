package ir.alijk.atombits.events;

import ir.alijk.atombits.AtomBits;
import ir.alijk.atombits.models.AtomPlayer;
import me.neznamy.tab.api.team.UnlimitedNametagManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Bukkit.getScheduler().runTaskAsynchronously(AtomBits.getInstance(), () -> {
            AtomPlayer atomPlayer = new AtomPlayer(e.getPlayer().getName(), e.getPlayer().getUniqueId().toString());
            atomPlayer.create();
        });
    }
}
