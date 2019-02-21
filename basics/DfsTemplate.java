package basics;

public class DfsTemplate {

    /*
     * 递归做法，其实利用了call stack。
     * Return true if there is a path from cur to target.

    boolean DFS(Node cur, Node target, Set<Node> visited) {
        return true if cur is target;
        for (next : each neighbor of cur) {
            if (next is not in visited) {
                add next to visited;
                return true if DFS(next, target, visited) == true;
            }
        }
        return false;
    }
    */

    /*
     * Stack的做法，可以避免Stack Overflow。
    boolean DFS(int root, int target) {
        Set<Node> visited;
        Stack<Node> stack;
        add root to stack;
        while (s is not empty) {
            Node cur = the top element in stack;
            remove the cur from the stack;
            return true if cur is target;
            for (Node next : the neighbors of cur) {
                if (next is not in visited) {
                    add next to visited;
                    add next to stack;
                }
            }
        }
        return false;
    }
     */
}
