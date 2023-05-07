/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.io.File;

/**
 *
 * @author branp
 */
public class ExamenesSolicitadosConsultas {
    private int IdConsulta;
    private int IdExamen;
    private File Examen;

    public ExamenesSolicitadosConsultas(int IdConsulta, int IdExamen, File Examen) {
        this.IdConsulta = IdConsulta;
        this.IdExamen = IdExamen;
        this.Examen = Examen;
    }

    public int getIdConsulta() {
        return IdConsulta;
    }

    public void setIdConsulta(int IdConsulta) {
        this.IdConsulta = IdConsulta;
    }

    public int getIdExamen() {
        return IdExamen;
    }

    public void setIdExamen(int IdExamen) {
        this.IdExamen = IdExamen;
    }

    public File getExamen() {
        return Examen;
    }

    public void setExamen(File Examen) {
        this.Examen = Examen;
    }
    
}
