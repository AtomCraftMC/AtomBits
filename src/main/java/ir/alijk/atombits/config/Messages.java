package ir.alijk.atombits.config;

import ir.alijk.megacore.config.Configurable;
import ir.alijk.megacore.utils.Common;

public class Messages extends Configurable {
        public static String PREFIX;
        public static String BALANCE;
        public static String UPDATE;

        public static String PERMISSION_ERROR;
        public static String USAGE_ERROR;
        public static String PLAYER_DOESNT_EXIST_ERROR;
        public static String NEGATIVE_BALANCE_ERROR;

        public static String ADMIN_BALANCE;
        public static String ADMIN_UPDATE;

        public Messages() {
                super("messages.yml");
        }

        @Override
        public void init() {
                PREFIX = Common.colorize(getConfigu().getString("prefix"));
                BALANCE = Common.colorize(getConfigu().getString("balance"));
                UPDATE = Common.colorize(getConfigu().getString("update"));
                PERMISSION_ERROR = Common.colorize(getConfigu().getString("permission-error"));
                USAGE_ERROR = Common.colorize(getConfigu().getString("usage-error"));
                ADMIN_BALANCE = Common.colorize(getConfigu().getString("admin-balance"));
                ADMIN_UPDATE = Common.colorize(getConfigu().getString("admin-update"));
                PLAYER_DOESNT_EXIST_ERROR = Common.colorize(getConfigu().getString("player-doesnt-exist-error"));
                NEGATIVE_BALANCE_ERROR = Common.colorize(getConfigu().getString("negative-balance-error"));
        }
}
