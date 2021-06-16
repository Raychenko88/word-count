package org.example.service;

import org.example.model.Stats;

import java.util.List;
import java.util.Map;

public interface StatsService {

    Stats save(Stats stats);

    List<String> saveAll(String sentence);
}
