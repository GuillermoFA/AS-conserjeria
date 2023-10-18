/*
 * Copyright (c) 2023. Departamento de Ingenieria de Sistemas y Computacion, Universidad Catolica del Norte.
 */

package cl.ucn.disc.as.utils;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * Validation Utils.
 *
 * @author Diego Urrutia-Astorga.
 */
@Slf4j
public final class ValidationUtils {

    /**
     * Rut regular expression.
     */
    private static final Pattern RUT_PATTERN = Pattern.compile("^\\d+-[\\dkK]$");

    /**
     * Email regular expression.
     */
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");

    /**
     * Validate the Chilean RUT.
     *
     * @param rut to validate.
     * @return true is valid.
     */
    public static Boolean isRutValid(final String rut) {

        // No valid
        if (rut == null || !RUT_PATTERN.matcher(rut).matches()) {
            return Boolean.FALSE;
        }

        // The rut
        String numeric = StringUtils.substringBefore(rut, "-");

        // The dv
        String dv = StringUtils.substringAfter(rut, "-").toLowerCase();

        return dv.equals(dv(numeric));
    }

    /**
     * Calculate the DV of a RUT.
     *
     * @param numeric to calculate.
     * @return the dv.
     */
    private static String dv(final @NonNull String numeric) {
        int m = 0;
        int s = 1;
        int t = Integer.parseInt(numeric);

        for (; t != 0; t = (int) Math.floor(t /= 10)) {
            s = (s + t % 10 * (9 - m++ % 6)) % 11;
        }
        return s > 0 ? String.valueOf(s - 1) : "k";
    }

    /**
     * Validate the email.
     *
     * @param email to validate.
     * @return true is valid.
     */
    public static Boolean isEmailValid(final String email) {
        return (email != null && EMAIL_PATTERN.matcher(email).matches());
    }

}
