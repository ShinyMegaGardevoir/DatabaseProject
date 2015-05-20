package Database.controller;

public class DatabaseMachine
{
	public void DatabaseMachine()
	{
		
		
		
		
	}
	
	
	/**
	 * Swaps 2 items in an array.
	 * @param array The array being used.
	 * @param position The position that needs to be swapped.
	 * @param change The temporary position that allows you to swap the spots in the array.
	 */
	private void swap(int[] array, int position, int change)
	{
		int temp = array[position];
		array[position] = array[change];
		array[change] = temp;
	}
	/**
	 * Gets the time it took to sort.
	 * @return The time it took to sort.
	 */
	public String sortingTime(long sortTime)
	{
		String timeToSort = "";
		
		timeToSort += "Days: " + sortTime/(1000*60*60*24) + "\n";
		timeToSort += "Hours: " + sortTime/(1000*60*60) %24+ "\n";
		timeToSort += "Minutes: " + sortTime/(1000*60) %60+ "\n";
		timeToSort += "Seconds: " + sortTime/(1000) %60+ "\n";
		timeToSort += "Milliseconds: " + sortTime %  1000 + "\n";
		
		return timeToSort;
	}
	
	public int[] selectionSort(int [] toBeSorted)
	{
		int minimum;
		int minimumPosition;
//		startTime = System.currentTimeMillis();
		for(int position = 0; position < toBeSorted.length; position++)
		{
			minimumPosition = position;
			minimum = toBeSorted[position];
			for(int next = position +1; next < toBeSorted.length; next++)
			{
				if(toBeSorted[next] < minimum)
				{
					minimum = toBeSorted[next];
					minimumPosition = next;
				}
			}
			if(minimumPosition != position)
			{
				swap(toBeSorted, position, minimumPosition);
			}
		}
//		endTime = System.currentTimeMillis();
		
		return toBeSorted;
	}

}
