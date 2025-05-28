package com.suo.aisuperagent.demo.invoke;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.ContentType;
import cn.hutool.json.JSONUtil;
import java.util.HashMap;
import java.util.Map;

public class HttpAIInvoke {
    public static void main(String[] args) {
        // 构建 JSON 请求体
        Map<String, Object> message1 = new HashMap<>();
        message1.put("role", "system");
        message1.put("content", "You are a helpful assistant.");

        Map<String, Object> message2 = new HashMap<>();
        message2.put("role", "user");
        message2.put("content", "你是谁？");

        Map<String, Object> input = new HashMap<>();
        input.put("messages", new Object[]{message1, message2});

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("result_format", "message");

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "qwen-plus");
        requestBody.put("input", input);
        requestBody.put("parameters", parameters);

        // 转成 JSON 字符串
        String bodyJson = JSONUtil.toJsonStr(requestBody);

        // 发起请求
        HttpResponse response = HttpRequest.post("https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation")
                .header("Authorization", "Bearer sk-42c0bc0a732842aeb4953340e699f010")
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Connection", "keep-alive")
                .header("User-Agent", "Hutool-Http")
                .contentType(ContentType.JSON.toString())
                .body(bodyJson)
                .execute();

        // 打印结果
        System.out.println("Status: " + response.getStatus());
        System.out.println("Body: " + response.body());
    }
}
