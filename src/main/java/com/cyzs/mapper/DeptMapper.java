package com.cyzs.mapper;

import entity.Department;

/**
 * @author xiaoH
 * @create 2019-06-12-13:24
 */
public interface DeptMapper {
    Department getDeptById(Integer id);
    Department getDeptByIdPlus(Integer id);
    Department getDeptByIdStep(Integer id);
}
