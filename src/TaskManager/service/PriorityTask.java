package TaskManager.service;

import TaskManager.TaskManager;

public enum PriorityTask {

    IMPORTANT("important"),
    NOT_IMPORTANT("not important");

    private String value;

    PriorityTask(String value) {
        this.value = value;

    }

    public String getValue() {
        return value;
    }
}
