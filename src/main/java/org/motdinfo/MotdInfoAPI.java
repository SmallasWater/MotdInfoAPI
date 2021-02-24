package org.motdinfo;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;
import org.motdinfo.task.MotdInfoRunnable;
import org.motdinfo.utils.MotdInfoManager;
import java.util.concurrent.*;

/**
 * @author SmallasWater
 * Create on 2021/2/20 19:50
 * Package org.motdinfo
 */
public class MotdInfoAPI extends PluginBase {


    public static String IP;

    public static int PORT;

    private static MotdInfoAPI infoAPI;

    private static ExecutorService executor = Executors.newCachedThreadPool();


    @Override
    public void onEnable() {
        saveDefaultConfig();
        reloadConfig();
        infoAPI = this;
        IP = MotdInfoManager.getServerIp();
        PORT = getServer().getPort();
        getLogger().info(TextFormat.colorize('&',"&e---------------------\n"));
        getLogger().info("本插件运行于 "+
                IP+":"
                +getServer().getPort()+" 服务端上\n");
        getLogger().info(TextFormat.colorize('&',"&e---------------------"));

        executor.submit(new MotdInfoRunnable<>(this));
        this.getServer().getPluginManager().registerEvents(new MotdInfoListener(),this);


    }


    public static String getKey(){
        return infoAPI.getConfig().getString("key","69dcff60ade65ebb803b1b56ba6a3874");
    }
}
