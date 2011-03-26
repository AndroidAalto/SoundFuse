
package org.androidaalto.soundfused;

import org.androidaalto.soundfused.sequencer.Sequencer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

public class BoardActivity extends Activity {
    public static final int TOTAL_BEATS = 8;

    public static final int TOTAL_SAMPLES = 4;

    LinearLayout mainLayout;

    Sequencer sequencer;

    ToggleButton samplersButtons[][] = new ToggleButton[TOTAL_SAMPLES][TOTAL_BEATS];

    LinearLayout boardLayouts[] = new LinearLayout[TOTAL_SAMPLES];

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sequencer = new Sequencer(this, TOTAL_SAMPLES, TOTAL_BEATS);
        sequencer.setSample(0, R.raw.bass);
        sequencer.setSample(1, R.raw.hhc);
        sequencer.setSample(2, R.raw.hho);
        sequencer.setSample(3, R.raw.snare);
        sequencer.play();

        // Use the whole device screen.
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        prepareBoard();
    }
    
    @Override
    public void onPause() {
        super.onPause();
        sequencer.stop();
    }

    private void prepareBoard() {
        createLayouts();
        setContentView(mainLayout);
        createBoardButtons();
    }

    private void createLayouts() {
        mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
                LayoutParams.FILL_PARENT));
        for (int samplePos = 0; samplePos < TOTAL_SAMPLES; samplePos++) {
            boardLayouts[samplePos] = new LinearLayout(this);
            boardLayouts[samplePos].setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
            boardLayouts[samplePos].setBackgroundColor(Color.rgb(255, 0, 0));
            mainLayout.addView(boardLayouts[samplePos]);
        }

    }

    private void createBoardButtons() {
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay();
        int buttonWidth = display.getWidth() / TOTAL_BEATS;
        int buttonHeight = display.getHeight() / TOTAL_SAMPLES;

        SamplerToggleListener samplerListener = new SamplerToggleListener(sequencer, this,
                TOTAL_SAMPLES, TOTAL_BEATS);

        for (int samplePos = 0; samplePos < TOTAL_SAMPLES; samplePos++) {
            Log.d("Board", "Button width: " + buttonWidth);
            for (int beatPos = 0; beatPos < TOTAL_BEATS; beatPos++) {
                samplersButtons[samplePos][beatPos] = new ToggleButton(this);
                samplersButtons[samplePos][beatPos].setTextOff("");
                samplersButtons[samplePos][beatPos].setTextOn("");
                samplersButtons[samplePos][beatPos].setText("");
                samplersButtons[samplePos][beatPos].setWidth(buttonWidth);
                samplersButtons[samplePos][beatPos].setHeight(buttonHeight);
                samplersButtons[samplePos][beatPos].setId(TOTAL_BEATS * samplePos
                        + beatPos);
                samplersButtons[samplePos][beatPos].setOnClickListener(samplerListener);
                boardLayouts[samplePos].addView(samplersButtons[samplePos][beatPos]);
            }
        }
    }

}
