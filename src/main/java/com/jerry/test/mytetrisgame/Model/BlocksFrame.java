package com.jerry.test.mytetrisgame.Model;

import com.jerry.test.mytetrisgame.Shapes.*;

/**
 * Created by test on 22/01/16.
 */
public class BlocksFrame {

    /*   O ------> x
         |
         |     blocks coordinate
       y |
         +
     */


    public static final int NUMBER_BLOCKS_X_AXIS = 10;
    public static final int NUMBER_BLOCKS_Y_AXIS = 20;
    public static final int EXTEND_BLOCKS_Y_AXIS = 4;

    public static final int SHAPE_ORIGIN_X = 4;
    public static final int SHAPE_ORIGIN_Y = 0;

    private boolean[][] blocks = new boolean[NUMBER_BLOCKS_Y_AXIS+EXTEND_BLOCKS_Y_AXIS][NUMBER_BLOCKS_X_AXIS];
    private boolean[] rowsNeedClean = new boolean[NUMBER_BLOCKS_Y_AXIS+EXTEND_BLOCKS_Y_AXIS];



    public BlocksFrame(){
        // blocks data init
        for(int y=0;y<NUMBER_BLOCKS_Y_AXIS+EXTEND_BLOCKS_Y_AXIS;y++){
            rowsNeedClean[y] = false;
            for(int x=0;x<NUMBER_BLOCKS_X_AXIS;x++){
                blocks[y][x] = false;
            }
        }
    }

    private void detectRowsNeedClean(){
        for(int y=0;y<NUMBER_BLOCKS_Y_AXIS+EXTEND_BLOCKS_Y_AXIS;y++){
            int cleanCount = 0;
            for(int x=0;x<NUMBER_BLOCKS_X_AXIS;x++){
                if(blocks[y][x]){
                    cleanCount++;
                }
            }

            if(cleanCount == NUMBER_BLOCKS_X_AXIS){
                rowsNeedClean[y] = true;
            }
        }
    }

    public void cleanRows(){
        detectRowsNeedClean();

        boolean[][] currentBlocks = blocks;

        int rowShift = 0;
        // clean from bottom to top,
        // it will remain the top 'rowShift' number rows haven't clean
        for(int y=NUMBER_BLOCKS_Y_AXIS+EXTEND_BLOCKS_Y_AXIS-1;y>=0;y--){
            if(rowsNeedClean[y]){
                rowShift++;
                rowsNeedClean[y]=false;
            }else{
                if(rowShift != 0) {
                    for (int x = 0; x < NUMBER_BLOCKS_X_AXIS; x++) {
                        blocks[y+rowShift][x] = currentBlocks[y][x];
                    }
                }
            }
        }

        // clean the top 'rowShift' number rows
        for(int y=0;y<rowShift;y++){
            for (int x = 0; x < NUMBER_BLOCKS_X_AXIS; x++) {
                blocks[y][x] = false;
            }
        }
    }

    public void stickShapeToBlocks(RootShape shape){
        int[][] shapePositions = shape.getShapePosition();
        for(int b=0;b<RootShape.TOTAL_BLOCKS_NUMBER;b++){
            blocks[shapePositions[b][RootShape.POSITION_Y]][shapePositions[b][RootShape.POSITION_X]] = true;
        }
    }


    public boolean[][] getBlocks(){
        return blocks;
    }

    ///////////// code for testing ///////////////////////////

    public void test_setBlock(int y, int x,boolean haveBlock){
        blocks[y][x] = haveBlock;
    }

    public void test_printGameScreen(){
        for(int y=0;y<NUMBER_BLOCKS_Y_AXIS+EXTEND_BLOCKS_Y_AXIS;y++){
            for(int x=0;x<NUMBER_BLOCKS_X_AXIS;x++){
                if(blocks[y][x]){
                    System.out.print("@");
                }else{
                    System.out.print(".");
                }
            }
            System.out.println("");
        }
        System.out.println(">>>>>>>>>");
    }

    public void test_printRowsNeedClean(){
        for(int y=0;y<NUMBER_BLOCKS_Y_AXIS+EXTEND_BLOCKS_Y_AXIS;y++){
            if(rowsNeedClean[y]){
                System.out.println(y);
            }
        }
    }

    public static void main(String[] args){
        BlocksFrame gameBlocks = new BlocksFrame();

        gameBlocks.test_setBlock(1,3, true);
        gameBlocks.test_setBlock(2,0, true);
        gameBlocks.test_setBlock(2,1, true);
        gameBlocks.test_setBlock(2,2, true);
        gameBlocks.test_setBlock(2,3, true);
        gameBlocks.test_setBlock(3,0, true);
        gameBlocks.test_setBlock(3,1, true);
        gameBlocks.test_setBlock(3,2, true);
        gameBlocks.test_setBlock(3,3, true);

        gameBlocks.test_setBlock(4,1, true);
        gameBlocks.test_setBlock(5,2, true);
        /*gameBlocks.test_setBlock(6,0, true);
        gameBlocks.test_setBlock(6,1, true);
        gameBlocks.test_setBlock(6,2, true);
        gameBlocks.test_setBlock(6,3, true);*/
        gameBlocks.test_setBlock(7, 2, true);

        gameBlocks.test_printGameScreen();

        gameBlocks.cleanRows();

        gameBlocks.test_printGameScreen();

    }

}
