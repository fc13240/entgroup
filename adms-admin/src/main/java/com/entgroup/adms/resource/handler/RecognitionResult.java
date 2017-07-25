package com.entgroup.adms.resource.handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.entgroup.adms.model.system.SceneRecognitionResult;
import com.entgroup.adms.resource.handler.Base.BaseHandler;
import com.entgroup.adms.service.SceneRecognitionResultService;
import com.entgroup.adms.util.CheckInput;
import com.entgroup.adms.util.CheckReturn;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: TODO(场景识别服务通信接口)
 * @Author: sunzhen
 * @Date: 2017/3/27
 */

@Component("handler_recognitionResult")
public class RecognitionResult extends BaseHandler {

    @Resource
    SceneRecognitionResultService sceneRecognitionResultService;

    @Override
    public void process(JSONObject input, JSONObject output, Integer clientType, ServletContext servletContext) {

        try {
            CheckInput checkInput = new CheckInput(input);

            Long videoId = null;
            CheckReturn videoIdCheck = checkInput.checkKey(CheckInput.KeySelect.require, CheckInput.KeyType.integer, "videoId");
            if (!videoIdCheck.isSuccess()) {
                videoIdCheck.wrong(output);
                return;
            } else if (videoIdCheck.isUserable()) {
                videoId = input.getLong("videoId");
            }

            String imageServer = null;
            CheckReturn imageServerCheck = checkInput.checkKey(CheckInput.KeySelect.require, CheckInput.KeyType.string, "imageServer");
            if (!imageServerCheck.isSuccess()) {
                imageServerCheck.wrong(output);
                return;
            } else if (imageServerCheck.isUserable()) {
                imageServer = input.getString("imageServer");
            }

            JSONArray results = null;
            CheckReturn resultCheck = checkInput.checkKey(CheckInput.KeySelect.require, CheckInput.KeyType.string, "result");
            if (!resultCheck.isSuccess()) {
                resultCheck.wrong(output);
                return;
            } else if (resultCheck.isUserable()) {
                results = input.getJSONArray("result");
                if (results.size() <= 0) {
                    output.put("msg", "数据为空！");
                    return;
                }
            }

            List<SceneRecognitionResult> list = new ArrayList<>();
            for (int i = 0; i < results.size(); i++) {

                JSONObject result = results.getJSONObject(i);
                Long sceneId = null;
                String videoPosition = null;

                CheckInput checkResult = new CheckInput(result);
                CheckReturn sceneIdCheck = checkResult.checkKey(CheckInput.KeySelect.require, CheckInput.KeyType.integer, "sceneId");
                if (!sceneIdCheck.isSuccess()) {
                    sceneIdCheck.wrong(output);
                    return;
                } else if (sceneIdCheck.isUserable()) {
                    sceneId = result.getLong("sceneId");
                }

                CheckReturn videoPositionCheck = checkResult.checkKey(CheckInput.KeySelect.require, CheckInput.KeyType.string, "videoPosition");
                if (!videoPositionCheck.isSuccess()) {
                    videoPositionCheck.wrong(output);
                    return;
                } else if (sceneIdCheck.isUserable()) {
                    videoPosition = result.getString("videoPosition");
                }

                SceneRecognitionResult sceneRecognitionResult = new SceneRecognitionResult();
                sceneRecognitionResult.setVideoId(videoId);
                sceneRecognitionResult.setSceneId(sceneId);
                sceneRecognitionResult.setVideoPosition(videoPosition);
                sceneRecognitionResult.setCreated(new Date());
                sceneRecognitionResult.setImageServer(imageServer);
                list.add(sceneRecognitionResult);
            }
            sceneRecognitionResultService.insertBatch(list);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            output.put("errCode", 1);
            output.put("msg", e.getMessage());
        }
        output.put("errCode", "0");
        output.put("msg", "查询成功");
        output.put("version", "1.0");
    }
}
