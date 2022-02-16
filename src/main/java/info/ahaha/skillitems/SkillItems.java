package info.ahaha.skillitems;

import info.ahaha.skillitems.listener.ChargeListener;
import info.ahaha.skillitems.listener.SkillActiveListener;
import info.ahaha.skillitems.skill.*;
import info.ahaha.skillitems.utils.CreateItemData;
import info.ahaha.skillitems.utils.DataManager;
import info.ahaha.skillitems.utils.ItemData;
import info.ahaha.skillitems.utils.ParticleUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class SkillItems extends JavaPlugin {

    public static SkillItems plugin;
    public DataManager manager;
    public ParticleUtil particleUtil;
    private Map<SkillType,Skill>skills = new HashMap<>();
    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        manager = new DataManager(this);
        particleUtil = new ParticleUtil();

        BlowOff blowOff = new BlowOff(manager.getSkill().getInt("BlowOff.Cost"),manager.getSkill().getInt("BlowOff.Level"));
        DeadOrALife deadOrALife = new DeadOrALife(manager.getSkill().getInt("DeadOrALife.Cost"),manager.getSkill().getInt("DeadOrALife.Level"));
        ExplodeBurst explodeBurst = new ExplodeBurst(manager.getSkill().getInt("ExplodeBurst.Cost"),manager.getSkill().getInt("ExplodeBurst.Tick"),manager.getSkill().getInt("ExplodeBurst.Level"));
        RandomPotionThrow randomPotionThrow = new RandomPotionThrow(manager.getSkill().getInt("RandomPotionThrow.Cost"),manager.getSkill().getInt("RandomPotionThrow.Level"));
        StopTheMove stopTheMove = new StopTheMove(manager.getSkill().getInt("StopTheMove.Cost"),manager.getSkill().getInt("StopTheMove.Level"));
        Telekinesis telekinesis = new Telekinesis(manager.getSkill().getInt("Telekinesis.Cost"),manager.getSkill().getInt("Telekinesis.Level"));
        UpKnockBack upKnockBack = new UpKnockBack(manager.getSkill().getInt("UpKnockBack.Cost"),manager.getSkill().getInt("UpKnockBack.Level"));
        ThrowTNT throwTNT = new ThrowTNT(manager.getSkill().getInt("ThrowTNT.Cost"),manager.getSkill().getInt("ThrowTNT.Level"));

        skills.put(SkillType.BlowOff,blowOff);
        skills.put(SkillType.DeadOrALife,deadOrALife);
        skills.put(SkillType.ExplodeBurst,explodeBurst);
        skills.put(SkillType.RandomPotionThrow,randomPotionThrow);
        skills.put(SkillType.StopTheMove,stopTheMove);
        skills.put(SkillType.Telekinesis,telekinesis);
        skills.put(SkillType.UpKnockBack,upKnockBack);
        skills.put(SkillType.ThrowTNT,throwTNT);

        new CreateItemData(this);

        for (ItemData data : ItemData.data){
            Bukkit.addRecipe(data.getRecipe());
        }



        getServer().getPluginManager().registerEvents(new ChargeListener(),this);
        getServer().getPluginManager().registerEvents(new SkillActiveListener(),this);


    }

    public Skill getSkill(SkillType type){
        for (Skill skill : skills.values()){
            if (skill.getName().equalsIgnoreCase(type.name()))return skill;
        }
        return null;
    }

    public Map<SkillType, Skill> getSkills() {
        return skills;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        for (ItemData data : ItemData.data){
            data.removeRecipe();
        }
    }
}
