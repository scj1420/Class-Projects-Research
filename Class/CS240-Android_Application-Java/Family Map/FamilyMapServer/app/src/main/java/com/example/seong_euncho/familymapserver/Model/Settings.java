package com.example.seong_euncho.familymapserver.Model;

import android.graphics.Color;

/**
 * Created by Seong-EunCho on 4/19/17.
 */

public class Settings {
    private int lifeStoryLinesColor;
    private boolean lifeStoryOn;

    private int familyTreeLinesColor;
    private boolean familyTreeOn;

    private int spouseLinesColor;
    private boolean spouseOn;

    private int mapType;

    public Settings() {
        lifeStoryOn = true;
        lifeStoryLinesColor = 0;

        familyTreeOn = true;
        familyTreeLinesColor = 1;

        spouseOn = true;
        spouseLinesColor = 2;

        mapType = 0;
    }

    public int getLifeStoryLinesColor() {
        return lifeStoryLinesColor;
    }

    public void setLifeStoryLinesColor(int lifeStoryLinesColor) {
        this.lifeStoryLinesColor = lifeStoryLinesColor;
    }

    public boolean isLifeStoryOn() {
        return lifeStoryOn;
    }

    public void setLifeStoryOn(boolean lifeStoryOn) {
        this.lifeStoryOn = lifeStoryOn;
    }

    public int getFamilyTreeLinesColor() {
        return familyTreeLinesColor;
    }

    public void setFamilyTreeLinesColor(int familyTreeLinesColor) {
        this.familyTreeLinesColor = familyTreeLinesColor;
    }

    public boolean isFamilyTreeOn() {
        return familyTreeOn;
    }

    public void setFamilyTreeOn(boolean familyTreeOn) {
        this.familyTreeOn = familyTreeOn;
    }

    public int getSpouseLinesColor() {
        return spouseLinesColor;
    }

    public void setSpouseLinesColor(int spouseLinesColor) {
        this.spouseLinesColor = spouseLinesColor;
    }

    public boolean isSpouseOn() {
        return spouseOn;
    }

    public void setSpouseOn(boolean spouseOn) {
        this.spouseOn = spouseOn;
    }

    public int getMapType() {
        return mapType;
    }

    public void setMapType(int mapType) {
        this.mapType = mapType;
    }

    public int getColorConstant(int color){
        switch (color) {
            case 0:
                return Color.GREEN;
            case 1:
                return Color.BLUE;
            case 2:
                return Color.RED;
            default:
                return Color.GREEN;
        }
    }
}
