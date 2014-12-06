package myutils;

import com.mysql.jdbc.StringUtils;
import myutils.exception.BlankOrNullException;

class FormatCheck {

    public static void checkBlankOrNull(String rawValue, String msg)
            throws BlankOrNullException {
        if (StringUtils.isNullOrEmpty(rawValue))
            throw new BlankOrNullException(msg);
    }

}
