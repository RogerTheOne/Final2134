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


* 4b. Write the assertions in the code.
*
* 5a. Suggest one (1) exception that is appropriate
*     for the TARGET method and one (1) exception somewhere
*     else in the code.
*     State the condition under which the exceptions should be
*     thrown.
ANSWER HERE:


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

}
