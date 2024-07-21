package me.usainsrht.scwgflags;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.SetFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import me.usainsrht.scwgflags.listeners.BreakListener;
import me.usainsrht.scwgflags.listeners.InteractListener;
import me.usainsrht.scwgflags.listeners.PlaceListener;
import me.usainsrht.scwgflags.listeners.SpawnListener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class SCWGFlags extends JavaPlugin {

    public static StateFlag ABSOLUTE_BREAK;
    public static StateFlag ABSOLUTE_PLACE;
    public static StateFlag ABSOLUTE_INTERACT;
    public static SetFlag<CreatureSpawnEvent.SpawnReason> DENY_SPAWN_REASON;


    @Override
    public void onLoad() {
        FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();

        StateFlag absoluteBreak = new StateFlag("absolute-break", true);
        registry.register(absoluteBreak);
        ABSOLUTE_BREAK = absoluteBreak;

        StateFlag absolutePlace = new StateFlag("absolute-place", true);
        registry.register(absolutePlace);
        ABSOLUTE_PLACE = absolutePlace;

        StateFlag absoluteInteract = new StateFlag("absolute-interact", true);
        registry.register(absoluteInteract);
        ABSOLUTE_INTERACT = absoluteInteract;

        SetFlag<CreatureSpawnEvent.SpawnReason> denySpawnReason = new SetFlag<>("deny-spawn-reason", new DenySpawnReasonFlag("deny-spawn-reason"));
        registry.register(denySpawnReason);
        DENY_SPAWN_REASON = denySpawnReason;
    }

    @Override
    public void onEnable() {
        RegionContainer regionContainer = WorldGuard.getInstance().getPlatform().getRegionContainer();
        getServer().getPluginManager().registerEvents(new BreakListener(regionContainer), this);
        getServer().getPluginManager().registerEvents(new PlaceListener(regionContainer), this);
        getServer().getPluginManager().registerEvents(new InteractListener(regionContainer), this);
        getServer().getPluginManager().registerEvents(new SpawnListener(regionContainer), this);
    }

}
