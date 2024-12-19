package com.empresa.dto;

import java.util.Arrays;

public class ImageUtils {

    // Identificador de los primeros bytes de un archivo PNG
    private static final byte[] PNG_SIGNATURE = new byte[]{(byte) 0x89, 'P', 'N', 'G', '\r', '\n', 0x1A, '\n'};

    /**
     * Método para verificar si un arreglo de bytes corresponde a una imagen PNG.
     * @param bytes Arreglo de bytes que se desea verificar.
     * @return true si los primeros bytes coinciden con la firma de un archivo PNG, false en caso contrario.
     */
    public static boolean isPNG(byte[] bytes) {
        if (bytes == null || bytes.length < PNG_SIGNATURE.length) {
            return false;
        }
        return Arrays.equals(Arrays.copyOfRange(bytes, 0, PNG_SIGNATURE.length), PNG_SIGNATURE);
    }
}
