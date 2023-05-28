package structures;

import models.Model;

public class Node<T extends Model>{
    T data;
    Node<T> left;
    Node<T> right;

    public Node(T data) {
        // Assign data to the new node, set left and right children to null
        this.data = data;
        this.left = null;
        this.right = null;
    }
}