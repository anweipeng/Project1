package fanggu.org.lotteryapplication.http.common;

import android.util.Base64;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * Created by long on 2015/11/9.
 */
public class CryptoHelper {
    private static CryptoHelper instance = null;
    private final String KEY = "12345678";
    private final byte[] DESIV  = { 0x31, 0x32, 0x33, (byte)0x34, (byte)0x35, (byte)0x36, (byte)0x37, (byte)0x38 };
    private AlgorithmParameterSpec iv = null;// 加密算法的参数接口，IvParameterSpec是它的一个实现
    private Key key = null;
    private CryptoHelper(){
        try
        {
            byte [] DESkey = KEY.getBytes("UTF-8");
            DESKeySpec keySpec = new DESKeySpec(DESkey);// 设置密钥参数
            iv = new IvParameterSpec(DESIV);// 设置向量
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
            key = keyFactory.generateSecret(keySpec);// 得到密钥对象
        }
        catch(Exception ex)
        {

        }


    }
    public static CryptoHelper GetHandle()
    {
        if(null == instance)
        {
            instance = new CryptoHelper();
        }
        return instance;
    }
    public String encode(String data) throws Exception {
        Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");// 得到加密对象Cipher
        enCipher.init(Cipher.ENCRYPT_MODE, key, iv);// 设置工作模式为加密模式，给出密钥和向量
        byte[] pasByte = enCipher.doFinal(data.getBytes("utf-8"));
        // BASE64Encoder base64Encoder = new BASE64Encoder();
        return Base64.encodeToString(pasByte,Base64.DEFAULT);//base64Encoder.encode(pasByte);
    }

    public String decode(String data) throws Exception {
        Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        deCipher.init(Cipher.DECRYPT_MODE, key, iv);
        // BASE64Decoder base64Decoder = new BASE64Decoder();

        //Base64.decode(data,Base64.DEFAULT);//deCipher.doFinal(base64Decoder.decodeBuffer(data));
        byte[] pasByte =deCipher.doFinal(Base64.decode(data,Base64.DEFAULT));
        return new String(pasByte, "UTF-8");
    }
}
