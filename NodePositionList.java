import java.util.Iterator;

public class NodePositionList<E> implements PositionList<E>
{
	protected int numElts;
  	protected DNode<E> header, trailer;

  	public NodePositionList()
  	{
		numElts = 0;
  	  	header = new DNode<E>(null, null, null);
  	  	trailer = new DNode<E>(header, null, null);
  	  	header.setNext(trailer);
  	}

  	public int size()
  	{
		return numElts;
	}

  	public boolean isEmpty()
  	{
		return (numElts == 0);
	}

  	protected DNode<E> checkPosition(Position<E> p) throws InvalidPositionException
  	{
		if (p == null)
  	  		throw new InvalidPositionException("Null was passed to checkPosition");
  	  	if (p == header)
  	  		throw new InvalidPositionException("Can't use header as node");
  	  	if (p == trailer)
  	  		throw new InvalidPositionException("Can't use trailer as node");

  	  	try {
  	    	DNode<E> temp = (DNode<E>) p;										// Casting the paramter into a DNode
  	    	if ((temp.getPrev() == null) || (temp.getNext() == null))
  	    		throw new InvalidPositionException("Not a valid position");
  	    	return temp;
  	  		}catch (ClassCastException e) {
				throw new InvalidPositionException("Position is of wrong type");
  	  		}
  	}

  	public Position<E> first() throws EmptyListException
  	{
  	  	if (isEmpty())
  	    	throw new EmptyListException("List is empty");
  	  	return header.getNext();
  	}

	public Position<E> last() throws EmptyListException
	{
		if (isEmpty())
			throw new EmptyListException("List is empty");
		return trailer.getPrev();
	}

	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException
	{
		DNode<E> v = checkPosition(p);
		DNode<E> prev = v.getPrev();
		if (prev == header)
		  throw new BoundaryViolationException("Previous of this is the header, out of bounds");
		return prev;
	}

	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException
	{
		DNode<E> v = checkPosition(p);
		DNode<E> next = v.getNext();
		if (next == trailer)
		  throw new BoundaryViolationException("Next of this is the trailer, out of bounds");
		return next;
	}

	public void addFirst(E elem)
	{
		numElts++;
		DNode<E> v = new DNode<E>(header, header.getNext(), elem);	// 2 links dealt with here
		header.getNext().setPrev(v);
		header.setNext(v);			// at this point 4 links dealt with
	}

	public void addLast(E elem)
	{
		numElts++;
		DNode<E> old = trailer.getPrev();
		DNode<E> v = new DNode<E>(old, trailer, elem);	// 2 links
		old.setNext(v);
		trailer.setPrev(v);
	}

	public void addBefore(Position<E> p, E elem) throws InvalidPositionException
	{
		DNode<E> v = checkPosition(p);
		numElts++;
		DNode<E> n = new DNode<E>(v.getPrev(), v, elem);
		v.getPrev().setNext(n);
		v.setPrev(n);
	}

	public void addAfter(Position<E> p, E elem) throws InvalidPositionException
	{
		DNode<E> v = checkPosition(p);
		numElts++;
		DNode<E> n = new DNode<E>(v, v.getNext(), elem);
		v.getNext().setPrev(n);
		v.setNext(n);
	}

	public E remove(Position<E> p) throws InvalidPositionException
	{
		DNode<E> v = checkPosition(p);
		numElts--;
		DNode<E> prev = v.getPrev();
		DNode<E> next = v.getNext();
		prev.setNext(next);
		next.setPrev(prev);			// At this point 4 links have been dealt with
		E elem = v.element();

		// Old links dealt with below
		v.setNext(null);
		v.setPrev(null);
		return elem;
	}

	public E set(Position<E> p, E elem) throws InvalidPositionException
	{
		DNode<E> v = checkPosition(p);
		E old = v.element();
		v.setElement(elem);
		return old;
	}

	// Extra method that is useful when doing Bubble sort with NodeSequence
	public void swap(Position<E> a, Position<E> b) throws InvalidPositionException
	{
		DNode<E> A = checkPosition(a);
		DNode<E> B = checkPosition(b);
		E temp = A.element();
		A.setElement(B.element());
		B.setElement(temp);
	}

	public Iterable<Position<E>> positions()
	{
		PositionList<Position<E>> P = new NodePositionList<Position<E>>();
		if (!isEmpty())
		{
		  Position<E> p = first();
		  while (true)
		  {
			P.addLast(p);		// Keep adding until you reach the end
			if (p == last())
			  break;
			p = next(p);		// Reset
		  }
		}
		return P;
	}

	public Iterator<E> iterator()
	{
		return new ElementIterator<E>(this);	
	}

	// This toString method takes PositionList parameter
	public static <E> String toString(PositionList<E> pl)
	{
		Iterator<E> it = pl.iterator();	// Iterator imported in first line of this code
		String s = "[";
		while (it.hasNext())
		{
		  s += it.next();	// add to the string the next term
		  if (it.hasNext())
			s += ", ";		// comma in between terms to make it easier to read
		}
		s += "]";
		return s;
	}

	// This is the generic toString method that has no parameter
	public String toString()
	{
		return toString(this);
	}
}