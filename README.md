# MotdInfoAPI  
#### **介绍**  
 这是一款应用于 Motd网站的 API插件可以通过本插件可以向网站对接 实现部分功能  
 
#### **使用方法:**
```java
import org.motdinfo.utils.MotdInfoManager;
import org.motdinfo.utils.WebData;
import org.motdinfo.utils.GetValue;

/**
* 获取服务器IP
* */
class Demo{
    public void demo(){
        //获取服务器IP
        MotdInfoManager.getServerIp();
        //数据包
        WebData data = new WebData();
        data.put(new GetValue("键","值"));
        //向网站发送数据
        MotdInfoManager.sendWebData(data);
    }
}


```
 
