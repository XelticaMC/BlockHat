package work.xeltica.craft.blockhat;

import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getCommand("hat").setExecutor(this);
        logger = getLogger();
    }

    @Override
    public void onDisable() {
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            var player = ((Player)sender);
            var inv = player.getInventory();
            var prevHat = inv.getHelmet();
            var held = inv.getItemInMainHand();
            if (held.getType() == Material.AIR) {
                if (prevHat == null || prevHat.getType() == Material.AIR) {
                    player.sendMessage("何も持っていません。");
                } else {
                    inv.setHelmet(null);
                    if (prevHat != null && prevHat.getType() != Material.AIR) {
                        inv.addItem(prevHat);
                    }
                    player.sendMessage("帽子を外しました。");
                }
                return true;
            }
            inv.setHelmet(held);
            inv.setItemInMainHand(null);
            if (prevHat != null && prevHat.getType() != Material.AIR) {
                inv.addItem(prevHat);
            }
            player.sendMessage("新しい帽子を被りました。素敵ですよ。");
            return true;
        } else {
            sender.sendMessage("プレイヤーが実行してください。");
            return true;
        }
        // return false;
    }

    private Logger logger;
}
