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

/**
 * @class Matrix This class provides the functionality for delivering different
 *        sounds at different points in the time. This is done using a sequencer
 *        matrix like the one below:
 * 
 *        <pre>
 *  -----------------------------------
 * |   |   |   |   |   |   |   |   |   |
 *  -----------------------------------
 * |   |   |   |   |   |   |   |   |   |
 *  -----------------------------------
 *   1   2   3   4   5   6   7   8   9
 * </pre>
 * 
 *        Each row is a sample and each column is a beat within a time measure.
 * @author claudio
 */
public class Matrix {
    // attributes
    private int rows; // no. of samples

    private int beats; // no. of time divisions

    private int[] samples; // array of samples

    private Context context;

    private Cell[][] data;

    private boolean enabled;

    // constructors
    /**
     * Default constructor.
     * 
     * @param ctx Application context.
     * @param r Number of initial rows (sounds).
     * @param cols Number of initial columns (beta divisions).
     */
    public Matrix(Context ctx, int r, int cols) {
        context = ctx;
        rows = r;
        beats = cols;
        enabled = false;
        data = new Cell[r][cols];
        for (int i = 0; i < r; i++)
            for (int j = 0; j < cols; j++)
                data[i][j] = new Cell();
    }

    /**
     * Enable a cell.
     * 
     * @param r The row of the matrix where the cell is.
     * @param c The column of the matrix where the cell is.
     */
    public int getCellValue(int r, int c) {
        return (data[r][c]).getValue();
    }

    /**
     * Disable a cell.
     * 
     * @param r The row of the matrix where the cell is.
     * @param c The column of the matrix where the cell is.
     * @param v Value of the cell.
     */
    public void setCellValue(int r, int c, int v) {
        (data[r][c]).setValue(v);
    }

    /**
     * Add n columns to the end of the matrix.
     * 
     * @param n Number of columns to add.
     */
    public void grow(int n) {
        if (n <= 0)
            return;
    }

    /**
     * Delete n columns from the end of the matrix.
     * 
     * @param n Number of columns to delete.
     */
    public void shrink(int n) {
        if (n <= 0)
            return;
    }

    /**
     * Delete a concrete column in the matrix.
     * 
     * @param n Number (index) of the column to delete.
     */
    public void deleteColumn(int n) {
        if ((n <= 0) || (n >= this.beats))
            return;
    }
}
