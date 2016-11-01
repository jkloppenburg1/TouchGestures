package edu.orangecoastcollege.cs273.touchgestures;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private ScrollView gesturesScrollView;

    private TextView gesturesLogTextView;
    private TextView singleTapTextView;
    private TextView doubleTapTextView;
    private TextView longPressTextView;
    private TextView scrollTextView;
    private TextView flingTextView;

    private int singleTaps = 0, doubleTaps = 0, longPresses = 0, scrolls = 0, flings = 0;

    // Define a GestureDetector to listen to events on the ScrollView
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gesturesScrollView = (ScrollView) findViewById(R.id.gesturesScrollView);
        gesturesLogTextView = (TextView) findViewById(R.id.gesturesLogTextView);
        singleTapTextView = (TextView) findViewById(R.id.singleTapTextView);
        doubleTapTextView = (TextView) findViewById(R.id.doubleTapTextView);
        longPressTextView = (TextView) findViewById(R.id.longPressTextView);
        scrollTextView = (TextView) findViewById(R.id.scrollTextView);
        flingTextView = (TextView) findViewById(R.id.flingTextView);
        // Hooking up the gesture detector to our scroll view
        // 4 out of 5 gestures handled
        gestureDetector = new GestureDetector(gesturesScrollView.getContext(), this);
        // Special case: double tap
        gestureDetector.setOnDoubleTapListener(this);


    }

    // Any touch event is now dispatched from Activity to the View
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        return gestureDetector.onTouchEvent(ev);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        singleTaps++;
        updateViews();

        // Let's append to our gesture log:
        gesturesLogTextView.append("\nonSingleTapConfirmed touch event");

        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonDoubleTap touch event");
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        doubleTaps++;
        updateViews();

        gesturesLogTextView.append("\nonDoubleTapEvent touch event");
        return false;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonDown touch event");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonShowPress touch event");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonSingleTapUp touch event");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        scrolls++;
        updateViews();

        gesturesLogTextView.append("\nonScroll: distanceX is " + v + ", distanceY is " + v1);
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        longPresses++;
        updateViews();

        gesturesLogTextView.append("\nonLongPress touch event");

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        flings++;
        updateViews();

        gesturesLogTextView.append("\nonFling: velocityX is " + v + ", velocityY is " + v1);

        return false;
    }

    public void clearAll(View view)
    {
        gesturesLogTextView.setText("");
        singleTaps = 0;
        doubleTaps = 0;
        longPresses = 0;
        scrolls = 0;
        flings = 0;
        updateViews();
    }

    public void updateViews()
    {

        singleTapTextView.setText(Integer.toString(singleTaps));
        doubleTapTextView.setText(Integer.toString(doubleTaps));
        longPressTextView.setText(Integer.toString(longPresses));
        scrollTextView.setText(Integer.toString(scrolls));
        flingTextView.setText(Integer.toString(flings));
    }
}
