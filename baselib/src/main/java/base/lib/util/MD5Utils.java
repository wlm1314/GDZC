package base.lib.util;

import java.security.MessageDigest;

public class MD5Utils {
    /**
     * 加密
     *
     * @param val
     * @return
     */
    public static String md5(String val) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(val.getBytes());
            byte[] m = md5.digest();//加密
            return _md5(m);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String _md5(byte[] b) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            if (Integer.toHexString(0xFF & b[i]).length() == 1)
                sb.append("0").append(Integer.toHexString(0xFF & b[i]));
            else
                sb.append(Integer.toHexString(0xFF & b[i]));
        }
        return sb.toString();
    }

}
