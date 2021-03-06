package ir.alijk.megacore;

import ir.alijk.megacore.events.InventoryClick;
import ir.alijk.megacore.gui.MegaGUI;
import ir.alijk.megacore.utils.Common;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class MegaPlugin extends JavaPlugin {
        @Getter public static JavaPlugin instance;
        public static HashMap<String, MegaGUI> registeredGuis = new HashMap<>();

        @Override
        public void onEnable() {
                // For calculating
                long start = System.currentTimeMillis();

                // Assigning instance
                instance = this;

                onPluginEnable();

                // Disabling the plugin if it's disabled in the onPluinEnable
                if (!isEnabled()) return;

                // Registering core events
                register(new InventoryClick());

                // Finished loading plugin millis
                long end = System.currentTimeMillis();

                // Calculating plugin load time in milliseconds
                long time = end - start;

                // Logging MegaReports has been activated
                Common.log(
                        Common.repeat("&a&m=", 12, "&2"),
                        "&a&l" + getDescription().getName() + " &aActivated",
                        "&a&lVersion: &2" + getDescription().getVersion(),
                        "&a&lTook: &2" + time+ " ms",
                        Common.repeat("&a&m=", 12, "&2")
                );

        }

        @Override
        public void onDisable() {
                onPluginDisable();

                // Logging MegaReports has been deactivated
                Common.log(
                        Common.repeat("&c&m=", 12, "&4"),
                        "&c&l" + getDescription().getName() + " &cDeactivated",
                        Common.repeat("&c&m=", 12, "&4")
                );
        }

        public void register(String name, CommandExecutor executor) {
                getCommand(name).setExecutor(executor);
        }

        public void register(Listener listener) {
                getServer().getPluginManager().registerEvents(listener, getInstance());
        }

        public void register(Command command) {
                try {
                        final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

                        bukkitCommandMap.setAccessible(true);
                        CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

                        commandMap.register(instance.getName(), command);
                } catch(Exception e) {
                        e.printStackTrace();
                }
        }

        public abstract void onPluginEnable();

        public abstract void onPluginDisable();

}
