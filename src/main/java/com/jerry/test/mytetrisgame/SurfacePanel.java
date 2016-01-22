package com.jerry.test.mytetrisgame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by test on 14/01/16.
 */
public class SurfacePanel extends SurfaceView implements SurfaceHolder.Callback{

    private SurfaceHolder surfaceHolder;
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private SurfaceThread thread;

    public SurfacePanel(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
    }

    public SurfaceThread getThread(){
        return thread;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        thread = new SurfaceThread(holder);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){
        thread.setSurfaceSize(width, height);
    }

    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        thread.setRunning(false);
        while(retry){
            try{
                thread.join();
                retry = false;
            }catch(InterruptedException e){}
        }
    }





    public class SurfaceThread extends Thread {

        private int canvasWidth = 200;
        private int canvasHeight = 400;
        private int speedX = 5;
        private int speedY = 5;
        private boolean run = false;

        private float bubbleX;
        private float bubbleY;
        private float headingX;
        private float headingY;
        private float radius=50f;



        public SurfaceThread(SurfaceHolder surfaceHolder){
            SurfacePanel.this.surfaceHolder = surfaceHolder;
        }


        public void run(){
            while(run){
                Canvas c = null;

                try{
                    c = SurfacePanel.this.surfaceHolder.lockCanvas(null);
                    synchronized (SurfacePanel.this.surfaceHolder){
                        doDraw(c);
                    }
                }finally{
                    if(c != null){
                        SurfacePanel.this.surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
        }

        public void setRunning(boolean b){
            run = b;
        }

        public void setSurfaceSize(int width, int height){
            synchronized (SurfacePanel.this.surfaceHolder){
                canvasHeight = height;
                canvasWidth = width;
                bubbleX = canvasHeight / 2;
                bubbleY = canvasWidth / 2;
                headingX = (float) (-1+(Math.random())*2);
                headingY = (float) (-1+(Math.random())*2);
            }
        }

        private void doDraw(Canvas canvas){
            bubbleX = bubbleX + (headingX * speedX);
            bubbleY = bubbleY + (headingY * speedY);

            if(bubbleX+radius > canvasWidth){
                speedX = 0-speedX;
            }

            if(bubbleX < radius + 0){
                speedX = 0-speedX;
            }

            if(bubbleY+radius > canvasHeight){
                speedY = 0-speedY;
            }

            if(bubbleY < radius + 0){
                speedY = 0-speedY;
            }

            if(canvas != null) {
                canvas.drawColor(Color.BLACK);
                canvas.drawCircle(bubbleX, bubbleY, radius, SurfacePanel.this.paint);
            }
        }
    }



}






































