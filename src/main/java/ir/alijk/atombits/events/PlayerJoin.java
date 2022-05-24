package ir.alijk.atombits.events;

import ir.alijk.atombits.AtomBits;
import ir.alijk.atombits.config.Config;
import ir.alijk.atombits.models.AtomPlayer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Bukkit.getScheduler().runTaskAsynchronously(AtomBits.getInstance(), () -> {
            new AtomPlayer(e.getPlayer().getName(), e.getPlayer().getUniqueId().toString()).create();

            if (Config.LEVELBITS_ENABLED) {
                AtomPlayer atomPlayer = AtomPlayer.findPlayer(e.getPlayer().getName());
                e.getPlayer().setLevel((int) atomPlayer.getBits());
            }
        });
    }
}
