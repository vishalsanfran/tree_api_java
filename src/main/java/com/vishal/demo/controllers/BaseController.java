package com.vishal.demo.controllers;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vishal.demo.service.Node;
import com.vishal.demo.service.InputNode;
import com.vishal.demo.service.Tree;
import java.util.List;

@RestController
public class BaseController {

    Tree tree = new Tree();

    @GetMapping("/")
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("/api/tree")
    public List<Node> getTree() {
        return tree.getRoots();
    }

    @PostMapping("/api/tree")
    public Node addNodeToTree(@RequestBody InputNode node) {
        try {
            return tree.addNode(node.getLabel(), node.getParent());
        } catch(IllegalArgumentException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "parent " + node.getParent() + " not found");
        }
    }
}