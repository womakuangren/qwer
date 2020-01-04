package com.atguigu.springboot_mp.service.impl;

import com.atguigu.springboot_mp.entity.TblEmp;
import com.atguigu.springboot_mp.mapper.TblEmpMapper;
import com.atguigu.springboot_mp.service.ITblEmpService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.swing.*;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lnn
 * @since 2019-12-12
 */
@Service
public class TblEmpServiceImpl extends ServiceImpl<TblEmpMapper, TblEmp> implements ITblEmpService {

    @Resource
    private TblEmpMapper tblEmpMapper;
    @Override
    public List<TblEmp> getselect() {

        return tblEmpMapper.selectList(new QueryWrapper<TblEmp>().like("emp_id","4"));
    }
}
