package com.niezhiliang.ec.core.entity;

import java.math.BigDecimal;

/**
 * 签署坐标
 */
public class SignPortParms {

    /***左上****/
    private BigDecimal leftTop;

    /***左下****/
    private BigDecimal leftButtom;

    /***右上*****/
    private BigDecimal rightTop;

    /***右下*****/
    private BigDecimal rightButtom;

    public BigDecimal getLeftTop() {
        return leftTop;
    }

    public void setLeftTop(BigDecimal leftTop) {
        this.leftTop = leftTop;
    }

    public BigDecimal getLeftButtom() {
        return leftButtom;
    }

    public void setLeftButtom(BigDecimal leftButtom) {
        this.leftButtom = leftButtom;
    }

    public BigDecimal getRightTop() {
        return rightTop;
    }

    public void setRightTop(BigDecimal rightTop) {
        this.rightTop = rightTop;
    }

    public BigDecimal getRightButtom() {
        return rightButtom;
    }

    public void setRightButtom(BigDecimal rightButtom) {
        this.rightButtom = rightButtom;
    }
}
