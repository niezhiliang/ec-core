package com.niezhiliang.ec.core.entity;

import com.itextpdf.text.pdf.PdfSignatureAppearance;

import java.util.List;

public class SignParams extends ParentParams {

    /****待签署的pdf地址****/
    private String pdfUrl;

    /******签章图片地址*********/
    private String imgUrl;

    /********证书别名*********/
    private String alias;

    /*******签署坐标******/
    private List<SignPortParms> list;

    private int certificationLevel = 0; //批准签章

    private PdfSignatureAppearance.RenderingMode renderingMode;//表现形式：仅描述，仅图片，图片和描述，签章者和描述

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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
}
