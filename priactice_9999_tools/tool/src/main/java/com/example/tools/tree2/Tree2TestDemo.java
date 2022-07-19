package com.example.tools.tree2;

import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 使用java8 方式生成tree   可用
 */
public class Tree2TestDemo {

    /**
     * list转tree方法
     */
    public static List<DepartmentVO> getDepartmentTree(List<DepartmentVO> list) {
        for (DepartmentVO l : list) {
            //每次循环找到每个节点的子节点们（f代表符合条件的集合），并挂载到当前节点下
            List<DepartmentVO> children = list.stream().filter(f -> l.getDeptId().equals(f.getParentId())).collect(Collectors.toList());
            l.setChildren(children);
        }
        //for循环结束，整棵树挂载完毕，需要找到树的根节点（此例定义parentId为0就是根节点）
        List<DepartmentVO> rootNodes = list.stream()
                .filter(f -> f.getParentId() == "0" || Objects.isNull(f.getParentId())).collect(Collectors.toList());
        return rootNodes;
    }

    /**
     *
     * @param args
     */


    /**
     * Stream分组
     *
     * 此方法主要通过Collectors.groupingBy(Menu::getParentId)方法对menus按照parentId进行分组，分组后父节点相同的都放一起了。
     * 然后再循环menus，给其设置children属性。
     * 执行完成后已经形成了多颗树，最后我们再通过filter()方法挑选出根节点的那颗树即可。

     */
//
//    public List<Menu> selectMenuTree() {
//        List<Menu> menus = menuMapper.selectList(null);
//        //操作所有菜单数据
//        Map<Long, List<Menu>> groupMap = menus.stream().collect(Collectors.groupingBy(Menu::getParentId));
//        menus.forEach(menu -> {
//            menu.setChildren(groupMap.get(menu.getMenuId()));
//        });
//        List<Menu> collect = menus.stream().filter(menu -> menu.getParentId().equals(0L)).collect(Collectors.toList());
//        return collect;
//    }



    public static void main(String[] args) {

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
        List<DepartmentVO> tree = getDepartmentTree(list);

        System.out.println(JSONUtil.toJsonStr(tree));

    }
}
