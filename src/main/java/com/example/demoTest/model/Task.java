package com.example.demoTest.model;

import com.example.demoTest.entity.TaskEntity;

public class Task {
    private Long id;
    private String title;
    private Boolean completion;

    public static Task toModel(TaskEntity entity){
        Task model = new Task();
        model.setId(entity.getId());
        model.setTitle(entity.getTitle());
        model.setCompletion(entity.getCompletion());
        return model;
    }

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompletion() {
        return completion;
    }

    public void setCompletion(Boolean completion) {
        this.completion = completion;
    }
}
