package org.ajou.realcoding.lolcrawler.lolcrawler.controller;


import org.ajou.realcoding.lolcrawler.lolcrawler.domain.CurrentSummonerStat;
import org.ajou.realcoding.lolcrawler.lolcrawler.service.SummonerStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class SummonerStatController {

    @Autowired
    SummonerStatService summonerStatService;

    @GetMapping("/lol-crawler/{summonerName}")
    public Set<CurrentSummonerStat> getCurrentSummonerStat(@PathVariable String summonerName){
       return summonerStatService.
               getCurrentSummonerStat(summonerStatService.
                       getEncryptedSummonerIdBySummonerName(summonerName));
    }
}
