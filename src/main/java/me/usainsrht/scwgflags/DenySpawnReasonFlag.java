package me.usainsrht.scwgflags;

import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.FlagContext;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public class DenySpawnReasonFlag extends Flag<CreatureSpawnEvent.SpawnReason> {

    public DenySpawnReasonFlag(String name) {
        super(name);
    }

    @Override
    public CreatureSpawnEvent.SpawnReason parseInput(FlagContext flagContext) {
        String input = flagContext.getUserInput().trim();
        return CreatureSpawnEvent.SpawnReason.valueOf(input.toUpperCase(Locale.ENGLISH));
    }

    @Override
    public CreatureSpawnEvent.SpawnReason unmarshal(@Nullable Object o) {
        return CreatureSpawnEvent.SpawnReason.valueOf(o.toString().toUpperCase(Locale.ENGLISH));
    }

    @Override
    public Object marshal(CreatureSpawnEvent.SpawnReason spawnReason) {
        return spawnReason.name();
    }
}
