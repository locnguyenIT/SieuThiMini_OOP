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
import sieuthimini_oop.SanPham;

/**
 *
 * @author ntloc
 */
public class HangGiaDung extends SanPham implements In_Out{
    private String vatlieu;
    private String congdung;
    
    public HangGiaDung() throws IOException{}

    public HangGiaDung(String masp, String maloai, String tensp, int sl, float dongia, String dvt,String vatlieu, String congdung) throws IOException {
        super(masp,maloai,tensp,sl,dongia,dvt);
        this.vatlieu = vatlieu;
        this.congdung = congdung;
    }
    public void nhap(String masp)
    {
        super.nhap(masp);
        Scanner in = new Scanner(System.in);
        System.out.print("Nhập vật liệu: ");
        vatlieu=in.nextLine();
        System.out.print("Nhập công dụng: ");
        congdung=in.nextLine();
    }
    @Override
    public void nhap()
    {
        super.nhap();
        Scanner in = new Scanner(System.in);
        System.out.print("Nhập vật liệu: ");
        vatlieu=in.nextLine();
        System.out.print("Nhập công dụng: ");
        congdung=in.nextLine();
    }
    @Override
    public void xuat()
    {
        System.out.printf("| %10s | %10s | %15s | %10s | %10s | %15s | %10s | %10s | %10s | %10s | %10s | %10s |\n",
                            masp, maloai,tensp,  sl,  dongia, dvt,  null,  null,  null,   null, vatlieu,congdung);
    }
    @Override
    public void WriteFile(String file) throws IOException
    {
        DataOutputStream out = new DataOutputStream(new FileOutputStream(file,Boolean.TRUE));
        int loai = 3;
        out.writeInt(loai);
        out.writeUTF(masp);
        out.writeUTF(maloai);
        out.writeUTF(tensp);
        out.writeInt(sl);
        out.writeFloat(dongia);
        out.writeUTF(dvt);
        out.writeUTF(vatlieu);
        out.writeUTF(congdung);
        out.close();
    }
    public String getVatlieu() {
        return vatlieu;
    }

    public void setVatlieu(String vatlieu) {
        this.vatlieu = vatlieu;
    }

    public String getCongdung() {
        return congdung;
    }

    public void setCongdung(String congdung) {
        this.congdung = congdung;
    }
    
}
