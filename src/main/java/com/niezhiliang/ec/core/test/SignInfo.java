package com.niezhiliang.ec.core.test;

import com.itextpdf.text.pdf.PdfSignatureAppearance;

import java.math.BigDecimal;

/**
 * Created by hxy on 2018/6/6.
 * E-mail:hxyHelloWorld@163.com
 * github:https://github.com/haoxiaoyong1014
 */
public class SignInfo {

    private String reason; //理由

    private String location;//位置

    private String pdfUrl;

    private String ksUrl;

    private String imgUrl;

    private String positionName;//签署位置的名称

    private String alias;      //别名

    private BigDecimal firstX;

    private BigDecimal firstY;

    private BigDecimal secrondX;

    private BigDecimal secrondY;

    private short num;

    private String oldPdfPath;//未签署的pdf地址

    private String downloadFilePath;

    private String downloadImagePath;

    private int certificationLevel = 0; //批准签章

    private PdfSignatureAppearance.RenderingMode renderingMode;//表现形式：仅描述，仅图片，图片和描述，签章者和描述

    public int getCertificationLevel() {
        return certificationLevel;
    }

    public void setCertificationLevel(int certificationLevel) {
        this.certificationLevel = certificationLevel;
    }

    public PdfSignatureAppearance.RenderingMode getRenderingMode() {
        return renderingMode;
    }

    public void setRenderingMode(PdfSignatureAppearance.RenderingMode renderingMode) {
        this.renderingMode = renderingMode;
    }

    public String getReason() {
        return "this is reason";
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getLocation() {
        return "this is location";
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDownloadImagePath() {
        return downloadImagePath;
    }

    public void setDownloadImagePath(String downloadImagePath) {
        this.downloadImagePath = downloadImagePath;
    }

    public String getDownloadFilePath() {
        return downloadFilePath;
    }

    public void setDownloadFilePath(String downloadFilePath) {
        this.downloadFilePath = downloadFilePath;
    }

    public String getOldPdfPath() {
        return oldPdfPath;
    }

    public void setOldPdfPath(String oldPdfPath) {
        this.oldPdfPath = oldPdfPath;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public String getKsUrl() {
        return ksUrl;
    }

    public void setKsUrl(String ksUrl) {
        this.ksUrl = ksUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public BigDecimal getFirstX() {
        return firstX;
    }

    public void setFirstX(BigDecimal firstX) {
        this.firstX = firstX;
    }

    public BigDecimal getFirstY() {
        return firstY;
    }

    public void setFirstY(BigDecimal firstY) {
        this.firstY = firstY;
    }

    public BigDecimal getSecrondX() {
        return secrondX;
    }

    public void setSecrondX(BigDecimal secrondX) {
        this.secrondX = secrondX;
    }

    public BigDecimal getSecrondY() {
        return secrondY;
    }

    public void setSecrondY(BigDecimal secrondY) {
        this.secrondY = secrondY;
    }

    public short getNum() {
        return num;
    }

    public void setNum(short num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "SignInfo{" +
                "reason='" + reason + '\'' +
                ", location='" + location + '\'' +
                ", pdfUrl='" + pdfUrl + '\'' +
                ", ksUrl='" + ksUrl + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", positionName='" + positionName + '\'' +
                ", alias='" + alias + '\'' +
                ", firstX=" + firstX +
                ", firstY=" + firstY +
                ", secrondX=" + secrondX +
                ", secrondY=" + secrondY +
                ", num=" + num +
                ", oldPdfPath='" + oldPdfPath + '\'' +
                ", downloadFilePath='" + downloadFilePath + '\'' +
                ", downloadImagePath='" + downloadImagePath + '\'' +
                ", certificationLevel=" + certificationLevel +
                ", renderingMode=" + renderingMode +
                '}';
    }
}
