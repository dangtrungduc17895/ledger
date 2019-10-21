package com.pet.ledger.utils;

import com.pet.ledger.constant.FormatConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtils {

    private ValidateUtils() {}

    public static boolean isNTQMail(String email, String formatEmail) {
        String emailPattern = FormatConstant.FORM_EMAIL + formatEmail + "$";
        Pattern regex = Pattern.compile(emailPattern);
        Matcher matcher = regex.matcher(email);
        return !matcher.find();
    }

    public static boolean isStringNullOrEmpty(String string) {
        return StringUtils.isBlank(string);
    }

    public static boolean isStringNotNullAndEmpty(String string) {
        return !isStringNullOrEmpty(string);
    }

    public static boolean isObjectNullOrEmpty(Object object) {
        return object==null|| ObjectUtils.isEmpty(object);
    }

    public static boolean isObjectNotNullAndEmpty(Object object) {
        return !isObjectNullOrEmpty(object);
    }

    public static boolean isCollectionNullOrEmpty(Collection<Object> collection) {
        return collection==null|| CollectionUtils.isEmpty(collection);
    }

    public static boolean isCollectionNotNullAndEmpty(Collection<Object> collection) {
        return !isCollectionNullOrEmpty(collection);
    }
}
