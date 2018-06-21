package fanggu.org.lotteryapplication.http.buyNOOrderRequest;

import java.math.BigDecimal;

import fanggu.org.lotteryapplication.http.common.ResultInfo;

public class BuyNOOrderResponse extends ResultInfo {

    /// <summary>
    /// 订单ID
    /// </summary>
    public String OrderID ;


    /// <summary>
    /// 用户最新余额
    /// </summary>
    public BigDecimal UseraAcount ;


    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public BigDecimal getUseraAcount() {
        return UseraAcount;
    }

    public void setUseraAcount(BigDecimal useraAcount) {
        UseraAcount = useraAcount;
    }
}
