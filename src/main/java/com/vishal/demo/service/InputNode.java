package com.vishal.demo.service;

public class InputNode {
    Long parent;
    String label;
    public InputNode(Long parent, String label) {
        this.parent = parent;
        this.label = label;
    }
    public Long getParent() {return this.parent;}
    public String getLabel() {return this.label;}
}
