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
public class LoaiSP implements In_Out{
    private String maloai;
    private String tenloai;
    public LoaiSP(){}
    public LoaiSP(String maloai, String tenloai) {
        this.maloai = maloai;
        this.tenloai = tenloai;
    }
    @Override
    public void nhap()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Nhap Mã loại: ");
        maloai=in.nextLine();
        System.out.print("Nhap Tên: ");
        tenloai=in.nextLine();
    }
    @Override
    public void xuat()
    {
         System.out.printf("| %10s | %15s |\n",maloai,tenloai);
    }
    public void nhap(String maloai)
    {
        Scanner in = new Scanner(System.in);
        this.maloai = maloai;
        System.out.print("Nhap Tên: ");
        tenloai=in.nextLine();
    }
     public void WriteFile(String file) throws IOException 
    {
        DataOutputStream outStream = new DataOutputStream(new FileOutputStream(file,Boolean.TRUE));
        outStream.writeUTF(maloai);
        outStream.writeUTF(tenloai);
        outStream.close();
    }
    public String getMaloai() {
        return maloai;
    }

    public void setMaloai(String maloai) {
        this.maloai = maloai;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }
    
    
}
