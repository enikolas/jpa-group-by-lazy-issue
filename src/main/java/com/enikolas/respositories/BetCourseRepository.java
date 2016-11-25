package com.enikolas.respositories;

import com.enikolas.dtos.Stats;
import com.enikolas.models.BetCourse;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BetCourseRepository extends JpaRepository<BetCourse, Integer> {

    @Query("select new com.enikolas.dtos.Stats(" +
            "c.course, " +
            "c.courseType, " +
            "count(c), " +
            "SUM(CASE WHEN win = true THEN 1 ELSE 0 END), " +
            "cpt) " +
            "from Competition cpt " +
            "inner join cpt.betMatches bm " +
            "inner join bm.courses c " +
            "group by c.course, c.courseType, cpt")
    @ReadOnlyProperty
    public List<Stats> computeStatsByBetShop();
}
