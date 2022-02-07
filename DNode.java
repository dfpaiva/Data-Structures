// Doubly linked list

public class DNode<E> implements Position<E>	
{
	protected DNode<E> prev, next;
  	protected E element;

  	public DNode(DNode<E> p, DNode<E> n, E e)
  	{
  	  prev = p;
  	  next = n;
  	  element = e;
  	}

  	public E element()
  	{
  		return element;
  	}

  	public DNode<E> getPrev()
  	{
		return prev;
  	}

  	public DNode<E> getNext()
  	{
		return next;
  	}

  	public void setPrev(DNode<E> p)
  	{
		prev = p;
  	}

  	public void setNext(DNode<E> n)
  	{
		next = n;
  	}

  	public void setElement(E e)
  	{
		element = e;
  	}
}
