package info.ahaha.skillitems.skill;

import info.ahaha.skillitems.Skill;
import org.bukkit.ChatColor;
import org.bukkit.EntityEffect;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Telekinesis implements Skill {
    private final int cost;

    private final int level;

    public Telekinesis(int cost, int level) {
        this.cost = cost;
        this.level = level;
    }

    @Override
    public String getName() {
        return "Telekinesis";
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
            for (int i = 0; i < 4; i++) {
                if (entity.getLocation().add(0, 1, 0).getBlock().isEmpty()) {
                    entity.teleport(entity.getLocation().add(0, 1, 0));
                    entity.playEffect(EntityEffect.TELEPORT_ENDER);
                }
            }
        }
    }
}
