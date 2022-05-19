package sv.edu.udb.rest.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sv.edu.udb.rest.constants.AppConstants;

public final class PatternUtils {
	public static boolean checkValidEmail(String email) {
		Pattern pattern = Pattern.compile(AppConstants.EMAIL_RFC5322_REGEX, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		
		return matcher.find();
	}
	
	public static boolean checkValidDate(String date) {
		Pattern pattern = Pattern.compile(AppConstants.DATE_REGEX);
		Matcher matcher = pattern.matcher(date);
		
		return matcher.find();
	}
}
