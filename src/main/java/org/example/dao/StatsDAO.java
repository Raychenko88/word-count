package org.example.dao;

import org.example.model.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatsDAO extends JpaRepository<Stats, Integer> {
}
