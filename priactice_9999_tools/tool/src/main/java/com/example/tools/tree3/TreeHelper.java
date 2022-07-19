package com.example.tools.tree3;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 树形结构组装工具,不好用
 */
@Slf4j
public abstract class TreeHelper {

    private static boolean lessThan(String code, String superiorCode){
        if(code == null){
            return false;
        }else {
            if(superiorCode == null){
                return true;
            }else {
                return code.compareTo(superiorCode) > 0;
            }
        }
    }

    /**
     * 构建树
     *
     * @param collection 各节点元素
     * @param supplier   占位元素供应
     * @param <T>        限定符
     * @return 有序的根节点列表
     */
    public static <T extends TreeNode<T>> List<T> buildTree(Collection<T> collection, Supplier<T> supplier) {
        Map<String, T> dict = new HashMap<>();
        return collection.stream().filter(e -> {
            String code = e.getCode();
            String superiorCode = e.getSuperiorCode();
            boolean check =  lessThan(code, superiorCode);
            if(!check){
                log.warn("buildTree---1--->不合理的---code={}, superCode={}", code, superiorCode);
            }
            return check;
        }).peek(treeNode -> {
            String superiorCode = treeNode.getSuperiorCode();
            String code = treeNode.getCode();
            T sup;
            if ((sup = dict.get(superiorCode)) != null) {
                sup.getSubs().add(treeNode);
            } else {
                sup = supplier.get();
                dict.put(superiorCode, sup);
                sup.setSubs(new TreeSet<>());
                sup.getSubs().add(treeNode);
            }
            T old = dict.get(code);
            if (null == old) {
                treeNode.setSubs(new TreeSet<>());
                dict.put(code, treeNode);
            } else {
                if (StringUtils.isEmpty(old.getCode())) {
                    treeNode.setSubs(old.getSubs());
                    dict.put(code, treeNode);
                }
            }
        }).filter(e -> e.getSuperiorCode() == null).collect(Collectors.toList());
    }


    /**
     * 构建树
     *
     * @param treeNode 节点元素
     * @param supplier 占位元素供应
     * @param <T>      限定符
     */
    public static <T extends TreeNode<T>> void buildTree(T treeNode, Supplier<T> supplier, Map<String, T> dict) {
        String superiorCode = treeNode.getSuperiorCode(), code = treeNode.getCode();
        boolean check =  lessThan(code, superiorCode);
        if(!check){
            log.warn("buildTree---2--->不合理的---node={}", JSONUtil.toJsonStr(treeNode));
        }else {
            T sup;
            if ((sup = dict.get(superiorCode)) != null) {
                if (null == sup.getSubs()) {
                    sup.setSubs(new LinkedList<>());
                }
                sup.getSubs().add(treeNode);
            } else {
                sup = supplier.get();
                dict.put(superiorCode, sup);
                sup.setSubs(new LinkedList<>());
                sup.getSubs().add(treeNode);
            }
            T old = dict.get(code);
            if (null == old) {
                dict.put(code, treeNode);
            } else {
                if (StringUtils.isEmpty(old.getCode())) {
                    treeNode.setSubs(old.getSubs());
                    dict.put(code, treeNode);
                }
            }
        }
    }


    public static class Abs implements TreeNode<Abs> {
        String code;
        String superCode;
        Collection<Abs> subs;

        public Abs() {
        }

        public Abs(String code, String superCode) {
            this.code = code;
            this.superCode = superCode;
        }

        @Override
        public String getCode() {
            return code;
        }

        @Override
        public void setCode(String code) {
            this.code = code;
        }

        @Override
        public String getSuperiorCode() {
            return superCode;
        }

        @Override
        public void setSuperiorCode(String superiorCode) {
            this.superCode = superiorCode;
        }

        @Override
        public Collection<Abs> getSubs() {
            return this.subs;
        }

        @Override
        public void setSubs(Collection<Abs> subs) {
            this.subs = subs;
        }

        @Override
        public int compareTo(Abs o) {
            return Integer.parseInt(o.code);
        }
    }

    public static void main(String[] args) {
        List<Abs> abs = new ArrayList<>();
        Abs a1 = new Abs("000000", null);
        Abs a2 = new Abs("000100", "000300");
        Abs a3 = new Abs("000200", "000100");
        Abs a4 = new Abs("000300", "000200");
        abs.add(a1);
        abs.add(a2);
        abs.add(a3);
        abs.add(a4);
        List<Abs> ss = buildTree(abs, Abs::new);
        System.out.println(ss);

        System.out.println(JSONUtil.toJsonStr(ss));


    }
}
