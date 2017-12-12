package com.example.seong_euncho.familymapserver.Model;

/**
 * Created by Seong-EunCho on 4/17/17.
 */

public class Filter {

    private String type;
    private String description;
    private boolean on;

    public Filter(String type, String description) {
        this.type = type;
        this.description = description;
        on = true;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }
}
