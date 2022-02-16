package info.ahaha.skillitems.utils;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;

public class ParticleUtil {
    public ParticleUtil(){

    }

    public void createCircle(Location location , Particle particle, int size ,int count){
        for (int d = 0; d <= 90; d += count) {
            Location particleLoc = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());
            particleLoc.setX(location.getX() + Math.cos(d) * size);
            particleLoc.setZ(location.getZ() + Math.sin(d) * size);
            if (particle == Particle.REDSTONE) {
                location.getWorld().spawnParticle(particle, particleLoc, 1, new Particle.DustOptions(Color.WHITE, 5));
            }else {
                location.getWorld().spawnParticle(particle, particleLoc, 1,0,0,0,0);
            }
        }
    }
}
