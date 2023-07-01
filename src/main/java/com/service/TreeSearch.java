package com.hingehealth.demo.service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Set;
import java.util.HashSet;

public class TreeSearch {
    Set<Node> seen = ConcurrentHashMap.newKeySet();
    Tree tree;
    public TreeSearch(Tree tree) {
        this.tree = tree;
    }

    public Node searchParent(Long parent) {
        for(Node root: tree.getRoots()) {
            Node node = dfs(root, parent);
            if (null != node) return node;
        }
        return null;
    }

    // Depth First Search to search for a parent value
    private Node dfs(Node root, Long parent) {
        if(root.id == parent) return root;
        seen.add(root);
        for(Node child: root.children) {
            if(!seen.contains(child)) dfs(child, parent);
        }
        return null;
    }
}
