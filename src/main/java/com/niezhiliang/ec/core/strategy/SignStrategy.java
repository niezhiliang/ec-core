package com.niezhiliang.ec.core.strategy;

import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.security.*;
import com.niezhiliang.ec.core.entity.SignInitParams;
import com.niezhiliang.ec.core.entity.SignParams;
import com.niezhiliang.ec.core.entity.SignPortParms;
import com.niezhiliang.ec.core.pattern.Behave;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.*;
import java.security.cert.Certificate;

@Component
public class SignStrategy implements Behave<SignParams> {
    final static File RESULT_FOLDER = new File("/Users/suyu/Desktop/ec-core/src/test/tmp", "signservice");

    @Override
    public void doSomeJob(SignParams signParams) {

        signParams.setCertificationLevel(PdfSignatureAppearance.NOT_CERTIFIED);
        signParams.setRenderingMode(PdfSignatureAppearance.RenderingMode.GRAPHIC);


        try {

            SignInitParams signInitParams = setUp(signParams);
            String filepath = signParams.getPdfDir()+signParams.getFileName();
            String digestAlgorithm = DigestAlgorithms.SHA256;
            MakeSignature.CryptoStandard subfilter = MakeSignature.CryptoStandard.CMS;
            // Creating the reader and the stamper
            PdfReader reader = new PdfReader(filepath);
            FileOutputStream os = new FileOutputStream(new File(RESULT_FOLDER, System.currentTimeMillis()+ ".pdf"));
            PdfStamper stamper =
                    PdfStamper.createSignature(reader, os, '\0', RESULT_FOLDER, true);
            // Creating the appearance
            PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
            appearance.setReason(signParams.getReason());
            appearance.setLocation(signParams.getLocation());

            for(SignPortParms signPortParms : signParams.getList()) {

                //设置签名的位置，页码，签名域名称，多次追加签名的时候，签名域名称不能一样
                //签名的位置，是图章相对于pdf页面的位置坐标，原点为pdf页面左下角
                //四个参数的分别是，图章左下角x，图章左下角y，图章右上角x，图章右上角y
                appearance.setVisibleSignature(new Rectangle(signPortParms.getLeftButtomX().floatValue(),
                                signPortParms.getLeftButtomY().floatValue() , signPortParms.getRightTopX().floatValue(),
                                signPortParms.getRightTopY().floatValue()),
                        signPortParms.getPageno() + 1, System.currentTimeMillis()+"");//fileName: 随机作用域
            };

            //签署图片地址
            Image image = Image.getInstance(signParams.getImgPath());
            appearance.setSignatureGraphic(image);
            appearance.setCertificationLevel(signParams.getCertificationLevel());
            //设置图章的显示方式，如下选择的是只显示图章（还有其他的模式，可以图章和签名描述一同显示）
            appearance.setRenderingMode(signParams.getRenderingMode());
            // Creating the signature   签名算法
            ExternalSignature pks = new PrivateKeySignature(signInitParams.getPk(), digestAlgorithm, "BC");
            //摘要算法
            ExternalDigest digest = new BouncyCastleDigest();
            // 调用itext签名方法完成pdf签章
            MakeSignature.signDetached(appearance, digest, pks, signInitParams.getChain(),
                    null, null, null, 0, subfilter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 取出证书
     * @param signParams
     * @return
     */
    private SignInitParams setUp(SignParams signParams) {
        SignInitParams signInitParams = new SignInitParams();
        String KEYSTORE = signParams.getKeyStorePath();
        char[] PASSWORD = signParams.getKeyStorePwd().toCharArray();
        RESULT_FOLDER.mkdirs();
        BouncyCastleProvider bcp = new BouncyCastleProvider();
        Security.insertProviderAt(bcp, 1);
        try {
                KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
                ks.load(new FileInputStream(KEYSTORE), PASSWORD);
                PrivateKey pk = (PrivateKey) ks.getKey(signParams.getAlias(), PASSWORD);
                Certificate[] chain = ks.getCertificateChain(signParams.getAlias());
                signInitParams.setPk(pk);
                signInitParams.setChain(chain);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return signInitParams;
    }
}
