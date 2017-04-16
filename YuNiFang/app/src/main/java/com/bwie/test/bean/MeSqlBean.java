package com.bwie.test.bean;

/**
 * 类的用途：
 *
 * @author 赵永生
 * @time 2017/4/12 18:51
 */
public class MeSqlBean {
    private int id;
    private String zhanghao;
    private String mima;

    public MeSqlBean() {
    }

    public String getZhanghao() {
        return zhanghao;
    }

    public void setZhanghao(String zhanghao) {
        this.zhanghao = zhanghao;
    }

    public String getMima() {
        return mima;
    }

    public void setMima(String mima) {
        this.mima = mima;
    }

    public MeSqlBean(String zhanghao, String mima) {
        this.zhanghao = zhanghao;
        this.mima = mima;
    }
}
