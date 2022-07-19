package com.example.tools.tree;

import cn.hutool.json.JSONUtil;
import com.example.tools.tree2.DepartmentVO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 使用
 *
 *
 */


public class ListToTreeTestDemo {




    public static void main(String[] args) {

        /**
         * TreeBuilder默认实体的父子关联字段为id和parentId, 子集合为children字段, 若满足默认条件，则可直接使用以下方法进行转化：
         *
         */

       // List<SomeEntity> tree = TreeUtil.list2Tree(list); // list转tree
       // List<SomeEntity> list = TreeUtil.tree2List(tree); // tree转list

        /**
         * 若不满足默认属性，则可自建TreeBuilder，传入自定义TreeBuilder进行转化 如某实体Org（机构），使用code与pcode做父子关联, 子机构使用childOrgs存储，则可使用以下方法：
         */

       // TreeBuilder builder = new TreeBuilder<Org>().asId(Org::getCode).asPid(Org::getPcode).setChildren(Org::setChildOrgs);
       // List<Org> tree = TreeUtil.list2Tree(list, builder); // list转tree
       // List<Org> list = TreeUtil.tree2List(tree, builder); // tree转list


        /**
         * list转tree过程中，对子集合进行排序的方法，同样在构造TreeBuilder时，通过sorted方法传入 Comparator对象即可，如上述机构Org实体，按照编码、名称排序：
         *
         */

       // TreeBuilder builder = new TreeBuilder<Org>().asId(Org::getCode).asPid(Org::getPcode).setChildren(Org::setChildOrgs)
       //         .sorted(Comparator.comparing(Org::getCode).thenComparing(Org::getName));
       // List<Org> tree = TreeUtil.list2Tree(list, builder); // list转tree
       // List<Org> list = TreeUtil.tree2List(tree, builder); // tree转list

        /**====================================================================================================== **/
     //数据准备
        //一级部门
        DepartmentVO level100 = new DepartmentVO();
        level100.setDeptId("10000");
        level100.setParentId("0");

        //二级部门
        DepartmentVO level200 = new DepartmentVO();
        level200.setDeptId("20000");
        level200.setParentId("10000");
        DepartmentVO level201 = new DepartmentVO();
        level201.setDeptId("20001");
        level201.setParentId("10000");
        DepartmentVO level202 = new DepartmentVO();
        level202.setDeptId("20002");
        level202.setParentId("10000");

        //三级部门
        DepartmentVO level300 = new DepartmentVO();
        level300.setDeptId("30000");
        level300.setParentId("20000");
        DepartmentVO level301 = new DepartmentVO();
        level301.setDeptId("30000");
        level301.setParentId("20000");
        DepartmentVO level302 = new DepartmentVO();
        level302.setDeptId("30000");
        level302.setParentId("20000");


        List<DepartmentVO> list = new ArrayList<>();
        list.add(level100);
        list.add(level200);
        list.add(level201);
        list.add(level202);
        list.add(level300);
        list.add(level301);
        list.add(level302);


        List<DepartmentVO> departmentVOS = TreeUtil.list2Tree(list);
        List<DepartmentVO> departmentVOS1 = TreeUtil.list2Tree(departmentVOS);
        System.out.println("list2Tree:"+JSONUtil.toJsonStr(departmentVOS1));


        TreeBuilder builder = new TreeBuilder<DepartmentVO>().asId(DepartmentVO::getDeptId).asPid(DepartmentVO::getParentId).setChildren(DepartmentVO::setChildren)
                .sorted(Comparator.comparing(DepartmentVO::getDeptId));

        List list1 = TreeUtil.list2Tree(list, builder);
        System.out.println("自定义:"+JSONUtil.toJsonStr(list1));

    }


}
