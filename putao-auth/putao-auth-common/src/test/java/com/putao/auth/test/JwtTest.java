package com.putao.auth.test;

import com.putao.common.pojo.UserInfo;
import com.putao.common.utils.JwtUtils;
import com.putao.common.utils.RsaUtils;
import org.junit.Before;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;

public class JwtTest {

    private static final String pubKeyPath = "C:\\tmp\\rsa\\rsa.pub";

    private static final String priKeyPath = "C:\\tmp\\rsa\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "234");
    }

    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        // 生成token
        String token = JwtUtils.generateToken(new UserInfo("9527", "呵呵"), privateKey, -1);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6Ijk1MjciLC" +
                "J1c2VybmFtZSI6IuWRteWRtSIsImV4cCI6MTU5ODUwODU5M30.Bhn" +
                "_qmJKcmG1v4Gokhc6lIuRaLQIhG-BldHi2ybLkqSR5f2bh-PM456v9yBGZbe" +
                "x6sjFWU-TpGHVEOjMsr24wbshRf_yvqPX9NAi76ZqTHTWC-noCrLimnbFltKU" +
                "FBcaEgHiMegSly8SZxAS0LkOxKqgj3cj4MKArFBd5_k9CQM";

        // 解析token
        UserInfo user = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + user.getId());
        System.out.println("userName: " + user.getUsername());
    }
}