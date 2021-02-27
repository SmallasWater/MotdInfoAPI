package org.motdinfo;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import org.motdinfo.task.MotdInfoRunnable;
import org.motdinfo.utils.MotdInfoManager;

import java.util.Scanner;
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

    private static boolean PRIVACY = true;
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
        getLogger().info(TextFormat.colorize('&',"&e---------------------\n\n"));
        if("".equals(getConfig().get("privacy",""))){
            getLogger().info(TextFormat.colorize('&',"&c[隐私条款]&e本插件仅收集服务器的玩家名称信息与插件名称信息上传到Motd网站"));
            getLogger().info(TextFormat.colorize('&',"&c[隐私条款]&e信息上传采用异步线程 不会影响服务器的游戏体验"));
            getLogger().info(TextFormat.colorize('&',"&c[隐私条款]&b本插件不会收集额外的信息，不会向第三方传递传递任何信息 若不同意请在配置文件关闭\n"));
            Config config = getConfig();
            config.set("privacy",PRIVACY);
            config.save();

        }else{
            PRIVACY = getConfig().getBoolean("privacy");
        }

        if(PRIVACY) {
            executor.submit(new MotdInfoRunnable<>(this));
        }
        this.getServer().getPluginManager().registerEvents(new MotdInfoListener(),this);


    }


    public static String getKey(){
        return infoAPI.getConfig().getString("key","69dcff60ade65ebb803b1b56ba6a3874");
    }
}
