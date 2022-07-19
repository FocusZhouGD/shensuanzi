package com.example.tools.tree3;

import java.util.Collection;

public interface TreeNode<T> extends Comparable<T>{

    /**
     * 获取上级代码
     * @return
     */
    String getSuperiorCode();

    /**
     * 设置上级代码
     * @param code
     */
    void setSuperiorCode(String code);

    /**
     * 获取自身的code
     * @return
     */
    String getCode();


    /**
     * 设置自身的code
     * @param code
     */
    void setCode(String code);

    /**
     * 下级元素
     * @return
     */
    Collection<T> getSubs();


    /**
     * 设置下级元素
     */
    void setSubs(Collection<T> subs);



}
