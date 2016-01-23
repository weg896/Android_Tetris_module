package com.jerry.test.mytetrisgame.Shapes;

/**
 * Created by test on 21/01/16.
 */
public class I_Shape extends RootShape{

    /*
    shape face to east
                   @ @ @ @
    shapePosition  0 1 2 3


    shape face south
        @  0
        @  1
        @  2
        @  3
     */


    public I_Shape(int centerX, int centerY){
        // rotation center
        centerBlockIndex = 0;

        shapePosition[centerBlockIndex][POSITION_X] = centerX;
        shapePosition[centerBlockIndex][POSITION_Y] = centerY;

        for(int i=1;i<TOTAL_BLOCKS_NUMBER;i++) { // don't need to change block 0 position
            shapePosition[i][POSITION_X] = centerX+i;
            shapePosition[i][POSITION_Y] = centerY;
        }


    }


    public void rotateShape(){
        int currentCenterX = shapePosition[centerBlockIndex][POSITION_X];
        int currentCenterY = shapePosition[centerBlockIndex][POSITION_Y];

        int currentShapeFaceTo = shapeFaceTo;

        switch (shapeFaceTo) {
            case SHAPE_FACE_EAST:
                shapeFaceTo = SHAPE_FACE_SOUTH;
                for(int i=1;i<TOTAL_BLOCKS_NUMBER;i++) { // don't need to change block 0 position
                    shapePosition[i][POSITION_X] = currentCenterX;
                    shapePosition[i][POSITION_Y] = currentCenterY+i;
                }

                // TODO:should test collision
                break;
            case SHAPE_FACE_SOUTH:
                shapeFaceTo = SHAPE_FACE_EAST;
                for(int i=1;i<TOTAL_BLOCKS_NUMBER;i++) { // don't need to change block 0 position
                    shapePosition[i][POSITION_X] = currentCenterX + i;
                    shapePosition[i][POSITION_Y] = currentCenterY;
                }

                // TODO:should test collision
                break;
        }

    }

    public static void main(String[] args){

        int mockScreenSize = 5;
        char[][] mockScreen = new char[mockScreenSize][mockScreenSize];
        I_Shape iShape = new I_Shape(1,1);

        for(int i=0;i<7;i++) {
            test_cleanMockScreen(mockScreen, mockScreenSize);
            test_boundShapeToMockScreen(mockScreen, iShape);
            test_printMockScreen(mockScreen, mockScreenSize);
            iShape.rotateShape();

            System.out.println(">>>>>>>>>>>>>>>");
        }

    }

}
