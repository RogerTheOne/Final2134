import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/* YOUR TARGET METHOD IS: contains(int item) 
 *
 * FINAL EXAM NON-CODING ANSWERS
 ********************************************************
 * 1. Using Git. Clone and pull the repository for your exam.
 *    Add your name and banner ID to Tests.java, commit and push.
 *    You have used Git.
 *    NOTE: To get full marks you MUST perform a commit after
 *          each question.  This way, if something goes wrong
 *          I can check each part separately and give you the
 *          marks you deserve.
 ANSWER HERE:
 * Name: Roger Zhang
 * Banner ID: B00882222


 *
 * 2a. Give three (3) test cases for the TARGET method (see above)
 *     Each test case should be one line long.
 ANSWER HERE:
 * 1. actuallyContains: @description: This method will return true if the heap contains the specific item, @result: return true
 * 2. notContains: @description: This method will return false if the heap does not contain the item, @result: return false
 * 3. heapNull: @: This method will return false when the heap is empty, @result: return false


 * 2b. Implement the unit tests below (after all these comments).
 *
 * 3. Debug the issues causing your tests to fail.
 *    There are at least a couple bugs in the code.
 *    Add more unit tests if necessary.
 *    List bugs you found and fixed below.  Give
 *    - a brief description of each bug
 *    - method where the bug occurs
 *    - how you fixed the problem
ANSWER HERE:
* @description: the actuallyContains should be returned true instead of false
* @Location: containsHelper()
* @Fixed: change return false to true when nodes[index].item == item which means item is found in the node


* 4a.  Identify three (3) locations in the code where assertions
*      are appropriate. Give
*      - method where assertion should be used
*      - what the assertions should assert
ANSWER HERE:
* 1: add(); make sure the priority is not less than zero: assert priority >= 0: "priority is less than zero";
* 2: extendNodes(); make sure the nodes is not null: assert nodes != null: "nodes is null";
* 3: extendNodes(); make sure the preNodes is larger than zero: assert prevNodes.length > 0: "the length of prevNodes less than zero";


* 4b. Write the assertions in the code.
*
* 5a. Suggest one (1) exception that is appropriate
*     for the TARGET method and one (1) exception somewhere
*     else in the code.
*     State the condition under which the exceptions should be
*     thrown.
ANSWER HERE:
* 1: the NullPointerException should be placed in the containsHelper method as the nodes should not be null
* 2: the IndexOutOfBoundsException should be placed in the extendNodes as the extend should be larger than 0


* 5b. Implement the exceptions you suggested.
*
* 5c. Add new unit tests to test that the exceptions are thrown when
*     appropriate.
*
* 6a. Identify three (3) procedural refactorings that can be
*     done in the code.  Give
*    - a brief description of each issue
*    - the method where the issue is
*    - how to fix the issue
ANSWER HERE:
* 1: magic number; add(); change 10 to size
* 2: data members are public; the place where variable initialized; change multiple public accessible to private
* 3: one constructor is intimate to another one; constructor using arraylist; remove one of the constructor
* 4: comments redundant; the comment above the constructor; remove one of the duplicate comments


* 6b. Perform the refactorings on the code.  Be sure to do
*     regression testing
*
* 7. Identify three (3) class-level refactorings that can be
*     done in the code.  Give
*    - a brief description of each issue
*    - where the issue is
*    - what SOLID principle (if any) are violated
*    - whether a class implementation or class interface refactoring
*      is needed
*    - how to fix the issue (note: do not do the refactoring!)
ANSWER HERE:
* 1: there are overriding methods in the TrinarySet Class, and we can initialize the data at the superclass
*    location: two add method is TrinarySet.java
*    no Solid principle are violated
*    class implementation refactoring
*    initialized the data and write the two method in the superclass, use the method in subclass
* 2: there are duplicate data initialize in subclass
*    location: two variables in subclass
*    no solid
*    class implementation refactoring
*    Pull a method and variable into the superclass
* 3: the implementation of the subclass is similar to the superclass
*    location: TrinarySet.java
*    SOLID: Single responsibility principle
*    class interface refactoring
*    Collapse the superclass and subclass
* 4: class look like they may need to be modified and it seems hard to do so
*    location: TrinaryHeap
*    Open/Close principle
*    class interface refactoring
*    use the most general supertype possible in the interface


*******************************************************
* Place written answers above this line
*******************************************************
*/

class Tests {
    /* You can clone and rename this method for all the tests that
     * you will need to do.
     *
     * All tests for all source files should be placed here.
     */


    // ... (previous test cases)
    TrinaryHeap heap = new TrinaryHeap();
    @Test
    void actuallyContains() {
        heap.add(2, 3);
        heap.add(3, 4);

        assertTrue(heap.contains(3), "does not return true when the heap contains the item");
        assertTrue(heap.contains(4), "does not return true when the heap contains the item");
    }

    @Test
    void notContains(){
        heap.add(2,3);
        assertFalse(heap.contains(4), "does not return false when the heap does not contains the item");
    }

    @Test
    void heapNull(){
        assertFalse(heap.contains(3), "does not return false when the heap is null");
    }

    @Test
    void itemWithSameIndex(){
        heap.add(2,3);
        heap.add(2,4);

        assertTrue(heap.contains(4), "does not return true when two item placed in same index");
        assertTrue(heap.contains(3), "does not return true when two item placed in same index");
    }

    @Test
    void containsAndRemove(){
        heap.add(2,3);
        heap.remove(2);
        assertFalse(heap.contains(2), "does not return false when item is removed");
    }

    @Test
    void nodeIsNull() {
        try{
            TrinaryHeap.IntNode[] nodes = null;
            heap.containsHelper(3, 3);
        }catch (NullPointerException e){
            assertEquals(e.getMessage(), "nodes is null");
        }
    }

    @Test
    void extendLessThanZero(){
        int extend = -1;
        try{
            heap.extendNodes(extend);
        }catch (IndexOutOfBoundsException e){
            assertEquals(e.getMessage(), "extend is less than zero");
        }
    }

}
