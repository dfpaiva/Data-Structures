import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListCompleteBinaryTree<E> implements CompleteBinaryTree<E> 
{
	protected ArrayList<BTPos<E>> T;

    protected static class BTPos<E> implements Position<E>
    {
        E element;
        int index;

        public BTPos(E e, int i)
        {
            element = e;
            index = i;
        }

        public E element()
        {
            return element;
        }

        public int index()
        {
            return index;
        }


        public E setElement(E e)
        {
            E temp = element;
            element = e;
            return temp;
        }

        public String toString()
        {
            return ("[" + element + ", " + index + "]");
        }
    }

    public ArrayListCompleteBinaryTree()
    {
        T = new ArrayList<BTPos<E>>();
        T.add(0, null);
    }

    public int size()
    {
        return T.size() - 1;
    }

    public boolean isEmpty()
    {
        return (size() == 0);
    }

    protected BTPos<E> checkPosition(Position<E> v) throws  InvalidPositionException
    {
        if (v==null || !(v instanceof BTPos))
        {
            throw new InvalidPositionException("Position is invalid.");
        }
        return (BTPos<E>) v;
    }

    public Iterator<E> elements()
    {
        ArrayList<E> list = new ArrayList<E>();
        Iterator<BTPos<E>> iter = T.iterator();
        iter.next();
        while (iter.hasNext())
        {
            list.add(iter.next().element());
        }
        return list.iterator();
    }

    public Iterable<Position<E>> positions()
    {
        ArrayList<Position<E>> P = new ArrayList<Position<E>>();
        Iterator<BTPos<E>> iter = T.iterator();
        iter.next();
        while (iter.hasNext())
        {
            P.add(iter.next());
        }
        return P;
    }

    public Position<E> root() throws EmptyTreeException
    {
        if (isEmpty())
        {
            throw new EmptyTreeException("Tree is empty");
        }
        return T.get(1);
    }

    public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException
    {
        if (isRoot(v))
        {
            throw new BoundaryViolationException("No parent for root.");
        }
        BTPos<E> pos = checkPosition(v);
        return T.get(pos.index()/2);
    }

    public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException
    {
        PositionList<Position<E>> children = new NodePositionList<Position<E>>();
        if (hasLeft(v))
        {
            children.addLast(left(v));
        }
        if (hasRight(v))
        {
            children.addLast(right(v));
        }
        return children;
    }

    public boolean isInternal(Position<E> v) throws InvalidPositionException
    {
        return hasLeft(v);
    }

    public boolean isExternal(Position<E> v) throws InvalidPositionException
    {
        return !isInternal(v);
    }

    public boolean isRoot(Position<E> v) throws InvalidPositionException
    {
        BTPos<E> pos = checkPosition(v);
        return pos.index() == 1;
    }

    public E replace(Position<E> v, E e) throws InvalidPositionException
    {
        BTPos<E> pos = checkPosition(v);
        return pos.setElement(e);
    }

    public Position<E> left(Position<E> v) throws InvalidPositionException, BoundaryViolationException
    {
        if (!hasLeft(v))
        {
            throw new BoundaryViolationException("No left child.");
        }
        BTPos<E> pos = checkPosition(v);
        return T.get(2*pos.index());
    }

    public Position<E> right(Position<E> v) throws InvalidPositionException, BoundaryViolationException
    {
        if (!hasRight(v))
        {
            throw new BoundaryViolationException("No right child");
        }
        BTPos<E> pos = checkPosition(v);
        return T.get(2*pos.index()+1);
    }

    public boolean hasLeft(Position<E> v) throws InvalidPositionException
    {
        BTPos<E> pos = checkPosition(v);
        return 2*pos.index() <= size();
    }

    public boolean hasRight(Position<E> v) throws InvalidPositionException
    {
        BTPos<E> pos = checkPosition(v);
        return 2*pos.index()+1 <= size();
    }

    public Position<E> add(E element)
    {
        int i = size() + 1;
        BTPos<E> pos = new BTPos<E> (element, i);
        T.add(i, pos);
        return pos;
    }

    public E remove()
    {
        if(isEmpty())
        {
            throw new EmptyTreeException("Tree is empty.");
        }
        return T.remove(size()).element();
    }

    public String toString()
    {
        return T.toString();
    }
}