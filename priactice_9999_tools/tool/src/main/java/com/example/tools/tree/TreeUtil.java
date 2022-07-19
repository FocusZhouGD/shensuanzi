package com.example.tools.tree;

import java.util.List;

/**
 * 树工具类
 *
 * @description：树工具类
 * @author：polybius
 * @date：2022/1/12
 */
public class TreeUtil {

    /**
     * 列表转化树形结构
     * list转tree
     *
     * @param list 列表数据
     * @return 树数组
     */
    public static <T> List<T> list2Tree(List<T> list) {
        return new TreeBuilder<T>().list(list).build();
    }

    /**
     * 列表转化树形结构
     * list转tree
     *
     * @param list 列表数据
     * @param treeBuilder 自定义树构造器
     * @return 树数组
     */
    public static <T> List<T> list2Tree(List<T> list, TreeBuilder<T> treeBuilder) {
        return treeBuilder.list(list).build();
    }

    /**
     * 树转化成列表
     * tree转list
     *
     * @param tree 树数组
     * @return 实体list
     */
    public static <T> List<T> tree2list(List<T> tree) {
        return new TreeBuilder<T>().tree(tree).expand();
    }

    /**
     * 树转化成列表
     * tree转list
     *
     * @param tree 树数组
     * @param treeBuilder 自定义树构造器
     * @return 实体list
     */
    public static <T> List<T> tree2list(List<T> tree, TreeBuilder<T> treeBuilder) {
        return treeBuilder.tree(tree).expand();
    }

}

