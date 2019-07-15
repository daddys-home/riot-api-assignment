package org.ajou.realcoding.lolcrawler.lolcrawler.repository;

import org.ajou.realcoding.lolcrawler.lolcrawler.domain.CurrentSummonerStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.Set;

@Repository
public class CurrentSummonerStatRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    public void insertOrUpdateCurrentSummonerStat(Set<CurrentSummonerStat> currentSummonerStat) {

        Iterator<CurrentSummonerStat> iterator = currentSummonerStat.iterator();

        while (iterator.hasNext()) {
            CurrentSummonerStat summonerStat = iterator.next();
            if (mongoTemplate.exists(Query.query(Criteria.where("summonerName").is(summonerStat.getSummonerName()).and("queueType").is(summonerStat.getQueueType())), CurrentSummonerStat.class)) {

                Update update = new Update();

                update.set("queueType", summonerStat.getQueueType());
                update.set("summonerName", summonerStat.getSummonerName());
                update.set("hotStreak", summonerStat.isHotStreak());
                update.set("miniSeriesDTO", summonerStat.getMiniSeries());
                update.set("wins", summonerStat.getWins());
                update.set("veteran", summonerStat.isVeteran());
                update.set("losses", summonerStat.getLosses());
                update.set("rank", summonerStat.getRank());
                update.set("leagueId", summonerStat.getLeagueId());
                update.set("inactive", summonerStat.isInactive());
                update.set("tier", summonerStat.getTier());
                update.set("summonerId", summonerStat.getSummonerId());
                update.set("leaguePoints", summonerStat.getLeaguePoints());

                mongoTemplate.updateFirst(Query.query(Criteria.where("summonerName").is(summonerStat.getSummonerName()).and("queueType").is(summonerStat.getQueueType())), update, CurrentSummonerStat.class);
            } else mongoTemplate.insert(summonerStat);

        }
    }
}
