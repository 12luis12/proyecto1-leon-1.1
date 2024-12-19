package com.empresa.util;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NumeroALetras {

    private static final String[] UNIDADES = {
        "", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve",
        "diez", "once", "doce", "trece", "catorce", "quince", "dieciséis", "diecisiete", "dieciocho", "diecinueve"
    };

    private static final String[] DECENAS = {
        "", "", "veinte", "treinta", "cuarenta", "cincuenta", "sesenta", "setenta", "ochenta", "noventa"
    };

    private static final String[] CENTENAS = {
        "", "ciento", "doscientos", "trescientos", "cuatrocientos", "quinientos", "seiscientos", "setecientos", "ochocientos", "novecientos"
    };

    public static String convert(BigDecimal number) {
    	if (number == null) {
            // Maneja el caso en que el número es null, por ejemplo, devolviendo un valor predeterminado
            return "Número no disponible";
        }
        BigInteger integerPart = number.toBigInteger();
        int decimalPart = number.remainder(BigDecimal.ONE).movePointRight(number.scale()).abs().intValue();

        String integerPartInWords = convert(integerPart);
        String decimalPartInWords = convertDecimalPart(decimalPart);

        if (integerPart.equals(BigInteger.ZERO)) {
            return "cero" + (decimalPart > 0 ? " con " + decimalPartInWords : "");
        }

        return integerPartInWords + (decimalPart > 0 ? " con " + decimalPartInWords : "");
    }

    private static String convert(BigInteger number) {
        if (number.equals(BigInteger.ZERO)) {
            return "";
        } else if (number.compareTo(BigInteger.valueOf(20)) < 0) {
            return UNIDADES[number.intValue()];
        } else if (number.compareTo(BigInteger.valueOf(100)) < 0) {
            int unidad = number.mod(BigInteger.TEN).intValue();
            return DECENAS[number.divide(BigInteger.TEN).intValue()] + (unidad > 0 ? " y " + UNIDADES[unidad] : "");
        } else if (number.compareTo(BigInteger.valueOf(1000)) < 0) {
            int resto = number.mod(BigInteger.valueOf(100)).intValue();
            return (number.intValue() == 100 ? "cien" : CENTENAS[number.divide(BigInteger.valueOf(100)).intValue()]) + (resto > 0 ? " " + convert(BigInteger.valueOf(resto)) : "");
        } else if (number.compareTo(BigInteger.valueOf(1000000)) < 0) {
            BigInteger miles = number.divide(BigInteger.valueOf(1000));
            int resto = number.mod(BigInteger.valueOf(1000)).intValue();
            return (miles.equals(BigInteger.ONE) ? "mil" : convert(miles) + " mil") + (resto > 0 ? " " + convert(BigInteger.valueOf(resto)) : "");
        } else if (number.compareTo(BigInteger.valueOf(1000000000)) < 0) {
            BigInteger millones = number.divide(BigInteger.valueOf(1000000));
            int resto = number.mod(BigInteger.valueOf(1000000)).intValue();
            return (millones.equals(BigInteger.ONE) ? "un millón" : convert(millones) + " millones") + (resto > 0 ? " " + convert(BigInteger.valueOf(resto)) : "");
        } else {
            return "Número demasiado grande";
        }
    }

    private static String convertDecimalPart(int number) {
        if (number == 0) {
            return "";
        }
        return convert(BigInteger.valueOf(number));
    }
}
