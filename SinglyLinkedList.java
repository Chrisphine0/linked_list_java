import java.util.NoSuchElementException;


public class SinglyLinkedList<T> {

    private SinglyLinkedListNode<T> head;
    private SinglyLinkedListNode<T> tail;
    private int size;


    /**
     * Adds the element to the specified index.
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index to add the new element
     * @param data  the data to add
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null.");
        }

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<>(data);

        if (index == 0) {
            newNode.setNext(head);
            head = newNode;
            if (tail == null) {
                tail = newNode;
            }
        } else if (index == size) {
            tail.setNext(newNode);
            tail = newNode;
        } else {
            SinglyLinkedListNode<T> prevNode = getNodeAtIndex(index - 1);
            SinglyLinkedListNode<T> nextNode = prevNode.getNext();
            prevNode.setNext(newNode);
            newNode.setNext(nextNode);
        }

        size++;
    }

    /**
     * Adds the element to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null.");
        }

        SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<>(data);

        if (head == null) {
            // The list is empty, so both head and tail will point to the new node
            head = newNode;
            tail = newNode;
        } else {
            // The list is not empty, so the new node will become the new head
            newNode.setNext(head);
            head = newNode;
        }

        size++;
    }

    /**
     * Adds the element to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null.");
        }

        SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<>(data);

        if (tail == null) {
            // The list is empty, so both head and tail will point to the new node
            head = newNode;
            tail = newNode;
        } else {
            // The list is not empty, so the new node will be added after the current tail
            tail.setNext(newNode);
            tail = newNode;
        }

        size++;
    }

    /**
     * Removes and returns the element at the specified index.
     *
     * Must be O(1) for indices 0 and O(n) for all other
     * cases.
     *
     * @param index the index of the element to remove
     * @return the data that was removed
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        T removedData;

        if (index == 0) {
            // Removing the first element
            removedData = head.getData();
            head = head.getNext();
            if (head == null) {
                // The list becomes empty after removal
                tail = null;
            }
        } else {
            SinglyLinkedListNode<T> prevNode = getNodeAtIndex(index - 1);
            SinglyLinkedListNode<T> currentNode = prevNode.getNext();
            removedData = currentNode.getData();
            prevNode.setNext(currentNode.getNext());
            if (currentNode == tail) {
                // Removing the last element, update the tail reference
                tail = prevNode;
            }
        }

        size--;

        return removedData;
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (head == null) {
            throw new NoSuchElementException("List is empty.");
        }

        T removedData = head.getData();

        if (head == tail) {
            // Only one element in the list
            head = null;
            tail = null;
        } else {
            head = head.getNext();
        }

        size--;

        return removedData;
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Must be O(n).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        if (head == null) {
            throw new NoSuchElementException("List is empty.");
        }

        T removedData;

        if (head == tail) {
            // Only one element in the list
            removedData = head.getData();
            head = null;
            tail = null;
        } else {
            SinglyLinkedListNode<T> currentNode = head;
            while (currentNode.getNext() != tail) {
                currentNode = currentNode.getNext();
            }
            removedData = tail.getData();
            currentNode.setNext(null);
            tail = currentNode;
        }

        size--;

        return removedData;
    }

    /**
     * Returns the element at the specified index.
     *
     * Must be O(1) for indices 0 and size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        SinglyLinkedListNode<T> node = getNodeAtIndex(index);
        return node.getData();
    }

    private SinglyLinkedListNode<T> getNodeAtIndex(int index) {
        SinglyLinkedListNode<T> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }
        return currentNode;
    }


    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(n).
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null.");
        }

        if (head == null) {
            throw new NoSuchElementException("List is empty.");
        }

        T removedData = null;
        SinglyLinkedListNode<T> prevNode = null;
        SinglyLinkedListNode<T> currentNode = head;

        while (currentNode != null) {
            if (currentNode.getData().equals(data)) {
                removedData = currentNode.getData();
                if (prevNode == null) {
                    // Removing the first occurrence
                    head = currentNode.getNext();
                    if (head == null) {
                        // The list becomes empty
                        tail = null;
                    }
                } else {
                    prevNode.setNext(currentNode.getNext());
                    if (currentNode == tail) {
                        // Removing the last occurrence, update the tail reference
                        tail = prevNode;
                    }
                }
            }
            prevNode = currentNode;
            currentNode = currentNode.getNext();
        }

        if (removedData == null) {
            throw new NoSuchElementException("Data not found.");
        }

        size--;

        return removedData;
    }

    /**
     * Returns an array representation of the linked list.
     *
     * Must be O(n) for all cases.
     *
     * @return the array of length size holding all of the data (not the
     * nodes) in the list in the same order
     */
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] array = (T[]) new Object[size];
        SinglyLinkedListNode<T> currentNode = head;
        int index = 0;

        while (currentNode != null) {
            array[index] = currentNode.getData();
            currentNode = currentNode.getNext();
            index++;
        }

        return array;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public SinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the tail node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the tail of the list
     */
    public SinglyLinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }
}