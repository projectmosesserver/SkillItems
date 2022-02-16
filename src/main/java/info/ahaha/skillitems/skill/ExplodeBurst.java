package info.ahaha.skillitems.skill;

import info.ahaha.skillitems.Skill;
import info.ahaha.skillitems.SkillItems;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;

public class ExplodeBurst implements Skill {
    private final int cost;
    private final int tick;
    public static Map<Player, Integer> charges = new HashMap<>();

    private final int level;

    public ExplodeBurst(int cost, int tick, int level) {
        this.cost = cost;
        this.level = level;
        this.tick = tick;
    }

    @Override
    public String getName() {
        return "ExplodeBurst";
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
        if (charges.containsKey(player)) {
            if (isUse(player.getInventory().getItemInMainHand())) {
                if (!isLevel(player)) {
                    player.sendMessage(ChatColor.GOLD + "[ SkillItems ] " + ChatColor.RED + "このスキルを使うためには Lv." + getLevel() + " 以上になる必要があります！");
                    return;
                }
                if (!isCost(player.getInventory().getItemInMainHand())) return;
                player.swingMainHand();
                int charge = charges.get(player) / 10;
                Location loc = player.getLocation().clone();
                Vector vector = loc.getDirection().multiply(1.0).normalize();
                for (int i = 0; i < 3; i++) {
                    loc.add(vector);
                }
                new BukkitRunnable() {
                    int i = 0;

                    @Override
                    public void run() {
                        if (i >= charge) {
                            player.setSneaking(false);
                            this.cancel();
                        }
                        i++;
                        loc.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, loc, 1, 0, 0, 0, 1);
                        loc.getWorld().playSound(loc, Sound.ENTITY_GENERIC_EXPLODE, 3, 1);
                        for (Entity entity : loc.getWorld().getNearbyEntities(loc, 2.5, 2.5, 2.5)) {
                            if (entity instanceof LivingEntity) {
                                if (!(entity instanceof Player)) {
                                    LivingEntity livingEntity = (LivingEntity) entity;
                                    livingEntity.damage(8 + charge, player);
                                }
                            }
                        }
                        loc.add(vector);
                    }
                }.runTaskTimer(SkillItems.plugin, 0, 2);

            }
        }
    }

    public void charge(PlayerToggleSneakEvent e) {
        if (isUse(e.getPlayer().getInventory().getItemInMainHand())) {
            if (!isLevel(e.getPlayer())) return;
            if (!charges.containsKey(e.getPlayer())) {
                charges.put(e.getPlayer(), 0);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (e.getPlayer().isSneaking()) {
                            if (charges.get(e.getPlayer()) + 10 <= 100) {
                                charges.put(e.getPlayer(), charges.get(e.getPlayer()) + 10);
                            }
                            if (charges.get(e.getPlayer()) < 100) {
                                for (int i = 0; i < 5; i++) {
                                    e.getPlayer().spawnParticle(Particle.EXPLOSION_NORMAL, e.getPlayer().getLocation(), 5, 0.2, 0.2, 0.2, 0);

                                }
                            } else {
                                for (int i = 0; i < 5; i++) {
                                    e.getPlayer().spawnParticle(Particle.EXPLOSION_NORMAL, e.getPlayer().getLocation(), 5, 0.2, 0.2, 0.2, 0);
                                    e.getPlayer().spawnParticle(Particle.FLAME, e.getPlayer().getLocation(), 20, 0.3, 0.5, 0.3, 0);

                                }
                            }
                            e.getPlayer().sendMessage(ChatColor.GOLD + "[ SkillItems ] " + ChatColor.DARK_GREEN + "現在のチャージ率 : " + charges.get(e.getPlayer()) + "%");

                        } else {
                            charges.remove(e.getPlayer());
                            e.getPlayer().sendMessage(ChatColor.GOLD + "[ SkillItems ] " + ChatColor.DARK_GREEN + "チャージが解除されました！");
                            this.cancel();
                        }
                    }
                }.runTaskTimer(SkillItems.plugin, 0, tick);
            }
        }
    }
}
