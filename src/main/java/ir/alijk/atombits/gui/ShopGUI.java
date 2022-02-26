package ir.alijk.atombits.gui;

import ir.alijk.atombits.AtomBits;
import ir.alijk.atombits.config.Messages;
import ir.alijk.atombits.models.AtomPlayer;
import ir.alijk.atombits.models.Transaction;
import ir.alijk.megacore.gui.MegaGUI;
import ir.alijk.megacore.utils.Common;
import ir.alijk.megacore.utils.MegaItem;
import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ShopGUI extends MegaGUI {
    public ShopGUI() {
        super("&aAtomBits Shop", 36);
    }

    public void addRank(String name, String features, long price, int itemSlot, Material material) {
        List<String> lore = new ArrayList<>();

        for (String line: Messages.SHOP_GUI_RANK_LORE) {
            line = line.replace("%rank%", name.toUpperCase(Locale.ROOT))
                        .replace("%price%", Long.toString(price))
                        .replace("%features%", features);
            lore.add(line);
        }

        MegaItem rank = new MegaItem(
                material,
                Messages.SHOP_GUI_RANK_NAME.replace("%rank%", name.toUpperCase(Locale.ROOT)),
                lore
        );

        place(itemSlot, rank, (player, itemStack, slot, clickType) -> {
            AtomPlayer atomPlayer = AtomPlayer.findPlayer(player.getName());

            if (clickType == ClickType.LEFT) {
                if (atomPlayer.getBits() < price) {
                    Common.send(
                            player,
                            Messages.SHOP_GUI_RANK_NOT_ENOUGH
                                    .replace("%rank%", name.toUpperCase(Locale.ROOT))
                    );
                } else {
                    atomPlayer.setBits(atomPlayer.getBits() - price);
                    atomPlayer.save();

                    Transaction transaction = new Transaction();
                    transaction.setAction("purchase");
                    transaction.setAmount(price);
                    transaction.setReason(name + " Rank");
                    transaction.setSender("CONSOLE");
                    transaction.setReceiver(player.getName());
                    transaction.create();

                    AtomBits.getInstance().getServer().dispatchCommand(
                            AtomBits.getInstance().getServer().getConsoleSender(),
                            ("lp user %player% parent addtemp " + name + " 30d").replace("%player%", player.getName())
                    );

                    Common.send(
                            player,
                            Messages.SHOP_GUI_RANK_PURCHASE
                                    .replace("%rank%", name.toUpperCase(Locale.ROOT))
                    );

                    player.closeInventory();
                }
            } else if (clickType == ClickType.RIGHT) {
                List<String> info = new ArrayList<>();

                for (String line: Messages.SHOP_GUI_RANK_INFO) {
                    info.add(line.replace("%features%", features));
                }

                for (String message: info) {
                    Common.send(player, message);
                }
            }
        });

    }

    @Override
    public void setup() {
        MegaItem closeItem = new MegaItem(
                Material.BARRIER,
                Messages.SHOP_GUI_CLOSE_NAME,
                Messages.SHOP_GUI_CLOSE_LORE
        );

        addRank("vip", "https://yun.ir/rkkkv8", 20000, 10, Material.BRICK);
        addRank("vip+", "https://yun.ir/o1hnra", 45000, 12, Material.IRON_INGOT);
        addRank("mvp", "https://yun.ir/f2ahh3", 80000, 14, Material.GOLD_INGOT);
        addRank("mvp+", "https://yun.ir/l9t1cb", 120000, 16, Material.DIAMOND);
//        addRank("atom", "https://yun.ir/80qv87", 300000, 20, Material.EMERALD);

        place(31, closeItem, (player, itemStack, slot, clickType) -> {
            player.closeInventory();
            Common.send(player, Messages.SHOP_GUI_CLOSED);
        });
    }
}
