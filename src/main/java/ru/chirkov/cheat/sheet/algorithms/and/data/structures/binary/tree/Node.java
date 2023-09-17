package ru.chirkov.cheat.sheet.algorithms.and.data.structures.binary.tree;

import lombok.Data;

@Data
public class Node<T> {

    public Node() {}

    private T data;
    private Node<T> leftNode;
    private Node<T> rightNode;



}
