package com.contaat.sys.mapper;

import com.contaat.sys.entity.WrText;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 笔记表 Mapper 接口
 * </p>
 *
 * @author 胡胡道人
 * @since 2018-12-17
 */
public interface WrTextMapper extends BaseMapper<WrText> {
    /**
     * 修改数据
     * @param wrText
     */
    public void WrTextUpdata(WrText wrText);
}
