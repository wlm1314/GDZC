package base.lib.util;

import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * 格式检查工具
 *
 * @author hyh
 *         creat_at：2013-8-9-下午7:33:24
 */
public class AppFormatUtil {
    public static final int PASSWORD_MIN_LENGTH = 5;


    /**
     * 汉字判断
     */
    public static final Pattern Chinese_Pattern = Pattern.compile("^[\u4E00-\u9FFF]+$");
    public static final Pattern Password_Pattern = Pattern.compile("^(?!\\D+$)(?![^a-zA-Z]+$).{6,16}$");
    public static final Pattern NickName_Pattern = Pattern.compile("^[0-9a-zA-Z_-—\u0391-\uFFE5\u4e00-\u9fa5]+$");
    /**
     * 手机号码格式
     */
    public static final Pattern MobilePhone_Pattern = Pattern.compile("^(1[34578])\\d{9}$");
    public static final Pattern TelePhone_Pattern = Pattern.compile("((400)-(\\d{4})-(\\d{3}))|(^(0(\\d{2,3})-?)?\\d{7,8}$)|((400)(\\d{3})(\\d{4}))|((400)-(\\d{3})-(\\d{4}))");//固定电话 包括400
    public static final Pattern AnyPhone_Pattern = Pattern.compile("^((1[34578][0-9]{9})|((0\\d{2}-\\d{8})|(0\\d{3}-\\d{7,8})|(0\\d{10,11}))|(((400)-(\\d{3})-(\\d{4}))|((400)-(\\d{4})-(\\d{3}))|((400)-(\\d{5})-(\\d{2}))|(400\\d{7}))|(((800)-(\\d{3})-(\\d{4}))|((800)-(\\d{4})-(\\d{3}))|((800)-(\\d{5})-(\\d{2}))|(800\\d{7})))$");
    /**
     * 邮箱地址格式
     */
    public static final Pattern Email_Pattern = Pattern.compile("^([a-zA-Z0-9]+[\\_|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[\\_|\\.]?)*[a-zA-Z0-9]+.[a-zA-Z]{2,3}$");
    /**
     * 15位身份证
     */
    public static final Pattern IDCard15_Pattern = Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");
    /**
     * 18位身份证
     */
    public static final Pattern IDCard18_Pattern = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$");
    public static final Pattern URL_Pattern = Pattern.compile("(http|https|ftp|Http|Https|Ftp|HTTP|HTTPS|FTP):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?");
    public static final Pattern Http_or_Https_URL_Pattern = Pattern.compile("(http|https|Http|Https|HTTP|HTTPS):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?");
    public static final Pattern CarLicense_Pattern = Pattern.compile("^[\\u4e00-\\u9fa5]{1}[a-zA-Z]{1}[a-zA-Z_0-9]{5}$");
    public static final Pattern Vin_Pattern = Pattern.compile("^[a-zA-Z_0-9]{17}$");
    public static final Pattern Money_Pattern = Pattern.compile("^[0-9.,]{1,}$");
    /**
     * 由数字、26个英文字母或者下划线组成的字符串
     */
    public static final Pattern UserName_Pattern = Pattern.compile("^[a-zA-Z0-9_]+$");
    /**
     * 由数字和字母 组成的字符串
     */
    public static final Pattern CharNumber_Pattern = Pattern.compile("^[A-Za-z0-9]+$");
    public static final Pattern OnlyChar_Pattern = Pattern.compile("^[A-Za-z]+$");
    public static final Pattern OnlyNumber_Pattern = Pattern.compile("^[0-9]*$");
    /**
     * 传真验证
     */
    public static final Pattern Fax_Pattern = Pattern.compile("((^\\d{11}$)|(^\\d{12}$))|(^\\d{3}-\\d{8}$)|(^\\d{4}-\\d{7}$)|(^\\d{4}-\\d{8}$)");
    /**
     * 邮政编码
     */
    public static final Pattern Post_Pattern = Pattern.compile("[1-9]\\d{5}(?!\\d)");
    //(http|ftp|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&amp;:/~\+#]*[\w\-\@?^=%&amp;/~\+#])?

    /**
     * 正则表达式匹配手机号码
     *
     * @param phoneNumber
     * @return
     */
    public static boolean isPhoneNumber(String phoneNumber) {
        return MobilePhone_Pattern.matcher(phoneNumber).matches();
    }

    public static boolean isTelePhoneNumber(String phoneNumber) {
        return TelePhone_Pattern.matcher(phoneNumber).matches();
    }

    public static boolean isAnyPhoneNumber(String phoneNumber) {
        return AnyPhone_Pattern.matcher(phoneNumber).matches();
    }

    public static boolean isPostNumber(String postNumber) {
        return Post_Pattern.matcher(postNumber).matches();
    }

