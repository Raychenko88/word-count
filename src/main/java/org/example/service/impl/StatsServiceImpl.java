package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dao.StatsDAO;
import org.example.model.Request;
import org.example.model.Stats;
import org.example.service.StatsService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {

    private final StatsDAO statsDAO;
    private final RequestServiceImpl requestService;

    @Override
    public Stats save(Stats stats) {
        return statsDAO.save(stats);
    }

    @Override
    public List<String> saveAll(String sentence) {
        Integer count = 0;
        List<String> list = new ArrayList<>();
        Map<String, Integer> map = countRepetitions(splitString(sentence));
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (map.size() > 0) {
                Integer mapSize = map.size();
                Request request = requestService.save(sentence);
                Stats stats = new Stats();
                stats.setWord(entry.getKey());
                stats.setEntry(entry.getValue());
                stats.setRequestId(request);
                statsDAO.save(stats);
                if (stats.getEntry() > 1) {
                    list.add(stats.getWord() + " - " + stats.getEntry());
                }else if (count == 0) {
                    count++;
                    list.add(list.size(), "Unique:" + " - " + stats.getEntry());
                }else if (count > 0) {
                    count++;
                    list.set(list.size() -1, "Unique:" + " - " + count);
                }
            }

        }
        return list;
    }

    public List<String> splitString(String st) {
        ArrayList<String> arr = new ArrayList<>();
        for (String s : st.split("[\\s|,]")) {
            if (!s.isEmpty()) {
                arr.add(s);
            }
        }
        return arr;
    }

    public static Map<String, Integer> countRepetitions(List<String> list) {
        Map<String, Integer> map = list.stream()
                .collect(Collectors.toMap(s -> s, s -> 1, (a, b) -> a + 1));

        Map<String, Integer> sortedMap =
                map.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));
        return sortedMap;
    }
}
