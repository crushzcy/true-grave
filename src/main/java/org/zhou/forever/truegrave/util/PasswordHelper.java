package org.zhou.forever.truegrave.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.zhou.forever.truegrave.domain.User;

/**
 * @ClassName PasswordHelper
 * @Description TODO
 * @Author Mr.wang
 * @Date 2019/10/22 14:09
 **/
public class PasswordHelper {

    /**
     * 加密方案
     */
    private static final String  ALGORITHM_NAME = "md5";

    /**
     * 加密次数
     */
    private static int  HASH_ITERATIONS= 2;

    public static void encryptPassword(User user) {
        //String salt=randomNumberGenerator.nextBytes().toHex();
        String newPassword = new SimpleHash(ALGORITHM_NAME, user.getPassword(),  ByteSource.Util.bytes(user.getUserName()), HASH_ITERATIONS).toHex();
        user.setPassword(newPassword);
    }
}
