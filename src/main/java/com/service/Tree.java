package com.hingehealth.demo.service;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

// Thread safe in memory tree
// Note: This tree can have more than 1 root.
public class Tree {
    List<Node> roots = new CopyOnWriteArrayList<>();
    AtomicLong counter =  new AtomicLong();

    public Node addNode(String label, Long parent) {
        Node node;
        if(parent == null) {
            node = new Node(counter.incrementAndGet(), label);
            roots.add(node);
        } else {
            Node pNode = new TreeSearch(this).searchParent(parent);
            if (pNode == null) throw new IllegalArgumentException();
            node = new Node(counter.incrementAndGet(), label);
            pNode.addChild(node);
        }
        return node;
    }

    public List<Node> getRoots() {return this.roots;}
}
