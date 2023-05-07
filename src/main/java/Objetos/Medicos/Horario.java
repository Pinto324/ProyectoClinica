/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos.Medicos;

import java.time.LocalTime;

/**
 *
 * @author branp
 */
public class Horario {
    private LocalTime HoraEntrada;
    private LocalTime HoraSalida;

    public Horario(LocalTime HoraEntrada, LocalTime HoraSalida) {
        this.HoraEntrada = HoraEntrada;
        this.HoraSalida = HoraSalida;
    }

    public LocalTime getHoraEntrada() {
        return HoraEntrada;
    }

    public void setHoraEntrada(LocalTime HoraEntrada) {
        this.HoraEntrada = HoraEntrada;
    }

    public LocalTime getHoraSalida() {
        return HoraSalida;
    }

    public void setHoraSalida(LocalTime HoraSalida) {
        this.HoraSalida = HoraSalida;
    }
    
}
