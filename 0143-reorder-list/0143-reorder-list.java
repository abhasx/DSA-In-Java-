class Solution {
    public void reorderList(ListNode head) {
        if(head==null||head.next==null){
            return;
        }   
        ListNode fast=head,slow=head;
        //divide list into two halves
        while(fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        ListNode current=slow.next;
        slow.next=null;
        //reverse the second list
        ListNode prev=null;
        while(current!=null){
        ListNode next=current.next;
        current.next=prev;
        prev=current;
        current=next;
        }
        //merge two linklist
        ListNode first=head;
        ListNode second=prev;
        while(second!=null){
            ListNode firstNext=first.next;
            ListNode secondNext=second.next;
            first.next=second;
            second.next=firstNext;
            first=firstNext;
            second=secondNext;
        }
    }
}