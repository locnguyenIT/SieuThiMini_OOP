/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import sieuthimini_oop.In_Out;
/**
 *
 * @author ntloc
 */
public class NhaCungCap implements In_Out{
    
    private String mancc;
    private String tenncc;
    private String diachi;
    public NhaCungCap(){}
    public NhaCungCap(String mancc, String tenncc, String diachi) 
    {
        this.mancc = mancc;
        this.tenncc = tenncc;
        this.diachi = diachi;
    }
    @Override
    public void nhap()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Nhập Mã NCC: ");
        mancc = in.nextLine();
        System.out.print("Nhập Tên : ");
        tenncc = in.nextLine();
        System.out.print("Nhập Địa chỉ : ");
        diachi = in.nextLine();
    }
     public void nhap(String mancc)
    {
        Scanner in = new Scanner(System.in);
        this.mancc = mancc;
        System.out.print("Nhập Tên : ");
        tenncc = in.nextLine();
        System.out.print("Nhập Địa chỉ : ");
        diachi = in.nextLine();
    }
    @Override
    public void xuat()
    {
        System.out.printf("| %10s | %15s | %35s |\n",mancc,tenncc,diachi); 
    }
    public void WriteFile(String file) throws IOException
    {
        DataOutputStream outStream = new DataOutputStream(new FileOutputStream(file,Boolean.TRUE));
        outStream.writeUTF(mancc);
        outStream.writeUTF(tenncc);
        outStream.writeUTF(diachi);
        outStream.close();
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
    
    public String getMancc() {
        return mancc;
    }

    public void setMancc(String mancc) {
        this.mancc = mancc;
    }

    public String getTenncc() {
        return tenncc;
    }

    public void setTenncc(String tenncc) {
        this.tenncc = tenncc;
    }
    
}
