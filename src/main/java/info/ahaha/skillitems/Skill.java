package info.ahaha.skillitems;

import info.ahaha.levelsystem.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public interface Skill {

    String getName();

    int getCost();

    int getLevel();

    List<String>getLore();

    default boolean isLevel(Player player){
        PlayerData data = null;
        for (PlayerData data1 : PlayerData.data){
            if (data1.getUuid().equals(player.getUniqueId())){
                data = data1;
                break;
            }
        }
        if (data == null)return false;
        if (data.getLevel() >= getLevel()){
            return true;
        }else {
            return false;
        }
    }

    default boolean isCost(ItemStack item){
        Damageable damageable = (Damageable) item.getItemMeta();
        int durability = item.getType().getMaxDurability() - damageable.getDamage();
        if (durability > getCost()) {
            damageable.setDamage(damageable.getDamage() + getCost());
            item.setItemMeta(damageable);
            return true;
        }else {
            return false;
        }
    }

    default boolean isUse(ItemStack item) {
        if (item.getItemMeta() == null) return false;
        if (item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(SkillItems.plugin, getName()), PersistentDataType.STRING)) {
            String container = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(SkillItems.plugin, getName()), PersistentDataType.STRING);
            if (container.equalsIgnoreCase(getName())) {
                return true;
            } else return false;
        } else return false;
    }


    void SkillActive(Player player);
}
