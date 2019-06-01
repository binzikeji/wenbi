package com.contaat.sys.entity;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 笔记表
 * </p>
 *
 * @author 胡胡道人
 * @since 2018-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class WrText implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(type= IdType.UUID)
    private String id;

    private String kehuid;

    private String textName;

    private String textSubname;

    private String text;

    private String img;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 修改时间
     */
    private Date updateDate;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 状态
     */
    private String status;

    private String string1;

    private String string2;

    private String string3;

    private String string4;

    private String string5;

    private String string6;

    private String string7;

    private String string8;

    private String string9;

    private String string10;


}