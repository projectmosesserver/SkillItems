package info.ahaha.skillitems.skill;

import info.ahaha.skillitems.Skill;
import info.ahaha.skillitems.SkillItems;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StopTheMove implements Skill {

    private final int cost;
    private final int level;

    public StopTheMove(int cost, int level) {
        this.cost = cost;
        this.level = level;
    }

    @Override
    public String getName() {
        return "StopTheMove";
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
            entity.setAI(false);
            new BukkitRunnable() {
                int i = 0;

                @Override
                public void run() {
                    SkillItems.plugin.particleUtil.createCircle(entity.getLocation().add(0, 2, 0), Particle.END_ROD, 1, 1);
                    if (i >= 10) {
                        entity.setAI(true);
                        this.cancel();
                    }
                    i++;
                }
            }.runTaskTimer(SkillItems.plugin, 0, 2);
        }
    }
}
