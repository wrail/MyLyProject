package com.leyou;


import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringCloudApplication
@EnableZuulProxy
public class LyGateWay {
    public static void main(String[] args) {
        SpringApplication.run(LyGateWay.class);
    }

}
