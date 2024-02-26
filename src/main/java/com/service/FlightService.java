package com.service;

import com.dao.TaskDao;
import com.dto.FlightDto;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public final class FlightService {

    private static final FlightService FLIGHT_SERVICE = new FlightService();

    private FlightService () {

    }

    private final TaskDao taskDao = TaskDao.getTaskDao();
    public List<FlightDto> findAll () {
        return taskDao.readAll()
                .stream()
                .map(e -> new FlightDto(e.getTaskId(), e.getTaskName()))
                .collect(toList());
    }

    public static FlightService getInstance () {
        return FLIGHT_SERVICE;
    }
}
