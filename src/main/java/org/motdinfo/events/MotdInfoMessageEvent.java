package org.motdinfo.events;

import cn.nukkit.event.Event;

import cn.nukkit.event.HandlerList;
import cn.nukkit.utils.ConfigSection;
import org.motdinfo.utils.MotdInfoManager;

/**
 * @author SmallasWater
 * Create on 2021/2/22 22:15
 * Package org.motdinfo.events
 */
public class MotdInfoMessageEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    public static HandlerList getHandlers() {
        return HANDLERS;
    }

    private ConfigSection msg;

    public MotdInfoMessageEvent(ConfigSection msg) {
        this.msg = msg;
    }

    public ConfigSection getMsg() {
        return msg;
    }

    public ConfigSection getData(){
        String data = msg.get("data").toString();
        if(data == null || "".equals(data) || "null".equals(data)){
            return new ConfigSection();
        }
        return MotdInfoManager.decipherWebData(data);


    }
}
