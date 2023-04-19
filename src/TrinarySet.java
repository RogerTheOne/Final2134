import java.util.ArrayList;

/* This class implements a TrinarySet which is a variant of a TrinaryHeap.
 * In the TrinarySet, the item and the priority are the same so that removeMin
 * returns the items in sorted order.
 */
public class TrinarySet extends TrinaryHeap {
  private int capacity;
  private int size;
  private IntNode[] nodes = new IntNode[10];

  public TrinarySet() {
    super();
  }

  /* This method adds a node to the heap with the same priority and item.
   * The node is added to the bottom of the tree and then moved up the
   * tree until it is in the correct position.
   */
  public void add(int item) {
    super.add(item, item);
  }

  /* This method adds a node to the heap. The node is added to the bottom of the tree
   * and then moved up the tree until it is in the correct position.
   * Warning: This method is overridden to ignore the priority parameter.
   */
  @Override
  public void add(int priority, int item) {
    add(item);
  }
}

