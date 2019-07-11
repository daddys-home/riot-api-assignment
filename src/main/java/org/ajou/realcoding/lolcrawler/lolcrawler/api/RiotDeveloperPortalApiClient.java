package org.ajou.realcoding.lolcrawler.lolcrawler.api;

import org.ajou.realcoding.lolcrawler.lolcrawler.domain.CurrentSummonerStat;
import org.ajou.realcoding.lolcrawler.lolcrawler.domain.EncryptedSummonerId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Service
public class RiotDeveloperPortalApiClient {
    //TODO: apiKey는 riot developer portal에서 24시간마다 갱신해야한다.
    private final String apiKey = "RGAPI-5b72ceaf-caff-4778-869f-968408bb7090";
    private final String summonerV4Url = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/{summonerName}?api_key={apiKey}";
    private final String leagueV4Url = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/{encryptedSummonerId}?api_key={apiKey}";

    @Autowired
    RestTemplate restTemplate;

    public EncryptedSummonerId requestEncryptedSummonerId(String summonerName){
        return restTemplate.exchange(summonerV4Url, HttpMethod.GET, null, EncryptedSummonerId.class, summonerName, apiKey)
                .getBody();
    }
    public Set<CurrentSummonerStat> requestCurrentSummonerStat(EncryptedSummonerId encryptedSummonerId){
        return restTemplate.exchange(leagueV4Url, HttpMethod.GET, null, new ParameterizedTypeReference<Set<CurrentSummonerStat>>(){}, encryptedSummonerId.getId(), apiKey)
                .getBody();
    }

}
