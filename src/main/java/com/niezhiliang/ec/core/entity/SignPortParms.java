package com.niezhiliang.ec.core.entity;

import java.math.BigDecimal;

/**
 * 签署坐标
 */
public class SignPortParms {

    /*****pdf签署页数******/
    private int pageno;

    /***左下X****/
    private BigDecimal leftButtomX;

    /***左下Y****/
    private BigDecimal leftButtomY;

    /***右上X*****/
    private BigDecimal rightTopX;

    /***右上Y*****/
    private BigDecimal rightTopY;

    public BigDecimal getLeftButtomX() {
        return leftButtomX;
    }

    public void setLeftButtomX(BigDecimal leftButtomX) {
        this.leftButtomX = leftButtomX;
    }

    public BigDecimal getLeftButtomY() {
        return leftButtomY;
    }

    public void setLeftButtomY(BigDecimal leftButtomY) {
        this.leftButtomY = leftButtomY;
    }

    public BigDecimal getRightTopX() {
        return rightTopX;
    }

    public void setRightTopX(BigDecimal rightTopX) {
        this.rightTopX = rightTopX;
    }

    public BigDecimal getRightTopY() {
        return rightTopY;
    }

    public void setRightTopY(BigDecimal rightTopY) {
        this.rightTopY = rightTopY;
    }

    public int getPageno() {
        return pageno;
    }

    public void setPageno(int pageno) {
        this.pageno = pageno;
    }
}
