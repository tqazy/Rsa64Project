package com.tqazy.rsa64project.controller;

import com.tqazy.rsa64project.util.RSAUtil;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @author : 陶其
 * @date : 2022/7/14 11:20
 * @description :
 */
public class TestUtilsController {

    public static void main(String[] args) {

        try {
            String str = "1267ghasiuFDSBIWOE.~!@#$%^&*()_+<>?L:\"{}";

            Map<String, String> stringMap = RSAUtil.genKeyPair();
            String publicKey = stringMap.get(RSAUtil.PUBLIC_KEY);
            String privateKey = stringMap.get(RSAUtil.PRIVATE_KEY);
            System.out.println( RSAUtil.PRIVATE_KEY + ":" + privateKey);
            System.out.println( RSAUtil.PUBLIC_KEY + ":" + publicKey);

            ParamVo paramVo = new ParamVo();
            paramVo.setContent(str);
            paramVo.setKey(publicKey);

            String content = RSAUtil.publicKeyEncrypt(paramVo.getContent(), paramVo.getKey());
            System.out.println("公钥加密后的密文：" + content);

            paramVo = new ParamVo();
            paramVo.setContent(content);
            paramVo.setKey(privateKey);
            String mdStr = RSAUtil.privateKeyDecrypt(paramVo.getContent(), paramVo.getKey());
            System.out.println("私钥解密后的密文：" + mdStr);


        } catch (NoSuchAlgorithmException e) {
            System.out.println("生成密钥对失败！");
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println("加密失败！");
            throw new RuntimeException(e);
        }

    }

}
