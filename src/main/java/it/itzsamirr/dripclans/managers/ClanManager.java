package it.itzsamirr.dripclans.managers;

import it.itzsamirr.dripclans.model.Clan;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author ItzSamirr
 * Created at 11.07.2022
 **/
public class ClanManager {
    private List<Clan> clans = new ArrayList<>();

    public void add(Clan clan) {
        clans.add(clan);
    }

    public void remove(Clan clan) {
        clans.remove(clan);
    }

    public Clan getClan(String name){
        return clans.stream().filter(clan -> clan.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public Clan getClan(UUID player){
        return clans.stream().filter(clan -> clan.getLeader().equals(player.toString()) ||
                clan.getCoLeader() != null && clan.getCoLeader().equals(player.toString())
        || clan.getMembers().contains(player)).findFirst().orElse(null);
    }

    public void setClans(List<Clan> clans) {
        this.clans = clans;
    }

    public List<Clan> getClans() {
        return clans;
    }
}