    public static boolean isCharNumber(String postNumber) {
        return CharNumber_Pattern.matcher(postNumber).matches();
    }
    public static boolean isNumber(String postNumber) {
        return OnlyNumber_Pattern.matcher(postNumber).matches();
    }

//    public static boolean isPhoneNumberWithCode(String phoneNumber) {
//        String stringTemp = phoneNumber.replace("-","").replace("+","");
//		return TextUtils.isDigitsOnly(stringTemp);
//	}

    /**
     * 将手机号码中间4位替换为****
     *
     * @param phoneNumber
     * @return
     */
    public static String replicePhoneNumber(String phoneNumber) {
        if (isPhoneNumber(phoneNumber)) {
            return phoneNumber.substring(0, 3) + "****" + phoneNumber.substring(7, phoneNumber.length());
        }
        return phoneNumber;
    }
//	public static boolean isNickname(String nickname){
//		return NickName_Pattern.matcher(nickname).matches();
//	}

    /**
     * 正则表达式匹配邮箱地址
     *
     * @param emailAddr
     * @return
     */
    public static boolean isEmailAddr(String emailAddr) {
        return Email_Pattern.matcher(emailAddr).matches();
    }

    /**
     * 正则表达式匹配http或https地址
     *
     * @param httpAddr
     * @return
     */
    public static boolean isHttpAddr(String httpAddr) {
        return Http_or_Https_URL_Pattern.matcher(httpAddr).matches();
    }

    /**
     * 正则表达式匹配汉字
     *
     * @param string
     * @return
     */
    public static boolean isChineseOnly(String string) {
        return Chinese_Pattern.matcher(string).matches();
    }
    /**
     * 正则表达式26字母
     *
     * @param string
     * @return
     */
    public static boolean isCharOnly(String string) {
        return OnlyChar_Pattern.matcher(string).matches();
    }
    /**
     *正则表达式 传真
     *
     * @param string
     * @return
     */
    public static boolean isFax(String string) {
        return Fax_Pattern.matcher(string).matches();
    }

    /**
     * 由数字、26个英文字母或者下划线组成的字符串
     *
     * @param string
     * @return
     */
    public static boolean isUserName(String string) {
        return UserName_Pattern.matcher(string).matches();
    }

    /**
     * 正则表达式匹配金额(可以有.或,)
     *
     * @param string
     * @return
     */
    public static boolean isMoneyNum(String string) {
        return Money_Pattern.matcher(string).matches();
    }

    /**
     * 正则表达式匹配身份证号码
     *
     * @param string
     * @return
     */
    public static boolean isIDCard(String string) {
        return IDCard18_Pattern.matcher(string).matches();
    }

    /**
     * 正则表达式检查密码复杂度
     *
     * @param password
     * @return
     */
    public static boolean isPwdComplex(String password) {
        return Password_Pattern.matcher(password).matches();
    }

    /**
     * 正则表达式检查密码复杂度
     *
     * @param password
     * @return
     */
    public static boolean isPasswordLengthEnough(String password) {
        if (TextUtils.isEmpty(password)) {
            return false;
        }
        return password.length() >= PASSWORD_MIN_LENGTH;
    }

    public static boolean isCarLicence(String string) {
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        return CarLicense_Pattern.matcher(string).matches();
    }

    public static boolean isVinCode(String string) {
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        return Vin_Pattern.matcher(string).matches();
    }

    /**
     * 判断字符串的长度,每个汉字为1,每个字母为1
     *
     * @param str
     * @return
     */
    public static int getStringLengthEn1Cn1(String str) {
        return str.length();
    }

    /**
     * 判断字符串的长度,每个汉字为1,每个字母为0.5
     *
     * @param str
     * @return
     */
    public static int getStringLengthEn0_5Cn1(String str) {
        double num = 0;
        String len;
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            len = Integer.toBinaryString(c[i]);
            if (len.length() > 8) {
                num++;
            } else {
                num += 0.5;
            }
        }
        if (0 == num % 1) {
            return (int) num;
        } else {
            return (int) num + 1;
        }
    }

    /**
     * 判断字符串的长度,每个汉字为1,每个字母为1
     *
     * @param str
     * @return
     */
    public static int getStringLengthEn1Cn2(String str) {
        double num = 0;
        String len;
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            len = Integer.toBinaryString(c[i]);
            if (len.length() > 8) {
                num++;
            } else {
                num += 1;
            }
        }
        if (0 == num % 1) {
            return (int) num;
        } else {
            return (int) num + 2;
        }
    }

//	public static boolean isVenueUrl(String url){
//		return VenueURL_Pattern.matcher(url).matches();
//	}
//	public static boolean isEventUrl(String url){
//		return EventURL_Pattern.matcher(url).matches();
//	}
}
