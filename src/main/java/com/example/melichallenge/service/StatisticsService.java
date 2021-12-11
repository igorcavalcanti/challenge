package com.example.melichallenge.service;

import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

//    @Async
//    @Transactional
//    Statistics incrementCounterForSimian() {
//        Statistics statistics = this.getCurrentStatistics();
//        statistics.setSimianCount(statistics.getSimianCount().add(BigInteger.ONE));
//
//        while (true) {
//            try {
//                statistics = this.repository.save(statistics);
//            } catch (OptimisticLockException e) {
//                statistics.setVersion(statistics.getVersion() + 1);
//                continue;
//            }
//            break;
//        }
//        return statistics;
//    }
//
//    @Async
//    @Transactional
//    Statistics incrementCounterForHuman() {
//        Statistics statistics = this.getCurrentStatistics();
//        statistics.setHumanCount(statistics.getHumanCount().add(BigInteger.ONE));
//
//        while (true) {
//            try {
//                statistics = this.repository.save(statistics);
//            } catch (OptimisticLockException e) {
//                statistics.setVersion(statistics.getVersion() + 1);
//                continue;
//            }
//            break;
//        }
//        return statistics;
//    }
//
//    private Statistics getCurrentStatistics() {
//        Statistics statistics;
//
//        if (this.repository.findAll().isEmpty()) {
//            statistics = new Statistics();
//            statistics.setId(1l);
//            statistics.setVersion(0);
//            statistics.setHumanCount(BigInteger.ZERO);
//            statistics.setSimianCount(BigInteger.ZERO);
//
//            try {
//                this.repository.save(statistics);
//            } catch (OptimisticLockException e) {
//                statistics = this.repository.findById(1l).get();
//            }
//        } else {
//            statistics = this.repository.findById(1l).get();
//        }
//        return statistics;
//    }
}
