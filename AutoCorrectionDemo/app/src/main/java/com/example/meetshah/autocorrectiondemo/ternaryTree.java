package com.example.meetshah.autocorrectiondemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Meet Shah on 30-09-2016.
 */
public class ternaryTree {
    private TernarySearchTree tst;
    public ternaryTree(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line = null;
        Boolean flag = true;
        tst=new TernarySearchTree();
        while ((line = in.readLine()) != null) {
            tst.insert(line.trim());
        }
    }



public List<String> getString(String s){
        return tst.getCompletionsFor(s);
    }
    public ArrayList<String> tofind(String str){
        return tst.toFind(str);
    }

    public String toString(){
        return tst.toString();
    }
    public boolean search(String s){
        return tst.search(s);
    }
    public  String prefix(String s){
        return tst.longestPrefixOf(s);
    }
}
