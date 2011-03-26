package org.androidaalto.org.sequencer;


/**
 * @class Sequencer
 * 
 * This class provides the functionality for delivering different sounds
 * at different points in the time. This is done using a sequencer matrix
 * like the one below:
 * 
 *   -----------------------------------
 *  |   |   |   |   |   |   |   |   |   |
 *   -----------------------------------
 *  |   |   |   |   |   |   |   |   |   |
 *   -----------------------------------
 *    1   2   3   4   5   6   7   8   9
 *    
 * Each row is a sample and each column is a beat within a time measure.
 * 
 * @author claudio
 *
 */
public class Sequencer
{
	// attributes
	private int rows;
	private int divisions;
	
	
	// constructors
	/**
	 * Default constructor.
	 * 
	 * FIXME: document this code!
	 */
	public Sequencer()
	{
		rows = 4;
		divisions = 8;
	}
	
	/**
	 * Concrete constructor.
	 * 
	 * @param r Number of samples (rows).
	 * @param d Number of time divisions (columns).
	 */
	public Sequencer(int r, int d)
	{
		rows = r;
		divisions = d;
	}
	
	
	// API
	/**
	 * Obtain the number of current samples (rows).
	 * 
	 * @return an integer with the number of current samples.
	 */
	public int getNumberOfSamples()
	{
		return rows;
	}
	
	/**
	 * Alias of getNumberOfRows().
	 * 
	 * @return an integer with the number of current samples. 
	 */
	public int getRows()
	{
		return getNumberOfSamples();
	}
	
	/**
	 * Obtain the number of time divisions (columns).
	 * 
	 * @return an integer with the number of time divisions.
	 */
	public int getDivisions()
	{
		return divisions;
	}
	
	/**
	 * Alias of getDivisions().
	 * 
	 * @return an integer with the number of time divisions.
	 */
	public int getColumns()
	{
		return getDivisions();
	}
}
