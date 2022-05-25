package ir.alijk.atombits.events;

import ir.alijk.atombits.AtomBits;
import ir.alijk.atombits.models.AtomPlayer;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;

public class Placeholders extends PlaceholderExpansion {
    private final AtomBits plugin;

    public Placeholders(AtomBits plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getAuthor() {
        return "Alijk";
    }

    @Override
    public String getIdentifier() {
        return "atombits";
    }

    @Override
    public String getVersion() {
        return "1.0.1";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if (params.equalsIgnoreCase("balance")) {
            AtomPlayer atomPlayer = AtomPlayer.findPlayer(player.getName());

            if (atomPlayer == null) return "0";

            return Long.toString(atomPlayer.getBits());
        }
        return null;
    }

}
