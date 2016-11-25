package com.enikolas.respositories;

import com.enikolas.models.BetMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BetMatchRepository extends JpaRepository<BetMatch, Integer> {
}
