package com.example.tools.tree3;

import java.util.ArrayList;
import java.util.List;

public class TestDemo {


    public static void main(String[] args) {

        //需要获取到全量数据从数据库中
        List<ConditionDictDemo> conditionDictDtos = new ArrayList<>();
        List<ConditionDictDemo> conditionDictDemos = TreeHelper.buildTree(conditionDictDtos, ConditionDictDemo::new);

        //结果就返回树形数据

    }
}
