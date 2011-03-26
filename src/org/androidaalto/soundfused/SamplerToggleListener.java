package org.androidaalto.soundfused;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class SamplerToggleListener implements OnClickListener{

    @Override
    public void onClick(View v) {
        Log.d("SampleToggleListener" , "Button id: " + v.getId());
    }

}
