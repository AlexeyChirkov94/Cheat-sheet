package ru.chirkov.cheat.sheet.algorithms.and.data.structures.binary.tree;


import java.util.Comparator;
import java.util.function.Function;

public class MyTree<T, K> {

    private final Node<T> root;
    private final Comparator<K> comparator;
    private final Function<T, K> getKey;

    public MyTree(Comparator<K> comparator, Function<T, K> getKey) {
        this.comparator = comparator;
        this.getKey = getKey;
        this.root = new Node<>();
    }

    public void insert(T data){
        insetValue(root, data);
    }

    public T find(K id){
        return findValue(root, id);
    }

    private void insetValue(Node<T> node, T data){
        if (node.getData() == null){
            node.setData(data);
            node.setLeftNode(new Node<>());
            node.setRightNode(new Node<>());
        } else {
            if (comparator.compare(getKey.apply(data), getKey.apply(node.getData())) > 0 ||
                    comparator.compare(getKey.apply(data), getKey.apply(node.getData())) == 0) {
                Node<T> right = node.getRightNode();
                insetValue(right, data);
            } else {
                Node<T> left = node.getLeftNode();
                insetValue(left, data);
            }
        }
    }

    private T findValue(Node<T> node, K key){
        if (node.getData() == null) return null;
        else {
            if (comparator.compare(key, getKey.apply(node.getData())) == 0){
                return node.getData();
            } else if (comparator.compare(key, getKey.apply(node.getData())) > 0){
                Node<T> right = node.getRightNode();
                return findValue(right, key);
            } else {
                Node<T> left = node.getLeftNode();
                return findValue(left, key);
            }
        }
    }






}
