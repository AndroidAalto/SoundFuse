
package org.androidaalto.soundfused;

import org.androidaalto.soundfused.sequencer.Sequencer.OnBPMListener;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Handler;
import android.view.View;

public class ProgressBarView extends View implements OnBPMListener {

    private static final int BAR_WIDTH = 25;

    private static final int PROGRESS_SIZE = 15;

    ShapeDrawable progressBar;

    ProgressBarView thisView = this;

    Handler progressHandler;

    // All the sizes are in pixels
    int totalWidth, barHeight, currentBarXPos, beatLength;

    private static int barColor = Color.YELLOW;

    private static final int BAR_TRANSPARENCY = 200;

    long nextCallDelay = 0;

    Runnable progressRunnable = new Runnable() {

        @Override
        public void run() {
            thisView.invalidate();

            // Clean any pending callback
            progressHandler.removeCallbacks(progressRunnable);
            // Schedule a new callback
            progressHandler.postDelayed(progressRunnable, nextCallDelay);
            moveBar();
        }
    };

    /**
     * @param context
     * @param width sequencer board total width
     * @param height sequencer board total height
     * @param beatLength beat length in pixels
     */
    public ProgressBarView(Context context, int width, int height, int beatLength, int bpm) {
        super(context);
        progressHandler = new Handler();

        barHeight = height;
        totalWidth = width;
        currentBarXPos = 0;

        // How long it takes to go through a beat
        long beatTime = (60 * 1000) / bpm;

        // How many times the bar needs to be moved to complete a beat
        int nCallsToGoThroughABeat = beatLength / PROGRESS_SIZE;

        // the total time per beat divided by the number of calls tells us how
        // often the move needs to be called
        nextCallDelay = beatTime / nCallsToGoThroughABeat;

        this.beatLength = beatLength;

        progressBar = new ShapeDrawable(new RectShape());
        progressBar.getPaint().setColor(barColor);
        progressBar.setAlpha(BAR_TRANSPARENCY);
        moveBar();
        progressHandler.postDelayed(progressRunnable, 0);
    }

    private void moveBar() {
        synchronized (progressBar) {
            currentBarXPos = (currentBarXPos + PROGRESS_SIZE) % totalWidth;
            progressBar.setBounds(currentBarXPos - BAR_WIDTH, 0, currentBarXPos, barHeight);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        synchronized (progressBar) {
            progressBar.draw(canvas);
        }
    }

    @Override
    public void onBPM(int beatCount) {
        currentBarXPos = beatCount * beatLength;
    }
}
