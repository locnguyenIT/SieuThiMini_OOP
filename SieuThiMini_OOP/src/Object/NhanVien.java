/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import sieuthimini_oop.In_Out;
/**
 *
 * @author ntloc
 */
public class NhanVien implements In_Out {
    private String manv;
    private String honv;
    private String tennv;
    private String phai;
    private String diachi;
    private int namsinh;
    public NhanVien(){}
    public NhanVien(String manv, String honv, String tennv, String phai, String diachi, int namsinh) 
    {
        this.manv = manv;
        this.honv = honv;
        this.tennv = tennv;
        this.phai = phai;
        this.diachi = diachi;
        this.namsinh = namsinh;
    }
   @Override
   public void nhap()
   {
       Scanner in = new Scanner(System.in);
        System.out.print("Nhap Ma NV: ");
        manv=in.nextLine();
        System.out.print("Nhap Ho: ");
        honv=in.nextLine();
        System.out.print("Nhap Ten: ");
        tennv=in.nextLine();
        System.out.print("Nhap Phai: ");
        phai=in.nextLine();
        System.out.print("Nhap Dia chi: ");
        diachi=in.nextLine();
        System.out.print("Nhap Nam sinh: ");
        namsinh=in.nextInt();
   }
    public void nhap(String manv)
    {
        Scanner in = new Scanner(System.in);
        this.manv = manv;
        System.out.print("Nhap Ho: ");
        honv=in.nextLine();
        System.out.print("Nhap Ten: ");
        tennv=in.nextLine();
        System.out.print("Nhap Phai: ");
        phai=in.nextLine();
        System.out.print("Nhap Dia chi: ");
        diachi=in.nextLine();
        System.out.print("Nhap Nam sinh: ");
        namsinh=in.nextInt();
    }
    @Override
    public void xuat()
    {
        System.out.printf("| %10s | %10s | %15s | %10s | %30s | %10s |\n",manv,honv,tennv,phai,diachi,namsinh);
    }
    public void WriteFile(String file) throws IOException 
    {
        DataOutputStream outStream = new DataOutputStream(new FileOutputStream(file,Boolean.TRUE));
        outStream.writeUTF(manv);
        outStream.writeUTF(honv);
        outStream.writeUTF(tennv);
        outStream.writeUTF(phai);
        outStream.writeUTF(diachi);
        outStream.writeInt(namsinh);
        outStream.close();
    }
    
    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getHonv() {
        return honv;
    }

    public void setHonv(String honv) {
        this.honv = honv;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public String getPhai() {
        return phai;
    }

    public void setPhai(String phai) {
        this.phai = phai;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public int getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(int namsinh) {
        this.namsinh = namsinh;
    }
    
    
}
