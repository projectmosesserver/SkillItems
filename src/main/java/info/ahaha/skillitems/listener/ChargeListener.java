package info.ahaha.skillitems.listener;

import info.ahaha.skillitems.SkillItems;
import info.ahaha.skillitems.SkillType;
import info.ahaha.skillitems.skill.ExplodeBurst;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.persistence.PersistentDataType;

public class ChargeListener implements Listener {

    @EventHandler
    public void onCharge(PlayerToggleSneakEvent e){
        if (e.isSneaking()) {
            ExplodeBurst explodeBurst = (ExplodeBurst) SkillItems.plugin.getSkill(SkillType.ExplodeBurst);
            explodeBurst.charge(e);
        }
    }
}
