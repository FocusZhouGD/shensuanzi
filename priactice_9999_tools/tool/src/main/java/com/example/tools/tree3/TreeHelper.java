package com.example.tools.tree3;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 树形结构组装工具
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
        }).filter(e -> e.getSuperiorCode() == null).sorted(Comparator.naturalOrder()).collect(Collectors.toList());
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
            log.warn("buildTree---2--->不合理的---node={}", JSON.toJSONString(treeNode));
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


    @Data
    public static class Implant implements TreeNode<Implant> {
        private String id;
        private String pid;
        private String name;
        Collection<Implant> subs;
        public Implant(){}


        public Implant(String id, String pid, String name) {
            this.id = id;
            this.pid = pid;
            this.name = name;
        }


        @Override
        public String getSuperiorCode() {
            return pid;
        }

        @Override
        public void setSuperiorCode(String code) {
            this.pid=code;

        }

        @Override
        public String getCode() {
            return id;
        }

        @Override
        public void setCode(String code) {
            this.id=code;

        }

        @Override
        public Collection<Implant> getSubs() {
            return subs;
        }

        @Override
        public void setSubs(Collection<Implant> subs) {
            this.subs = subs;

        }

        @Override
        public int compareTo(Implant o) {

            return Integer.parseInt(o.id);
        }
    }

    public static void main(String[] args) {
//        List<Abs> abs = new ArrayList<>();
//        Abs a1 = new Abs("000000", null);
//        Abs a2 = new Abs("000100", "000300");
//        Abs a3 = new Abs("000200", "000100");
//        Abs a4 = new Abs("000300", "000200");
//        abs.add(a1);
//        abs.add(a2);
//        abs.add(a3);
//        abs.add(a4);
//        List<Abs> ss = buildTree(abs, Abs::new);
//        System.out.println(ss);

        //(T treeNode, Supplier<T> supplier, Map<String, T> dict)



        Map<String, Implant> dict =new HashMap<>();

        //Implant item =new Implant();

        Implant itemA =new Implant("1612327718025740288","","百齿泰");
        //
        Implant itemB =new Implant("1612329044944158720","1612327718025740288","DM Equal");
        Implant itemB1 =new Implant("1612329044944158721","1612327718025740288","DM Driving");

        Implant itemC =new Implant("1612347186378526720","1612329044944158721","7002435800S");
        Implant itemC1 =new Implant("1612347186378526721","1612329044944158721","7002435950S");
        Implant itemC2 =new Implant("1612347186378526722","1612329044944158721","7002435110S");


        List<Implant> listA =new ArrayList<>();
        listA.add(itemA);
        List<Implant> listB =new ArrayList<>();
        listB.add(itemB);
        listB.add(itemB1);
        List<Implant> listC =new ArrayList<>();
        listC.add(itemC);
        listC.add(itemC1);
        listC.add(itemC2);


        //buildTree(item,Implant::new,dict);
        listA.forEach(a-> buildTree(a,Implant::new,dict));

        listB.forEach(b-> buildTree(b,Implant::new,dict));
        listC.forEach(c-> buildTree(c,Implant::new,dict));
        System.out.println(JSONObject.toJSONString(dict.values().stream().filter(e -> StringUtils.isEmpty(e.pid)&&!StringUtils.isEmpty(e.id)).collect(Collectors.toList()), SerializerFeature.DisableCircularReferenceDetect));


    }
}
