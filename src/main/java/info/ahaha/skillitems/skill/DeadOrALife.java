package info.ahaha.skillitems.skill;

import info.ahaha.skillitems.Skill;
import info.ahaha.skillitems.SkillItems;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DeadOrALife implements Skill {

    private final int cost;

    private final int level;

    public DeadOrALife(int cost, int level) {
        this.cost = cost;
        this.level = level;
    }

    @Override
    public String getName() {
        return "DeadOrALife";
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

    }

    public void skillActive(Player player, LivingEntity entity) {

        if (isUse(player.getInventory().getItemInMainHand())) {
            if (!isLevel(player)) {
                player.sendMessage(ChatColor.GOLD + "[ SkillItems ] " + ChatColor.RED + "このスキルを使うためには Lv." + getLevel() + " 以上になる必要があります！");
                return;
            }
            if (!isCost(player.getInventory().getItemInMainHand())) return;
            player.swingMainHand();
            Random random = new Random();
            int r = random.nextInt(100);
            if (r > 49) {
                player.damage(player.getMaxHealth() / 2, player);
                SkillItems.plugin.particleUtil.createCircle(player.getLocation(), Particle.SOUL, 2, 1);
            } else {
                entity.damage(entity.getMaxHealth() / 2, player);
                SkillItems.plugin.particleUtil.createCircle(entity.getLocation(), Particle.SOUL, 2, 1);
            }
        }
    }
}
