package com.Test.gen02;

/**
 * @Author 胡字蒙.
 * @Date 2018/12/12   14:43
 *
 * 泛型类：声明时使用泛型
 * T Type 表示类型
 * K V 分表代表键值中的Key Value
 * E 代表Element
 * ？ 代表不确定类型
 *
 * 1.泛型只能使用引用类型，不能基本类型
 * 2.泛型声明时不能使用 静态属性 |静态方法上
 */
public class Student<T> {
    private T javaScore;

    public T getJavaScore() {
        return javaScore;
    }

    public void setJavaScore(T javaScore) {
        this.javaScore = javaScore;
    }
    public static void main(String [] args){
    }
}
