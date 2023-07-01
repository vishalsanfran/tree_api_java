package com.hingehealth.demo.service;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

// Thread safe in memory tree node
@JsonSerialize(using = NodeSerializer.class)
public class Node {
    Long id;
    String label;
    List<Node> children = new CopyOnWriteArrayList<>();

    public Node(Long id, String label) {
        this.id = id;
        this.label = label;
    }

    public Long getId() {return this.id;}
    public String getLabel() {return this.label;}
    public List<Node> getChildren() {return this.children;}
    public boolean addChild(Node child) {return this.children.add(child);}
}
