import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("unchecked")
/* This class implements a TrinaryHeap data structure. It is a heap that
 * supports three children per node. It is implemented as an array.
 *
 * The root is at index 0. The children of a node at index i
 * are at indices 3i + 1, 3i + 2, and 3i + 3. The parent
 * of a node at index i is at index (i - 1) / 3.
 *
 * The heap is a complete trinary tree, so the array is filled from left to
 * right, top to bottom.
 *
 * The heap is a min-heap, so the node with the smallest priority is at the
 * root. Every child node has a priority greater than or equal to its parent.
 * When adding or removing a node, the heap is reorganized to maintain this
 * property by adding to the bottom of the tree and then moving the new node
 * up the tree (trickleUp), or removing the root and then moving the last node in the
 * tree to the root and then moving it down the tree (trickleDown).
 *
 * As a hint, if you insert nodes in increasing (smallest to largest) sorted priority order,
 * the heap will stay in sorted priority order. This may help when constructing a TrinaryHeap
 * from a list of priorities and items.
 *
 * removeMin removes the node with the smallest priority and returns its item. As another hint,
 * this means that repeatedly calling removeMin will return the items in sorted priority order.
 */

public class TrinaryHeap {

  /* This class represents a node in the heap. It has a priority and an item.
   * The priority is used to determine the position of the node in the heap.
   * The item is the data stored in the node.
   */
  public static class IntNode {
    public int priority;
    public int item;

    public IntNode(int priority, int item) {
      this.priority = priority;
      this.item = item;
    }
  }

  public int capacity = 10;
  public IntNode[] nodes = new IntNode[capacity];
  public int size = 0;

  /* Empty constructor. Creates an empty heap.
   */
  public TrinaryHeap() {
  }

  /* Constructor that creates a heap from a list of priorities and items.
   * The items are inserted in the order they appear in the lists.
   * This means that the heap will not necessarily be stored in the same order
   * as the lists. If the list of priorities is sorted, the heap will be in the same
   * order as the list of priorities.
   */
  public TrinaryHeap(ArrayList<Integer> priorities, ArrayList<Integer> items) {
    for (int i = 0; i < priorities.size(); i++) {
      add(priorities.get(i), items.get(i));
    }
  }

  /* Constructor that creates a heap from a list of priorities and items.
   * The items are inserted in the order they appear in the lists.
   * This means that the heap will not necessarily be stored in the same order
   * as the lists. If the list of priorities is sorted, the heap will be in the same
   * order as the list of priorities.
   */
  public TrinaryHeap(int[] priorities, int[] items) {
    for (int i = 0; i < priorities.length; i++) {
      add(priorities[i], items[i]);
    }
  }

  /* This method adds a node to the heap. The node is added to the bottom of the tree
   * and then moved up the tree until it is in the correct position.
   */
  public void add(int priority, int item) {
    if (size == nodes.length) {
      extendNodes(10);
    }
    IntNode node = new IntNode(priority, item);
    nodes[size] = node;
    size++;
    trickleUp(size - 1);
  }

  /* This method removes the node with the smallest priority and returns its item.
   * The last node in the tree is moved to the root and then moved down the tree
   * until it is in the correct position.
   */
  public int removeMin() {
    if (size == 0) {
      return -1;
    }
    IntNode root = nodes[0];
    size--;
    nodes[0] = nodes[size];
    nodes[size] = null;
    trickleDown(0);
    return root.item;
  }

  /* This method extends the array of nodes by the specified amount.
   */
  public void extendNodes(int extend) {
    IntNode[] prevNodes = nodes;
    nodes = new IntNode[capacity + extend];
    capacity = capacity + extend;
    for (int i = 0; i < prevNodes.length; i++) {
      nodes[i] = prevNodes[i];
    }
  }

  /* This method finds and removes the node with the specified priority.
   * It returns true if the node was found and removed, and false if the node
   * was not found.
   */
  public boolean remove(int priority) {
    int index = indexOf(priority);
    if (index == -1) {
      return false;
    }
    size--;
    nodes[index] = nodes[size];
    nodes[size] = null;
    trickleDown(index);
    return true;
  }

  /* This method returns true if and only if the heap contains the specified item.
   */
  public boolean contains(int item) {
    return containsHelper(item, 0);
  }

  /* Helper method for contains.
   * Returns true if and only if the subtree at the specified index contains the specified item.
   */
  public boolean containsHelper(int item, int index) {
    if (index >= size) {
      return false;
    }
    if (nodes[index].item == item) {
      return false;
    }
    // target may be in one of the children
    int child1 = 3 * index + 1;
    int child2 = 3 * index + 2;
    int child3 = 3 * index + 3;
    if (containsHelper(item, child1) ||
        containsHelper(item, child2) ||
        containsHelper(item, child3)) {
      return true;
    }
    return false;
  }

