package com.jerry.test.mytetrisgame.Shapes;

/**
 * Created by test on 21/01/16.
 */
public class T_Shape extends RootShape {

    /*
    shape face to east
    @      1
    @ @    2 0
    @      3

    shape face to south
    @ @ @  3 2 1
      @      0

    shape face to west
      @      3
    @ @    0 2
      @      1

    shape face to north
        @       0
      @ @ @   1 2 3
    */

    public T_Shape(int centerX, int centerY) {
        // rotation center
        centerBlockIndex = 2;

        shapePosition[centerBlockIndex][POSITION_X] = centerX;
        shapePosition[centerBlockIndex][POSITION_Y] = centerY;

        shapePosition[1][POSITION_X] = centerX;
        shapePosition[1][POSITION_Y] = centerY-1;

        shapePosition[0][POSITION_X] = centerX+1;
        shapePosition[0][POSITION_Y] = centerY;


        shapePosition[3][POSITION_X] = centerX;
        shapePosition[3][POSITION_Y] = centerY+1;

    }


    public void rotateShape(){
        int currentCenterX =  shapePosition[centerBlockIndex][POSITION_X];
        int currentCenterY = shapePosition[centerBlockIndex][POSITION_Y];
        int currentShapeFaceTo = shapeFaceTo;

        switch (shapeFaceTo) {
            case SHAPE_FACE_EAST:
                shapeFaceTo = SHAPE_FACE_SOUTH;

                shapePosition[1][POSITION_X] = currentCenterX+1;
                shapePosition[1][POSITION_Y] = currentCenterY;

                shapePosition[0][POSITION_X] = currentCenterX;
                shapePosition[0][POSITION_Y] = currentCenterY+1;

                shapePosition[3][POSITION_X] = currentCenterX-1;
                shapePosition[3][POSITION_Y] = currentCenterY;

                // TODO:should test collision
                break;
            case SHAPE_FACE_SOUTH:
                shapeFaceTo = SHAPE_FACE_WEST;

                shapePosition[1][POSITION_X] = currentCenterX;
                shapePosition[1][POSITION_Y] = currentCenterY+1;

                shapePosition[0][POSITION_X] = currentCenterX-1;
                shapePosition[0][POSITION_Y] = currentCenterY;

                shapePosition[3][POSITION_X] = currentCenterX;
                shapePosition[3][POSITION_Y] = currentCenterY-1;

                // TODO:should test collision
                break;
            case SHAPE_FACE_WEST:
                shapeFaceTo = SHAPE_FACE_NORTH;

                shapePosition[1][POSITION_X] = currentCenterX-1;
                shapePosition[1][POSITION_Y] = currentCenterY;

                shapePosition[0][POSITION_X] = currentCenterX;
                shapePosition[0][POSITION_Y] = currentCenterY-1;

                shapePosition[3][POSITION_X] = currentCenterX+1;
                shapePosition[3][POSITION_Y] = currentCenterY;

                // TODO:should test collision

                break;
            case SHAPE_FACE_NORTH:
                shapeFaceTo = SHAPE_FACE_EAST;

                shapePosition[1][POSITION_X] = currentCenterX;
                shapePosition[1][POSITION_Y] = currentCenterY-1;

                shapePosition[0][POSITION_X] = currentCenterX+1;
                shapePosition[0][POSITION_Y] = currentCenterY;

                shapePosition[3][POSITION_X] = currentCenterX;
                shapePosition[3][POSITION_Y] = currentCenterY+1;

                // TODO:should test collision
                break;
        }

    }

    public static void main(String[] args){

        int mockScreenSize = 5;
        char[][] mockScreen = new char[mockScreenSize][mockScreenSize];
        T_Shape tShape = new T_Shape(2,2);

        for(int i=0;i<7;i++) {
            test_cleanMockScreen(mockScreen, mockScreenSize);
            test_boundShapeToMockScreen(mockScreen, tShape);
            test_printMockScreen(mockScreen, mockScreenSize);
            tShape.rotateShape();

            System.out.println(">>>>>>>>>>>>>>>");
        }

    }

}
