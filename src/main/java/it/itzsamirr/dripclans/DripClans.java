package it.itzsamirr.dripclans;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.itzsamirr.dripclans.managers.ClanManager;
import it.itzsamirr.dripclans.model.Clan;
import it.itzsamirr.samirlib.SamirLib;
import it.itzsamirr.samirlib.configuration.json.builder.JsonConfigBuilder;
import it.itzsamirr.samirlib.plugin.PluginInfo;
import it.itzsamirr.samirlib.plugin.SamirPlugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

@PluginInfo(prefix = "&7[&3&lDripClans&7] &r")
public final class DripClans extends SamirPlugin {
    private ClanManager clanManager;

    @Override
    public void enable() {
        this.clanManager = new ClanManager();
        SamirLib.getInstance().getJsonConfigManager().registerConfig(
                new JsonConfigBuilder<DripClans, Clan>(this,
                        new File(getDataFolder(), "clans.json"),
                        clanManager.getClans())
                        .onLoad((file, entry) -> {
                            Gson gson = entry.getKey();
                            try {
                                FileReader reader = new FileReader(file);
                                HashMap<String, Object>[] arr = gson.fromJson(reader, HashMap[].class);
                                if(arr == null || arr.length == 0)return;
                                List<Clan> clans = Arrays.asList(arr)
                                        .stream()
                                        .map(map -> {
                                           String name = String.valueOf(map.get("name"));
                                           UUID leader = UUID.fromString(String.valueOf(map.get("leader")));
                                           UUID coleader = UUID.fromString(String.valueOf(map.get("coleader")));
                                           ArrayList<UUID> members = new ArrayList<>(((ArrayList<String>) map.get("members")).stream().map(UUID::fromString).collect(Collectors.toList()));
                                           ArrayList<UUID> invited = new ArrayList<>(((ArrayList<String>) map.get("invited")).stream().map(UUID::fromString).collect(Collectors.toList()));
                                           boolean friendlyFire = (boolean) map.get("friendlyFire");
                                           boolean open = (boolean) map.get("open");
                                             return new Clan(this, name, leader, coleader, members, friendlyFire, open);
                                        })
                                        .collect(Collectors.toList());
                                clanManager.setClans(clans);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        })
                .build());
        SamirLib.getInstance().getJsonConfigManager().load(this);
    }

    @Override
    public void disable() {

    }
}
