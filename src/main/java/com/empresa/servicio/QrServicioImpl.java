package com.empresa.servicio;

import java.io.IOException;


import org.springframework.stereotype.Service;

import com.google.zxing.WriterException;

@Service
public class QrServicioImpl implements QrServicio{
	 private final QRCodeServicio qrCodeService;

	    
	    public QrServicioImpl(QRCodeServicio qrCodeService) {
	        this.qrCodeService = qrCodeService;
	    }

	    @Override
	    public byte[] generateQRCode(String text, int width, int height) throws WriterException, IOException {
	        return qrCodeService.generateQRCode(text, width, height);
	    }
}
