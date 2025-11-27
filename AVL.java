package csc212project11;


	
	class BSTNode<T> {
		public int key;
		public T data;
		public BSTNode<T> left, right;
	       
		public BSTNode(int key, T data) {
			this.key = key;
			this.data = data;
			left = right = null;
		}
	}
	
	public class AVL<T> {

		private BSTNode<T> root, current;
		
		public AVL() {
			current = root = null;
		}	
		
		public boolean empty() {
			return root == null;
		}

		public boolean full() {
			return false;
		}

		public T retrieve() {
			return current.data;
		}

		
	   public boolean findKey(int k) {

			BSTNode<T> p = root;
			while (p != null) {
	                   
				current = p;
				if (k==p.key) {
					return true;
				} else if (k< p.key) {
					p = p.left;
				} else {
					p = p.right;
				}
			}
			return false;
		}

	  //INSERT
	   public boolean insert(int k, T val) {
		    if (findKey(k)) return false; // key exists
		    root = insertAVL(root, k, val);
		    return true;
		}


		private BSTNode<T> insertAVL(BSTNode<T> node, int k, T val) {
		    if (node == null)
		        return new BSTNode<T>(k, val);

		    if (k < node.key)
		        node.left = insertAVL(node.left, k, val);
		    else
		        node.right = insertAVL(node.right, k, val);

		    // rebalance
		    return rebalance(node);
		}

////////////////////////////////////	    


	    //LL ROTATION
	    private BSTNode<T> rotateRight(BSTNode<T> y) {
	        BSTNode<T> x = y.left;
	        BSTNode<T> T2 = x.right;

	        x.right = y;
	        y.left = T2;
	        return x;
	    }
	//RR ROTATION
	    private BSTNode<T> rotateLeft(BSTNode<T> y) {
	        BSTNode<T> x = y.right;
	        BSTNode<T> T2 = x.left;

	        x.left = y;
	        y.right = T2;

	        return x;
	    }
	    //RL ROTATION	
	    private BSTNode<T> rotateLeftRight(BSTNode<T> node) {
	        node.left = rotateLeft(node.left);
	        return rotateRight(node);
	    }
	    //LR ROTATION
	    private BSTNode<T> rotateRightLeft(BSTNode<T> node) {
	        node.right = rotateRight(node.right);
	        return rotateLeft(node);
	    }

	    
	    //////////
	    private int height(BSTNode<T> node) {
	        if (node == null) return 0;
	        return 1 + Math.max(height(node.left), height(node.right));
	    }

	    
	    private int balanceFactor(BSTNode<T> node) {
	        if (node == null) return 0;
	        return height(node.right) - height(node.left);
	    }

	    
	    private BSTNode<T> rebalance(BSTNode<T> node) {
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

	   /////////////////
	    public void inOrder() {
	        if (root == null)
	            System.out.println("empty tree");
	        else
	            inOrder(root);
	    }

	    private void inOrder(BSTNode<T> p) {
	        if (p == null) return;
	        inOrder(p.left);
	        System.out.println("key= " + p.key + " , data=" + p.data);
	        inOrder(p.right);
	    }

	    public BSTNode<T> getRoot() {
	        return root;
	    }

		

	}
