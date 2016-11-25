package com.enikolas;

import com.enikolas.dtos.Stats;
import com.enikolas.models.BetCourse;
import com.enikolas.models.BetMatch;
import com.enikolas.models.Competition;
import com.enikolas.respositories.BetCourseRepository;
import com.enikolas.respositories.BetMatchRepository;
import com.enikolas.respositories.CompetitionRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class JpaOneToOneCascadeApplicationTests {

    @Autowired
    private BetCourseRepository betCourseRepository;

    @Autowired
    private BetMatchRepository betMatchRepository;

    @Autowired
    private CompetitionRepository competitionRepository;

    @After
    public void clearDatabase() {
        betCourseRepository.deleteAll();
        betMatchRepository.deleteAll();
        competitionRepository.deleteAll();
    }

    @Test
    public void testStatsWithCustomQueryCreation() {
        Competition aCompetition = Competition.builder()
                .name("aCompetition")
                .build();

        aCompetition = competitionRepository.saveAndFlush(aCompetition);

        BetMatch betMatch = BetMatch.builder()
                .competition(aCompetition)
                .build();

        betMatch = betMatchRepository.saveAndFlush(betMatch);

        BetCourse betCourse = BetCourse.builder()
                .betMatch(betMatch)
                .course("aCourse")
                .courseType("aCourseType")
                .win(true)
                .build();

        betCourseRepository.saveAndFlush(betCourse);

        List<Stats> statsList = betCourseRepository.computeStatsByBetShop();

        log.info("Stats: {}", statsList);

        Stats expectedStats = Stats.builder()
                .course(betCourse.getCourse())
                .courseType(betCourse.getCourseType())
                .count(1l)
                .score(1l)
                .competition(aCompetition)
                .build();
        assertThat(statsList, hasItems(expectedStats));
    }

}
