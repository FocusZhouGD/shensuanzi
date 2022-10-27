package com.example.tools.tree3;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Setter
@Getter
public class ConditionDictDemo implements TreeNode<ConditionDictDemo>,Serializable {
    private String id;
    //自己code
    private String code;
    private String label;
    private String description;
    private String abbreviation;
    //严重程度
    private Integer severityLevel;
    //上级code
    private String topCode;
    //暂时没有意义的
    private boolean checked = false;
    private Collection<ConditionDictDemo> subClass=new ArrayList<>();

    /**
     * 获取上级代码
     *
     * @return
     */
    @Override
    @JsonIgnore
    public String getSuperiorCode() {
        return topCode;
    }

    /**
     * 设置上级代码
     *
     * @param code
     */
    @Override
    public void setSuperiorCode(String code) {
        this.topCode = code;
    }

    /**
     * 下级元素
     *
     * @return
     */
    @Override
    @JsonIgnore
    public Collection<ConditionDictDemo> getSubs() {
        return this.subClass;
    }

    /**
     * 设置下级元素
     *
     * @param subs
     */
    @Override
    public void setSubs(Collection<ConditionDictDemo> subs) {
        this.subClass = subs;
    }


    @Override
    public int compareTo(ConditionDictDemo o) {
        return this.code.compareTo(o.code);
    }
}
