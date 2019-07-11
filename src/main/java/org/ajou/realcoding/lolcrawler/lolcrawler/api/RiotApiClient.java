package org.ajou.realcoding.lolcrawler.lolcrawler.api;

import org.cnu.realcoding.weathercrawler.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RiotApiClient {
    @Autowired
    private RestTemplate restTemplate;

    private final String appid = "RGAPI-646d549a-57ab-44e1-a5f6-f629523d2227";
    private final String id = "%EA%B3%A0%EB%9D%BC%EC%95%84";
    private final String getencryptedSummonerId = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/{id}?api_key={appid}";

    public User getCurrentUser(){
        User user = restTemplate.exchange(getencryptedSummonerId,HttpMethod.GET,null,User.class,id,appid).getBody();
        return  user;
    }
}
