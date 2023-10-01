package com.tokmakov.fuzzing_pantry.service;

import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface DateService {
    Timestamp getCurrentTimeStamp();
    long getDifferenceFirstDateAndNow(Timestamp firsTimestamp);
}
