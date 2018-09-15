//package Array;
//
///**
// * Created by DrunkPiano on 13/05/2017.
// */
//
//public class VerifyPreorderSerializationofaBinaryTree {
//	//	int count = 0 ;
////	public boolean isValidSerialization(String preorder) {
////		String[] s = preorder.split(",");
////		Queue<String> queue = new LinkedList<>(Arrays.asList(s));
////		int size = queue.size();
////		TreeNode node = buildTree(queue);
////		return count == size;
////	}
////
////	private TreeNode buildTree(Queue<String> queue) {
////		String s = queue.poll();
////		if (s == null) return null;
////		if (s.equals("#")) {
////			count ++ ;
////			return null;
////		}
////		TreeNode node = new TreeNode(Integer.valueOf(s));
////		count ++ ;
////		node.left = buildTree(queue);
////		node.right = buildTree(queue);
////		return node;
////	}
//	public boolean isValidSerialization(String preorder) {
//		int diff = 1;
//		String[] s = preorder.split(",");
//		for (String i : s) {
//			if (diff-- < 0) return false;
//			if (!i.equals("#")) {
//				diff += 2;
//			}
//		}
//		return diff == 0;
//
//		String [] s = preorder.split(",");
//		int leaves = 0 , noneLeaves = 0 ;
//		for (String i : s){
//			if (i.equals("#")){
//				noneLeaves ++;
//			}else {
//				leaves ++ ;
//			}
//		}
//		return leaves + 1 == noneLeaves;
//	}
//
//	public static void main(String args[]) {
//		new VerifyPreorderSerializationofaBinaryTree().isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#");
//	}
//}
