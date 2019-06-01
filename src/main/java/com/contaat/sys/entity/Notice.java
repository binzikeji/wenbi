package com.contaat.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 胡胡道人
 * @since 2018-12-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_notice")
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 发布人
     */
    private String loginName;

    /**
     * 标题
     */
    private String textName;

    /**
     * 副标题
     */
    private String textSubname;

    /**
     * 公告内容
     */
    private String text;

    /**
     * 图片
     */
    private String img;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private String remarks;

    private String status;

    /**
     * 备用字段
     */
    private String string1;

    /**
     * 备用字段
     */
    private String string2;

    /**
     * 备用字段
     */
    private String string3;

    /**
     * 备用字段
     */
    private String string4;

    /**
     * 备用字段
     */
    private String string5;

    /**
     * 备用字段
     */
    private String string6;

    /**
     * 备用字段
     */
    private String string7;

    /**
     * 备用字段
     */
    private String string8;

    /**
     * 备用字段
     */
    private String string9;

    /**
     * 备用字段
     */
    private String string10;


}
