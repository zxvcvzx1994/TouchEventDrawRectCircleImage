package com.example.kh.myapplication.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.BoringLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import com.example.kh.myapplication.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by kh on 5/18/2017.
 */

public class Custom_View extends View{

    private Rect rect;
    private Paint paintRect;
    private float cx;
    private float cy;
    private float radias=100;
    private Paint paintCircle;
    private Bitmap bmBitmap;

    public Custom_View(Context context) {
        super(context);
        Init(null);
    }

    public Custom_View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Init((attrs));
    }

    public Custom_View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Init((attrs));
    }

    public Custom_View(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Init((attrs));
    }

    private void Init(AttributeSet set){
        paintRect = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintRect.setColor(Color.RED);
        rect = new Rect();
        paintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintCircle.setColor(Color.GREEN);
        //Draw Image
        bmBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.v);

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(Build.VERSION.SDK_INT>=16){
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }else{
                    getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                int padding = 50;
                bmBitmap = getResizedBitMap(bmBitmap, getWidth()-padding, getHeight()-padding);
                postInvalidate();
                new Timer().scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        int newHeight, newWidth;
                        newHeight  = bmBitmap.getHeight()-50;
                        newWidth = bmBitmap.getWidth()-50;
                        if(newHeight<=0|| newWidth<=0)
                        {
                            cancel();
                            return;
                        }
                        bmBitmap = getResizedBitMap(bmBitmap, newWidth, newHeight);
                        postInvalidate();
                    }
                },5001,3001);
            }
        });

    }

    private Bitmap getResizedBitMap(Bitmap bmBitmap, int width, int height) {
        Matrix matrix = new Matrix();
        RectF src = new RectF(0,0,bmBitmap.getWidth(), bmBitmap.getHeight());
        RectF dst = new RectF(0,0,width, height);
        matrix.setRectToRect(src, dst, Matrix.ScaleToFit.CENTER);
        return Bitmap.createBitmap(bmBitmap, 0,0,bmBitmap.getWidth(), bmBitmap.getHeight(), matrix, true);
    }

    public void Swap_Color(){
        paintRect.setColor(paintRect.getColor()==Color.RED?Color.GREEN:Color.RED);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Draw Rect
        rect.top = 100;
        rect.left = 100;
        rect.right = rect.left+100;
        rect.bottom  = rect.top  +100;
        //Draw Circle
        if(cx==0||cy==0){
        cx = getWidth()/2;
        cy= getHeight()/2;
        }
        canvas.drawRect(rect, paintRect);
        canvas.drawCircle(cx, cy, radias, paintCircle);
        //Draw Image
        int xIm,yIm;
        xIm  = (getWidth()-bmBitmap.getWidth())/2;
        yIm = (getHeight()-bmBitmap.getHeight())/2;
        canvas.drawBitmap(bmBitmap, xIm, yIm,null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x,y;
        x= event.getX();
        y = event.getY();
        boolean value = super.onTouchEvent(event);
        switch (event.getAction()){
         case    MotionEvent.ACTION_DOWN:
            return true;
          case  MotionEvent.ACTION_MOVE:
            double dx,dy;
              dx = Math.pow(x-cx,2);
              dy = Math.pow(y-cy,2);
              if(dx+dy<Math.pow(radias,2)){
                cx = x;
                  cy = y;
              }
              postInvalidate();
            return true;
        }
        return value;
    }
}
