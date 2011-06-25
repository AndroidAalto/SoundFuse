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

import org.androidaalto.soundfused.picker.NumberPicker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

public class AddColumnPicker extends Activity {
    NumberPicker np;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.e("TEST", "Creating picker");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_columns);
        np = (NumberPicker) findViewById(R.id.pref_num_picker);
        np.setRange(1, 10);
        np.setCurrent(1);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == 4) {
            Bundle b = new Bundle();
            Log.e("TEST", "Return " + np.getCurrent());
            b.putInt("amount", np.getCurrent());
            Intent i = new Intent();
            i.putExtras(b);
            setResult(1, i);
            finish();
            return true;
        }
        // TODO Auto-generated method stub
        return super.onKeyUp(keyCode, event);
    }
}
