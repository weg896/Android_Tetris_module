package com.jerry.test.mytetrisgame.Shapes;

/**
 * Created by test on 21/01/16.
 */
public class O_Shape extends RootShape {

    /*
    shape face to east
        @ @ shapePosition[0] shapePosition[1]
        @ @ shapePosition[2] shapePosition[3]

    */
    public O_Shape(int centerX, int centerY){
        centerBlockIndex = 0;
        shapePosition[centerBlockIndex][POSITION_X] = centerX;
        shapePosition[centerBlockIndex][POSITION_Y] = centerY;

        shapePosition[1][POSITION_X] = centerX+1;
        shapePosition[1][POSITION_Y] = centerY;

        shapePosition[2][POSITION_X] = centerX;
        shapePosition[2][POSITION_Y] = centerY+1;

        shapePosition[3][POSITION_X] = centerX+1;
        shapePosition[3][POSITION_Y] = centerY+1;
    }

    public void rotateShape(){
        // this shape has no rotation feature
    }

    public static void main(String[] args){

        int mockScreenSize = 5;
        char[][] mockScreen = new char[mockScreenSize][mockScreenSize];
        O_Shape oShape = new O_Shape(1,1);

        for(int i=0;i<7;i++) {
            test_cleanMockScreen(mockScreen, mockScreenSize);
            test_boundShapeToMockScreen(mockScreen, oShape);
            test_printMockScreen(mockScreen, mockScreenSize);
            oShape.rotateShape();

            System.out.println(">>>>>>>>>>>>>>>");
        }

    }

}
