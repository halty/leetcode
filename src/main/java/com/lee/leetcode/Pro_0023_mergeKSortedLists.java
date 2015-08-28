package com.lee.leetcode;

public class Pro_0023_mergeKSortedLists {

	public static void main(String[] args) {
		int[][] array = new int[][] {
				{-10,-6,-1},
				{},
				{-10,-7,-4,-2,-2,-1},
				{-10,-9,4},
				{4},
				{-3,1},
				{-8,-3,1,3}
		};
		ListNode[] list = build(array);
		print(list);
		print(mergeKLists1(list));
	}
	
	public static ListNode mergeKLists1(ListNode[] lists) {
        int count = lists.length;
        if(count == 0) { return null; }
        if(count == 1) { return lists[0]; }
        
        sort(lists, 0, count-1, new ListNode[count]);
        
        int end = count - 1;
        for(; end>=0 && lists[end]==null; end--);
        
        ListNode head = new ListNode(0);
        ListNode prev = head;
        if(end <= 0) {
        	prev.next = lists[0];
        }else {
        	int first = 0;
	        while(lists[first] != null) {
	        	prev = prev.next = lists[first];
	        	lists[first] = prev.next;
	        	
	        	if(end == first) { break; }
	        	ListNode node = lists[first];
	        	if(node == null) {
	        		first++;
	        	}else {
	            	int left = first+1, right = end;
	            	while(left < right) {
	            		int middle = (left + right) / 2;
	            		if(node.val < lists[middle].val) {
	            			right = middle - 1;
	            		}else if(node.val > lists[middle].val) {
	            			left = middle + 1;
	            		}else {
	            			left = middle;
	            			break;
	            		}
	            	}
	            	if(left == right) {
	            		if(node.val > lists[left].val) { left++; }
	            	}
	            	if(left > first+1) {
	            		System.arraycopy(lists, first+1, lists, first, left-first-1);
	            		lists[left-1] = node;
	            	}
	        	}
	        }
        }
        
        return head.next;
    }
	
	private static void sort(ListNode[] lists, int left, int right, ListNode[] backup) {
		if(left+5 > right) {
			for(int i=left; i<right;) {
				ListNode node = lists[i];
				if(node == null) {
					System.arraycopy(lists, i+1, lists, i, right-i);
					lists[right] = null;
					right--;
					continue;
				}
				int k = i;
				for(int j=i+1; j<=right; j++) {
					if(lists[j] != null && lists[j].val < node.val) {
						node = lists[j];
						k = j;
					}
				}
				if(node == null) { break; } 
				lists[k] = lists[i];
				lists[i] = node;
				i++;
			}
		}else {
			int middle = (left + right) / 2;
			sort(lists, left, middle, backup);
			sort(lists, middle+1, right, backup);
			int i=left, a=left, b=middle+1;
			while(a<=middle && b<=right) {
				if(lists[b] == null) {
					int len = middle - a + 1;
					System.arraycopy(lists, a, backup, i, len);
					i += len;
					a = middle + 1;
					break;
				}else {
					if(lists[a] == null) {
						int len = right - b + 1;
						System.arraycopy(lists, b, backup, i, len);
						i += len;
						b = right + 1;
						break;
					}else {
						if(lists[a].val <= lists[b].val) {
							backup[i++] = lists[a++];
						}else {
							backup[i++] = lists[b++];
						}
					}
				}
			}
			if(a > middle) {
				System.arraycopy(lists, b, backup, i, right-b+1);
			}else {
				System.arraycopy(lists, a, backup, i, middle-a+1);
			}
			System.arraycopy(backup, left, lists, left, right-left+1);
		}
	}

	public static ListNode mergeKLists(ListNode[] lists) {
        int count = lists.length;
        int k = 0;
		for(int i=0; i<count; i++) { if(lists[i] != null) { lists[k++] = lists[i]; } }
		
		if(k == 0) { return null; }
		if(k == 1) { return lists[0]; }
		
		int idx = 0;
		ListNode head = lists[0];
		for(int i=1; i<k; i++) {
			if(lists[i].val < head.val) {
				idx = i;
				head = lists[i];
			}
		}
		lists[idx] = head.next;
        ListNode prev = head;
        
        count = k;
        while(count > 0) {
        	k = 0;
        	for(int i=0; i<count; i++) { if(lists[i] != null) { lists[k++] = lists[i]; } }
        	if((count = k) == 1) {
        		prev.next = lists[0];
        		break;
        	}
        	idx = 0;
        	ListNode min = lists[0];
        	for(int i=1; i<count; i++) {
        		if(lists[i].val < min.val) {
    				idx = i;
    				min = lists[i];
    			}
        	}
        	lists[idx] = min.next;
        	prev.next = min;
        	prev = min;
        }
        
        return head;
    }
	
	private static ListNode[] build(int[][] array) {
		ListNode[] list = new ListNode[array.length];
		for(int i=0; i<array.length; i++) {
			list[i] = build(array[i]);
		}
		return list;
	}
	
	private static ListNode build(int[] array) {
		if(array == null || array.length == 0) { return null; }
		ListNode head = new ListNode(array[0]);
		ListNode prev = head;
		for(int i=1; i<array.length; i++) {
			ListNode node = new ListNode(array[i]);
			prev.next = node;
			prev = node;
		}
		return head;
	}
	
	private static void print(ListNode[] list) {
		for(ListNode n : list) {
			print(n);
		}
	}
	
	private static void print(ListNode head) {
		StringBuilder buf = new StringBuilder();
		if(head != null) {
			buf.append(head.val);
			head = head.next;
		}
		while(head != null) {
			buf.append(" -> ").append(head.val);
			head = head.next;
		}
		System.out.println(buf.toString());
	}
	
	public static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
}
