package fanggu.org.lotteryapplication.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by 77386 on 2018/5/22.
 */

public class HistoryLayout05250930 extends LinearLayout {
    private Scroller thisScroller;



    public HistoryLayout05250930(Context context) {
        super(context);
        thisScroller=new Scroller(context);
    }
    public HistoryLayout05250930(Context context, AttributeSet attrs) {
        super(context, attrs);
        thisScroller=new Scroller(context);
    }

    @Override
    public void computeScroll() {
        // 第三步，重写computeScroll()方法，并在其内部完成平滑滚动的逻辑
        if (thisScroller.computeScrollOffset()) {  // computeScrollOffset()方法,返回true说明滚动尚未完成，false说明滚动已经完成。
            scrollTo(thisScroller.getCurrX(), thisScroller.getCurrY());
            invalidate();
        }
        super.computeScroll();
    }

    public Scroller getThisScroller() {
        return thisScroller;
    }

    public void setThisScroller(Scroller thisScroller) {
        this.thisScroller = thisScroller;
    }


}
