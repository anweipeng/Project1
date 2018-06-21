package fanggu.org.lotteryapplication.http.timeno;

public class TimeNORequest {

    public TimeNORequest() {

    }
    public TimeNORequest(String ShipNO){
        this.ShipNO=ShipNO;
    }
    /// <summary>
    /// 实体店标号 目前为空
    /// </summary>
    private String ShipNO;



    public String getShipNO() {
        return ShipNO;
    }

    public void setShipNO(String shipNO) {
        ShipNO = shipNO;
    }
}

