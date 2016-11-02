package csc205.bridges.project1;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.UnaryOperator;

import bridges.base.DLelement;

/**
 * This class implements a Linked List using a doubly-linked, non-circular list
 * with a dummy header node and a reference to the last element in the List. The
 * class also contains a reference to the most recently accessed node (ie, this
 * is a "finger", or an "iterator")
 */

public class CSC205_Project_1_Linked_List<E> implements java.util.List<E> {
	DLelement<E> header;
	DLelement<E> last;
	DLelement<E> finger;
	int size;
	int fingerPosition;

	/** Constructor method */
	public CSC205_Project_1_Linked_List() {

		header = new DLelement<E>();
		header.setLabel("dummy");
		header.setValue((E) new Object());
		last = header;
		finger = header;
		size = 0;
		fingerPosition = -1;
	}

	/**
	 * Returns the number of elements in this list.
	 *
	 * @return the number of elements in this list
	 */
	public int size() {
		return size;
	}

	/**
	 * @return a reference to the dummy header node in the List
	 */
	public DLelement<E> getDummyHeader() {
		return header;
	}

	/**
	 * @return a reference to the node pointed to by the Finger, which will be
	 *         the header node when the list is empty
	 * 
	 */
	public DLelement<E> getFinger() {
		return finger;
	}

	/**
	 * Appends the specified element to the end of this list.
	 *
	 * @param item
	 *            element to be appended to this list
	 * @return true if this collection changed as a result of the call (as
	 *         specified by Collection.add(E))
	 */
	public boolean add(E item) {

		DLelement<E> newItem = new DLelement<E>();
		newItem.setValue(item);
		newItem.setLabel(item.toString());
		if (finger.equals(header)) {
			header.setNext(newItem);
			finger = header.getNext();
			finger.setPrev(header);
			last = finger;
			fingerPosition++;
		} else {
			while (finger.getNext() != null) {
				finger = finger.getNext();
				fingerPosition++;
			}
			DLelement<E> temp = finger;
			temp.setNext(newItem);
			newItem.setPrev(temp);
			finger = newItem;
			last = finger;
		}
		size++;
		return true;
	}

