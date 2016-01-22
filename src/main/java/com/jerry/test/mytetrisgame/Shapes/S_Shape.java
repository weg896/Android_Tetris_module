package com.jerry.test.mytetrisgame.Shapes;

/**
 * Created by test on 21/01/16.
 */
public class S_Shape extends RootShape {

    /*
    shape face to east
      @ @     1 0
    @ @     3 2

    shape face to south
    @       0
    @ @     1 2
      @       3

    */

    public S_Shape(int centerX, int centerY) {
        // rotation center
        centerBlockIndex = 1;

        shapePosition[centerBlockIndex][POSITION_X] = centerX;
        shapePosition[centerBlockIndex][POSITION_Y] = centerY;

        shapePosition[0][POSITION_X] = centerX+1;
        shapePosition[0][POSITION_Y] = centerY;

        shapePosition[2][POSITION_X] = centerX;
        shapePosition[2][POSITION_Y] = centerY+1;

        shapePosition[3][POSITION_X] = centerX-1;
        shapePosition[3][POSITION_Y] = centerY+1;

    }

    public void rotateShape(){
        int currentCenterX =  shapePosition[centerBlockIndex][POSITION_X];
        int currentCenterY = shapePosition[centerBlockIndex][POSITION_Y];
        int currentShapeFaceTo = shapeFaceTo;

        switch (shapeFaceTo) {
            case SHAPE_FACE_EAST:
                shapeFaceTo = SHAPE_FACE_SOUTH;

                shapePosition[0][POSITION_X] = currentCenterX;
                shapePosition[0][POSITION_Y] = currentCenterY-1;

                shapePosition[2][POSITION_X] = currentCenterX+1;
                shapePosition[2][POSITION_Y] = currentCenterY;

                shapePosition[3][POSITION_X] = currentCenterX+1;
                shapePosition[3][POSITION_Y] = currentCenterY+1;

                // TODO:should test collision
                break;
            case SHAPE_FACE_SOUTH:
                shapeFaceTo = SHAPE_FACE_EAST;

                shapePosition[0][POSITION_X] = currentCenterX+1;
                shapePosition[0][POSITION_Y] = currentCenterY;

                shapePosition[2][POSITION_X] = currentCenterX;
                shapePosition[2][POSITION_Y] = currentCenterY+1;

                shapePosition[3][POSITION_X] = currentCenterX-1;
                shapePosition[3][POSITION_Y] = currentCenterY+1;


                // TODO:should test collision
                break;
        }

    }

    public static void main(String[] args){
        int mockScreenSize = 5;
        char[][] mockScreen = new char[mockScreenSize][mockScreenSize];
        S_Shape sShape = new S_Shape(2,2);

        for(int i=0;i<7;i++) {
            test_cleanMockScreen(mockScreen, mockScreenSize);
            test_boundShapeToMockScreen(mockScreen, sShape);
            test_printMockScreen(mockScreen, mockScreenSize);
            sShape.rotateShape();

            System.out.println(">>>>>>>>>>>>>>>");
        }
    }
}
