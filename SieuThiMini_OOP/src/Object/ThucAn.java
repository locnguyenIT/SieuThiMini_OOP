package Object;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import sieuthimini_oop.In_Out;
import java.util.Scanner;
import sieuthimini_oop.SanPham;

/**
 *
 * @author ntloc
 */
public class ThucAn extends SanPham implements In_Out{
    
    private String nguon;
    private String trangthai;

    public ThucAn() throws IOException {}
 
    public ThucAn(String masp,String maloai,String tensp, int sl, float dongia, String dvt, String nguon, String trangthai) 
    {
        super(masp,maloai,tensp,sl,dongia,dvt);
        this.nguon = nguon;
        this.trangthai = trangthai;
    }
    @Override
    public void nhap()
    {
        super.nhap();
        Scanner in = new Scanner(System.in);
        System.out.print("Nhập nguồn: ");
        nguon=in.nextLine();
        System.out.print("Nhập trạng thái: ");
        trangthai=in.nextLine();
    }
    public void nhap(String masp)
    {
        super.nhap(masp);
        Scanner in = new Scanner(System.in);
        System.out.print("Nhập nguồn: ");
        nguon=in.nextLine();
        System.out.print("Nhập trạng thái: ");
        trangthai=in.nextLine();
    }
    @Override
    public void xuat()
    {
        System.out.printf("| %10s | %10s | %15s | %10s | %10s | %15s | %10s | %10s | %10s | %10s | %10s | %10s |\n",
                            masp, maloai,tensp, sl, dongia,  dvt,  nguon, trangthai, null, null, null,  null);
    }
    @Override
    public void WriteFile(String file) throws IOException
    { 
        int loai = 1;
        DataOutputStream out = new DataOutputStream(new FileOutputStream(file,Boolean.TRUE));        
        out.writeInt(loai);
        out.writeUTF(masp);
        out.writeUTF(maloai);
        out.writeUTF(tensp);
        out.writeInt(sl);
        out.writeFloat(dongia);
        out.writeUTF(dvt);
        out.writeUTF(nguon);
        out.writeUTF(trangthai);
        out.close();
        
    }
    public String getNguon() {
        return nguon;
    }

    public void setNguon(String nguon) {
        this.nguon = nguon;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
    
    
}
