/**
 * Problem Set 2
 * Writing methods for an IntNode class
 * 
 * @author Kevin Raj
 * @date Nov 18 2021
 * @period 7th Period
 */
public class IntNode {
	  public int data;
      public IntNode next;
      public IntNode() {
    	  this.data = 0; 
    	  this.next = null;
      }
      public IntNode(int data, IntNode next) {
          this.data = data; 
          this.next = next;
      }
      public String toString() 
      {
    	  if(next != null)
    		  return data + " " + next.toString();
          return data + "";
      }
      

      /** #1
       * Implement the addBefore method such that newItem will be added before 
       * target in the list starting with front.  If the target does not exist,
       * addBefore should return the original list, unchanged.
       * 
       * @param front, the first node in the linked list
       * @param target 
       * @param newItem item to be inserted
       * @return the front node of the updated linked list
       */
      public static IntNode addBefore(IntNode front, int target, int newItem) {
    	  /* COMPLETE THIS METHOD */
          if(front.data == target){
              IntNode newNode = new IntNode(newItem, front);
              return newNode;
          } else {
              IntNode pointer = front;

              while(pointer.next != null){
                  if(pointer.next.data == target){
                      IntNode newNode = new IntNode(newItem, pointer.next);
                      pointer.next = newNode;
                      return front;
                  } else {
                      pointer = pointer.next;
                  }
              }
          }

    	  return front; //quiets the compiler
      }

      /** #2
       * Implement the addBeforeLast method such that newItem will be added 
       * just before the last item in the linked list.  If the initial list is 
       * empty addBeforeLast should return null, returning the original list, unchanged.
       * 
       * @param front, the first node in the linked list
       * @param newItem
       * @return the front node of the updated linked list
       */
      public static IntNode addBeforeLast(IntNode front, int newItem) {
          /* COMPLETE THIS METHOD */

          IntNode pointer = front;

          if(pointer == null){
              return front;
          }
          if(pointer.next == null){
              IntNode newNode = new IntNode(newItem, front);
              return newNode;
          }



          while(pointer.next != null && pointer.next.next != null ){
              pointer = pointer.next;
          }

          IntNode newNode = new IntNode(newItem, pointer.next);
          pointer.next = newNode;


    	  return front; //quiets the compiler
      }
      
      /** #3
       * Implement the method numberOfOccurrances that will search 
       * a given linked list for a target int, and return the 
       * number of occurrences of the target
       * 
       * @param front, the first node in the linked list
       * @param target
       * @return the number of occurrences of the target
       */
      public static int numberOfOccurrences(IntNode front, int target) {
    	  /*COMPLETE THIS METHOD */

          IntNode pointer = front;
          int counter = 0;

          if(pointer == null)
              return 0;

          if(pointer.next == null){
              if(pointer.data == target){
                  return 1;
              } else {
                  return 0;
              }
          }

          while(pointer.next != null) {
              if(pointer.data == target){
                  counter++;
              }
              pointer = pointer.next;
          }

          // account for last element being a target value
          if(pointer.data == target){
              counter++;
          }

    	  return counter; //quiets the compiler
      }
      
      /** #4
       * Implement the method deleteEveryOther to delete EVERY OTHER 
       * item from an integer linked list. 
       * For example:
       * 	before: 3->9->12->15->21
       * 	after: 3->12->21
       * 
       * 	before: 3->9->12->15
       * 	after: 3->12
       *
       * 	before: 3->9
       * 	after: 3
       * 	
       * 	before: 3
       * 	after: 3
       * 
       * If the list is empty, the method should do nothing.
       * @param front, the first node in the linked list
       */
      public static void deleteEveryOther(IntNode front) {
          /* COMPLETE THIS METHOD */


          if(front == null || front.next == null)
              return;

          IntNode pointer = front;

          while( pointer != null && pointer.next != null) {
              pointer.next = pointer.next.next;
              pointer = pointer.next;
          }


      }
      
      /** #5
       * Implement the method deleteAllOccurrences that will 
       * delete all occurrences of a given target int from a 
       * linked list, and return a pointer to the first node 
       * of the resulting linked list.
       * 
       * @param front, the first node in the linked list
       * @param target
       * @return the front node of the updated linked list
       */
      public static IntNode deleteAllOccurrences(IntNode front, int target) {
    	  /* COMPLETE THIS METHOD */

          IntNode pointer = front;

          if(pointer == null)
              return pointer;


          while(pointer.next != null){
              if(pointer.next.data == target){
                  pointer.next = pointer.next.next;
              } else {
                  pointer = pointer.next;
              }
          }

          if(front.data == target){
              front = front.next;
          }

          return front;

      }
      
      /** #6
       * Implement the method commonElements to find the common elements 
       * in two SORTED linked lists, and return the common elements in 
       * sorted order in a NEW linked list. The original linked lists 
       * should not be modified. 
       * For instance:
       *  	l1 = 3->9->12->15->21
       *  	l2 = 2->3->6->12->19
       *  should produce a new linked list:
       *  	3->12
       *  
       * You may assume that the original lists do not have any duplicate items.
       * Return null if no common elements exist.
       * 
       * @param frontL1, the first node in the linked list 1
       * @param frontL2, the first node in the linked list 2
       * @return A reference to the front node of a new linked list
       * 	which holds the common elements of L1 and L2 in sorted order.
       * 	Or null if no common elements exist.
       */
      public static IntNode commonElements(IntNode frontL1, IntNode frontL2) {
    	  /* COMPLETE THIS METHOD */
          if(frontL1 == null || frontL2 == null){
              return null;
          }

            IntNode pointerL1 = frontL1;
            IntNode pointerL2 = frontL2;
            IntNode result = null;

            while(pointerL1 != null){
                while(pointerL2 != null){
                    if(pointerL1.data == pointerL2.data){
                        if(result == null)
                            result = new IntNode(pointerL1.data, null);
                        else {
                            while(result.next != null){
                                result = result.next;
                            }
                            result.next = new IntNode(pointerL1.data, null);
                        }
                    }

                    pointerL2 = pointerL2.next;
                }
                pointerL2 = frontL2;
                pointerL1 = pointerL1.next;
            }

    	  return result;
      }
}