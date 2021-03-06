package org.motdinfo.utils;

import cn.nukkit.Server;
import cn.nukkit.utils.ConfigSection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.motdinfo.MotdInfoAPI;
import org.motdinfo.events.MotdInfoMessageEvent;
import org.motdinfo.events.SendDataPacketEvent;
import java.util.LinkedHashMap;

/**
 * API类
 * @author SmallasWater
 * Create on 2021/2/24 20:10
 * Package org.motdinfo.utils
 */
public class MotdInfoManager {

    /**
     * 获取服务器ip
     * */
    public static String getServerIp(){
        return MotdInfoAPI.getInfoAPI().getWebInfo().getLoginIp();
    }

    public static WebInfo getWebInfo(){
        return new WebInfo(BaseWebTools.getInstance().loadJson());
    }

    /**
     * 向服务器发送请求
     * */
    public static void sendWebData(WebData webData){
        SendDataPacketEvent event = new SendDataPacketEvent(webData);
        Server.getInstance().getPluginManager().callEvent(event);
        if(event.isCancelled()){
            return;
        }
        ConfigSection section = BaseWebTools.getInstance().send(webData);
        if(!section.isEmpty()){
            Server.getInstance().getPluginManager().callEvent(new MotdInfoMessageEvent(section));
        }
    }

    public static ConfigSection decipherWebData(String data){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return new ConfigSection(gson.fromJson(BaseWebTools.getInstance().encrypt(data, false), (new TypeToken<LinkedHashMap<String, Object>>() {
        }).getType()));
    }
}
