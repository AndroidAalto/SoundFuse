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
package org.androidaalto.soundfused.sequencer;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;


/**
 * @class Cell
 * 
 * This class provides the functionality for delivering different sounds
 * at different points in the time. This is done using a sequencer matrix
 * like the one below:
 * 
 * <pre>
 *  -----------------------------------
 * |   |   |   |   |   |   |   |   |   |
 *  -----------------------------------
 * |   |   |   |   |   |   |   |   |   |
 *  -----------------------------------
 *   1   2   3   4   5   6   7   8   9
 * </pre>
 * Each row is a sample and each column is a beat within a time measure.
 * 
 * @author claudio
 */
public class Cell
{
    private int value = 0;
    
    public Cell()
    {
    	value = 0;
    }
    /**
     * Check if a cell is enabled or disabled.
     * 
     * @return true if the cell is enabled.
     */
    public boolean isEnabled()
    {
        if (value > 0)
            return true;
        
        return false;
    }

    
    /**
     * Enable a cell.
     */
    public void enable()
    {
        value = 1;
    }
    
    
    /**
     * Disable a cell.
     */
    public void disable()
    {
        value = 0;
    }

    
    /**
     * Set the value of a cell (0 = disable, >0 = enabled).
     * 
     * @param v Value to set the cell.
     */
    public void setValue(int v)
    {
        value = v;
    }
    
    /**
     * Get the value of a cell (0 = disable, >0 = enabled).
     * 
     * @return the value of the cell.
     */
    public int getValue()
    {
    	Log.v("vafad","asdlkfsdlkfhsld");
        return value;
    }
}
