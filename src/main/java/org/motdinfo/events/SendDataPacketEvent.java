package org.motdinfo.events;

import cn.nukkit.event.Cancellable;
import cn.nukkit.event.Event;
import cn.nukkit.event.HandlerList;
import org.motdinfo.utils.WebData;

/**
 * @author SmallasWater
 * Create on 2021/2/22 22:38
 * Package org.motdinfo.events
 */
public class SendDataPacketEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();

    public static HandlerList getHandlers() {
        return HANDLERS;
    }

    private WebData data;

    public SendDataPacketEvent(WebData data){
        this.data = data;
    }

    public WebData getData() {
        return data;
    }

    public void setData(WebData data) {
        this.data = data;
    }
}
