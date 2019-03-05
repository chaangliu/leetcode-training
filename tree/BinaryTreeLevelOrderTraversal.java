package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by DrunkPiano on 2017/4/2.
 */

public class BinaryTreeLevelOrderTraversal {
//    public List<List<Integer>> levelOrder(TreeNode root) {
//        List<List<Integer>> res = new ArrayList<>();
//        if (root == null) return res;
//        LinkedList<TreeNode> queue = new LinkedList<>();
//        queue.add(root);
//
//        int curNum = 1;
//        int nextNum = 0;
//        List<Integer> cell = new ArrayList<>();
//        while (!queue.isEmpty()) {
//            TreeNode temp = queue.poll();
//            curNum--;
//            cell.add(temp.val);
//
//            if (temp.left != null) {
//                queue.add(temp.left);
//                nextNum++;
//            }
//            if (temp.right != null) {
//                queue.add(temp.right);
//                nextNum++;
//            }
//            if (curNum == 0) {
//                res.add(cell);
//                curNum = nextNum;
//                ndefxtNum = 0;
//
//                cell = new ArrayList<>();
//            }
//        }
//        return res;
//    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        dfs(res, root, 0);
        return res;
    }

//	private void dfs(List<List<Integer>> res, TreeNode node, int level) {
//		if (node == null) return;
//		if (level >= res.size()) {
//			res.add(new ArrayList<Integer>());
//		}
//		res.get(level).add(node.val);
//		dfs(res, node.left, level + 1);
//		dfs(res, node.right, level + 1);
//	}

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        List<List<Integer>> nodes = new ArrayList<>();
        if (root == null) return res;
        dfs(nodes, root, 0);
        for (int i = 0; i < nodes.size(); i++) {
            double total = 0;
            for (int j = 0; j < nodes.get(i).size(); j++) {
                total += nodes.get(i).get(j);
            }
            res.add(total / nodes.get(i).size());
        }
        return res;
    }

    private void dfs(List<List<Integer>> res, TreeNode node, int level) {
        if (node == null) return;
        if (level >= res.size()) {
            res.add(new ArrayList<Integer>());
        }
        res.get(level).add(node.val);
        dfs(res, node.left, level + 1);
        dfs(res, node.right, level + 1);
    }


//	public TreeNode addOneRow(TreeNode t, int v, int d) {
//		if (d == 1) {
//			TreeNode n = new TreeNode(v);
//			n.left = t;
//			return n;
//		}
//		Queue<TreeNode> queue = new LinkedList<>();
//		queue.add(t);
//		int depth = 1;
//		while (depth < d - 1) {
//			Queue<TreeNode> temp = new LinkedList<>();
//			while (!queue.isEmpty()) {
//				TreeNode node = queue.remove();
//				if (node.left != null) temp.add(node.left);
//				if (node.right != null) temp.add(node.right);
//			}
//			queue = temp;
//			depth++;
//		}
//		while (!queue.isEmpty()) {
//			TreeNode node = queue.remove();
//			TreeNode temp = node.left;
//			node.left = new TreeNode(v);
//			node.left.left = temp;
//			temp = node.right;
//			node.right = new TreeNode(v);
//			node.right.right = temp;
//		}
//		return t;
//	}


    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode newNode = new TreeNode(v);
            newNode.left = root;
            return newNode;
        }
        bfs(root, v, d);
        return root;
    }

    private void bfs(TreeNode root, int v, int d) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        //number of nodes of current level
        int curNum = 1;
        //number of nodes of next level
        int nextNum = 0;
        int level = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            curNum--;
            if (node.left != null) {
                queue.add(node.left);
                nextNum++;
            }
            if (node.right != null) {
                queue.add(node.right);
                nextNum++;
            }
            if (level == d - 1) {
                TreeNode temp = node.left;
                node.left = new TreeNode(v);
                node.left.left = temp;
                temp = node.right;
                node.right = new TreeNode(v);
                node.right.right = temp;
            }
            if (curNum == 0) {
                curNum = nextNum;
                nextNum = 0;
                level++;
            }
            if (level == d) {
                break;
            }
        }
    }

    TreeNode left;
    TreeNode right;
    int count = 0;

    private void dfs(TreeNode root, int level, int v, int d) {
        if (root == null) return;
        if (level == d - 1) {
            left = root.left;
            right = root.right;
            root.left = new TreeNode(v);
            root.right = new TreeNode(v);
        }
        if (level == d) {
            count++;
            if (count == 1) {
                root.left = left;
            }
            if (count == 2) {
                root.right = right;
                count = 0;
            }
            return;
        }
        dfs(root.left, level + 1, v, d);
        dfs(root.right, level + 1, v, d);
    }

    private List<List<Integer>> bfsTraningJuly15th(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int curNum = 1;
        int nextNum = 0;
        while (!queue.isEmpty()) {
            List<Integer> item = new ArrayList<>();
            for (int i = 0; i < curNum; i++) {
                TreeNode node = queue.poll();
                item.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                    nextNum++;
                }
                if (node.right != null) {
                    queue.add(node.right);
                    nextNum++;
                }
            }
            curNum = nextNum;
            nextNum = 0;
            res.add(item);
        }
        return res;
    }

    //会自底向上插入node
    private List<List<Integer>> dfsTraningJuly15th(TreeNode root, int level, List<List<Integer>> res) {
        if (root == null) {
            return res;
        }
        if (level >= res.size()) {
            res.add(new ArrayList<Integer>());
        }
        res.get(level).add(root.val);
        dfsTraningJuly15th(root.left, level + 1, res);
        dfsTraningJuly15th(root.right, level + 1, res);
        return res;
    }

    public static void main(String args[]) {
        BinaryTreeLevelOrderTraversal instance = new BinaryTreeLevelOrderTraversal();
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
//		root.left.left = new TreeNode(4);
//		root.left.right = new TreeNode(2);
//		root.left.left.right = new TreeNode(1);

//		instance.bfsTraningJuly15th(root);
		List<List<Integer>> res = instance.dfsTraningJuly15th(root, 0, new ArrayList<List<Integer>>());

//        instance.findTarget(root, 4);

    }
