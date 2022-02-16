package info.ahaha.skillitems.skill;

import info.ahaha.skillitems.Skill;
import info.ahaha.skillitems.SkillItems;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThrowTNT implements Skill {

    private final int cost;

    private final int level;

    public ThrowTNT(int cost, int level) {
        this.cost = cost;
        this.level = level;
    }

    @Override
    public String getName() {
        return "ThrowTNT";
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
            Item tnt = player.getWorld().dropItem(player.getEyeLocation(), new ItemStack(Material.TNT));
            tnt.setVelocity(player.getLocation().getDirection().multiply(1.0));
            tnt.setCustomName("ThrowTNT");
            new BukkitRunnable() {
                @Override
                public void run() {
                    for (Entity entity : tnt.getWorld().getNearbyEntities(tnt.getLocation(), 0.5, 0.5, 0.5)) {
                        if (entity instanceof LivingEntity) {
                            if (entity.getUniqueId().equals(player.getUniqueId())) continue;
                            ((LivingEntity) entity).damage(10, player);

                            tnt.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, tnt.getLocation(), 1);
                            tnt.getWorld().playSound(tnt.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 2, 1);
                            tnt.remove();
                            this.cancel();
                        }
                    }
                    if (tnt.isOnGround()) {
                        tnt.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, tnt.getLocation(), 1);
                        tnt.getWorld().playSound(tnt.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 2, 1);
                        for (Entity entity : tnt.getWorld().getNearbyEntities(tnt.getLocation(), 2.5, 2.5, 2.5)) {
                            if (entity instanceof LivingEntity) {
                                if (entity.equals(player)) continue;
                                ((LivingEntity) entity).damage(10, player);
                            }
                        }
                        tnt.remove();
                        this.cancel();
                    }
                }
            }.runTaskTimer(SkillItems.plugin, 0, 2);
        }
    }
}
