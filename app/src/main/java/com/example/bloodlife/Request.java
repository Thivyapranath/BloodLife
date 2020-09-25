package com.example.bloodlife;

public class Request {

    private String Name;
    private String blgrp;
    private String units;
    private String hos;
    private Integer pno;

    public Integer getPno() {
        return pno;
    }

    public void setPno(Integer pno) {
        this.pno = pno;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getHos() {
        return hos;
    }

    public void setHos(String hos) {
        this.hos = hos;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getBlgrp() {
        return blgrp;
    }

    public void setBlgrp(String blgrp) {
        this.blgrp = blgrp;
    }

    public Request() {
    }
}
