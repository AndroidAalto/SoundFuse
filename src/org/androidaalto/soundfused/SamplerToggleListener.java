/*******************************************************************************

   Copyright: 2011 Android Aalto Community

   This file is part of SoundFused.

   SoundFused is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation; either version 2 of the License, or
   (at your option) any later version.

   SoundFused is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with SoundFused; if not, write to the Free Software
   Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA

 ******************************************************************************/
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
