package ir.alijk.atombits;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import ir.alijk.atombits.commands.BitsCommand;
import ir.alijk.atombits.config.Config;
import ir.alijk.atombits.config.Messages;
import ir.alijk.atombits.events.Placeholders;
import ir.alijk.atombits.events.PlayerJoin;
import ir.alijk.atombits.events.TabHook;
import ir.alijk.atombits.models.AtomPlayer;
import ir.alijk.atombits.models.Transaction;
import ir.alijk.megacore.MegaPlugin;
import ir.alijk.megacore.utils.Common;
import lombok.Getter;
import me.neznamy.tab.api.TabAPI;
import org.bukkit.Bukkit;

import java.sql.SQLException;

public final class AtomBits extends MegaPlugin {
    @Getter private static Dao<AtomPlayer,String> atomPlayersDao;
    @Getter private static Dao<Transaction,String> transactionDao;
    @Getter private static TabAPI tabManager;
    private static ConnectionSource connectionSource;

    @Override
    public void onPluginEnable() {
        // Registering configurations
        new Config().setup();
        new Messages().setup();

        // Checking if MySQL is configured
        if (Config.MYSQL_HOST.equalsIgnoreCase("NOT_SET")) {
            disablePlugin(true, "Please setup MySQL in the config before using this plugin.");
            return;
        }

        // Setting up PlaceholderAPI hook
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
            new Placeholders(this).register();
            Common.logPrefixed("Hooked to PlaceholderAPI");
        }

        // Setting up TAB hook
        if (Bukkit.getPluginManager().isPluginEnabled("TAB")){
            tabManager = TabAPI.getInstance();
            register(new TabHook());
            Common.logPrefixed("Hooked to TAB");
        }

        // Registering commands
        this.getCommand("bits").setExecutor(new BitsCommand());

        // Registering events
        register(new PlayerJoin());

        // Setting up the database
        try {
            connectionSource =
                    new JdbcConnectionSource(
                            "jdbc:mysql://" + Config.MYSQL_HOST + ":" + Config.MYSQL_PORT + "/" + Config.MYSQL_DATABASE,
                            Config.MYSQL_USERNAME,
                            Config.MYSQL_PASSWORD
                    );
            atomPlayersDao = DaoManager.createDao(connectionSource, AtomPlayer.class);
            TableUtils.createTableIfNotExists(connectionSource, AtomPlayer.class);

            transactionDao = DaoManager.createDao(connectionSource, Transaction.class);
            TableUtils.createTableIfNotExists(connectionSource, Transaction.class);

        } catch (SQLException exception) {
            exception.printStackTrace();
            disablePlugin(true, "We could not establish connection to the database, disabling the plugin, please check the stack trace above");
        }

    }

    @Override
    public void onPluginDisable() {
        if (connectionSource != null) connectionSource.closeQuietly();
    }

    public static void disablePlugin(boolean addPrefix, String... messages) {
        if (addPrefix)
            Common.logPrefixed(messages);
        else
            Common.log(messages);
        Bukkit.getPluginManager().disablePlugin(instance);
    }
}
