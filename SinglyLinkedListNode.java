public class SinglyLinkedListNode<T> {

    private T data;
    private SinglyLinkedListNode<T> next;

    
    SinglyLinkedListNode(T data, SinglyLinkedListNode<T> next) {
        this.data = data;
        this.next = next;
    }

    /**
     * Creates a new SinglyLinkedListNode with only the given data.
     *
     * @param data the data stored in the new node
     */
    SinglyLinkedListNode(T data) {
        this(data, null);
    }

    /**
     * Gets the data.
     *
     * @return the data
     */
    T getData() {
        return data;
    }

    /**
     * Gets the next node.
     *
     * @return the next node
     */
    SinglyLinkedListNode<T> getNext() {
        return next;
    }

    /**
     * Sets the next node.
     *
     * @param next the new next node
     */
    void setNext(SinglyLinkedListNode<T> next) {
        this.next = next;
    }


    @Override
    public String toString() {
        return "Node containing: " + data;
    }
}