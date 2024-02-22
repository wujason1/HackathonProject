package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Prize;

@Repository
public interface PrizeRepository extends JpaRepository<Prize, Long>{

    @Query(value = "SELECT c.*, p.type AS prize_type " +
            "FROM challenge c " +
            "INNER JOIN prize p ON c.prize_id = p.id " +
            "WHERE c.is_claimed = 0 AND c.user_email = :userEmail " +
            "AND c.prize_id = :prizeId", nativeQuery = true)
    List<Object[]> findAllUnclaimedChallengesWithPrizesByUserEmailAndPrizeType(String userEmail, Long prizeId);
}

