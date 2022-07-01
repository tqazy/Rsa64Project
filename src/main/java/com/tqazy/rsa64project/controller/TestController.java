package com.tqazy.rsa64project.controller;

import com.tqazy.rsa64project.util.RSAUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : 陶其
 * @date : 2022/6/30 14:29
 * @description :
 */
@RestController
@RequestMapping("/rsa64")
public class TestController {
    private KeyPair keyPair;

    /**
     * 生成秘钥对
     *
     * @return
     */
    @GetMapping("/getKeyPair")
    public Map<String, String> getKeyPair() {
        Map<String, String> resultMap = new HashMap<>();
        try {
            keyPair = RSAUtil.getKeyPair();
            String privateKey = RSAUtil.getPrivateKey(keyPair);
            privateKey = privateKey.replaceAll("\r\n", "");
            System.out.println(privateKey);
            String publicKey = RSAUtil.getPublicKey(keyPair);
            publicKey = publicKey.replaceAll("\r\n", "");
            System.out.println(publicKey);
            resultMap.put("publicKey", publicKey);
            resultMap.put("privateKey", privateKey);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultMap;
    }

    /**
     * @param paramVo
     * @return
     */
    @PostMapping("/CodeBase64")
    public String CodeBase64(ParamVo paramVo) {
        if (StringUtils.isBlank(paramVo.getContent())) {
            throw new RuntimeException("要加密的内容为空！");
        }
        if (StringUtils.isBlank(paramVo.getKey())) {
            throw new RuntimeException("公钥为空！");
        }

        try {
            return RSAUtil.CodeBase64(paramVo.getContent(), paramVo.getKey());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/Base64Code")
    public String Base64Code(ParamVo paramVo) {
        try {
            if (StringUtils.isBlank(paramVo.getContent())) {
                throw new RuntimeException("密文为空！");
            }
            if (StringUtils.isBlank(paramVo.getKey())) {
                throw new RuntimeException("密钥为空！");
            }
            return RSAUtil.Base64Code(paramVo.getContent(), paramVo.getKey());
        } catch (Exception e) {
            System.out.println("解密异常！");
            e.printStackTrace();
        }
        return null;
    }

}
