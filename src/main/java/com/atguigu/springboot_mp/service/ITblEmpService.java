package com.atguigu.springboot_mp.service;

import com.atguigu.springboot_mp.entity.TblEmp;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lnn
 * @since 2019-12-12
 */
public interface ITblEmpService extends IService<TblEmp> {
    List<TblEmp> getselect();
}
