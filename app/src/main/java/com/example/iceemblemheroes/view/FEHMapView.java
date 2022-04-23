package com.example.iceemblemheroes.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.iceemblemheroes.R;
import com.example.iceemblemheroes.utils.Point;
import com.example.iceemblemheroes.utils.Position;

import java.util.ArrayList;

// Handles whatever happens on the map background
// => Drawing unit ranges and generating background image

public class FEHMapView extends View{
    // Useful variables

    private float nbCellsWidth = 6; // Number of cells in sudoku grid
    private float nbCellsHeight = 8;
    private int selectedRow = 3; // Selected row of cell in grid
    private int selectedCol = 3; // Selected column of cell in grid

    private Drawable mapBackground;

    private float cellSizePixelWidth = 0f; // Size of cell in grid
    private float cellSizePixelHeight = 0f; // Size of cell in grid

    private ArrayList<Position> selectedCharacterMoves = null;

    // Constructor
    public FEHMapView(Context context, AttributeSet attrs){
        super(context, attrs);
        setBackgroundResource(R.drawable.lavamap);
        setWillNotDraw(false);
    }

    // Override of onDraw method, draw sudoku grid and fill cells according to events
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        System.out.println("ALLLO");
        //drawLines(canvas);
        //positionUnits(fehGame.getUnits());
//        fillCells(canvas);
        drawMovementAndRange(canvas);
    }

    // Override of onMeasure method, set the size of a pixel
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int sizePixel = Math.min(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(sizePixel, sizePixel);
    }

    /*private void positionUnits(ArrayList<FEHCharacter> units) {
        for (FEHCharacter unit: units) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.lucina_face));
            LinearLayout.LayoutParams params =  new LinearLayout
                    .LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            imageView.setLayoutParams(params);
        }
    }*/

    // Draw lines of the sudoku grid, alternating between thick and thin lines
    /*private void drawLines(Canvas canvas) {
        // Thick line component
        Paint thickLine = new Paint(Paint.ANTI_ALIAS_FLAG);
        thickLine.setStyle(Paint.Style.STROKE);
        thickLine.setColor(Color.BLACK);
        thickLine.setStrokeWidth(6f);

        // Thin line component
        Paint thinLine = new Paint(Paint.ANTI_ALIAS_FLAG);
        thinLine.setStyle(Paint.Style.STROKE);
        thinLine.setColor(getResources().getColor(R.color.red_light));
        thinLine.setStrokeWidth(3f);

        // Draw the limits of the grid
        canvas.drawRect(0f, 0f, getWidth(), getHeight(), thickLine);
        // Draw lines and columns to delimit the cells of the grid
        for (int i = 1; i <= 8; i++) {
            if (i % 3 == 0) {
                canvas.drawLine(i * cellSizePixelWidth, 0f, i * cellSizePixelWidth, getHeight(), thickLine);
                canvas.drawLine(0f, i * cellSizePixelHeight, getWidth(), i * cellSizePixelHeight, thickLine);
            } else {
                canvas.drawLine(i * cellSizePixelWidth, 0f, i * cellSizePixelWidth, getHeight(), thinLine);
                canvas.drawLine(0f, i * cellSizePixelHeight, getWidth(), i * cellSizePixelHeight, thinLine);
            }
        }
    }*/

    // Override of onTouchEvent method, call handleTouchEvent when touching the screen
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //handleTouchEvent(event.getX(), event.getY());
            return true;
        }
        else {
            return false;
        }
    }

    // Fill a cell with a certain color
    private void fillCell(Canvas canvas, int row, int col, Paint paint) {
        canvas.drawRect(col * cellSizePixelWidth, row * cellSizePixelHeight,
                (col + 1) * cellSizePixelWidth, (row + 1) * cellSizePixelHeight, paint);
    }

    // Iterate on every cell of the grid to fill them with a certain color if necessary
    private void fillCells(Canvas canvas) {
        // No selected row, nothing to do
        if (selectedCol == -1 || selectedRow == -1) {
            return;
        }

        // Selected cell component
        Paint selectedCell = new Paint();
        selectedCell.setStyle(Paint.Style.FILL_AND_STROKE);
        selectedCell.setColor(getResources().getColor(R.color.light_red));

        // Iterate on every cell, fill them with a certain color according to selected cell
        for (int i = 0; i < nbCellsHeight; i++) {
            for (int j = 0; j < cellSizePixelWidth; j++) {
                if (i == selectedRow && j == selectedCol) {
                    fillCell(canvas, i, j, selectedCell);
                }
            }
        }
    }

    private void drawMovementAndRange(Canvas canvas) {
        if (selectedCharacterMoves == null) {
            return;
        }

        // Selected cell component
        Paint movePaint = new Paint();
        movePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        movePaint.setColor(getResources().getColor(R.color.light_blue));

        Paint attackPaint = new Paint();
        attackPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        attackPaint.setColor(getResources().getColor(R.color.light_red));

        for (Position position : selectedCharacterMoves) {
            fillCell(canvas, position.getX(), position.getY(),
                    position.getAtkOrMove().equals("atk") ? attackPaint : movePaint);
        }
    }

    public void drawMovementAndRange(ArrayList<Position> positions) {
        selectedCharacterMoves = positions;
        invalidate();
    }

    public Point getPositionFromRowCol(int row, int column) {
        Point p = new Point(column * cellSizePixelWidth, row * cellSizePixelHeight);
        return p;
    }

    public Point getPositions(float x, float y) {
        selectedCol = (int) (x / cellSizePixelWidth);
        selectedRow = (int) (y / cellSizePixelHeight);

        Point p = new Point(selectedCol * cellSizePixelWidth, selectedRow * cellSizePixelHeight);
        return p;
    }

    public void computeCellSize() {
        cellSizePixelWidth = getWidth() / nbCellsWidth;
        cellSizePixelHeight = getHeight() / nbCellsHeight;
    }

    public float getCellSizePixelWidth() {
        return cellSizePixelWidth;
    }

    public float getCellSizePixelHeight() {
        return cellSizePixelHeight;
    }

    public float getNbCellsHeight() {
        return nbCellsHeight;
    }

    public float getNbCellsWidth() {
        return nbCellsWidth;
    }

    // Set selected column and row according to where we touch the screen so that the engine knows
    // which cell has been selected
    /*private void handleTouchEvent(float x, float y) {
        selectedCol = (int) (x / cellSizePixelWidth);
        selectedRow = (int) (y / cellSizePixelHeight);
        //game.updateRowCol(selectedRow, selectedCol);
        // Says that the current scene is not valid anymore and reload it, calling onDraw again
        // with the right selectedCol and selectedRow
        invalidate();
    }*/
}