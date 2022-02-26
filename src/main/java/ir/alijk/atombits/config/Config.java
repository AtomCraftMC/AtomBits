package ir.alijk.atombits.config;

import ir.alijk.megacore.config.Configurable;

public class Config extends Configurable {
        public static String MYSQL_HOST;
        public static String MYSQL_PORT;
        public static String MYSQL_DATABASE;
        public static String MYSQL_USERNAME;
        public static String MYSQL_PASSWORD;
        public static boolean NAMETAG_ENABLED;

        public Config() {
                super("config.yml");
        }

        @Override
        public void init() {
                MYSQL_HOST = Config.getConfig().getString("mysql.host");
                MYSQL_PORT = Config.getConfig().getString("mysql.port");
                MYSQL_DATABASE = Config.getConfig().getString("mysql.database");
                MYSQL_USERNAME = Config.getConfig().getString("mysql.username");
                MYSQL_PASSWORD = Config.getConfig().getString("mysql.password");
                NAMETAG_ENABLED = Config.getConfig().getBoolean("nametag-enabled");
        }
}
