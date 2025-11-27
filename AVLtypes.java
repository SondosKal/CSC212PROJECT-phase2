package csc212project11;


class BST_node<K extends Comparable<K>, T> {
	public K key;
	public T data;
	public BST_node<K , T> left, right;

	public BST_node(K key, T data) {
		this.key = key;
		this.data = data;
		left = right = null;
	}
}

public class AVLtypes<K extends Comparable<K>, T>{
	private BST_node<K, T> root, current;
	
	public AVLtypes() {
		current = root = null;
	}
    
	public BST_node<K,T> getRoot(){
		return root;
	}
       
  
	public boolean full() {
		return false;
	}

	
	public T retrieve() {
        return current.data;
    }	
 
	public boolean findKey(K key) {
            BST_node<K,T>p=root;
            while(p!=null)
            {
             if(key.compareTo(p.key)==0) //***
             {
                 current=p;
                 return true;
             }
            else if(key.compareTo(p.key)<0) 
                p=p.left;
             else
                p=p.right;
            }
		return false; 
	}

	
// Insert a new element if does not exist and return true . If k already exists , return false .
     public boolean insert(K k, T val) {
    if (findKey(k)) return false; // key exists
    root = insertAVL(root, k, val);
    return true;
}


private BST_node<K,T> insertAVL(BST_node<K,T> node, K k, T val) {
    if (node == null)
        return new BST_node<K,T>(k, val);

    if (k.compareTo(node.key)<0)
        node.left = insertAVL(node.left, k, val);
    else
        node.right = insertAVL(node.right, k, val);

    // rebalance
    return rebalance(node);
}
/////////////
    //LL ROTAION
    private BST_node<K,T> rotateRight(BST_node<K,T> y) {
        BST_node<K,T> x = y.left;
        BST_node<K,T> T2 = x.right;

        x.right = y;
        y.left = T2;
        return x;
    }
//RR ROTATION
    private BST_node<K,T> rotateLeft(BST_node<K,T> y) {
        BST_node<K,T> x = y.right;
        BST_node<K,T> T2 = x.left;

        x.left = y;
        y.right = T2;

        return x;
    }
    // RL ROTATION
    private BST_node<K,T> rotateLeftRight(BST_node<K,T> node) {
        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }
    // LR ROTATION
    private BST_node<K,T> rotateRightLeft(BST_node<K,T> node) {
        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }

  ///////
    private int height(BST_node<K,T> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    
    private int balanceFactor(BST_node<K,T> node) {
        if (node == null) return 0;
        return height(node.right) - height(node.left);
    }

    
    private BST_node<K,T> rebalance(BST_node<K,T> node) {
        int bf = balanceFactor(node);
        // ---- RIGHT HEAVY ----
        if (bf > 1) {
            if (balanceFactor(node.right) >= 0)
                return rotateLeft(node);       // RR
            else
                return rotateRightLeft(node);  // RL
        }

    
        if (bf < -1) {
            if (balanceFactor(node.left) <= 0)
                return rotateRight(node);      // LL
            else
                return rotateLeftRight(node);  // LR
        }

        return node; 
    }

	public boolean remove(K k) {
		// Search for k
		K k1 = k;
		BST_node<K, T> p = root;
		BST_node<K, T> q = null; // Parent of p
		while (p != null) {
			int res = k1.compareTo(p.key);
			if (res < 0) {
				q = p;
				p = p.left;
			} else if (res > 0) {
				q = p;
				p = p.right;
			} else { // Found the key

				// Check the three cases
				if ((p.left != null) && (p.right != null)) { // Case 3: two
																// children
					// Search for the min in the right subtree
					BST_node<K, T> min = p.right;
					q = p;
					while (min.left != null) {
						q = min;
						min = min.left;
					}
					p.key = min.key;
					p.data = min.data;
					k1 = min.key;
					p = min;
					// Now fall back to either case 1 or 2
				}

				// The subtree rooted at p will change here
				if (p.left != null) { // One child
					p = p.left;
				} else { // One or no children
					p = p.right;
				}

				if (q == null) { // No parent for p, root must change
					root = p;
				} else {
					if (k1.compareTo(q.key) < 0) {
						q.left = p;
					} else {
						q.right = p;
					}
				}
				current = root;
				return true;

			}
		}

		return false; // Not found
	}        
       
}
