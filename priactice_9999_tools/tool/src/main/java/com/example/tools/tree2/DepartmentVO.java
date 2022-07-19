package com.example.tools.tree2;

import lombok.Data;

import java.util.List;

@Data
public class DepartmentVO {
    /**
     * 当前部门id
     */
    private String deptId;

    /**
     * 父部门id
     */
    private String parentId;

    /**
     * 当前部门子部门
     */
    private List<DepartmentVO> children;

}
