
package org.androidaalto.soundfused;

import android.app.Activity;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initSounds();
    }

    private void initSounds() {
        // 4 streams
        // stream type 3 is music
        SoundPool soundPool = new SoundPool(4, 3, 100);
        int explosionSound = soundPool.load(this.getBaseContext(), R.raw.example, 1);
        while (soundPool.play(explosionSound, 100, 100, 1, 0, 1) == 0)
            Log.e("SoundPool", "Unable to play sound");
    }
}
