import com.github.javaparser.*;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


import java.io.FileInputStream;

public class main {
    public static void main(String args[]) throws Exception{
        FileInputStream in1 = new FileInputStream("/Users/apple/Downloads/test1/ASTTest/src/main/java/test.java");
        FileInputStream in2 = new FileInputStream("/Users/apple/Downloads/test1/ASTTest/src/main/java/A.java");
        CompilationUnit cu1 = JavaParser.parse(in1);
        CompilationUnit cu2 = JavaParser.parse(in2);
        //String src = "hello";
        //String tar = "hello";
        System.out.print(cu1.toString());
        System.out.print("相似度为" + similarity(cu1.toString(), cu2.toString()));
    }


    public static int ld(String s, String t) {
        int dis[][];
        int sLen = s.length();
        int tLen = t.length();
        int si;
        int ti;
        char ch1;
        char ch2;
        int cost;
        if(sLen == 0) {
            return tLen;
        }
        if(tLen == 0) {
            return sLen;
        }
        dis = new int[sLen+1][tLen+1];
        for(si=0; si<=sLen; si++) {
            dis[si][0] = si;
        }
        for(ti=0; ti<=tLen; ti++) {
            dis[0][ti] = ti;
        }
        for(si=1; si<=sLen; si++) {
            ch1 = s.charAt(si-1);
            for(ti=1; ti<=tLen; ti++) {
                ch2 = t.charAt(ti-1);
                if(ch1 == ch2) {
                    cost = 0;
                } else {
                    cost = 1;
                }
                dis[si][ti] = Math.min(Math.min(dis[si-1][ti]+1, dis[si][ti-1]+1),dis[si-1][ti-1]+cost);
            }
        }
        return dis[sLen][tLen];
    }

    public static double similarity(String src, String tar) {
        int ld = ld(src, tar);
        return 1 - (double) ld / Math.max(src.length(), tar.length());
    }

}
