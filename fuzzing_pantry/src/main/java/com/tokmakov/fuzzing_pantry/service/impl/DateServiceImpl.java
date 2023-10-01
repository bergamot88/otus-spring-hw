package com.tokmakov.fuzzing_pantry.service.impl;

import com.tokmakov.fuzzing_pantry.service.DateService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class DateServiceImpl implements DateService {
    @Override
    public Timestamp getCurrentTimeStamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    @Override
    public long getDifferenceFirstDateAndNow(Timestamp firsTimestamp) {
        return getCurrentTimeStamp().getTime() - firsTimestamp.getTime();
    }
}
