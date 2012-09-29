
package com.rooandqoo.tricoromedals.models;

public class Medal {

    private int rowid;
    private int category;
    private int color;
    private String description;
    private int check; // 0 = false, 1 = true

    public String dump() {
        return "rowid:" + rowid + ", category:" + category + ", color:" + color + "desc:"
                + description + "checked:" + check;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int isChecked() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public int getRowid() {
        return rowid;
    }

    public void setRowid(int rowid) {
        this.rowid = rowid;
    }
}
