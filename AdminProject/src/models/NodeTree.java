package models;

import java.util.ArrayList;

public class NodeTree<T> {

    private T data;
    private ArrayList<NodeTree<T>> children;

    public NodeTree(T data) {
        this.data = data;
        children = new ArrayList<>();
    }

    public ArrayList<NodeTree<T>> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<NodeTree<T>> children) {
        this.children = children;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void addChild(NodeTree<T> child){
        children.add(child);
    }
    
}
