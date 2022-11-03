package com.test.springtest.model;

import java.time.LocalDate;

public class Candidate {

    //	electoral district選區
    private String ed;
    private LocalDate sData;
    private String name;
    private String party;
    private String remark;

    public Candidate(String ed, LocalDate sData, String name, String party, String remark) {
        this.ed = ed;
        this.sData = sData;
        this.name = name;
        this.party = party;
        this.remark = remark;
    }

    public Candidate() {

    }

    public String getEd() {
        return ed;
    }

    public void setEd(String ed) {
        this.ed = ed;
    }

    public LocalDate getsData() {
        return sData;
    }

    public void setsData(LocalDate sData) {
        this.sData = sData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String toCSV() {
        return String.format("%s,%s,%s,%s,%s", this.ed, this.sData, this.name, this.party, this.remark);
    }

    public String Cdlist() {
        return String.format("選區: %s 登記日期: %s 姓名: %s 推薦政黨: %s 備註: %s", this.ed, this.sData, this.name, this.party, this.remark);
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "ed='" + ed + '\'' +
                ", sData=" + sData +
                ", name='" + name + '\'' +
                ", party='" + party + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
