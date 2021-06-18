/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import sieuthimini_oop.In_Out;
import sieuthimini_oop.SanPham;

/**
 *
 * @author ntloc
 */
public class ThucUong extends SanPham implements In_Out{
    
    private String dungtich;
    private String tinhchat;

    public ThucUong() throws IOException{}

    public ThucUong(String masp,String maloai, String tensp, int sl, float dongia, String dvt,String dungtich, String tinhchat) 
    {
        super(masp,maloai,tensp, sl, dongia, dvt);
        this.dungtich = dungtich;
        this.tinhchat = tinhchat;
    }
    @Override
    public void nhap(String masp)
    {
        super.nhap(masp);
        Scanner in = new Scanner(System.in);
        System.out.print("Nhập dung tích: ");
        dungtich=in.nextLine();
        System.out.print("Nhập tính chất: ");
        tinhchat=in.nextLine();
    }
    @Override
    public void nhap()
    {
        super.nhap();
        Scanner in = new Scanner(System.in);
        System.out.print("Nhập dung tích: ");
        dungtich=in.nextLine();
        System.out.print("Nhập tính chất: ");
        tinhchat=in.nextLine();
    }
    @Override
    public void xuat()
    {
        System.out.printf("| %10s | %10s | %15s | %10s | %10s | %15s | %10s | %10s | %10s | %10s | %10s | %10s |\n",
                            masp, maloai,tensp,sl,dongia,dvt,null,null,dungtich,tinhchat,null,  null);
    }
    @Override
    public void WriteFile(String file) throws IOException
    {
        int loai = 2;
        DataOutputStream out = new DataOutputStream(new FileOutputStream(file,Boolean.TRUE));       
        out.writeInt(loai);
        out.writeUTF(masp);
        out.writeUTF(maloai);
        out.writeUTF(tensp);
        out.writeInt(sl);
        out.writeFloat(dongia);
        out.writeUTF(dvt);
        out.writeUTF(dungtich);
        out.writeUTF(tinhchat);
        out.close();
    }
    
    public String getDungtich() {
        return dungtich;
    }

    public void setDungtich(String dungtich) {
        this.dungtich = dungtich;
    }

    public String getTinhchat() {
        return tinhchat;
    }
    public void setTinhchat(String tinhchat) {
        this.tinhchat = tinhchat;
    }
    
    
    
    
}
