package com.jerry.test.mytetrisgame.Shapes;

import com.jerry.test.mytetrisgame.Model.BlocksFrame;

/**
 * Created by test on 21/01/16.
 */
public abstract class RootShape {
    // the first 4 indicate each block,
    // the last 2 indicate x,y position

    public static final int POSITION_X = 0;
    public static final int POSITION_Y = 1;
    public static final int TOTAL_BLOCKS_NUMBER = 4;

    protected int centerBlockIndex = -1;
    protected int[][] shapePosition = new int[TOTAL_BLOCKS_NUMBER][2];

    protected static final int SHAPE_FACE_EAST = 0;
    protected static final int SHAPE_FACE_SOUTH = 1;
    protected static final int SHAPE_FACE_WEST = 2;
    protected static final int SHAPE_FACE_NORTH = 3;

    protected int shapeFaceTo=SHAPE_FACE_EAST;

    public static final int I_SHAPE_ID = 0;
    public static final int J_SHAPE_ID = 1;
    public static final int L_SHAPE_ID = 2;
    public static final int O_SHAPE_ID = 3;
    public static final int S_SHAPE_ID = 4;
    public static final int T_SHAPE_ID = 5;
    public static final int Z_SHAPE_ID = 6;

    public static final int SHAPE_TYPE_NUMBER = 7;

    abstract public void rotateShape(BlocksFrame blocksFrame);

    public boolean detectCollisionWithBlocksFrame(BlocksFrame blocksFrame){
        boolean[][] blocks = blocksFrame.getBlocks();

        for(int b=0;b<TOTAL_BLOCKS_NUMBER;b++) {
            if (shapePosition[b][POSITION_X] < 0 ||
                    shapePosition[b][POSITION_X] >= BlocksFrame.NUMBER_BLOCKS_X_AXIS){
                return true;
            }
            if (shapePosition[b][POSITION_Y] < 0 ||
                    shapePosition[b][POSITION_Y] >= BlocksFrame.NUMBER_BLOCKS_Y_AXIS+BlocksFrame.EXTEND_BLOCKS_Y_AXIS) {
                return true;
            }
            if(blocks[shapePosition[b][POSITION_Y]][shapePosition[b][POSITION_X]]){
                return true;
            }
        }

        return false;
    }

    public boolean detectShapeStopMoveDown(BlocksFrame blocksFrame){
        boolean[][] blocks = blocksFrame.getBlocks();

        for(int b=0;b<RootShape.TOTAL_BLOCKS_NUMBER;b++){
            if(shapePosition[b][RootShape.POSITION_Y] >= BlocksFrame.NUMBER_BLOCKS_Y_AXIS+BlocksFrame.EXTEND_BLOCKS_Y_AXIS-1) {
                return true;
            }
        }

        for(int b=0;b<RootShape.TOTAL_BLOCKS_NUMBER;b++){
            if(blocks[shapePosition[b][RootShape.POSITION_Y]+1][shapePosition[b][RootShape.POSITION_X]]){
                return true;
            }
        }
        return false;
    }

    public synchronized void updateShapePosition(int xOffset, int yOffset, BlocksFrame blocksFrame){
        int[][] currentShape = shapePosition;

        for(int b=0;b<TOTAL_BLOCKS_NUMBER;b++){
            shapePosition[b][POSITION_X] += xOffset;
            shapePosition[b][POSITION_Y] += yOffset;
        }
        if(detectCollisionWithBlocksFrame(blocksFrame)){
            shapePosition = currentShape;
            System.out.println("collision on updateShapePosition");
        }
    }

    public int[][] getShapePosition(){
        return shapePosition;
    }


    ///////////// code for testing ///////////////////////////


    public static void test_boundShapeToMockScreen(char[][] mockScreen, RootShape rootShape){
        int[][] tempP = rootShape.getShapePosition();
        for(int i=0;i<TOTAL_BLOCKS_NUMBER;i++){
            mockScreen[tempP[i][POSITION_Y]][tempP[i][POSITION_X]] = '*';
        }
    }

    public static void test_printMockScreen(char[][] mockScreen, int mockScreenSize){
        for(int i=0;i<mockScreenSize;i++){
            for(int j=0;j<mockScreenSize;j++){
                System.out.print(mockScreen[i][j]);
            }
            System.out.print('\n');
        }
    }

    public static void test_cleanMockScreen(char[][] mockScreen, int mockScreenSize){
        // clean mock screen
        for(int i=0;i<mockScreenSize;i++){
            for(int j=0;j<mockScreenSize;j++){
                mockScreen[i][j] = '-';
            }
        }
    }

    public void test_printPosition(){
        for(int b=0;b<RootShape.TOTAL_BLOCKS_NUMBER;b++){
            System.out.print(shapePosition[b][RootShape.POSITION_Y]+" :- ");
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>");
    }
}
