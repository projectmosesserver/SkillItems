package info.ahaha.skillitems.utils;

import info.ahaha.skillitems.SkillItems;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DataManager {
    public static SkillItems plugin;
    public static FileConfiguration items,recipe,skill = null;
    public static File itemsfile,recipefile,skillfile = null;

    public DataManager(SkillItems plugins) {
        plugin = plugins;
        saveDefaultConfig();
    }

    public static void reloadConfig() {
        if (itemsfile == null)
            itemsfile = new File(plugin.getDataFolder(), "items.yml");
        if (recipefile == null)
            recipefile = new File(plugin.getDataFolder(),"recipe.yml");
        if (skillfile == null)
            skillfile = new File(plugin.getDataFolder(),"skill.yml");

        items = YamlConfiguration.loadConfiguration(itemsfile);
        recipe = YamlConfiguration.loadConfiguration(recipefile);
        skill = YamlConfiguration.loadConfiguration(skillfile);

        InputStream itemsStream = plugin.getResource("items.yml");
        InputStream recipeStream = plugin.getResource("recipe.yml");
        InputStream skillStream = plugin.getResource("skill.yml");

        if (itemsStream != null) {
            YamlConfiguration item = YamlConfiguration.loadConfiguration(new InputStreamReader(itemsStream));
            items.setDefaults(item);
        }
        if (recipeStream != null){
            YamlConfiguration recipes = YamlConfiguration.loadConfiguration(new InputStreamReader(recipeStream));
            recipe.setDefaults(recipes);
        }
        if (skillStream != null){
            YamlConfiguration skills = YamlConfiguration.loadConfiguration(new InputStreamReader(skillStream));
            skill.setDefaults(skills);
        }

    }


    public FileConfiguration getItems() {
        if (items == null)
            reloadConfig();
        return items;
    }

    public FileConfiguration getRecipe() {
        if (recipe == null)
            reloadConfig();
        return recipe;
    }

    public FileConfiguration getSkill() {
        if (skill == null)
            reloadConfig();
        return skill;
    }


    public void saveDefaultConfig() {

        if (itemsfile == null)
            itemsfile = new File(plugin.getDataFolder(), "items.yml");
        if (recipefile == null)
            recipefile = new File(plugin.getDataFolder(),"recipe.yml");
        if (skillfile == null)
            skillfile = new File(plugin.getDataFolder(),"skill.yml");

        if (!itemsfile.exists())
            plugin.saveResource("items.yml", false);
        if (!recipefile.exists())
            plugin.saveResource("recipe.yml",false);
        if (!skillfile.exists())
            plugin.saveResource("skill.yml",false);
    }
}
