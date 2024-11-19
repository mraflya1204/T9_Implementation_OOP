package predictive.treeimpl;

import java.util.List;

public class NodeTest {
	public static void main (String[] args) {
		TreeNode root = new TreeNode();
		root.insert("ap");
		root.insert("ar");
		root.insert("ara");
		
		List<String> dongo = root.signatureToWords("272");
		
		if (!dongo.isEmpty()) {
			for (String a : dongo) {
				System.out.println(a);
			}
		}
		else {
			System.out.println("Empty");
		}
		
	}
}
