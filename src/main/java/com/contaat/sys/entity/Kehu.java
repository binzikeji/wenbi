package com.contaat.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 客户表
 * </p>
 *
 * @author 胡胡道人
 * @since 2018-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_kehu")
public class Kehu implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 登录账号
     */
    private String loginCode;

    /**
     * 用户昵称
     */
    private String loginName;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 姓名
     */
    private String idName;

    /**
     * 身份证
     */
    private String idNumber;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private String age;

    /**
     * 角色
     */
    private String role;

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

//    public String getPassword() {
//        if(this.password == null){
//            return this.password;
//        }
//        String pa =JM(KL(this.password));
//        return pa;
//        //return this.password;
//    }
//    public Kehu setPassword(String password) {
//        password = MD5(password);
//        this.password = password;
//        return this;
//    }
//
//    public static String MD5(String inStr) {
//        MessageDigest md5 = null;
//        try {
//            md5 = MessageDigest.getInstance("MD5");
//        } catch (Exception e) {
//            System.out.println(e.toString());
//            e.printStackTrace();
//            return "";
//        }
//        char[] charArray = inStr.toCharArray();
//        byte[] byteArray = new byte[charArray.length];
//
//        for (int i = 0; i < charArray.length; i++)
//            byteArray[i] = (byte) charArray[i];
//
//        byte[] md5Bytes = md5.digest(byteArray);
//
//        StringBuffer hexValue = new StringBuffer();
//
//        for (int i = 0; i < md5Bytes.length; i++) {
//            int val = ((int) md5Bytes[i]) & 0xff;
//            if (val < 16)
//                hexValue.append("0");
//            hexValue.append(Integer.toHexString(val));
//        }
//
//        return hexValue.toString();
//    }
//
//    // 可逆的加密算法
//    public static String KL(String inStr) {
//        // String s = new String(inStr);
//        char[] a = inStr.toCharArray();
//        for (int i = 0; i < a.length; i++) {
//            a[i] = (char) (a[i] ^ 't');
//        }
//        String s = new String(a);
//        return s;
//    }
//
//    // 加密后解密
//    public static String JM(String inStr) {
//        char[] a = inStr.toCharArray();
//        for (int i = 0; i < a.length; i++) {
//            a[i] = (char) (a[i] ^ 't');
//        }
//        String k = new String(a);
//        return k;
//    }

}
