package info.ahaha.skillitems.utils;

import info.ahaha.skillitems.Skill;
import info.ahaha.skillitems.SkillItems;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class CreateItemData {

    public CreateItemData(SkillItems plugin) {
        create(plugin.manager);
        setDataRecipes();
    }

    public void create(DataManager manager) {
        for (String config : manager.getItems().getStringList("Items")) {
            ItemStack item = new ItemStack(Material.valueOf(manager.getItems().getString(config + ".Material")));
            ItemMeta meta = item.getItemMeta();


            meta.setDisplayName(manager.getItems().getString(config + ".Name"));
            List<String>lore = manager.getItems().getStringList(config + ".Lore");
            if (manager.getItems().getConfigurationSection(config + ".Enchant") != null) {
                for (String ench : manager.getItems().getStringList(config + ".Enchant.List")) {
                    meta.addEnchant(Enchantment.getByName(ench), manager.getItems().getInt(config + ".Enchant." + ench + ".Level"), true);
                }
            } else {
                if (manager.getItems().getBoolean(config + ".Glow")) {
                    meta.addEnchant(Enchantment.MENDING, 1, true);
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                }
            }

            for (Skill skill : SkillItems.plugin.getSkills().values()){
                if (manager.getItems().getString(config+".Skill") == null)break;
                if (manager.getItems().getString(config+".Skill").equalsIgnoreCase(skill.getName())){
                    meta.getPersistentDataContainer().set(new NamespacedKey(SkillItems.plugin,skill.getName()), PersistentDataType.STRING,skill.getName());
                    lore.addAll(skill.getLore());
                    break;
                }
            }
            meta.setLore(lore);
            item.setItemMeta(meta);
            ItemData data = new ItemData(config, item);
            ItemData.data.add(data);

        }
    }

    public void setDataRecipes() {
        for (ItemData data : ItemData.data) {
            data.setRecipe(RecipeRegister.register(data));
        }
    }
}
