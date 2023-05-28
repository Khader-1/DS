package structures;

import java.util.ArrayList;
import java.util.List;

import models.Model;

public class BinarySearchTree<T extends Model> {

    // Represent a node of binary tree

    // Represent the root of binary tree
    public Node<T> root;
    Node<T> curr;
    Node<T> parent;

    public BinarySearchTree() {
        root = null;
    }

    // insert() will add new node to the binary search tree
    public void insert(T data) {
        // Create a new node
        Node<T> newNode = new Node<T>(data);

        // Check whether tree is empty
        if (root == null) {
            root = newNode;
            return;
        } else {
            // current node point to root of the tree
            Node<T> current = root, parent = null;

            while (true) {
                // parent keep track of the parent node of current node.
                parent = current;

                // If data is less than current's data, node will be inserted to the left of
                // tree
                if (data.compareTo(current.data) < 0) {
                    current = current.left;
                    if (current == null) {
                        parent.left = newNode;
                        return;
                    }
                }
                // If data is greater than current's data, node will be inserted to the right of
                // tree
                else {
                    current = current.right;
                    if (current == null) {
                        parent.right = newNode;
                        return;
                    }
                }
            }
        }
    }

    // inorder() will perform inorder traversal on binary search tree
    public void inorderTraversal(Node<T> node) {

        // Check whether tree is empty
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        } else {

            if (node.left != null)
                inorderTraversal(node.left);
            System.out.print(node.data + " ");
            if (node.right != null)
                inorderTraversal(node.right);

        }
    }

    public List<T> inorderTraversal() {
        List<T> list = new ArrayList<>();
        inorderTraversal(root, list);
        return list;
    }

    private void inorderTraversal(Node<T> node, List<T> list) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left, list);
        list.add(node.data);
        inorderTraversal(node.right, list);
    }

    public void preorderTraversal(Node<T> node) {

        // Check whether tree is empty
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        } else {
            System.out.print(node.data + " ");
            if (node.left != null)
                preorderTraversal(node.left);
            if (node.right != null)
                preorderTraversal(node.right);

        }
    }

    public List<T> preorderTraversal() {
        List<T> list = new ArrayList<>();
        preorderTraversal(root, list);
        return list;
    }

    private void preorderTraversal(Node<T> node, List<T> list) {
        if (node == null) {
            return;
        }
        list.add(node.data);
        preorderTraversal(node.left, list);
        preorderTraversal(node.right, list);
    }

    public void postorderTraversal(Node<T> node) {

        // Check whether tree is empty
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        } else {

            if (node.left != null)
                postorderTraversal(node.left);
            if (node.right != null)
                postorderTraversal(node.right);
            System.out.print(node.data + " ");

        }
    }

    public List<T> postorderTraversal() {
        List<T> list = new ArrayList<>();
        postorderTraversal(root, list);
        return list;
    }

    private void postorderTraversal(Node<T> node, List<T> list) {
        if (node == null) {
            return;
        }
        postorderTraversal(node.left, list);
        postorderTraversal(node.right, list);
        list.add(node.data);
    }

    Node<T> minValue(Node<T> root) {
        T minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return new Node<T>(minv);
    }

    T maxValue(Node<T> root) {
        T maxv = root.data;
        while (root.right != null) {
            maxv = root.right.data;
            root = root.right;
        }
        return maxv;
    }

    public Node<T> searchIteratively(T value) {
        curr = root;
        while (curr != null) {
            if ((T) curr.data == value)
                return curr;
            parent = curr;
            if (value.compareTo(curr.data) < 0)
                curr = curr.left;

            else
                curr = curr.right;
        }

        return curr;
    }

    public void deleteNode(T key) {
        searchIteratively(key);
        // return if the key is not found in the tree
        if (curr == null) {
            System.out.println("not exist");
        }

        // Case 1: node to be deleted has no children, i.e., it is a leaf node
        if (curr.left == null && curr.right == null) {
            // if the node to be deleted is not a root node, then set its
            // parent left/right child to null
            if (curr != root) {
                if (parent.left == curr) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
            // if the tree has only a root node, set it to null
            else {
                root = null;
            }
        }
        // Case 2: node to be deleted has two children
        else if (curr.left != null && curr.right != null) {
            // find its inorder successor node
            Node<T> successor = minValue(curr.right);

            // store successor value
            T val = successor.data;

            // recursively delete the successor. Note that the successor
            // will have at most one child (right child)
            deleteNode(successor.data);

            // copy value of the successor to the current node
            curr.data = val;
        }

        // Case 3: node to be deleted has only one child
        else {
            // choose a child node
            Node<T> child = (curr.left != null) ? curr.left : curr.right;

            // if the node to be deleted is not a root node, set its parent
            // to its child
            if (curr != root) {
                if (curr == parent.left) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            }

            // if the node to be deleted is a root node, then set the root to the child
            else {
                root = child;
            }
        }

    }
}