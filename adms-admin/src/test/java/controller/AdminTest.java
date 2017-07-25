package controller;

import com.alibaba.fastjson.JSONObject;
import com.entgroup.adms.util.HttpUtil;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by luodezhao on 16/11/11.
 */
public class AdminTest {
    public static void main(String[] args) {
        getList();
    }

    public static  void getList() {
        JSONObject jsonObject = new JSONObject();
        //jsonObject.put("page", "3");
        jsonObject.put("name", "欢乐颂");
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json;charset=utf-8");
        //System.out.println(jsonObject.toJSONString());
        //       String result =  HttpUtil.get("http://1us6021828.51mypc.cn:8880/pictureRecognition/clientRequest", jsonObject.toJSONString());
        //String result = HttpUtil.post("http://210.14.158.210:8081/video/recomm", headers,jsonObject.toJSONString());
        String result = HttpUtil.get("http://210.14.158.210:8081/video/recomm?name=欢乐颂",jsonObject.toJSONString());
        System.out.println(result);
    }
    public static  void ensure() {
        JSONObject jsonObject = new JSONObject();
        String[] pics = {"11_46224-402_0.jpg"};
        jsonObject.put("selectPics",pics);
        jsonObject.put("method", "searchFace");
        jsonObject.put("person_id", "2000");
        jsonObject.put("method", "selectTrainPics");
        //System.out.println(jsonObject.toJSONString());
//       String result =  HttpUtil.get("http://1us6021828.51mypc.cn:8880/pictureRecognition/clientRequest", jsonObject.toJSONString());
        String result = HttpUtil.post("http://1us6021828.51mypc.cn:8880/pictureRecognition/clientRequest", null,jsonObject.toJSONString());
        //System.out.println(result);
    }


    public static void train() {
        File my = new File("/Users/luodezhao/Desktop/2.png");
        String encode = "";
        try {
            InputStream input=new FileInputStream(my);
            byte[] data = null;
            data = new byte[input.available()];
            input.read(data);
            input.close();
            BASE64Encoder encoder = new BASE64Encoder();
            encode = encoder.encode(data);
        } catch (Exception e) {
            e.printStackTrace();
        }


        JSONObject jsonObject = new JSONObject();

//        String[] videos = {"11_46222","11_46223","11_46224"};
        String[] videos = {"18_301378"};
        jsonObject.put("videoIds",videos);
        jsonObject.put("pic_base64",encode);
        jsonObject.put("pic_name", "123.png");
        //System.out.println(jsonObject.toJSONString());
        String result = HttpUtil.post("http://1us6021828.51mypc.cn:8880/pictureRecognition/clientRequest", null, jsonObject.toJSONString());
//		String aa = HttpUtil.get("http://1us6021828.51mypc.cn:8880/pictureRecognition/clientRequest",jsonObject.toJSONString());
//		String aa = HttpUtil.get("http://1us6021828.51mypc.cn:8880/pictureRecognition/clientRequest",jsonObject.toJSONString());
        //System.out.println(result);
    }

}
