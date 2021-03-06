package org.motdinfo;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;
import org.motdinfo.task.MotdInfoRunnable;
import org.motdinfo.utils.MotdInfoManager;
import org.motdinfo.utils.WebInfo;

import java.util.concurrent.*;

/**
 * @author SmallasWater
 * Create on 2021/2/20 19:50
 * Package org.motdinfo
 */
public class MotdInfoAPI extends PluginBase {


    private static final String PLUGIN_VERSION = "1.0.2";
    public static String IP;

    public static int PORT;

    private static MotdInfoAPI infoAPI;

    private WebInfo webInfo;

    private static ExecutorService executor = Executors.newCachedThreadPool();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        reloadConfig();
        infoAPI = this;
        webInfo = MotdInfoManager.getWebInfo();
        IP = webInfo.getLoginIp();
        PORT = getServer().getPort();
        if(!webInfo.getVersion().contains(PLUGIN_VERSION)){
            getLogger().info(TextFormat.colorize('&',"&c插件过低！请前往: https://github.com/SmallasWater/MotdInfoAPI 下载最新版本"));
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }
        getLogger().info(TextFormat.colorize('&',"&e---------------------\n"));
        getLogger().info(TextFormat.colorize('&',"&e本插件运行于&a "+
                IP+":"
                +getServer().getPort()+" &e服务端上"));
        getLogger().info(TextFormat.colorize('&',"&b插件版本: &f"+PLUGIN_VERSION)+"\n");
        getLogger().info(TextFormat.colorize('&',"&e---------------------\n\n"));
        boolean privacy = getConfig().getBoolean("privacy", true);
        if(privacy) {
            executor.submit(new MotdInfoRunnable<>(this));
        }
        this.getServer().getPluginManager().registerEvents(new MotdInfoListener(),this);


    }

    public WebInfo getWebInfo() {
        return webInfo;
    }

    public static MotdInfoAPI getInfoAPI() {
        return infoAPI;
    }

    public static String getKey(){
        return infoAPI.getConfig().getString("key","69dcff60ade65ebb803b1b56ba6a3874");
    }

    @Override
    public void onDisable() {
        executor.shutdown();
    }
}
