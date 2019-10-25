package com.pet.ledger.service.impl;

import com.pet.ledger.constant.TypeConstant;
import com.pet.ledger.model.type.Trading;
import com.pet.ledger.model.type.User;
import com.pet.ledger.repository.TradingRepository;
import com.pet.ledger.service.BaseService;
import com.pet.ledger.service.base.TradingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.time.temporal.TemporalAdjusters.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Transactional
public class TradingServiceImpl extends BaseService<Trading> implements TradingService<Trading> {

    private TradingRepository tradingRepository;

    @Override
    public List<Trading> getTradingListByUser(User user) {
        return tradingRepository.getAllByUser(user);
    }

    @Override
    public Map<String, Float> getStatisticByUser(User user, int type, long time) {
        
        Map<String,Float> statisticResult = new HashMap<>();
        
        if (type == TypeConstant.STATISTIC_BY_DAY) {
            statisticResult = getStatisticByDay(user, time);
        }
        else if (type == TypeConstant.STATISTIC_BY_MONTH) {
            statisticResult = getStatisticByMonth(user, time);
        }
        else if (type == TypeConstant.STATISTIC_BY_YEAR) {
            statisticResult = getStatisticByYear(user, time);
        }

        return statisticResult;
    }

    private Map<String, Float> getStatisticByYear(User user, long time) {
        Map<String, Float> statisticResult = new HashMap<>();
        LocalDate localDate = LocalDate.ofEpochDay(time);
        long firstOfYear = localDate.with(firstDayOfYear()).toEpochDay();
        long lastOfYear = localDate.with(lastDayOfYear()).toEpochDay();
        List<Trading> tradings = tradingRepository.getTradingsByUserAndTimeBetween(user, firstOfYear, lastOfYear);
        statisticResult = getStatisticFromTradings(tradings);
        return statisticResult;
    }

    private Map<String, Float> getStatisticByMonth(User user, long time) {
        Map<String, Float> statisticResult = new HashMap<>();
        LocalDate localDate = LocalDate.ofEpochDay(time);
        long firstOfMonth = localDate.with(firstDayOfMonth()).toEpochDay();
        long lastOfMonth = localDate.with(lastDayOfMonth()).toEpochDay();
        List<Trading> tradings = tradingRepository.getTradingsByUserAndTimeBetween(user, firstOfMonth, lastOfMonth);
        statisticResult = getStatisticFromTradings(tradings);
        return statisticResult;
    }

    private Map<String, Float> getStatisticByDay(User user, long time) {
        Map<String, Float> statisticResult = new HashMap<>();
        List<Trading> tradings = tradingRepository.getAllByUserAndTime(user, time);
        statisticResult = getStatisticFromTradings(tradings);
        return statisticResult;
    }

    private Map<String, Float> getStatisticFromTradings(List<Trading> tradings) {
        Map<String, Float> statisticResult = new HashMap<>();
        statisticResult.put(TypeConstant.REQUEST_TRADING_KEY, 0.0f);
        statisticResult.put(TypeConstant.SENT_TRADING_KEY, 0.0f);
        for (Trading trading : tradings) {
            if (trading.getTradingType() == TypeConstant.REQUEST_TRADING) {
                float newTotalRequest = statisticResult.get(TypeConstant.REQUEST_TRADING_KEY) + trading.getMoneyChanges();
                statisticResult.put(TypeConstant.REQUEST_TRADING_KEY, newTotalRequest);
            }
            else if (trading.getTradingType() == TypeConstant.SENT_TRADING) {
                float newTotalSent = statisticResult.get(TypeConstant.SENT_TRADING_KEY) + trading.getMoneyChanges();
                statisticResult.put(TypeConstant.SENT_TRADING_KEY, newTotalSent);
            }
        }
        return statisticResult;
    }
}
