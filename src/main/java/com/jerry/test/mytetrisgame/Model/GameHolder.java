package com.jerry.test.mytetrisgame.Model;

import com.jerry.test.mytetrisgame.Shapes.I_Shape;
import com.jerry.test.mytetrisgame.Shapes.J_Shape;
import com.jerry.test.mytetrisgame.Shapes.L_Shape;
import com.jerry.test.mytetrisgame.Shapes.O_Shape;
import com.jerry.test.mytetrisgame.Shapes.RootShape;
import com.jerry.test.mytetrisgame.Shapes.S_Shape;
import com.jerry.test.mytetrisgame.Shapes.T_Shape;
import com.jerry.test.mytetrisgame.Shapes.Z_Shape;

/**
 * Created by test on 23/01/16.
 */
public class GameHolder {

    private RootShape currentShape;
    private BlocksFrame blocksFrame;


    public GameHolder(){
        blocksFrame = new BlocksFrame();
        tryCreateShape();
    }

    public boolean tryCreateShape(){
        int shapeId = (int)(Math.random()*RootShape.SHAPE_TYPE_NUMBER);

        switch(shapeId){
            case RootShape.I_SHAPE_ID:
            /* shape face to east
                # @ @ @
                0 1 2 3      */
                currentShape = new I_Shape(BlocksFrame.SHAPE_ORIGIN_X, BlocksFrame.SHAPE_ORIGIN_Y);
                break;
            case RootShape.J_SHAPE_ID:
            /* shape face to east
                @ @    1 0
                #      2
                @      3     */
                currentShape = new J_Shape(BlocksFrame.SHAPE_ORIGIN_X,BlocksFrame.SHAPE_ORIGIN_Y+1);
                break;
            case RootShape.L_SHAPE_ID:
            /* shape face to east
                @      1
                #      2
                @ @    3 0   */
                currentShape = new L_Shape(BlocksFrame.SHAPE_ORIGIN_X,BlocksFrame.SHAPE_ORIGIN_Y+1);
                break;
            case RootShape.O_SHAPE_ID:
            /* shape face to east
                # @    0 1
                @ @    2 3   */
                currentShape = new O_Shape(BlocksFrame.SHAPE_ORIGIN_X,BlocksFrame.SHAPE_ORIGIN_Y);
                break;
            case RootShape.S_SHAPE_ID:
            /* shape face to east
                  # @    1 0
                @ @    3 2   */
                currentShape = new S_Shape(BlocksFrame.SHAPE_ORIGIN_X,BlocksFrame.SHAPE_ORIGIN_Y);
                break;
            case RootShape.T_SHAPE_ID:
            /* shape face to east
                @      1
                # @    2 0
                @      3     */
                currentShape = new T_Shape(BlocksFrame.SHAPE_ORIGIN_X,BlocksFrame.SHAPE_ORIGIN_Y+1);
                break;
            case RootShape.Z_SHAPE_ID:
            /* shape face to east
                @ #    3 2
                  @ @    1 0 */
                currentShape = new Z_Shape(BlocksFrame.SHAPE_ORIGIN_X,BlocksFrame.SHAPE_ORIGIN_Y);
                break;
            default:
                currentShape = new O_Shape(BlocksFrame.SHAPE_ORIGIN_X,BlocksFrame.SHAPE_ORIGIN_Y);
        }
        // collision at top means game over
        return !currentShape.detectCollisionWithBlocksFrame(blocksFrame);
    }

    public boolean tryMoveShapeDown(){
        if(currentShape.detectShapeStopMoveDown(blocksFrame)){
            blocksFrame.stickShapeToBlocks(currentShape);
            cleanBlocksFrame();
            return tryCreateShape();
        }else {
            currentShape.updateShapePosition(0, 1, blocksFrame);
            return true;
        }
    }

    public void tryMoveShapeLeft(){
        currentShape.updateShapePosition(-1,0,blocksFrame);
    }

    public void tryMoveShapeRight(){
        currentShape.updateShapePosition(1,0,blocksFrame);
    }

    public void tryRotateShape(){
        currentShape.rotateShape(blocksFrame);
    }

    public void cleanBlocksFrame(){
        blocksFrame.cleanRows();
    }

    public int[][] getShapePositions(){
        return currentShape.getShapePosition();
    }

    public boolean[][] getBlocks(){
        return blocksFrame.getBlocks();
    }

    //////////////// test functions ////////////////////
    public void test_printBlocksFrame(){
        blocksFrame.test_printGameScreen();
    }

    public void test_setBlocks(int y, int x){
        blocksFrame.test_setBlock(y, x, true);
    }
}
