package com.example.meetshah.autocorrectiondemo;

import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Meet Shah on 30-09-2016.
 */
class TernarySearchTree
{
    private TSTNode root;
    private ArrayList<String> al;
    public ArrayList<String> ans;

    /** Constructor **/
    public TernarySearchTree(){

        makeEmpty();
    }
    /** function to check if empty **/
    public boolean isEmpty()
    {
        return root == null;
    }
    /** function to clear **/
    public void makeEmpty()
    {
        root = null;
    }
    /** function to insert for a word **/
    public void insert(String word)
    {
        root = insert(root, word.toCharArray(), 0);
    }
    /** function to insert for a word **/
    public TSTNode insert(TSTNode r, char[] word, int ptr)
    {
        if (r == null)
            r = new TSTNode(word[ptr]);

        if (word[ptr] < r.data)
            r.left = insert(r.left, word, ptr);
        else if (word[ptr] > r.data)
            r.right = insert(r.right, word, ptr);
        else
        {
            if (ptr + 1 < word.length)
                r.middle = insert(r.middle, word, ptr + 1);
            else {
                r.isEnd = true;
                r.words.add(word.toString());
            }
        }
        return r;
    }



    /** function to search for a word **/
    public boolean search(String word)
    {
        return search(root, word.toCharArray(), 0);
    }

    public List<String> getCompletionsFor(String orig) {
        List<String> completions = null;
        String s = orig.toLowerCase();
        if (s == null || s.length() == 0) {
            throw new IllegalArgumentException();
        }
        int pos = 0;
        String ans = "";
        TSTNode node = root;
        while (node != null) {
            final char c = s.charAt(pos);
            if (c < node.data) {
                node = node.left;
            } else if (c > node.data) {
                node = node.right;
            } else {
                if (++pos == s.length()) {
                    if (!node.isEnd) {
                        node = node.middle;
                    }
                    completions = new ArrayList<String>();
                    collectCompletions(node, completions);
                    return completions;
                }
                node = node.middle;
                ans=ans+node.data;

            }
        }
        return completions;
    }
    public String longestPrefixOf(String query) {
        if (query == null || query.length() == 0) return null;
        int length = 0;
        TSTNode x = root;
        int i = 0;
        while (x != null && i < query.length()) {
            char c = query.charAt(i);
            if      (c < x.data) x = x.left;
            else if (c > x.data) x = x.right;
            else {
                i++;
                if (x.isEnd != false) length = i;
                x = x.middle;
            }
        }
        return query.substring(0, length);
    }









    /** function to search for a word **/
    private boolean search(TSTNode r, char[] word, int ptr)
    {
        if (r == null)
            return false;

        if (word[ptr] < r.data)
            return search(r.left, word, ptr);
        else if (word[ptr] > r.data)
            return search(r.right, word, ptr);
        else
        {
            if (r.isEnd && ptr == word.length - 1)
                return true;
            else if (ptr == word.length - 1)
                return false;
            else
                return search(r.middle, word, ptr + 1);
        }
    }

    private void collectCompletions(TSTNode node, List<String> completions) {
        if (node != null) {
            if (node.isEnd) {
                completions.addAll(node.words);
            }
            collectCompletions(node.middle, completions);
            collectCompletions(node.left, completions);
            collectCompletions(node.right, completions);
        }
    }


    /** function to print tree **/
    public String toString()
    {
        al = new ArrayList<String>();
        traverse(root, "");
        return "\nTernary Search Tree : "+ al;
    }
    /** function to traverse tree **/
    private void traverse(TSTNode r, String str)
    {
        if (r != null)
        {
            traverse(r.left, str);

            str = str + r.data;
            if (r.isEnd) {

                ans.add(str);
            }
            traverse(r.middle, str);
            str = str.substring(0, str.length() - 1);

            traverse(r.right, str);
        }
    }
    private void f(TSTNode r, char[] word, int ptr)
    {
        if (r == null)
            return;

        if (word[ptr] < r.data)
            f(r.left, word, ptr);
        else if (word[ptr] > r.data)
            f(r.right, word, ptr);
        else
        {
            if (r.isEnd && ptr == word.length - 1)
                traverse(r.middle,"");
            else if (ptr == word.length - 1)
                traverse(r.middle,"");
            else
                f(r.middle, word, ptr + 1);
        }
    }
    public ArrayList<String> toFind(String str)
    {
        ans = new ArrayList<String>();
        f(root, str.toCharArray(), 0);
        Collections.sort(ans, new LengthFirstComparator());


        return ans;
    }
}
