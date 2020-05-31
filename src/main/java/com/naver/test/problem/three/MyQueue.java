package com.naver.test.problem.three;

public class MyQueue {

    private Node node;

    private int min = 0;

    private int max = 0;

    public void push(int value){
        if(node == null){
            node = new Node();
            node.setValue(value);
        }else {
           Node pushed = new Node();
            Node firstNode = node.getFirstNode(node);
            pushed.setNext(firstNode);
            firstNode.setPre(pushed);
            pushed.setValue(value);
        }
    }

    public int pop(){
        if (node == null){
            return Integer.MIN_VALUE;
        }
        Node lastNode = node.getLastNode(node);
        Node pre = lastNode.getPre();
        pre.setNext(null);
        lastNode.setPre(null);

        if(lastNode == node){
            node = node.getPre();
        }
        return lastNode.getValue();
    }

    public int peek(){
        if(node == null){
            return 0;
        }
        Node firstNode = node.getFirstNode(node);
        int count = 1;
        while (firstNode.getNext() != null){
            firstNode = firstNode.getNext();
            count ++;
        }
        return count;
    }

    public boolean isEmpty(){
        return node == null;
    }

}

class Node{

    private Node pre;

    private Node next;

    private int value;

    public Node getFirstNode(Node node){
        if(node.getPre() == null){
            return node;
        }else{
            return getFirstNode(node.getPre());
        }
    }

    public Node getLastNode(Node node){
        if(node.getNext() == null){
            return this;
        }else{
            return getLastNode(node.getNext());
        }
    }

    public Node getPre() {
        return pre;
    }

    public void setPre(Node pre) {
        this.pre = pre;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