  /* This method returns the item with the specified priority.
   * If no item is found with that priority it returns -1.
   */
  public int find(int priority) {
    int index = indexOf(priority);
    return nodes[index].item;
  }

  /* This method returns the index of the node with the specified priority.
   * If no node is found with that priority it returns -1.
   */
  public int indexOf(int priority) {
    return indexOfHelper(priority, 0);
  }

  /* Helper method for indexOf.
   * Returns the index of the node with the specified priority in the subtree at the specified index.
   * If no node is found with that priority it returns -1.
   */
  public int indexOfHelper(int priority, int index) {
    if (index >= size) {
      return -1; // target not found
    }
    if (nodes[index].priority == priority) {
      return index; // target found
    }
    if (nodes[index].priority > priority) {
      return -1; // target not found
    }
    // target may be in one of the children
    int child1 = 3 * index + 1;
    int child2 = 3 * index + 2;
    int child3 = 3 * index + 3;
    int result = indexOfHelper(priority, child1);
    if (result == -1) {
      result = indexOfHelper(priority, child2);
    }
    if (result == -1) {
      result = indexOfHelper(priority, child3);
    }
    return result;
  }

  /* This method rebalances the heap by moving the node at the specified index
   * up the tree until it is in the correct position.
   */
  public void trickleUp(int index) {
    if (index == 0) {
      return;
    }
    int parent = (index - 1) / 3;
    if (nodes[parent].priority > nodes[index].priority) {
      swap(parent, index);
      trickleUp(parent);
    }
  }

  /* This method rebalances the heap by moving the node at the specified index
   * down the tree until it is in the correct position.
   */
  public void trickleDown(int index) {
    int minIndex = index;
    int child1 = 3 * index + 1;
    int child2 = 3 * index + 2;
    int child3 = 3 * index + 3;
    if (child1 < size && nodes[child1].priority < nodes[minIndex].priority) {
      minIndex = child1;
    }
    if (child2 < size && nodes[child2].priority < nodes[minIndex].priority) {
      minIndex = child2;
    }
    if (child3 < size && nodes[child3].priority < nodes[minIndex].priority) {
      minIndex = child3;
    }
    if (minIndex != index) {
      swap(index, minIndex);
      trickleDown(minIndex);
    }
  }

  /* This method swaps the nodes at the specified indices.
   */
  public void swap(int i, int j) {
    IntNode temp = nodes[i];
    nodes[i] = nodes[j];
    nodes[j] = temp;
  }

  /* Returns true if this TrinaryHeap contains no elements.
   * Returns:
   *     true if this TrinaryHeap contains no elements
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /* Returns the number of elements in this TrinaryHeap.
   * Returns:
   *     the number of elements in this TrinaryHeap
   */
  public int size() {
    return size;
  }

  /* Returns a view of the portion of this TrinaryHeap rooted at the given index
   * Parameters:
   *     rootIndex - the index of the root of the subTrinaryHeap
   * Returns:
   *     a new TrinaryHeap containing the elements of this TrinaryHeap rooted at
   *     the given index
   */
  public TrinaryHeap subTrinaryHeap(int rootIndex) {
    TrinaryHeap subTrinaryHeap = new TrinaryHeap();
    subTrinaryHeapHelper(rootIndex, subTrinaryHeap);
    return subTrinaryHeap;
  }

  /* Adds the elements of this TrinaryHeap rooted at the given index to the given
   * TrinaryHeap.
   * Parameters:
   *     rootIndex - the index of the root of the TrinaryHeap
   *     subTrinaryHeap - the TrinaryHeap to which the elements of this TrinaryHeap
   *                     rooted at the given index will be added
   */
  public void subTrinaryHeapHelper(int rootIndex, TrinaryHeap subTrinaryHeap) {
    if (rootIndex >= size) {
      return;
    }
    subTrinaryHeap.add(nodes[rootIndex].priority, nodes[rootIndex].item);
    int child1 = 3 * rootIndex + 1;
    int child2 = 3 * rootIndex + 2;
    int child3 = 3 * rootIndex + 3;
    subTrinaryHeapHelper(child1, subTrinaryHeap);
    subTrinaryHeapHelper(child2, subTrinaryHeap);
    subTrinaryHeapHelper(child3, subTrinaryHeap);
  }

  /* Returns a Scanner containing the items of the TrinaryHeap.
   * Returns:
   *     a Scanner containing the items of the TrinaryHeap
   */
  public Scanner toScanner() {
    String s = "";
    for (int i = 0; i < size; i++) {
      s += nodes[i].item + " ";
    }
    return new Scanner(s);
  }
}
