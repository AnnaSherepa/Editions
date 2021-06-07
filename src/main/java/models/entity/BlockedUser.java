package models.entity;


import java.io.Serializable;
import java.util.Date;

public class BlockedUser implements Serializable {
    private int id;
    private String reason;
    private Date endDate;

    public BlockedUser(){}

    public BlockedUser(int id,  Date endDate) {
        this.id = id;
        this.endDate = endDate;
    }

    public BlockedUser(int id, String reason, Date endDate) {
        this.id = id;
        this.reason = reason;
        this.endDate = endDate;
    }

    public void setId(int id) { this.id = id; }
    public void setReason(String reason) { this.reason = reason; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public int getId() { return id; }
    public String getReason() { return reason; }
    public Date getEndDate() { return endDate; }

}
