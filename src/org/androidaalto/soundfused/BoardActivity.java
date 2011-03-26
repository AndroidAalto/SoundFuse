
package org.androidaalto.soundfused;

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
    private static final int TIMELINE = 8;

    private static final int SAMPLERS = 4;

    LinearLayout mainLayout;

    ToggleButton samplersButtons[][] = new ToggleButton[SAMPLERS][TIMELINE];

    LinearLayout boardLayouts[] = new LinearLayout[SAMPLERS];

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        prepareBoard();

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
        for (int samplerPos = 0; samplerPos < SAMPLERS; samplerPos++) {
            boardLayouts[samplerPos] = new LinearLayout(this);
            boardLayouts[samplerPos].setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
            boardLayouts[samplerPos].setBackgroundColor(Color.rgb(255, 0, 0));
            mainLayout.addView(boardLayouts[samplerPos]);
        }

    }

    private void createBoardButtons() {
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay();
        int buttonWidth = display.getWidth() / TIMELINE;
        int buttonHeight = display.getHeight() / SAMPLERS;
        
        SamplerToggleListener samplerListener = new SamplerToggleListener();
        
        for (int samplerPos = 0; samplerPos < SAMPLERS; samplerPos++) {
            Log.d("Board", "Button width: " + buttonWidth);
            for (int timelinePos = 0; timelinePos < TIMELINE; timelinePos++) {
                samplersButtons[samplerPos][timelinePos] = new ToggleButton(this);
                samplersButtons[samplerPos][timelinePos].setTextOff("(" + samplerPos + ","
                        + timelinePos + ")");
                samplersButtons[samplerPos][timelinePos].setTextOn("(" + samplerPos + ","
                        + timelinePos + ")");
                samplersButtons[samplerPos][timelinePos].setText("(" + samplerPos + ","
                        + timelinePos + ")");
                samplersButtons[samplerPos][timelinePos].setWidth(buttonWidth);
                samplersButtons[samplerPos][timelinePos].setHeight(buttonHeight);
                samplersButtons[samplerPos][timelinePos].setId(TIMELINE * samplerPos + timelinePos);
                samplersButtons[samplerPos][timelinePos].setOnClickListener(samplerListener);
                boardLayouts[samplerPos].addView(samplersButtons[samplerPos][timelinePos]);
            }
        }
    }

}
