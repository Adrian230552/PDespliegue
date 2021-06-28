package com.challenge.longlife.resource;

import com.challenge.longlife.domain.model.AuditModel;

import javax.validation.constraints.NotNull;

public class SaveGreenLeafResource {

    @NotNull
    private String title;

    @NotNull
    private String scenario;

    @NotNull
    private String tip;

    public String getTitle() {
        return title;
    }

    public SaveGreenLeafResource setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getScenario() {
        return scenario;
    }

    public SaveGreenLeafResource setScenario(String scenario) {
        this.scenario = scenario;
        return this;
    }

    public String getTip() {
        return tip;
    }

    public SaveGreenLeafResource setTip(String tip) {
        this.tip = tip;
        return this;
    }
}