	/**
	 * Finds and returns the element at the specified position in this list.
	 *
	 * @param index
	 *            the index of the element to be retrieved
	 * @return the element at the specified position
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range (index < 0 || index >= size())
	 */
	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Array is out of bounds");
		}
		if(index==0)
		{
			finger = header.getNext();
			fingerPosition = 0;
			return finger.getValue();
		}
		else if(index==size-1)
		{
			finger = last;
			fingerPosition = index;
			return finger.getValue();
		}
		else if (index < fingerPosition && index != 0) {
			for (int i = fingerPosition; i > index; i--) {
				finger = finger.getPrev();
				fingerPosition--;
			}
			return finger.getValue();
		} else if (index > fingerPosition && index != size-1) {
			for (int i = fingerPosition; i < index; i++) {
				finger = finger.getNext();
				fingerPosition++;
			}
			return finger.getValue();
		} else
			return finger.getValue();

	}

	/**
	 * Finds and returns the element at the specified position in this list.
	 *
	 * @param index
	 *            the index of the element to be retrieved
	 * @return the element at the specified position
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range (index < 0 || index >= size())
	 */
	public E set(int index, E newValue) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Array is out of bounds");
		}
		if(index==0)
		{
			finger = header.getNext();
			fingerPosition = 0;
			finger.setValue(newValue);
			finger.setLabel(newValue.toString());
			return finger.getValue();
		}
		else if(index==size-1)
		{
			last.setValue(newValue);
			last.setLabel(newValue.toString());
			finger = last;
			fingerPosition = index;
			return finger.getValue();
		}
		else if (index > fingerPosition) {
			for (int i = fingerPosition; i < index; i++) {
				finger = finger.getNext();
				fingerPosition++;
			}
			finger.setValue(newValue);
			finger.setLabel(newValue.toString());
			return finger.getValue();
		} else if (index < fingerPosition) {
			for (int i = fingerPosition; index < i; i--) {
				finger = finger.getPrev();
				fingerPosition--;
			}
			finger.setValue(newValue);
			finger.setLabel(newValue.toString());
			return finger.getValue();
		} else
			finger.setValue(newValue);
			finger.setLabel(newValue.toString());
		return finger.getValue();
	}

	/**
	 * Removes the element at the specified position in this list. Shifts any
	 * subsequent elements to the left (subtracts one from their indices).
	 * Returns the element that was removed from the list.
	 *
	 * @param index
	 *            the index of the element to be removed
	 * @return the element previously at the specified position
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range (index < 0 || index >= size())
	 */
	public E remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Array is out of bounds");
		}
		if(index==0)
		{
			DLelement<E> temp = finger;
			finger = temp.getNext();
			header.setNext(finger);
			finger.setPrev(header);
			fingerPosition = 0;
			return temp.getValue();
		}
		else if(index==size-1)
		{
			DLelement<E> temp = last;
			last = temp.getPrev();
			last.setPrev(temp.getPrev());
			finger = last;
			temp.getPrev().setNext(last);
			fingerPosition=index;
			return temp.getValue();
		}
		else if (index > fingerPosition) {
			for (int i = fingerPosition; i < index; i++ ) {
				finger = finger.getNext();
				fingerPosition++;
			}
			DLelement<E> temp = finger;
			finger = temp.getNext();
			finger.setPrev(temp.getPrev());
			finger.getPrev().setNext(finger);
			size--;
			return temp.getValue();
		} else if (index < fingerPosition) {
			for (int i = fingerPosition; index < i; i--) {
				finger = finger.getPrev();
				fingerPosition--;
			}
			DLelement<E> temp = finger;
			finger = temp.getNext();
			finger.setPrev(temp.getPrev());
			finger.getPrev().setNext(finger);
			size--;
			return temp.getValue();
		} else {
			DLelement<E> temp = finger;
			finger = temp.getNext();
			finger.setPrev(temp.getPrev());
			finger.getPrev().setNext(finger);
			size--;
			return temp.getValue();
		}
	}

	/**
	 * Adds a new element at the specified position in this list. Shifts any
	 * subsequent elements to the right. Returns the element that was removed
	 * from the list.
	 *
	 * @param index
	 *            the index of the element to be removed
	 * @param newValue
	 *            the value of the item to be added
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range (index < 0 || index >= size())
	 */
	public void add(int index, E newValue) {
		DLelement<E> newItem = new DLelement<E>();
		newItem.setValue(newValue);
		newItem.setLabel(newValue.toString());
		if(index==0)
		{
			DLelement<E> temp = header.getNext();
			header.setNext(newItem);
			newItem.setPrev(header);
			newItem.setNext(temp.getNext());
			temp.getNext().setPrev(newItem);
			finger = newItem;
			fingerPosition = 0;
		}
		else if(index==size-1)
		{
			last.setNext(newItem);
			newItem.setPrev(last);
			finger = newItem;
			last = finger;
			fingerPosition = index;
		}
		if (index > fingerPosition) {
			for (int i = fingerPosition; i < index; i++) {
				finger = finger.getNext();
				fingerPosition++;
			}
			newItem.setPrev(finger);
			newItem.setNext(finger.getNext());
			finger.getNext().setPrev(newItem);
			finger.setNext(newItem);
			}
		 else if (index < fingerPosition) {
			for (int i = fingerPosition; i > index; i--) {
				finger = finger.getPrev();
				fingerPosition--;
			}
			newItem.setPrev(finger);
			newItem.setNext(finger.getNext());
			finger.getNext().setPrev(newItem);
			finger.setNext(newItem);
		} else if(fingerPosition==index){
			newItem.setPrev(finger);
			newItem.setNext(finger.getNext());
			finger.getNext().setPrev(newItem);
			finger.setNext(newItem);
		}
		size++;
		}
		

	

	/**
	 * @return the contents of this List as a String
	 */
	public String toString() {

		DLelement<E> temp = header.getNext();
		String allElements = "" + temp.getValue().toString();
		while (temp.getNext() != null) {
			temp = temp.getNext();
			allElements = allElements + ", " + temp.getValue().toString();
		}
		return allElements;
	}

	public int indexOf(Object target) {

		throw (new UnsupportedOperationException());
	}

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		throw (new UnsupportedOperationException());
	}

	@Override
	public void clear() {
		throw (new UnsupportedOperationException());

	}

	@Override
	public boolean contains(Object o) {
		throw (new UnsupportedOperationException());
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw (new UnsupportedOperationException());
	}

	@Override
	public boolean isEmpty() {
		throw (new UnsupportedOperationException());
	}

	@Override
	public Iterator<E> iterator() {
		throw (new UnsupportedOperationException());
	}

	@Override
	public int lastIndexOf(Object o) {
		throw (new UnsupportedOperationException());
	}

	@Override
	public ListIterator<E> listIterator() {
		throw (new UnsupportedOperationException());
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		throw (new UnsupportedOperationException());
	}

	@Override
	public boolean remove(Object o) {
		throw (new UnsupportedOperationException());
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw (new UnsupportedOperationException());
	}

	@Override
	public void replaceAll(UnaryOperator<E> operator) {
		throw (new UnsupportedOperationException());
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw (new UnsupportedOperationException());
	}

	@Override
	public void sort(Comparator<? super E> c) {
		throw (new UnsupportedOperationException());
	}

	@Override
	public Spliterator<E> spliterator() {
		throw (new UnsupportedOperationException());
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		throw (new UnsupportedOperationException());
	}

	@Override
	public Object[] toArray() {
		throw (new UnsupportedOperationException());
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw (new UnsupportedOperationException());
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		throw (new UnsupportedOperationException());
	}
}
