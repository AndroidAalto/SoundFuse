package org.androidaalto.soundfused.sequencer;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;

/**
 * @class Sequencer
 * 
 *        This class provides the functionality for delivering different sounds
 *        at different points in the time. This is done using a sequencer matrix
 *        like the one below:
 * 
 *        ----------------------------------- | | | | | | | | | |
 *        ----------------------------------- | | | | | | | | | |
 *        ----------------------------------- 1 2 3 4 5 6 7 8 9
 * 
 *        Each row is a sample and each column is a beat within a time measure.
 * 
 * @author claudio
 * 
 */
public class Sequencer
{
    // attributes
    private int       rows;     // no. of samples
    private int       beats;    // no. of time divisions
    private int[]     samples;  // array of samples
    private int[][]   matrix;
    private int       bpm;
    private SoundPool sound;
    private Context   context;
    private Runnable  playback;
    private Handler   myhandler;

    // constructors
    /**
     * Default constructor.
     * 
     * FIXME: document this code!
     */
    public Sequencer(Context ctx)
    {
        this(ctx, 4, 8);
    }

    /**
     * Concrete constructor.
     * 
     * @param nsamples
     *            Number of samples (rows).
     * @param ndivisions
     *            Number of time divisions (columns).
     */
    public Sequencer(Context ctx, int nsamples, int nbeats)
    {
        context = ctx;
        rows = nsamples;
        beats = nbeats;
        bpm = 120;
        samples = new int[nsamples];
        sound = new SoundPool(nsamples, AudioManager.STREAM_MUSIC, 0);
        matrix = new int[nsamples][nbeats];
    }

    // API
    /**
     * TODO: document this!
     * 
     */
    public void setSample(int id, int sampleSrc)
    {
        samples[id] = sound.load(context, sampleSrc, 1);
    }

    /**
     * TODO: document this!
     */
    public void enableCell(int sampleId, int beatId)
    {
        this.setCell(sampleId, beatId, 1);
    }

    /**
     * TODO: document this!
     */
    public void disableCell(int sampleId, int beatId)
    {
        this.setCell(sampleId, beatId, 0);
    }

    /**
	 */
    private void setCell(int sampleId, int beatId, int value)
    {
        matrix[sampleId][beatId] = value;
    }

    /**
     * TODO: document this!
     */
    public void play()
    {
        // play sound periodically
        playback = new Runnable()
        {
            int count = -1;

            public void run()
            {
                count = (count + 1) % beats;

                for (int i = 0; i < rows; i++)
                    if (matrix[i][count] != 0)
                        sound.play(samples[i], 100, 100, 1, 0, 1);

                // HERE WHAT IS MEANT TO BE DONE
                sound.play(samples[0], 100, 100, 1, 0, 1);
                myhandler.postDelayed(this, (60 * 1000) / bpm);
            }
        };

        // fetch messages and schedule a periodic retrieval
        // HERE WHAT IS MEANT TO DO THE FIRST TIME
        myhandler = new Handler();
        myhandler.postDelayed(playback, (60 * 1000) / bpm);
    }
}