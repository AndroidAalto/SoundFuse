
package org.androidaalto.soundfused;

import android.app.Activity;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.SystemClock;
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
        SoundPool soundPool = new SoundPool(8, 3, 100);
        int N_SOUNDS = 4;
        int sounds[] = new int[N_SOUNDS];
        sounds[0] = soundPool.load(this.getBaseContext(), R.raw.bass, 1);
        sounds[1] = soundPool.load(this.getBaseContext(), R.raw.hhc, 1);
        sounds[2] = soundPool.load(this.getBaseContext(), R.raw.hho, 1);
        sounds[3] = soundPool.load(this.getBaseContext(), R.raw.snare, 1);
        
        int i = 0;
        while (true) {
            long start = System.currentTimeMillis();
            soundPool.play(sounds[i], 100, 100, 1, 0, 1);
            i = (i+1) % N_SOUNDS;
            SystemClock.sleep(start + 1000 - System.currentTimeMillis());
        }
    }
}