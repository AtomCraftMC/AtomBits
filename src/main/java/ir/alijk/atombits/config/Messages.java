package ir.alijk.atombits.config;

import ir.alijk.megacore.config.Configurable;
import ir.alijk.megacore.utils.Common;

import java.util.List;

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

        public static String SHOP_GUI_CLOSED;
        public static String SHOP_GUI_OPENED;

        public static String SHOP_GUI_RANK_PURCHASE;
        public static String SHOP_GUI_RANK_NOT_ENOUGH;
        public static List<String> SHOP_GUI_RANK_INFO;
        public static String SHOP_GUI_RANK_NAME;
        public static List<String> SHOP_GUI_RANK_LORE;

        public static String SHOP_GUI_CLOSE_NAME;
        public static List<String> SHOP_GUI_CLOSE_LORE;

        public Messages() {
                super("messages.yml");
        }

        @Override
        public void init() {
                PREFIX = Common.colorize(Messages.getConfig().getString("prefix"));
                BALANCE = Common.colorize(Messages.getConfig().getString("balance"));
                UPDATE = Common.colorize(Messages.getConfig().getString("update"));
                PERMISSION_ERROR = Common.colorize(Messages.getConfig().getString("permission-error"));
                USAGE_ERROR = Common.colorize(Messages.getConfig().getString("usage-error"));
                ADMIN_BALANCE = Common.colorize(Messages.getConfig().getString("admin-balance"));
                ADMIN_UPDATE = Common.colorize(Messages.getConfig().getString("admin-update"));
                PLAYER_DOESNT_EXIST_ERROR = Common.colorize(Messages.getConfig().getString("player-doesnt-exist-error"));
                NEGATIVE_BALANCE_ERROR = Common.colorize(Messages.getConfig().getString("negative-balance-error"));

                // GUI Configurations
                SHOP_GUI_CLOSED = Common.colorize(Messages.getConfig().getString("shop-gui.closed"));
                SHOP_GUI_OPENED = Common.colorize(Messages.getConfig().getString("shop-gui.opened"));

                SHOP_GUI_RANK_PURCHASE = Common.colorize(Messages.getConfig().getString("shop-gui.rank.purchase"));
                SHOP_GUI_RANK_NOT_ENOUGH = Common.colorize(Messages.getConfig().getString("shop-gui.rank.not-enough"));
                SHOP_GUI_RANK_INFO = Common.colorize(Messages.getConfig().getStringList("shop-gui.rank.info"));
                SHOP_GUI_RANK_NAME = Common.colorize(Messages.getConfig().getString("shop-gui.rank.name"));
                SHOP_GUI_RANK_LORE = Common.colorize(Messages.getConfig().getStringList("shop-gui.rank.lore"));

                SHOP_GUI_CLOSE_NAME = Common.colorize(Messages.getConfig().getString("shop-gui.close.name"));
                SHOP_GUI_CLOSE_LORE = Common.colorize(Messages.getConfig().getStringList("shop-gui.close.lore"));

        }
}
