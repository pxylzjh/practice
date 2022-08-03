package leecode.day1;

/**
 * @author puxy
 * @version 1.0
 * @description https://leetcode-cn.com/problems/add-two-numbers/
 * @date 2022/4/6 18:36
 */
public class T2 {

    /**
     * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
     *
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     */
    public static void main(String[] args) {

        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode res = addTwoNumbers(new ListNode(), l1, l2);
        System.out.println(res);

    }

    /**
     * 递归
     *
     * @param node
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode node, ListNode l1, ListNode l2) {
        // l1 l2 都等于 null,说明全部计算完毕, 如果 node值 > 0 说明 还有进位,否则 将node置为 null（因为 node 的 val 默认为 0）
        if (l1 == null && l2 == null) {
            if (node.val > 0) {
                return node;
            }
            return null;
        }
        // 如果 连个链表长度不一致时,短的链表 补 0
        if (l1 == null) {
            l1 = new ListNode(0);
        }
        if (l2 == null) {
            l2 = new ListNode(0);
        }
        int temp = node.val;
        // 当前节点 存储 余数  如：l1 + l2 = 14, 当前节点 val = 4
        node.val = (temp + l1.val + l2.val) % 10;
        // 将 进位的值 存储到下个节点 传入递归 进行累加
        ListNode next = new ListNode((temp + l1.val + l2.val) / 10);
        node.next = addTwoNumbers(next, l1.next, l2.next);
        return node;
    }

}


class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
