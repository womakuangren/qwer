package com.atguigu.springboot_mp.entity;

    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableId;
    import java.io.Serializable;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 
    * </p>
*
* @author lnn
* @since 2019-12-12
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class TblEmp implements Serializable {

    private static final long serialVersionUID = 1L;

            @TableId(value = "emp_id", type = IdType.AUTO)
    private Integer empId;

    private String empName;

    private String gender;

    private String email;

    private Integer dId;


}
