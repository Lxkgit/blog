package com.example.blog;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {

    @Test
    void contextLoads() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("billetNumber", "111");
        jsonObject.put("trackingId", "222");
        jsonObject.put("analysisNumber", "333");
        System.out.println(jsonObject.toString());
    }

}
