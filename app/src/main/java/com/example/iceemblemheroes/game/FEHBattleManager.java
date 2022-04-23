package com.example.iceemblemheroes.game;

// Manages and calls functions to keep the game updated

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.iceemblemheroes.R;
import com.example.iceemblemheroes.utils.Point;
import com.example.iceemblemheroes.utils.Position;
import com.example.iceemblemheroes.view.FEHMapView;
import com.example.iceemblemheroes.view.MyDragShadowBuilder;

import java.util.ArrayList;
import java.util.HashMap;

public class FEHBattleManager {

    private RelativeLayout relativeLayout;
    private ArrayList<FEHCharacter> characterList;
    private HashMap<FEHCharacter, ImageView> characterToView;
    private HashMap<ImageView, FEHCharacter> viewToCharacter;
    private FEHBoard board;
    private FEHMapView mapView;
    private Context context;

    public FEHBattleManager(Context context, RelativeLayout relativeLayout, FEHMapView fehMapView) {
        this.relativeLayout = relativeLayout;

        characterList = new ArrayList<FEHCharacter>();
        characterToView = new HashMap<FEHCharacter, ImageView>();
        viewToCharacter = new HashMap<ImageView, FEHCharacter>();
        characterList.add(new FEHCharacter("Lucina", 2, 1, 47,
                34, 36, 27, 19, "sword", new Position(6, 3)));

        board = new FEHBoard(characterList);

        this.context = context;

        mapView = fehMapView;
        putCharactersOnMap();
    }

    private void putCharactersOnMap() {
        int width = (int) mapView.getCellSizePixelWidth();
        int height = (int) mapView.getCellSizePixelHeight();

        for (FEHCharacter character : characterList) {
            Point p = mapView.getPositionFromRowCol(character.getPosition().getX(), character.getPosition().getY());
            ImageView view = new ImageView(context);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.height = height;
            params.width = width;
            view.setLayoutParams(params);
            view.setImageDrawable(context.getResources().getDrawable(R.drawable.lucina_face));
            view.setX(p.getX());
            view.setY(p.getY());

            setDragAndDropFunctionalities(view);

            characterToView.put(character, view);
            viewToCharacter.put(view, character);

            relativeLayout.addView(view);
        }
    }

    private void setDragAndDropFunctionalities(View view) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // ask fehboard for legal moves
                // gives these moves to
                FEHCharacter correspondingChar = viewToCharacter.get(view);
                System.out.println("Lucina view has been touched");
                if (correspondingChar != null) {
                    System.out.println("Lucina view has a corresponding character");
                    ArrayList<Position> positions = board.getLegalMoves(correspondingChar);
                    mapView.drawMovementAndRange(positions);
                }
                return false;
            }
        });


        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                view.setVisibility(View.INVISIBLE);

                String clipText = "Clip text";
                ClipData.Item item = new ClipData.Item(clipText);
                ClipData dragData = new ClipData(
                        clipText,
                        new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);

                View.DragShadowBuilder myShadow = new MyDragShadowBuilder(view, context);

                // Start the drag.
                view.startDragAndDrop(dragData,  // The data to be dragged
                        myShadow,  // The drag shadow builder
                        null,      // No need to use local data
                        0          // Flags (not currently used, set to 0)
                );

                // Indicate that the long-click was handled.
                return true;


            }
        });

        // Set the drag event listener for the View.
        mapView.setOnDragListener( (v, e) -> {

            // Handles each of the expected events.
            switch(e.getAction()) {

                case DragEvent.ACTION_DRAG_STARTED:

                    // Determines if this View can accept the dragged data.
                    if (e.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {

                        // Returns true to indicate that the View can accept the dragged data.
                        return true;
                    }

                    // Returns false to indicate that, during the current drag and drop operation,
                    // this View will not receive events again until ACTION_DRAG_ENDED is sent.
                    return false;

                case DragEvent.ACTION_DRAG_ENTERED:

                    // Returns true; the value is ignored.
                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:

                    // Ignore the event.
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:

                    // Returns true; the value is ignored.
                    return true;

                case DragEvent.ACTION_DROP:

                    float x = e.getX();
                    float y = e.getY();

                    Point point = mapView.getPositions(x, y);

                    view.setTranslationX(point.getX());
                    view.setTranslationY(point.getY());
                    view.setVisibility(View.VISIBLE);

                    System.out.println("CHILDREN COUNT: " + relativeLayout.getChildCount());

                case DragEvent.ACTION_DRAG_ENDED:

                    // Returns true; the value is ignored.
                    return true;

                // An unknown action type was received.
                default:
                    Log.e("DragDrop Example","Unknown action type received by View.OnDragListener.");
                    break;
            }

            return false;

        });
    }
}
