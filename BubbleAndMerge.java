import java.util.Random;
import java.util.Arrays;

public class BubbleAndMerge
{
	public static void main(String[] args)
	{
		int iterate = 1000;

		NodeSequence<Integer> ns = new NodeSequence<>();	// NodeSequence uses bubble sort
		int ar[] = new int[iterate];		// array that will have same values of NodeSequeunce
		int artemp[] = new int[iterate];	// temp array that is needed for mergeSort method

		Random rand = new Random();
		int rnumb;

		for(int i = 0; i < iterate; i++)	// will iterate 1000 times
		{
			rnumb = rand.nextInt(1000);	// random number from 0 to 999

			ns.addLast(rnumb);	// adding numbers to NodeSequence
			ar[i] = rnumb;		// same numbers added to array
		}

 		System.out.println("Here is the NodeSequence:\n" +
 						   "----------------------------\n" + ns);
 		System.out.println();
 		System.out.println("Here is the array:\n" +
 						   "----------------------------\n" + Arrays.toString(ar));
		System.out.println();
		System.out.println("-----------------------------\n" +
						   "As you can see, they are both the same, so this should be fair in comparing the sorts\n" +
						   "------------------------------");
		System.out.println();

		System.out.println("Going to use bubble sort now on the NodeSequence");
		long sb = System.currentTimeMillis();
		bubbleSort(ns);
		long eb = System.currentTimeMillis();
		long btime = eb - sb;
		System.out.println("Bubble sort took " + btime + " milliseconds");

		System.out.println();
 		System.out.println("Here is the NodeSequence now sorted:\n" +
 						   "----------------------------\n" + ns);
 		System.out.println();
 		System.out.println("Here is the array to show you it's not affected:\n" +
 						   "----------------------------\n" + Arrays.toString(ar));
		System.out.println();

		System.out.println("Going to use merge sort now on the array");
		long sm = System.currentTimeMillis();
		mergeSort(ar, artemp, ar.length);
		long em = System.currentTimeMillis();
		long mtime = em - sm;
		System.out.println("Merge sort took " + mtime + " milliseconds");

 		System.out.println();
 		System.out.println("Here is the array now sorted:\n" +
 						   "----------------------------\n" + Arrays.toString(ar));
		System.out.println();

		System.out.println("----------------------------\n" +
						   "CONCLUSION\n" +
						   "----------------------------\n" +
						   "Bubble sort: " + btime + " ms\n" +
						   "Merge sort: " + mtime + " ms");
	}

	public static void bubbleSort(NodeSequence<Integer> ns)
	{
		Position<Integer> start, next;
		int n = ns.size();

		for(int i = 0; i < n; i++)
		{
			start = ns.first();				// starting point
			for(int j = 1; j < n-i; j++)
			{
				next = ns.next(start);
				if(start.element() > next.element())	// compare and swap the start and element next to it
					ns.swap(start, next);
				start = next;	// new start will be set
			}
		}
	}

	public static void mergeSort(int numbers[], int temp[], int array_size)
	{
	  m_sort(numbers, temp, 0, array_size - 1);
	}


	public static void m_sort(int numbers[], int temp[], int left, int right)
	{
	  int mid;

	  if (right > left)
	  {
	    mid = (right + left) / 2;
	    m_sort(numbers, temp, left, mid);
	    m_sort(numbers, temp, mid+1, right);
	    merge(numbers, temp, left, mid+1, right);
	  }
	}

	public static void merge(int numbers[], int temp[], int left, int mid, int right)
	{
	  int i, left_end, num_elements, tmp_pos;

	  left_end = mid - 1;
	  tmp_pos = left;
	  num_elements = right - left + 1;

	  while ((left <= left_end) && (mid <= right))
	  {
	    if (numbers[left] <= numbers[mid])
	    {
	      temp[tmp_pos] = numbers[left];
	      tmp_pos = tmp_pos + 1;
	      left = left +1 ;
	    }
	    else
	    {
	      temp[tmp_pos] = numbers[mid];
	      tmp_pos = tmp_pos + 1;
	      mid = mid + 1;
	    }
	  }

	  while (left <= left_end)
	  {
	    temp[tmp_pos] = numbers[left];
	    left = left + 1;
	    tmp_pos = tmp_pos + 1;
	  }
	  while (mid <= right)
	  {
	    temp[tmp_pos] = numbers[mid];
	    mid = mid + 1;
	    tmp_pos = tmp_pos + 1;
	  }

	  for (i=0; i < num_elements; i++)
	  {
	    numbers[right] = temp[right];
	    right = right - 1;
	  }
	}
}