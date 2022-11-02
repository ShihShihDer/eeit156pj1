package exam.e4;

import java.util.Date;

public class Candidate {

    //	electoral district選區
    private String ed;
    private Date sData;
    private String name;
    private String party;
    private String remark;

    public Candidate(String ed, Date sData, String name, String party, String remark) {
        this.ed = ed;
        this.sData = sData;
        this.name = name;
        this.party = party;
        this.remark = remark;
    }

    public String getEd() {
        return ed;
    }

    public void setEd(String ed) {
        this.ed = ed;
    }

    public Date getsData() {
        return sData;
    }

    public void setsData(Date sData) {
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

    public String dsecCd(){
        return String.format("%s,%s,%s,%s,%s",this.ed,this.sData,this.name,this.party,this.remark);
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
