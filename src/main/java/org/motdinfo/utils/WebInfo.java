package org.motdinfo.utils;

import cn.nukkit.utils.ConfigSection;

import java.util.List;
import java.util.Map;

/**
 * @author SmallasWater
 * Create on 2021/3/5 16:51
 * Package org.motdinfo.utils
 */
public class WebInfo {
    private ConfigSection section;

    private ConfigSection data;

    WebInfo(ConfigSection configSection){
        this.section = configSection;
        this.data = MotdInfoManager.decipherWebData(section.getString("data"));
    }

    public String getLoginIp() {
        Map o = (Map) section.get("client");
        return o.get("ip").toString();
    }

    public ConfigSection getData(){
        return data;
    }

    public List<String> getVersion() {
        return data.getStringList("version");
    }
}
