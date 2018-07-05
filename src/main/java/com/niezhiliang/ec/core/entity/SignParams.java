package com.niezhiliang.ec.core.entity;

import com.itextpdf.text.pdf.PdfSignatureAppearance;

import java.util.List;

public class SignParams extends ParentParams {

    /******keystore地址********/
    private String keyStorePath;

    /*******keystore密码***********/
    private String keyStorePwd;

    /****待签署的pdf地址****/
    private String pdfPath;

    /******签章图片地址*********/
    private String imgPath;

    /********证书别名*********/
    private String alias;

    /*******签署坐标******/
    private List<SignPortParms> list;

    /*******签署理由******/
    private String reason;

    /*******签署位置******/
    private String location;

    /****文件名称***/
    private String fileName;

    /***pdf存放的文件夹**/
    private String pdfDir;

    private int certificationLevel = 0; //批准签章

    private PdfSignatureAppearance.RenderingMode renderingMode;//表现形式：仅描述，仅图片，图片和描述，签章者和描述

    public String getPdfPath() {
        return this.pdfDir+this.pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public List<SignPortParms> getList() {
        return list;
    }

    public void setList(List<SignPortParms> list) {
        this.list = list;
    }

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

    public String getKeyStorePath() {
        return keyStorePath;
    }

    public void setKeyStorePath(String keyStorePath) {
        this.keyStorePath = keyStorePath;
    }

    public String getKeyStorePwd() {
        return keyStorePwd;
    }

    public void setKeyStorePwd(String keyStorePwd) {
        this.keyStorePwd = keyStorePwd;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPdfDir() {
        return pdfDir;
    }

    public void setPdfDir(String pdfDir) {
        this.pdfDir = pdfDir;
    }
}
