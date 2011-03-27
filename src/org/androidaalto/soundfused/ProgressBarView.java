
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

    private static final int PROGRESS_SIZE = 15;

    ShapeDrawable progressBar;

    ProgressBarView thisView = this;

    Handler progressHandler;

    // All the sizes are in pixels
    int totalWidth, totalHeight;

    int barWidth, barHeight;

    int currentBarXPos;

    int beatLength;

    private static int barColor = Color.RED;

    private static final int BAR_TRANSPARENCY = 200;

    long next = 0;

    Runnable progressRunnable = new Runnable() {

        @Override
        public void run() {
            moveBar();
            progressHandler.removeCallbacks(progressRunnable);
            progressHandler.postDelayed(progressRunnable, next);
            thisView.invalidate();
        }
    };

    /**
     * @param context
     * @param width sequencer board total width
     * @param height sequencer board total height
     * @param beatLength beat length in pixels
     */
    public ProgressBarView(Context context, int width, int height, int beatLength, int bpm,
            int totalBeats) {
        super(context);
        progressHandler = new Handler();

        barWidth = 15;
        barHeight = height;
        totalWidth = width;
        totalHeight = height;
        currentBarXPos = width / 2;

        next = (((60 * 1000) / bpm) * totalBeats) / (width / PROGRESS_SIZE);

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
            progressBar.setBounds(currentBarXPos - barWidth, 0, currentBarXPos, barHeight);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        synchronized (progressBar) {
            progressBar.draw(canvas);
        }
    }

    @Override
    public void onBPM(float progress) {
        currentBarXPos = (int) progress * beatLength;
    }
}
