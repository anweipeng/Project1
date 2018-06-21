package fanggu.org.lotteryapplication.http.timeno;

import fanggu.org.lotteryapplication.http.common.ResultInfo;

public class TimeNOResponse extends ResultInfo {

    /// <summary>
    /// 期号
    /// </summary>
    public String timeNO;
    /// <summary>
    /// 开始时间
    /// </summary>
    public String beginTime;
    /// <summary>
    /// 结束时间
    /// </summary>
    public String endTime;
    /// <summary>
    /// 剩余秒数
    /// </summary>
    public int RemainSec;

    public String getTimeNO() {
        return timeNO;
    }

    public void setTimeNO(String timeNO) {
        this.timeNO = timeNO;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getRemainSec() {
        return RemainSec;
    }

    public void setRemainSec(int remainSec) {
        RemainSec = remainSec;
    }
}

