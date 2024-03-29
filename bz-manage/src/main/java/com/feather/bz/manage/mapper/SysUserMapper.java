package com.feather.bz.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feather.bz.manage.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表  Mapper 接口
 * </p>
 *
 * @author feather
 * @since 2022-11-30
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    void  updateUserBatch(@Param(("userList")) List<SysUser> userList);

}
