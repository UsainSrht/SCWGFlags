package me.usainsrht.scwgflags.listeners;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import me.usainsrht.scwgflags.SCWGFlags;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlaceListener implements Listener {

    RegionContainer regionContainer;

    public PlaceListener(RegionContainer regionContainer) {
        this.regionContainer = regionContainer;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlace(BlockPlaceEvent e) {
        Location location = BukkitAdapter.adapt(e.getBlockPlaced().getLocation());
        ApplicableRegionSet regionSet = regionContainer.createQuery().getApplicableRegions(location);
        if (regionSet.getRegions().isEmpty()) return;
        for (ProtectedRegion region : regionSet.getRegions()) {
            StateFlag.State state = region.getFlag(SCWGFlags.ABSOLUTE_PLACE);
            if (state != null) {
                e.setCancelled(state == StateFlag.State.DENY);
                break;
            }
        }
    }

}
