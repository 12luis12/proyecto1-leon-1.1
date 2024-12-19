package com.empresa.servicio;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import com.empresa.util.NumeroALetras;

@Service
public class ConversionLetrasServicioImpl implements ConversionLetrasServicio {
    @Override
    public String NumeroALetras(BigDecimal number) {
        return NumeroALetras.convert(number);
    }
}
