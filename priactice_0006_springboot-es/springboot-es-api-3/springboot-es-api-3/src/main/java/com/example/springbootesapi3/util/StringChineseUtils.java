package com.example.springbootesapi3.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 字符串工具类
 * </p>
 *
 * @author zgd
 * @since 2022-04-15
 */
public class StringChineseUtils {

    public static boolean ifContainChinese( String str ) {
        Pattern p = Pattern.compile( "[\u4e00-\u9fa5]" );
        Matcher m = p.matcher(str);
        if ( m.find() ) {
            return true;
        }
        return false;
    }


}
