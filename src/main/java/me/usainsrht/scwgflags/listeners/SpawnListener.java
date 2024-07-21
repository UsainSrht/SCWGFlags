package me.usainsrht.scwgflags.listeners;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import me.usainsrht.scwgflags.SCWGFlags;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.Set;

public class SpawnListener implements Listener {

    RegionContainer regionContainer;

    public SpawnListener(RegionContainer regionContainer) {
        this.regionContainer = regionContainer;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlace(CreatureSpawnEvent e) {
        Location location = BukkitAdapter.adapt(e.getLocation());
        ApplicableRegionSet regionSet = regionContainer.createQuery().getApplicableRegions(location);
        if (regionSet.getRegions().isEmpty()) return;
        for (ProtectedRegion region : regionSet.getRegions()) {
            Set<CreatureSpawnEvent.SpawnReason> deniedReasons = region.getFlag(SCWGFlags.DENY_SPAWN_REASON);
            if (deniedReasons == null || deniedReasons.isEmpty()) continue;
            if (deniedReasons.contains(e.getSpawnReason())) {
                e.setCancelled(true);
                break;
            }
        }
    }

}
