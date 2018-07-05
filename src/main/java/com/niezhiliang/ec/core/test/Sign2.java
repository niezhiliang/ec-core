package com.niezhiliang.ec.core.test;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.security.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

/**
 * Created by hxy on 2018/6/6.
 * E-mail:hxyHelloWorld@163.com
 * github:https://github.com/haoxiaoyong1014
 */
public class Sign2 {

    final static File RESULT_FOLDER = new File("/Users/huluwa110/Desktop/ec-core/src/test/tmp", "signservice");
    final static FileUtil fileUtil = new FileUtil();
    final static String PASSWORD_ = "123456";
    public static KeyStore ks = null;
    public static PrivateKey pk = null;
    public static Certificate[] chain = null;

    private static void setUp(SignInfo signInfo) {
        String KEYSTORE = signInfo.getKsUrl();
        char[] PASSWORD = PASSWORD_.toCharArray();
        RESULT_FOLDER.mkdirs();
        BouncyCastleProvider bcp = new BouncyCastleProvider();
        Security.insertProviderAt(bcp, 1);
        try {
            ks = KeyStore.getInstance(KeyStore.getDefaultType());
            try {
                ks.load(new FileInputStream(KEYSTORE), PASSWORD);
                String alias = signInfo.getAlias();
                    System.out.println("--------"+alias);
                // String alias = (String) ks.aliases().nextElement();
                try {
                    pk = (PrivateKey) ks.getKey(alias, PASSWORD);
                    chain = ks.getCertificateChain(alias);
                } catch (UnrecoverableKeyException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (CertificateException e) {
                e.printStackTrace();
            }

        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

    }

    public static String sign2(SignInfo signInfo) throws IOException, DocumentException, GeneralSecurityException {
        //SignInfo signInfo = JSON.parseObject(str, SignInfo.class);
        setUp(signInfo);
        String filepath = signInfo.getDownloadFilePath();
        String digestAlgorithm = DigestAlgorithms.SHA256;
        MakeSignature.CryptoStandard subfilter = MakeSignature.CryptoStandard.CMS;

        // Creating the reader and the stamper
        PdfReader reader = new PdfReader(filepath);
        String tmpFileName = fileUtil.getTmpFileName();
        FileOutputStream os = new FileOutputStream(new File(RESULT_FOLDER, tmpFileName + ".pdf"));
        PdfStamper stamper =
                PdfStamper.createSignature(reader, os, '\0', RESULT_FOLDER, true);
        // Creating the appearance
        PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
        appearance.setReason(signInfo.getReason());
        appearance.setLocation(signInfo.getLocation());
        //设置签名的位置，页码，签名域名称，多次追加签名的时候，签名域名称不能一样
        //签名的位置，是图章相对于pdf页面的位置坐标，原点为pdf页面左下角
        //四个参数的分别是，图章左下角x，图章左下角y，图章右上角x，图章右上角y
        appearance.setVisibleSignature(new Rectangle(signInfo.getFirstX().floatValue(),
                        signInfo.getFirstY().floatValue() , signInfo.getSecrondX().floatValue(), signInfo.getSecrondY().floatValue()),
                signInfo.getNum() + 1, tmpFileName);//tmpFileName: 随机作用域
        System.out.println("FirstX: "+signInfo.getFirstX().floatValue());
        System.out.println("SecrondY: "+ signInfo.getSecrondY().floatValue());
        System.out.println("SecrondX: "+signInfo.getSecrondX().floatValue());
        System.out.println("FirstY: "+signInfo.getFirstY().floatValue());
        Image image = Image.getInstance(signInfo.getDownloadImagePath());
        appearance.setSignatureGraphic(image);
        appearance.setCertificationLevel(signInfo.getCertificationLevel());
        //设置图章的显示方式，如下选择的是只显示图章（还有其他的模式，可以图章和签名描述一同显示）
        appearance.setRenderingMode(signInfo.getRenderingMode());
        // Creating the signature   签名算法
        ExternalSignature pks = new PrivateKeySignature(Sign2.pk, digestAlgorithm, "BC");
        //摘要算法
        ExternalDigest digest = new BouncyCastleDigest();
        // 调用itext签名方法完成pdf签章
        MakeSignature.signDetached(appearance, digest, pks, Sign2.chain,
                null, null, null, 0, subfilter);
        return RESULT_FOLDER + File.separator + tmpFileName + ".pdf";
    }

    public static void main(String[] args) {
        SignInfo signInfo = new SignInfo();
        signInfo.setAlias("2037088e9d844f9d02c2832b368ba949c1f07eee");
        signInfo.setDownloadFilePath("/Users/huluwa110/Desktop/ec-core/src/test/tmp/test.pdf");
        signInfo.setFirstX(new BigDecimal(60));
        signInfo.setFirstY(new BigDecimal(60));
        signInfo.setSecrondX(new BigDecimal(80));
        signInfo.setSecrondY(new BigDecimal(80));
        signInfo.setKsUrl("/Users/huluwa110/Desktop/ec-core/src/test/kestore/personal.ks");
        signInfo.setDownloadImagePath("/Users/huluwa110/Desktop/ec-core/src/test/tmp/pic.jpeg");
        signInfo.setNum((short) 1);
        try {
            sign2(signInfo);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

}
