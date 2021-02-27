package org.motdinfo.task;

import cn.nukkit.Player;
import cn.nukkit.plugin.Plugin;
import org.motdinfo.utils.MotdInfoManager;
import org.motdinfo.utils.PlayerData;
import org.motdinfo.utils.WebData;

import java.util.ArrayList;
import java.util.LinkedHashMap;


/**
 * 收集服务器玩家与插件信息
 * @author SmallasWater
 * Create on 2021/2/22 17:15
 * Package org.motdinfo.runnables
 */
public class MotdInfoRunnable<T extends Plugin> implements Runnable {


    private T motdInfo;

    public MotdInfoRunnable(T info){
        this.motdInfo = info;
    }

    @Override
    public void run() {
        while (true) {
            ArrayList<LinkedHashMap<String, String>> players = new ArrayList<>();
            for (Player player : getMotdInfo().getServer().getOnlinePlayers().values()) {
                players.add(new PlayerData(player).get());
            }
            WebData data = new WebData();
            data.put("players",players.toString()).put("plugins",getMotdInfo().getServer().getPluginManager().getPlugins().keySet().toString());
            MotdInfoManager.sendWebData(data);
            try {
                Thread.sleep(1000 *60 * 5);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }

    }


    private T getMotdInfo() {
        return motdInfo;
    }
}
