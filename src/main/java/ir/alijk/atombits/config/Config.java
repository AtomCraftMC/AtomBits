package ir.alijk.atombits.config;

import ir.alijk.megacore.config.Configurable;

public class Config extends Configurable {
        public static String MYSQL_HOST;
        public static String MYSQL_PORT;
        public static String MYSQL_DATABASE;
        public static String MYSQL_USERNAME;
        public static String MYSQL_PASSWORD;

        public Config() {
                super("config.yml");
        }

        @Override
        public void init() {
                MYSQL_HOST = getConfigu().getString("mysql.host");
                MYSQL_PORT = getConfigu().getString("mysql.port");
                MYSQL_DATABASE = getConfigu().getString("mysql.database");
                MYSQL_USERNAME = getConfigu().getString("mysql.username");
                MYSQL_PASSWORD = getConfigu().getString("mysql.password");
        }
}
