package io.renren.modules.app.entity;


import lombok.Data;

@Data
public class TemplateData {
    private String value;

    public TemplateData(String value) {
        this.value = value;
    }
}