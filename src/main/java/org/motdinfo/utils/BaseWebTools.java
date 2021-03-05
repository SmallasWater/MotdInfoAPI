package org.motdinfo.utils;

import cn.nukkit.utils.ConfigSection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.codec.binary.Base64;
import org.motdinfo.MotdInfoAPI;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 禁止调用!
 *
 * @author SmallasWater
 * Create on 2021/2/22 17:21
 * Package org.motdinfo.utils
 */
abstract class BaseWebTools {



    private BaseWebTools(){
    }

    private static abstract class BaseDemo {
        private final static BaseWebTools INSTANCE = new BaseWebTools() {};
    }

    static BaseWebTools getInstance(){
        return BaseDemo.INSTANCE;
    }

    private static final String URL = "https://motd.52craft.cc/api/?token={token}";


    /***
     * */
    ConfigSection send(WebData data){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Map<String, Object> tokenMap = new LinkedHashMap<>();
        tokenMap.put("d","auth");
        tokenMap.put("ip",MotdInfoAPI.IP);
        tokenMap.put("port",MotdInfoAPI.PORT);
        tokenMap.put("data",data.toString());
        tokenMap.put("interface","//"+MotdInfoAPI.IP+"/api");
        //修复引号问题
        return push(gson, tokenMap);
    }



    private ConfigSection push(Gson gson, Map<String, Object> tokenMap) {
        String json = gson.toJson(tokenMap).replace("\"\\[","[").replace("\\]\"","]");
        String token = encrypt(json, MotdInfoAPI.getKey(),true);
        String load = loadJson(token);
        return new ConfigSection(gson.fromJson(load, (new TypeToken<LinkedHashMap<String, Object>>() {
        }).getType()));
    }

    private String loadJson(String token) {
        StringBuilder json = new StringBuilder();
        try {
            URL urlObject = new URL(BaseWebTools.URL.replace("{token}",token));
            HttpURLConnection uc = (HttpURLConnection) urlObject.openConnection();
            uc.addRequestProperty(".USER_AGENT","Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9.1.2) Gecko/20090803 java");
            uc.setRequestMethod("GET");
            uc.setConnectTimeout(15000);
            uc.setReadTimeout(15000);
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), StandardCharsets.UTF_8));
            String inputLine;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return json.toString();
    }
    ConfigSection loadJson () {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Map<String, Object> tokenMap = new LinkedHashMap<>();
        tokenMap.put("d","session");
        return push(gson,tokenMap);
    }

    /**加密 / 解密*/
    String encrypt(String input, String key,boolean operation) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHH");
        Date date = new Date();
        key = getMd5(simpleDateFormat.format(date)+key);
        Base64 base64 = new Base64(128);
        try {
            if(operation) {
                 byte[] encrypted = encryption(input,key,Cipher.ENCRYPT_MODE);
                 return URLEncoder.encode(new String(base64.encode(encrypted)).replace("\\", "\\\\"),"UTF-8").replace("%0D%0A","");
            }else{
                 byte[] encrypted = encryption(input,key,Cipher.DECRYPT_MODE);
                 if(encrypted == null){
                     return null;
                 }
                 return URLDecoder.decode(new String(encrypted),"UTF-8");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**使用md5加密字符串*/
    private String getMd5(String plainText) {
        // 返回字符串
        String md5Str = null;
        try {
            StringBuilder buf = new StringBuilder();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte[] b = md.digest();
            int i;
            for (byte value : b) {
                i = value;
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            md5Str = buf.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5Str;
    }

    /**
     * 密码器
     * */
    private byte[] encryption(String input,String key,int mode){
        try {
            String encryptKey = key.substring(0,16);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] raw = encryptKey.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(encryptKey.getBytes());
            cipher.init(mode, skeySpec, iv);
            if(mode == Cipher.DECRYPT_MODE){
                Base64 base64 = new Base64(128);
                byte[] encrypted1 = base64.decode(input);
                try {
                    return  cipher.doFinal(encrypted1);
                }catch (Exception e){
                    return null;
                }
            }
            return cipher.doFinal(input.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




}
