package Database.controller;

public class DatabaseMachine
{
	public void DatabaseMachine()
	{
		
		
		
		
	}
	
	
	
	private void swap(int[] array, int position, int change)
	{
		int temp = array[position];
		array[position] = array[change];
		array[change] = temp;
	}
	
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
