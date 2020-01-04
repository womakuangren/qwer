package com.atguigu.springboot_mp.controller;


import com.atguigu.springboot_mp.entity.TblEmp;
import com.atguigu.springboot_mp.mapper.TblEmpMapper;
import com.atguigu.springboot_mp.service.ITblEmpService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.org.apache.xerces.internal.util.EntityResolverWrapper;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lnn
 * @since 2019-12-12
 */
@RestController
@RequestMapping("/springboot_mp")
public class TblEmpController {
    @Resource
    private TblEmpMapper tblEmpMapper;
    @Resource
    private ITblEmpService iTblEmpService;
    @RequestMapping("/hello")
    public List<TblEmp>  asas(){
        List<TblEmp> tblEmpList= tblEmpMapper.selectList(null);
        //List<TblEmp> atblEmpList= tblEmpMapper.selectList(new EntityW);

        return tblEmpList;
    }

    @RequestMapping("/test1")
    public List<TblEmp> asdf(){

        return iTblEmpService.getselect();
    }

}
