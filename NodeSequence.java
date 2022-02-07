public class NodeSequence<E> extends NodePositionList<E> implements Sequence<E>
{
	public NodeSequence()
	{
		super();
	}

	public void checkRank(int r, int i) throws InvalidRankException
	{
		if(r < 0 || r >= i)
			throw new InvalidRankException("Invalid rank: " + r);
	}

	public Position<E> atRank(int r) throws InvalidRankException
	{
		DNode<E> temp;
		checkRank(r, size());

		if(r <= size()/2)	// From header forward
		{
			temp = header.getNext();
			for(int i = 0; i < r; i++)
				temp = temp.getNext();
		}
		else	// From the trailer but backwards
		{
			temp = trailer.getPrev();
			for(int i = 1; i < size()-r; i++)	// have to take into consideration of 0 n-1
				temp = temp.getPrev();
		}
		return temp;
	}

	public int rankOf(Position<E> p) throws InvalidRankException
	{
		DNode<E> temp = header.getNext();
		int j = -1;

		for(int i = 0; i < size(); i++)
		{
			if(p == temp)
				j = i;
			else						// Keep searching through the list till you reach the if statement
				temp = temp.getNext();
		}
		return j;
	}

	public E elemAtRank(int r) throws InvalidRankException
	{
		return atRank(r).element();	
	}


	public E replaceAtRank(int r, E e) throws InvalidRankException
	{
		checkRank(r, size());
		return set(atRank(r), e);	// Using set method from NodePositionList
	}

	public void insertAtRank(int r, E e) throws InvalidRankException
	{
		checkRank(r, size() + 1);

		if(r == size())
			addLast(e);					// NodePosiitonList method
		else
			addBefore(atRank(r), e);
	}

	public E removeAtRank(int r) throws InvalidRankException
	{
		checkRank(r, size());
		return remove(atRank(r));
	}

	public E removeFirst() throws EmptyDequeException
	{
		if(isEmpty())
			throw new EmptyDequeException("Sequence is empty");
		return removeAtRank(0);
	}

	public E removeLast() throws EmptyDequeException
	{
		if(isEmpty())
			throw new EmptyDequeException("Sequence is empty");
		return removeAtRank(size()-1);
	}

	// Similar to first() method in Position List but returns a generic instead of Position
	public E getFirst() throws EmptyDequeException
	{
		if(isEmpty())
			throw new EmptyDequeException("Sequence is empty");
		return elemAtRank(0);
	}

	// Similar to last() method, but returns a generic instead of Position
	public E getLast() throws EmptyDequeException
	{
		if(isEmpty())
			throw new EmptyDequeException("Sequence is empty");
		return elemAtRank(size()-1);
	}
}