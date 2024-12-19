package com.empresa.servicio;

import java.io.IOException;

import com.google.zxing.WriterException;

public interface QrServicio {
public abstract	byte[] generateQRCode(String text, int width, int height) throws WriterException, IOException;

}
