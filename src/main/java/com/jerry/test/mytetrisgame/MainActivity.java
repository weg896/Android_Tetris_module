package com.jerry.test.mytetrisgame;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button moveLeftBtn;
    private Button moveRightBtn;
    private Button rotateBtn;
    private GameSurfaceView gameSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(new SurfacePanel(this));

        moveLeftBtn = (Button)findViewById(R.id.move_left_btn);
        moveRightBtn = (Button)findViewById(R.id.move_right_btn);
        rotateBtn = (Button)findViewById(R.id.rotate_btn);
        gameSurfaceView = (GameSurfaceView)findViewById(R.id.game_surface_view);

        moveLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameSurfaceView.shapeMoveLeft();
            }
        });

        moveRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameSurfaceView.shapeMoveRight();
            }
        });

        rotateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameSurfaceView.shapeRotation();
            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }


}
