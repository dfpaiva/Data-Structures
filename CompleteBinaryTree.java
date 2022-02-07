public interface CompleteBinaryTree<E> extends BinaryTree<E>
{
	public Position<E> add(E e);
	public E remove();
}