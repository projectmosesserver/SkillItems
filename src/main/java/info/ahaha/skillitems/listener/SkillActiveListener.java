package info.ahaha.skillitems.listener;

import info.ahaha.skillitems.Skill;
import info.ahaha.skillitems.SkillItems;
import info.ahaha.skillitems.SkillType;
import info.ahaha.skillitems.skill.*;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.persistence.PersistentDataType;

public class SkillActiveListener implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEntityEvent e){
        if (!(e.getRightClicked() instanceof LivingEntity))return;
        if (e.getRightClicked() instanceof Player)return;
        for (Skill skill : SkillItems.plugin.getSkills().values()){
            if (skill.getName().equalsIgnoreCase(SkillType.BlowOff.name())){
                BlowOff blowOff = (BlowOff) skill;
                blowOff.SkillActive(e.getPlayer(), (LivingEntity) e.getRightClicked());
            }else if (skill.getName().equalsIgnoreCase(SkillType.Telekinesis.name())){
                Telekinesis telekinesis = (Telekinesis) skill;
                telekinesis.skillActive(e.getPlayer(), (LivingEntity) e.getRightClicked());
            }else if (skill.getName().equalsIgnoreCase(SkillType.UpKnockBack.name())){
                UpKnockBack upKnockBack = (UpKnockBack) skill;
                upKnockBack.SkillActive(e.getPlayer(), (LivingEntity) e.getRightClicked());
            }else if (skill.getName().equalsIgnoreCase(SkillType.StopTheMove.name())){
                StopTheMove stopTheMove = (StopTheMove) skill;
                stopTheMove.skillActive(e.getPlayer(), (LivingEntity) e.getRightClicked());
            }else if (skill.getName().equalsIgnoreCase(SkillType.DeadOrALife.name())){
                DeadOrALife deadOrALife = (DeadOrALife) skill;
                deadOrALife.skillActive(e.getPlayer(), (LivingEntity) e.getRightClicked());
            }
        }
    }

    @EventHandler
    public void onActive(PlayerInteractEvent e){
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if (e.getHand() != EquipmentSlot.HAND)return;
            for (Skill skill : SkillItems.plugin.getSkills().values()){
                skill.SkillActive(e.getPlayer());
            }
        }
    }
}
