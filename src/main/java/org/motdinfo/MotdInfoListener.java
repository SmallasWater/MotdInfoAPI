package org.motdinfo;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import org.motdinfo.events.MotdInfoMessageEvent;

/**
 * @author SmallasWater
 * Create on 2021/2/22 22:55
 * Package org.motdinfo
 */
public class MotdInfoListener implements Listener {

    @EventHandler
    public void onMotdInfoGet(MotdInfoMessageEvent event){
        //demo 这里是监听

    }

    @EventHandler
    public void onMotdInfoSend(MotdInfoMessageEvent event){
        //demo 这里是发送的时候
    }
}
