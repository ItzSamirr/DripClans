package it.itzsamirr.dripclans.model;

import it.itzsamirr.dripclans.DripClans;
import it.itzsamirr.samirlib.SamirLib;
import it.itzsamirr.samirlib.configuration.IConfigurationObject;
import it.itzsamirr.samirlib.configuration.json.annotations.JsonPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author ItzSamirr
 * Created at 11.07.2022
 **/
public class Clan implements IConfigurationObject {
    private final DripClans plugin;
    @JsonPath(name = "leader")
    private UUID leader;
    @JsonPath(name = "coleader")
    private UUID coLeader;
    @JsonPath(name = "members")
    private ArrayList<UUID> members;
    @JsonPath(name = "name")
    private String name;
    @JsonPath(name = "friendlyFire")
    private boolean friendlyFire;
    @JsonPath(name = "open")
    private boolean open;

    public Clan(DripClans plugin, String name, UUID leader, UUID coLeader, ArrayList<UUID> members, boolean friendlyFire, boolean open) {
        this.plugin = plugin;
        this.name = name;
        this.leader = leader;
        this.coLeader = coLeader;
        this.members = members;
        this.friendlyFire = friendlyFire;
        this.open = open;
    }

    public Clan(DripClans plugin, String name, UUID leader) {
        this(plugin, name, leader, null, new ArrayList<>(Arrays.asList(leader)), plugin.getConfig().getBoolean("defaults.friendly-fire"), plugin.getConfig().getBoolean("defaults.open"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getLeader() {
        return leader;
    }

    public void setLeader(UUID leader) {
        this.leader = leader;
    }

    public UUID getCoLeader() {
        return coLeader;
    }

    public void setCoLeader(UUID coLeader) {
        this.coLeader = coLeader;
    }

    public ArrayList<UUID> getMembers(Predicate<UUID> predicate) {
        return new ArrayList<>(members.stream().filter(predicate).collect(Collectors.toList()));
    }

    public ArrayList<UUID> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<UUID> members) {
        this.members = members;
    }
}
