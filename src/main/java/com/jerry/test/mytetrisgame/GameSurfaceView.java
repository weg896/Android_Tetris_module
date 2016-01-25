package com.jerry.test.mytetrisgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.jerry.test.mytetrisgame.Model.BlocksFrame;
import com.jerry.test.mytetrisgame.Model.GameHolder;
import com.jerry.test.mytetrisgame.Shapes.RootShape;

/**
 * Created by test on 14/01/16.
 */
public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback{

    private SurfaceHolder surfaceHolder;
    private final Paint blockPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint shapePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private final GameHolder gameHolder;

    private SurfaceThread thread;

    public GameSurfaceView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        blockPaint.setColor(Color.BLUE);
        blockPaint.setStyle(Paint.Style.FILL);
        shapePaint.setColor(Color.YELLOW);
        shapePaint.setStyle(Paint.Style.FILL);

        gameHolder = new GameHolder();
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

    public void shapeMoveLeft(){
        gameHolder.tryMoveShapeLeft();
    }

    public void shapeMoveRight(){
        gameHolder.tryMoveShapeRight();
    }

    public void shapeRotation(){
        gameHolder.tryRotateShape();
    }

    public class SurfaceThread extends Thread {

        private static final long MOVE_DOWN_INTERVAL = 1000;
        private int canvasWidth = 300;
        private int canvasHeight = 600;

        private boolean run = false;

        private float blockWidth=30;
        private float blockHeight=30;



        public SurfaceThread(SurfaceHolder surfaceHolder){
            GameSurfaceView.this.surfaceHolder = surfaceHolder;
        }


        public void run(){
            long timeInterval = 500;
            long startTime = 0;

            while(run){

                Canvas c = null;
                if(timeInterval >= MOVE_DOWN_INTERVAL){
                    run = gameHolder.tryMoveShapeDown();
                    startTime = System.currentTimeMillis();
                }

                try{
                    c = GameSurfaceView.this.surfaceHolder.lockCanvas(null);
                    synchronized (GameSurfaceView.this.surfaceHolder){
                        doDraw(c);
                    }
                }finally{
                    if(c != null){
                        GameSurfaceView.this.surfaceHolder.unlockCanvasAndPost(c);
                    }
                }

                timeInterval = (System.currentTimeMillis() - startTime);

            }
        }

        public void setRunning(boolean b){
            run = b;
        }

        public void setSurfaceSize(int width, int height){
            synchronized (GameSurfaceView.this.surfaceHolder){
                canvasHeight = height;
                canvasWidth = width;
                blockWidth = canvasWidth * 1.0f / BlocksFrame.NUMBER_BLOCKS_X_AXIS;
                blockHeight =  canvasHeight * 1.0f / (BlocksFrame.NUMBER_BLOCKS_Y_AXIS + BlocksFrame.EXTEND_BLOCKS_Y_AXIS);
            }
        }

        private void doDraw(Canvas canvas){

            boolean[][] currentBlocks = gameHolder.getBlocks();
            int[][] currentShape = gameHolder.getShapePositions();

            if(canvas != null) {
                canvas.drawColor(Color.BLACK);
                for(int y=0;y<BlocksFrame.NUMBER_BLOCKS_Y_AXIS + BlocksFrame.EXTEND_BLOCKS_Y_AXIS;y++){
                    for(int x=0;x<BlocksFrame.NUMBER_BLOCKS_X_AXIS;x++){
                        if(currentBlocks[y][x]) {
                            canvas.drawRect(blockWidth * x, blockHeight * y,
                                    blockWidth * (x + 1), blockHeight * (y + 1),
                                    GameSurfaceView.this.blockPaint);
                        }
                    }
                }

                for(int b=0;b<RootShape.TOTAL_BLOCKS_NUMBER;b++){
                    canvas.drawRect(blockWidth * currentShape[b][RootShape.POSITION_X],
                            blockHeight *  currentShape[b][RootShape.POSITION_Y],
                            blockWidth * (currentShape[b][RootShape.POSITION_X] + 1),
                            blockHeight * (currentShape[b][RootShape.POSITION_Y] + 1),
                            GameSurfaceView.this.shapePaint);
                }
            }
        }
    }



}






































