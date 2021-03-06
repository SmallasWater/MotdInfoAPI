package org.motdinfo.utils;

import cn.nukkit.Player;

import java.util.LinkedHashMap;

/**
 * @author SmallasWater
 * Create on 2021/2/24 20:21
 * Package org.motdinfo.utils
 */
public class PlayerData {
    private Player player;

    public PlayerData(Player player){
        this.player = player;
    }

    public LinkedHashMap<String, String> get(){
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("name",""+player.getName()+"");
        map.put("uuid",""+player.getUniqueId().toString()+"");
        return map;
    }
}
