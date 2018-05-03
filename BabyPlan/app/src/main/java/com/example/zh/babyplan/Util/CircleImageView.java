package com.example.zh.babyplan.Util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by 84481 on 2018/4/16.
 */

@SuppressLint("AppCompatCustomView")
public class CircleImageView extends ImageView {

    private Paint paint = null;
    // 画布抗锯齿
    private PaintFlagsDrawFilter pfdf = null;
    private Path path = null;

    public CircleImageView(Context context) {
        super(context);
        init(context, null);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

//    public CircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//        init(context, attrs);
//    }

    private void init(Context context, AttributeSet attrs){
        paint = new Paint();
        // 透明度：00%=FF（不透明），100%=00（透明）
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        // 图片拉伸后会出现锯齿，可以在画笔或画布上设置抗锯齿
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);
        int clear_bits = 0;
        int set_bits = Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG;
        pfdf = new PaintFlagsDrawFilter(clear_bits, set_bits);
        setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        // CCW: CounterClockwise(逆时针)
        // CW: Clockwise(顺时针)
        if(path == null){
            path = new Path();
            path.addCircle(width / 2f, height / 2f, Math.min(width / 2f, height / 2f), Path.Direction.CCW);;
        }
        canvas.drawCircle(width / 2f, height / 2f, Math.min(width / 2f, height / 2f), paint);
        int save_count = canvas.save();
        canvas.setDrawFilter(pfdf);
        // Region.Op.REPLACE 是显示第二次的
        canvas.clipPath(path, Region.Op.REPLACE);
        canvas.clipPath(path, Region.Op.INTERSECT);
        super.onDraw(canvas);
        canvas.restoreToCount(save_count);
    }
}
