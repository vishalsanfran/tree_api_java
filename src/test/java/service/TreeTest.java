package com.hingehealth.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import org.junit.jupiter.api.Test;

public class TreeTest {

    @Test
    public void testTree() {
        Tree tree = new Tree();
        tree.addNode("pets", null);
        Node node = tree.getRoots().get(0);
        assertEquals("pets", node.getLabel());
        assertEquals(1, node.getId());
        assertThat(node.getChildren(), hasSize(0));

        tree.addNode("birds", null);
        node = tree.getRoots().get(1);
        assertEquals("birds", node.getLabel());
        assertEquals(2, node.getId());
        assertThat(node.getChildren(), hasSize(0));
        
        // pets: [dogs] , birds: []
        tree.addNode("dogs", 1L);
        node = tree.getRoots().get(0).getChildren().get(0);
        assertEquals("dogs", node.getLabel());
        assertEquals(3, node.getId());
        assertThat(node.getChildren(), hasSize(0));
        
        // pets: [dogs] , birds: [parrot]
        tree.addNode("parrot", 2L);
        node = tree.getRoots().get(1).getChildren().get(0);
        assertEquals("parrot", node.getLabel());
        assertEquals(4, node.getId());
        assertThat(node.getChildren(), hasSize(0));

        // pets: [dogs, cats] , birds: [parrot]
        tree.addNode("cats", 1L);
        node = tree.getRoots().get(0).getChildren().get(1);
        assertEquals("cats", node.getLabel());
        assertEquals(5, node.getId());
        assertThat(node.getChildren(), hasSize(0));
    }
}
