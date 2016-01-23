package com.jerry.test.mytetrisgame.Shapes;

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

    public static final int TOTAL_SHAPE = 7;

    abstract public void rotateShape();

    public void updateShapePosition(int xOffset, int yOffset){
        for(int b=0;b<TOTAL_BLOCKS_NUMBER;b++){
            shapePosition[b][POSITION_X] += xOffset;
            shapePosition[b][POSITION_Y] += yOffset;
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
}