//
//	private int minDepth(TreeNode root) {
//		if (root == null) return 0;
//		if (root.left == null)
//			return minDepth(root.right) + 1;
//		if (root.right == null)
//			return minDepth(root.left) + 1;
//		int min = Math.min(minDepth(root.left), minDepth(root.right));
//		return min + 1;
//	}
//
//	public int countNodes2(TreeNode root) {
//		int h = height(root);
//		if (h == 0) return 0;
//		if (height(root.right) == h - 1) {
//			traverse(root.right);
//			return count + 1 + (int) Math.pow(2, h - 1) - 1;
//		} else {
//			traverse(root.left);
//			return count + 1 + (int) Math.pow(2, h - 2) - 1;
//		}
//	}
//
//	int count = 0;
//
//	private void traverse(TreeNode node) {
//		if (node == null) {
//			return;
//		}
//		count++;
//		traverse(node.left);
//		traverse(node.right);
//	}
//
//	private int height(TreeNode node) {
//		if (node == null) {
//			return 0;
//		}
//		return 1 + height(node.left);
//	}

    private int height(TreeNode node) {
        return node == null ? -1 : height(node.left) + 1;
    }

    public int countNodes(TreeNode root) {
        int h = height(root);
        int cnt = 0;
        while (root != null) {
            if (height(root.right) == h - 1) {
                cnt += 1 << (h - 1);
                root = root.right;
            } else {
                cnt += 1 << (h - 2);
                root = root.left;
            }
            h--;
        }
        return cnt;
    }

    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        preOrder(root, list);

        int i = 0, j = list.size() - 1;
        while (i < j) {
            if (list.get(i) + list.get(j) == k) {
                return true;
            }
            if (list.get(i) + list.get(j) < k) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }

    private void preOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        preOrder(root.left, list);
        list.add(root.val);
        preOrder(root.right, list);
    }


    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> indice = new LinkedList<>();
        queue.add(root);
        indice.add(0);
        int max = 1;
        int leftMost = 0, rightMost = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                int index = indice.poll();
                if (i == 0) leftMost = index;
                if (i == size - 1) rightMost = index;
                if (node.left != null) {
                    queue.add(node.left);
                    indice.add(index * 2);
                }
                if (node.right != null) {
                    queue.add(node.right);
                    indice.add(index * 2 + 1);
                }
            }
            max = Math.max(max, rightMost - leftMost + 1);
        }
        return max;
    }


}
