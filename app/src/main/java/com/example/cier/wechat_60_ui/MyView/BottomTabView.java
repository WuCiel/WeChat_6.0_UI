package com.example.cier.wechat_60_ui.MyView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Looper;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.example.cier.wechat_60_ui.R;

/**
 * 自定义view步骤
 * 1.创建一个类继承View，比如说MyView
 * 2.创建attrs.xml，在里面声明view所含有的属性，并且绑定上述所声明的类
 * 3.在布局文件中使用自定义的view
 * 4.在MyView的构造方法中获取自定义属性的值
 * 5.重写onMeasure
 * 6.重写onDraw
 * Created by Cier on 2017/10/7.
 */

public class BottomTabView extends View {
    public static final String TAG="BottomTabView";

    private int selectedColor;
    private int textColor;
    private Bitmap iconBitmap;
    private String text;
    private int textSize;

    private Canvas canvas;
    private Bitmap bitMap;
    private Paint paint;
    private Paint textPaint;
    private Rect iconRect;
    private Rect textBound;
    private float alpha=0.0f;

    private void init(){
        selectedColor= ContextCompat.getColor(getContext(),R.color.tabViewSelected);
        text=getResources().getString(R.string.chatListString);
        textColor=ContextCompat.getColor(getContext(),R.color.textColor);
        textSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20,
                getResources().getDisplayMetrics());
    }

    private void init2(){
        textPaint=new Paint();
        textBound=new Rect();
        textPaint.setTextSize(textSize);
        Log.i(TAG,"textSize="+textSize);
        textPaint.setColor(selectedColor);
        textPaint.getTextBounds(text,0,text.length(),textBound);
        Log.i(TAG,"textBound.width()="+textBound.width());

    }

    public BottomTabView(Context context) {
        this(context, null);
    }

    public BottomTabView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 获取自定义属性的值
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public BottomTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();

        TypedArray array=context.obtainStyledAttributes(attrs,R.styleable.BottomTabView);

        int len=array.getIndexCount();
        for(int i=0;i<len;i++){
            int attr=array.getIndex(i);
            switch (attr){
                case R.styleable.BottomTabView_icon:
                    BitmapDrawable drawable= (BitmapDrawable) array.getDrawable(attr);
                    iconBitmap=drawable.getBitmap();
                    break;

                case R.styleable.BottomTabView_color:
                    selectedColor=array.getColor(attr,selectedColor);
                    break;

                case R.styleable.BottomTabView_text:
                    text=array.getString(attr);
                    break;

                case R.styleable.BottomTabView_textSize:
                    textSize= (int) array.getDimension(attr,textSize);
                    break;

                default:
                    break;
            }
        }
        array.recycle();
        init2();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int iconWidth=Math.min(getMeasuredWidth()-getPaddingLeft()-getPaddingRight(),
                getMeasuredHeight()-getPaddingTop()-getPaddingBottom()-textBound.height());
        int left=(getMeasuredWidth()-iconWidth)/2;
        int top=(getMeasuredHeight()-iconWidth-textBound.height())/2;

        iconRect=new Rect(left+10,top+10,left+iconWidth-20,getMeasuredHeight()*2/3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //1.绘制原图，即显示没有被选中的图标
        canvas.drawBitmap(iconBitmap,null,iconRect,null);

        int alphaTmp= (int) Math.ceil(255*alpha);

        //2.绘制变色之后的图标，即被选中之后的图标
        //在内存中准备可变色的icon，Bitmap,setAlpha,纯色面板，xfermode，图标
        drawChangableIcon(alphaTmp);
        //将内存中的图像绘制到屏幕上
        canvas.drawBitmap(bitMap,0,0,null);

        //3.绘制原文本
        drawSourceText(canvas,alphaTmp);
        //4.绘制变色之后的文本
        drawChangableText(canvas,alphaTmp);

    }

    private void drawSourceText(Canvas canvas,int alpha){
        textPaint.setColor(textColor);
        textPaint.setAlpha(255-alpha);
        textPaint.setTextSize(textSize);
        int x=iconRect.left+10+iconRect.width()/2-textBound.width()*2/3;
        int y=iconRect.bottom+textBound.height();
        canvas.drawText(text,x,y,textPaint);
    }

    private void drawChangableText(Canvas canvas,int alpha){
        textPaint.setColor(selectedColor);
        textPaint.setAlpha(alpha);
        textPaint.setTextSize(textSize);
        int x=iconRect.left+10+iconRect.width()/2-textBound.width()*2/3;
        int y=iconRect.bottom+textBound.height();
        canvas.drawText(text,x,y,textPaint);
    }

    private void drawChangableIcon(int alpha){
        bitMap=Bitmap.createBitmap(getMeasuredWidth(),getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        canvas=new Canvas(bitMap);
        paint=new Paint();
        //绘制颜色为color的面板，即与图标重叠部分的颜色，也就是图标最后显示的颜色
        paint.setColor(selectedColor);
        paint.setAntiAlias(true);//去除锯齿
        paint.setDither(true);
        paint.setAlpha(alpha);//透明度可控制图标的透明度
        canvas.drawRect(iconRect,paint);//先绘画长方形面板
        //设置绘画模式
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        paint.setAlpha(255);
        canvas.drawBitmap(iconBitmap,null,iconRect,paint);//再绘画图标
    }

    public void setIconAlpha(float alpha){
        this.alpha=alpha;
        RePaint();
    }

    private void RePaint(){
        if(Looper.getMainLooper()==Looper.myLooper()){
            invalidate();
        }else{
            postInvalidate();
        }
    }
}
