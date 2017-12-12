package dataaccess;

import java.util.Calendar;

/**
 * Created by Seong-EunCho on 3/10/17.
 */

public class Timestamp {
    private java.sql.Timestamp timestamp;

    public Timestamp(){
        Calendar cal = Calendar.getInstance();
        timestamp = new java.sql.Timestamp(cal.getTime().getTime());
    }

    public java.sql.Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(java.sql.Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
