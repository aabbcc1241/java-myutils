package myutils;

import myutils.exception.BlankOrNullException;

import com.mysql.jdbc.StringUtils;

public class FormatCheck {

	public static void checkBlankOrNull(String rawValue, String msg)
			throws BlankOrNullException {
		if (StringUtils.isNullOrEmpty(rawValue))
			throw new BlankOrNullException(msg);
	}

}
