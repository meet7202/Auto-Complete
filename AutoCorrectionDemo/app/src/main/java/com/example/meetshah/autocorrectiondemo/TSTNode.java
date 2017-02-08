package com.example.meetshah.autocorrectiondemo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Meet Shah on 30-09-2016.
 */
class TSTNode {
    char data;
    TernarySearchTree tst;
    boolean isEnd;
    Set<String> words = new HashSet<String>();

    TSTNode left, middle, right;
   public TSTNode(char data)
    {
        this.data = data;
        this.isEnd = false;
        this.left = null;
        this.middle = null;
        this.right = null;
    }
    public TSTNode()
    {
        this.isEnd = false;
        this.left = null;
        this.middle = null;
        this.right = null;
    }




}
