package org.motdinfo.task;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.plugin.Plugin;
import org.motdinfo.MotdInfoAPI;
import org.motdinfo.utils.MotdInfoManager;
import org.motdinfo.utils.PlayerData;
import org.motdinfo.utils.WebData;

import java.util.ArrayList;
import java.util.Base64;
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
            data.put("players", players).put("plugins", getMotdInfo().getServer().getPluginManager().getPlugins().keySet());
            String name = MotdInfoAPI.getInfoAPI().getConfig().getString("server-name","Nukkit 服务器");
            String base64Name = Base64.getMimeEncoder().encodeToString(name.getBytes());
            data.put("name",base64Name);
            data.put("tps", Server.getInstance().getTicksPerSecond()+"");
            data.put("load",Server.getInstance().getTickUsage()+"");
            MotdInfoManager.sendWebData(data);
            try {
                Thread.sleep(1000 * 60 * 5);
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
