// Demonstration of HashTableMap and FavoriteList / FavoriteListMTF

import java.util.Random;
import java.util.Iterator;

public class Demo
{
	public static void main(String[] args)
	{
		/*
			Below is HashTableMap
		*/

		HashTableMap<Integer,Integer> tm = new HashTableMap<Integer,Integer>();
		System.out.println("Initial map size: " + tm.size());
		Random rand = new Random();

		System.out.print("Going to use put(k,v) on HashTableMap.\n");
		for(int i = 0; i < 10; i++)
		{
			tm.put(i, rand.nextInt(10));
		}
		System.out.println("\nFinished, here are the results:");

		Iterable<Entry<Integer,Integer>> it = tm.entries();
		for(Entry<Integer, Integer> e: it)
			System.out.println(e);

		System.out.println("\nGoing to see what value is associated with key 1 in this map using the method get(k):");
		System.out.println(tm.get(1));

		System.out.println("\nI'm going to now remove it using remove() method:");
		System.out.println(tm.remove(1));
		System.out.println("\nHere is the new result:");

		Iterable<Entry<Integer,Integer>> refresh = tm.entries();
		for(Entry<Integer, Integer> e: refresh)
			System.out.println(e);

		/*
			Below is FavoriteList  
		*/

		System.out.println("\nMoving onto FavoriteList");

		String[] strings = {"Red", "Blue", "Green", "Yellow", "Pink", "Black", "White"};
		FavoriteList<String> fl = new FavoriteList<String>();
    	FavoriteListMTF<String> mtf = new FavoriteListMTF<String>();

    	int n = 10;	// number of accesses
    	for(int i = 0; i < n; i++)
    	{
    		System.out.println("-------------------------------------------------------------------------");
    		int index = rand.nextInt(strings.length);
    		String s = strings[index];

    		System.out.println("Randomly accessing: " + s);
    		fl.access(s);
    		System.out.println("FavList = " + fl);
    		mtf.access(s);
    		System.out.println("FavListMTF = " + mtf);
    	} 

    	int top = fl.size()/2;
    	System.out.println("-------------------------------------------------------------------------");
    	System.out.println("Top " + top + " in FavList = " + fl);
    	System.out.println("Top " + top + " in FavListMTF = " + mtf);

	}
}