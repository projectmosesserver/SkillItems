package info.ahaha.skillitems.utils;

import info.ahaha.skillitems.SkillItems;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RecipeRegister {

    public static Recipe register(ItemData data) {

        ShapedRecipe recipe = new ShapedRecipe(data.getKey(), data.getItem());

        String first = SkillItems.plugin.manager.getRecipe().getString(data.getConfigName() + ".First");
        String second = SkillItems.plugin.manager.getRecipe().getString(data.getConfigName() + ".Second");
        String third = SkillItems.plugin.manager.getRecipe().getString(data.getConfigName() + ".Third");

        recipe.shape(first, second, third);
        Set<Character> characters = new HashSet<>();
        for (char c : first.toCharArray()) {
            if (c == ' ') continue;
            characters.add(c);
        }
        for (char c : second.toCharArray()) {
            if (c == ' ') continue;
            characters.add(c);
        }
        for (char c : third.toCharArray()) {
            if (c == ' ') continue;
            characters.add(c);
        }
        String type;
        String item;

        Map<Character, Material> recipes_material = new HashMap<>();
        Map<Character, ItemStack> recipes_itemStack = new HashMap<>();

        for (char c : characters) {
            type = SkillItems.plugin.manager.getRecipe().getString(data.getConfigName() + "." + c + ".Type");
            item = SkillItems.plugin.manager.getRecipe().getString(data.getConfigName() + "." + c + ".Item");
            if (type.equalsIgnoreCase("Material")) {
                recipes_material.put(c, Material.valueOf(item));
            } else if (type.equalsIgnoreCase("Item")) {
                ItemData data1 = null;
                for (ItemData data2 : ItemData.data){
                    if (data2.getConfigName().equalsIgnoreCase(item)){
                        data1 = data2;
                        break;
                    }
                }
                if (data1 == null)return null;
                recipes_itemStack.put(c,data1.getItem());
            }
        }

        if (recipes_material.size() != 0){
            for (char c : recipes_material.keySet()){
                recipe.setIngredient(c,recipes_material.get(c));
            }
        }
        if (recipes_itemStack.size() != 0){
            for (char c : recipes_itemStack.keySet()){
                recipe.setIngredient(c,new RecipeChoice.ExactChoice(recipes_itemStack.get(c)));
            }
        }
        return recipe;
    }
}
