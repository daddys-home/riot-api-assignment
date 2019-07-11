package org.ajou.realcoding.lolcrawler.lolcrawler.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.lolcrawler.lolcrawler.api.RiotDeveloperPortalApiClient;
import org.ajou.realcoding.lolcrawler.lolcrawler.domain.CurrentSummonerStat;
import org.ajou.realcoding.lolcrawler.lolcrawler.domain.EncryptedSummonerId;
import org.ajou.realcoding.lolcrawler.lolcrawler.repository.CurrentSummonerStatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
public class SummonerStatService {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    RiotDeveloperPortalApiClient riotDeveloperPortalApiClient;
    @Autowired
    CurrentSummonerStatRepository currentSummonerStatRepository;

    public EncryptedSummonerId getEncryptedSummonerIdBySummonerName(String summonerName) {
        return  riotDeveloperPortalApiClient.requestEncryptedSummonerId(summonerName);
    }

    public Set<CurrentSummonerStat> getCurrentSummonerStat(EncryptedSummonerId encryptedSummonerId) {

        Set<CurrentSummonerStat> currentSummonerStat = riotDeveloperPortalApiClient.requestCurrentSummonerStat(encryptedSummonerId);

        currentSummonerStatRepository.insertOrUpdateCurrentSummonerStat(currentSummonerStat);

        log.info("Current summoner stat has been inserted successfully. {}", currentSummonerStat);

        return currentSummonerStat;
    }
}
