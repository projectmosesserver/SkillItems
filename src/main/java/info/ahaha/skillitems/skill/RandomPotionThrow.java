package info.ahaha.skillitems.skill;

import info.ahaha.skillitems.Skill;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomPotionThrow implements Skill {

    private final int cost;
    private final int level;

    public RandomPotionThrow(int cost, int level) {
        this.cost = cost;
        this.level = level;
    }

    private PotionEffect[] effects = {

            new PotionEffect(PotionEffectType.REGENERATION, 900, 0),
            new PotionEffect(PotionEffectType.REGENERATION, 450, 1),
            new PotionEffect(PotionEffectType.REGENERATION, 2400, 0),
            new PotionEffect(PotionEffectType.REGENERATION, 1200, 1),

            new PotionEffect(PotionEffectType.SPEED, 3600, 0),
            new PotionEffect(PotionEffectType.SPEED, 1800, 1),
            new PotionEffect(PotionEffectType.SPEED, 9600, 0),
            new PotionEffect(PotionEffectType.SPEED, 4800, 1),

            new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 3600, 0),
            new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 3600, 0),
            new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 9600, 0),
            new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 9600, 0),

            new PotionEffect(PotionEffectType.POISON, 900, 0),
            new PotionEffect(PotionEffectType.POISON, 450, 1),
            new PotionEffect(PotionEffectType.POISON, 2400, 0),
            new PotionEffect(PotionEffectType.POISON, 1200, 1),

            new PotionEffect(PotionEffectType.HEAL, 1, 0),
            new PotionEffect(PotionEffectType.HEAL, 1, 1),
            new PotionEffect(PotionEffectType.HEAL, 1, 0),
            new PotionEffect(PotionEffectType.HEAL, 1, 1),

            new PotionEffect(PotionEffectType.NIGHT_VISION, 3600, 0),
            new PotionEffect(PotionEffectType.NIGHT_VISION, 3600, 0),
            new PotionEffect(PotionEffectType.NIGHT_VISION, 9600, 0),
            new PotionEffect(PotionEffectType.NIGHT_VISION, 9600, 0),

            new PotionEffect(PotionEffectType.WEAKNESS, 1800, 0),
            new PotionEffect(PotionEffectType.WEAKNESS, 1800, 0),
            new PotionEffect(PotionEffectType.WEAKNESS, 4800, 0),
            new PotionEffect(PotionEffectType.WEAKNESS, 4800, 0),

            new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 3600, 0),
            new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1800, 1),
            new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9600, 0),
            new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 4800, 1),

            new PotionEffect(PotionEffectType.SLOW, 1800, 0),
            new PotionEffect(PotionEffectType.SLOW, 1800, 0),
            new PotionEffect(PotionEffectType.SLOW, 4800, 0),
            new PotionEffect(PotionEffectType.SLOW, 4800, 0),

            new PotionEffect(PotionEffectType.JUMP, 3600, 0),
            new PotionEffect(PotionEffectType.JUMP, 1800, 1),
            new PotionEffect(PotionEffectType.JUMP, 9600, 0),
            new PotionEffect(PotionEffectType.JUMP, 4800, 1),

            new PotionEffect(PotionEffectType.HARM, 1, 0),
            new PotionEffect(PotionEffectType.HARM, 1, 1),
            new PotionEffect(PotionEffectType.HARM, 1, 0),
            new PotionEffect(PotionEffectType.HARM, 1, 1),

            new PotionEffect(PotionEffectType.WATER_BREATHING, 3600, 0),
            new PotionEffect(PotionEffectType.WATER_BREATHING, 3600, 0),
            new PotionEffect(PotionEffectType.WATER_BREATHING, 9600, 0),
            new PotionEffect(PotionEffectType.WATER_BREATHING, 9600, 0),

            new PotionEffect(PotionEffectType.INVISIBILITY, 3600, 0),
            new PotionEffect(PotionEffectType.INVISIBILITY, 3600, 0),
            new PotionEffect(PotionEffectType.INVISIBILITY, 9600, 0),
            new PotionEffect(PotionEffectType.INVISIBILITY, 9600, 0),

            new PotionEffect(PotionEffectType.REGENERATION, 675, 0),
            new PotionEffect(PotionEffectType.REGENERATION, 338, 1),
            new PotionEffect(PotionEffectType.REGENERATION, 1800, 0),
            new PotionEffect(PotionEffectType.REGENERATION, 900, 1),

            new PotionEffect(PotionEffectType.SPEED, 2700, 0),
            new PotionEffect(PotionEffectType.SPEED, 1350, 1),
            new PotionEffect(PotionEffectType.SPEED, 7200, 0),
            new PotionEffect(PotionEffectType.SPEED, 3600, 1),

            new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 2700, 0),
            new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 2700, 0),
            new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 7200, 0),
            new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 7200, 0),

            new PotionEffect(PotionEffectType.POISON, 675, 0),
            new PotionEffect(PotionEffectType.POISON, 338, 1),
            new PotionEffect(PotionEffectType.POISON, 1800, 0),
            new PotionEffect(PotionEffectType.POISON, 900, 1),

            new PotionEffect(PotionEffectType.HEAL, 1, 0),
            new PotionEffect(PotionEffectType.HEAL, 1, 1),
            new PotionEffect(PotionEffectType.HEAL, 1, 0),
            new PotionEffect(PotionEffectType.HEAL, 1, 1),

            new PotionEffect(PotionEffectType.NIGHT_VISION, 2700, 0),
            new PotionEffect(PotionEffectType.NIGHT_VISION, 2700, 0),
            new PotionEffect(PotionEffectType.NIGHT_VISION, 7200, 0),
            new PotionEffect(PotionEffectType.NIGHT_VISION, 7200, 0),

            new PotionEffect(PotionEffectType.WEAKNESS, 1350, 0),
            new PotionEffect(PotionEffectType.WEAKNESS, 1350, 0),
            new PotionEffect(PotionEffectType.WEAKNESS, 3600, 0),
            new PotionEffect(PotionEffectType.WEAKNESS, 3600, 0),

            new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2700, 0),
            new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1350, 1),
            new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 7200, 0),
            new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 3600, 1),

            new PotionEffect(PotionEffectType.SLOW, 1350, 0),
            new PotionEffect(PotionEffectType.SLOW, 1350, 0),
            new PotionEffect(PotionEffectType.SLOW, 3600, 0),
            new PotionEffect(PotionEffectType.SLOW, 3600, 0),

            new PotionEffect(PotionEffectType.JUMP, 2700, 0),
            new PotionEffect(PotionEffectType.JUMP, 1350, 1),
            new PotionEffect(PotionEffectType.JUMP, 7200, 0),
            new PotionEffect(PotionEffectType.JUMP, 3600, 1),

            new PotionEffect(PotionEffectType.HARM, 1, 0),
            new PotionEffect(PotionEffectType.HARM, 1, 1),
            new PotionEffect(PotionEffectType.HARM, 1, 0),
            new PotionEffect(PotionEffectType.HARM, 1, 1),

            new PotionEffect(PotionEffectType.WATER_BREATHING, 2700, 0),
            new PotionEffect(PotionEffectType.WATER_BREATHING, 2700, 0),
            new PotionEffect(PotionEffectType.WATER_BREATHING, 7200, 0),
            new PotionEffect(PotionEffectType.WATER_BREATHING, 7200, 0),

            new PotionEffect(PotionEffectType.INVISIBILITY, 2700, 0),
            new PotionEffect(PotionEffectType.INVISIBILITY, 2700, 0),
            new PotionEffect(PotionEffectType.INVISIBILITY, 7200, 0),
            new PotionEffect(PotionEffectType.INVISIBILITY, 7200, 0)


    };

    @Override
    public String getName() {
        return "RandomPotionThrow";
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public List<String> getLore() {
                return new ArrayList<>(Arrays.asList(
                ChatColor.DARK_GRAY+"[ "+ChatColor.YELLOW+"Skill"+ChatColor.DARK_GRAY+" ] -------------",
                ChatColor.YELLOW+"Name"+ChatColor.GRAY+" >> "+ChatColor.AQUA+getName(),
                ChatColor.YELLOW+"Cost"+ChatColor.GRAY+" >> "+ChatColor.AQUA+getCost(),
                ChatColor.YELLOW+"Level"+ChatColor.GRAY+" >> "+ChatColor.AQUA+getLevel(),
                ChatColor.DARK_GRAY+"-------------------"
        ));
    }

    @Override
    public void SkillActive(Player player) {

        if (isUse(player.getInventory().getItemInMainHand())) {
            if (!isLevel(player)) {
                player.sendMessage(ChatColor.GOLD + "[ SkillItems ] " + ChatColor.RED + "このスキルを使うためには Lv." + getLevel() + " 以上になる必要があります！");
                return;
            }
            if (!isCost(player.getInventory().getItemInMainHand())) return;
            player.swingMainHand();
            ItemStack item = new ItemStack(Material.SPLASH_POTION);
            PotionMeta meta = (PotionMeta) item.getItemMeta();
            Random r = new Random();
            int random = r.nextInt(effects.length);
            PotionEffect effect = effects[random];
            meta.addCustomEffect(effect, true);
            item.setItemMeta(meta);
            ThrownPotion potion = player.getWorld().spawn(player.getLocation().add(0, 2.5, 0), ThrownPotion.class);
            potion.setItem(item);
            potion.setShooter(player);
            potion.setVelocity(player.getLocation().getDirection().multiply(1.0D));
        }
    }
}
