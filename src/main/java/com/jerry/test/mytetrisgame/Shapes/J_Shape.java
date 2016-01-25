package com.jerry.test.mytetrisgame.Shapes;

import com.jerry.test.mytetrisgame.Model.BlocksFrame;

/**
 * Created by test on 21/01/16.
 */
public class J_Shape extends RootShape {
    /*
    shape face to east
    @ @     1 0
    @       2
    @       3

    shape face to south
    @ @ @   3 2 1
        @       0

    shape face to west
      @      3
      @      2
    @ @    0 1

    shape face to north
      @       0
      @ @ @   1 2 3
    */
    public J_Shape(int centerX, int centerY) {
        // rotation center
        centerBlockIndex = 2;

        shapePosition[centerBlockIndex][POSITION_X] = centerX;
        shapePosition[centerBlockIndex][POSITION_Y] = centerY;

        shapePosition[1][POSITION_X] = centerX;
        shapePosition[1][POSITION_Y] = centerY-1;

        shapePosition[3][POSITION_X] = centerX;
        shapePosition[3][POSITION_Y] = centerY+1;

        shapePosition[0][POSITION_X] = centerX+1;
        shapePosition[0][POSITION_Y] = centerY-1;

    }


    public synchronized void rotateShape(BlocksFrame blocksFrame) {
        int[][] currentShape = shapePosition;
        int currentCenterX =  shapePosition[centerBlockIndex][POSITION_X];
        int currentCenterY = shapePosition[centerBlockIndex][POSITION_Y];
        int currentShapeFaceTo = shapeFaceTo;

        switch (shapeFaceTo) {
            case SHAPE_FACE_EAST:
                shapeFaceTo = SHAPE_FACE_SOUTH;

                shapePosition[1][POSITION_X] = currentCenterX + 1;
                shapePosition[1][POSITION_Y] = currentCenterY;

                shapePosition[3][POSITION_X] = currentCenterX - 1;
                shapePosition[3][POSITION_Y] = currentCenterY;

                shapePosition[0][POSITION_X] = currentCenterX + 1;
                shapePosition[0][POSITION_Y] = currentCenterY + 1;

                if(detectCollisionWithBlocksFrame(blocksFrame)){
                    // has collision, roll back
                    shapePosition = currentShape;
                }
                break;
            case SHAPE_FACE_SOUTH:
                shapeFaceTo = SHAPE_FACE_WEST;

                shapePosition[1][POSITION_X] = currentCenterX;
                shapePosition[1][POSITION_Y] = currentCenterY + 1;

                shapePosition[3][POSITION_X] = currentCenterX;
                shapePosition[3][POSITION_Y] = currentCenterY - 1;

                shapePosition[0][POSITION_X] = currentCenterX - 1;
                shapePosition[0][POSITION_Y] = currentCenterY + 1;

                if(detectCollisionWithBlocksFrame(blocksFrame)){
                    // has collision, roll back
                    shapePosition = currentShape;
                }

                break;
            case SHAPE_FACE_WEST:
                shapeFaceTo = SHAPE_FACE_NORTH;

                shapePosition[1][POSITION_X] = currentCenterX - 1;
                shapePosition[1][POSITION_Y] = currentCenterY;

                shapePosition[3][POSITION_X] = currentCenterX + 1;
                shapePosition[3][POSITION_Y] = currentCenterY;

                shapePosition[0][POSITION_X] = currentCenterX - 1;
                shapePosition[0][POSITION_Y] = currentCenterY - 1;

                if(detectCollisionWithBlocksFrame(blocksFrame)){
                    // has collision, roll back
                    shapePosition = currentShape;
                }

                break;
            case SHAPE_FACE_NORTH:
                shapeFaceTo = SHAPE_FACE_EAST;

                shapePosition[1][POSITION_X] = currentCenterX;
                shapePosition[1][POSITION_Y] = currentCenterY - 1;

                shapePosition[3][POSITION_X] = currentCenterX;
                shapePosition[3][POSITION_Y] = currentCenterY + 1;

                shapePosition[0][POSITION_X] = currentCenterX + 1;
                shapePosition[0][POSITION_Y] = currentCenterY - 1;

                if(detectCollisionWithBlocksFrame(blocksFrame)){
                    // has collision, roll back
                    shapePosition = currentShape;
                }

                break;
        }
    }

    public static void main(String[] args){
        int mockScreenSize = 5;
        char[][] mockScreen = new char[mockScreenSize][mockScreenSize];
        J_Shape jShape = new J_Shape(2,2);

        for(int i=0;i<8;i++) {
            test_cleanMockScreen(mockScreen, mockScreenSize);
            test_boundShapeToMockScreen(mockScreen, jShape);
            test_printMockScreen(mockScreen, mockScreenSize);
            //jShape.rotateShape();

            System.out.println(">>>>>>>>>>>>>>>");
        }
    }
}
