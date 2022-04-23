package com.example.iceemblemheroes.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

import com.example.iceemblemheroes.R;
import com.example.iceemblemheroes.game.FEHBattleManager;

public class MainActivity extends AppCompatActivity {

    // Button button;

//    ImageView imageView;
    FEHMapView fehBoard;
    FEHBattleManager battleManager;
    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fehBoard = findViewById(R.id.FEHboardview);
        layout = findViewById(R.id.main_layout);

        // Use this to wait for the ImageView to actually be created before taking its measurements like height and width
        // (which are used in battleManager's instantiation call)
        fehBoard.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                fehBoard.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                fehBoard.computeCellSize();
                System.out.println("WIDTH AND HEIGHT" + fehBoard.getWidth() + "      " + fehBoard.getHeight());
                battleManager = new FEHBattleManager(getApplicationContext(), layout, fehBoard);
            }
        });

//        imageView = findViewById(R.id.lucina);
//
//        imageView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
////                Toast.makeText(getApplicationContext(), "ALLO", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
//
//
//        imageView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                imageView.setVisibility(View.INVISIBLE);
//
//                String clipText = "Clip text";
//                ClipData.Item item = new ClipData.Item(clipText);
//                ClipData dragData = new ClipData(
//                        clipText,
//                        new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);
//
//                View.DragShadowBuilder myShadow = new MyDragShadowBuilder(imageView, getApplicationContext());
//
//                // Start the drag.
//                view.startDragAndDrop(dragData,  // The data to be dragged
//                        myShadow,  // The drag shadow builder
//                        null,      // No need to use local data
//                        0          // Flags (not currently used, set to 0)
//                );
//
//                // Indicate that the long-click was handled.
//                return true;
//
//
//            }
//        });
//
//        // Set the drag event listener for the View.
//        fehBoard.setOnDragListener( (v, e) -> {
//
//            // Handles each of the expected events.
//            switch(e.getAction()) {
//
//                case DragEvent.ACTION_DRAG_STARTED:
//
//                    // Determines if this View can accept the dragged data.
//                    if (e.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
//
//                        // Returns true to indicate that the View can accept the dragged data.
//                        return true;
//                    }
//
//                    // Returns false to indicate that, during the current drag and drop operation,
//                    // this View will not receive events again until ACTION_DRAG_ENDED is sent.
//                    return false;
//
//                case DragEvent.ACTION_DRAG_ENTERED:
//
//                    // Returns true; the value is ignored.
//                    return true;
//
//                case DragEvent.ACTION_DRAG_LOCATION:
//
//                    // Ignore the event.
//                    return true;
//
//                case DragEvent.ACTION_DRAG_EXITED:
//
//                    // Returns true; the value is ignored.
//                    return true;
//
//                case DragEvent.ACTION_DROP:
//
//                    float x = e.getX();
//                    float y = e.getY();
//
//                    Point p = fehBoard.getPositions(x, y);
//
//                    imageView.setTranslationX(p.getX());
//                    imageView.setTranslationY(p.getY());
//                    imageView.setVisibility(View.VISIBLE);
//
//                case DragEvent.ACTION_DRAG_ENDED:
//
//                    // Returns true; the value is ignored.
//                    return true;
//
//                // An unknown action type was received.
//                default:
//                    Log.e("DragDrop Example","Unknown action type received by View.OnDragListener.");
//                    break;
//            }
//
//            return false;
//
//        });


//        button = findViewById(R.id.button);

        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchActivity(view);
            }
        });*/
    }


    public void launchActivity(View view) {
        //Intent intent = new Intent(this, test.class);
        //startActivity(intent);
    }
}