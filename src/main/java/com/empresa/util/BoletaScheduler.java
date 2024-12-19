package com.empresa.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.empresa.servicio.BoletaServicio;

@Component
public class BoletaScheduler {

    @Autowired
    private BoletaServicio boletaServicio;

    // Este método se ejecutará todos los días a la medianoche
    @Scheduled(cron = "0 0 0 * * ?")
    public void cambiarEstadoFirma() {
    	boletaServicio.actualizarEstadoFirma();
    }
    
   
}
