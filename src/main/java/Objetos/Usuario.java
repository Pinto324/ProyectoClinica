
package Objetos;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;

public class Usuario {
    private int Codigo;
    private String Nombre;
    private String UserName;
    private String Password;
    private String Direccion;
    private String CUI;
    private String Telefono;
    private String Email;
    private Date FechaNacimiento;
    private String Tipo;
    private double Saldo;

    public Usuario(int Codigo, String Nombre, String UserName, String Password, String Direccion, String CUI, String Telefono, String Email, Date FechaNacimiento, String Tipo, double Saldo) {
        this.Codigo = Codigo;
        this.Nombre = Nombre;
        this.UserName = UserName;
        this.Password = Password;
        this.Direccion = Direccion;
        this.CUI = CUI;
        this.Telefono = Telefono;
        this.Email = Email;
        this.FechaNacimiento = FechaNacimiento;
        this.Tipo = Tipo;
        this.Saldo = Saldo;
    }



    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getCUI() {
        return CUI;
    }

    public void setCUI(String CUI) {
        this.CUI = CUI;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public Date getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(Date FechaNacimiento) {
        this.FechaNacimiento = FechaNacimiento;
    }

    public double getSaldo() {
        return Saldo;
    }

    public void setSaldo(double Saldo) {
        this.Saldo = Saldo;
    }

    public String TransformarContra(String contra) throws NoSuchAlgorithmException{
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
         byte[] valorHashCalculado = digest.digest(contra.getBytes());
         String valorHashCalculadoHex = bytesToHex(valorHashCalculado);
         return valorHashCalculadoHex;
    }

    private String bytesToHex(byte[] bytes) {
    StringBuilder stringBuilder = new StringBuilder();
            for (byte b : bytes) {
            stringBuilder.append(String.format("%02x", b));
        }
        return stringBuilder.toString();
    }
}
