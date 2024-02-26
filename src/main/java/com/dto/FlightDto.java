package com.dto;

public class FlightDto {
    private final int taskId;
    private final String taskName;

    public FlightDto(int taskId, String taskName) {
        this.taskId = taskId;
        this.taskName = taskName;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

}
