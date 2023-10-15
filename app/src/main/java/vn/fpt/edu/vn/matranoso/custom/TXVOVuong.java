package vn.fpt.edu.vn.matranoso.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class TXVOVuong extends TextView {
    public TXVOVuong(Context context){
        super(context);
    }
    public TXVOVuong(Context context, AttributeSet attrs){
        super(context,attrs);
    }
    public  TXVOVuong(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int a = this.getMeasuredWidth();
        setMeasuredDimension(a,a);
    }
}
