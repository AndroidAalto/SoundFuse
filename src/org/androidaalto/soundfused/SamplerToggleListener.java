
package org.androidaalto.soundfused;

import org.androidaalto.soundfused.sequencer.Sequencer;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ToggleButton;

public class SamplerToggleListener implements OnClickListener {

    Sequencer sequencer;

    public SamplerToggleListener(Sequencer sequencer, Context ctx, int samples, int beats) {
        this.sequencer = sequencer;
    }

    @Override
    public void onClick(View v) {
        int buttonId = v.getId();

        int samplerId = buttonId / BoardActivity.TOTAL_BEATS;
        int beatId = buttonId % BoardActivity.TOTAL_BEATS;
        if (!(v instanceof ToggleButton)) {
            Log.e("SampleToggleListener", "Invalid View type: " + v.getClass());
            return;
        }
        ToggleButton currentButton = (ToggleButton) v;
        if (currentButton.isChecked())
            sequencer.enableCell(samplerId, beatId);
        else
            sequencer.disableCell(samplerId, beatId);
    }
}
