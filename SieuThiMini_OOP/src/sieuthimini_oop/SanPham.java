/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sieuthimini_oop;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author ntloc
 */
public abstract class SanPham implements In_Out{
    protected String masp;
    protected String maloai;
    protected String tensp;
    protected int sl;
    protected float dongia;
    protected String dvt;
    protected DanhSachLoaiSP dslsp = new DanhSachLoaiSP();
    public SanPham()
    {
        dslsp.ReadFile();
    }

    public SanPham(String masp,String maloai, String tensp, int sl, float dongia, String dvt)
    {
        this.dslsp.ReadFile();
        this.masp = masp;
        this.maloai = maloai;
        this.tensp = tensp;
        this.sl = sl;
        this.dongia = dongia;
        this.dvt = dvt;
    }
    public void nhap()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Nhập mã sản phẩm: ");
        masp=in.nextLine();
        System.out.print("Nhập mã loại: ");
        maloai=in.nextLine();
        check_Maloai();
        System.out.print("Nhập tên sản phẩm: ");
        tensp=in.nextLine();
        System.out.print("Nhập số lượng: ");
        sl=in.nextInt();
        System.out.print("Nhập đơn giá: ");
        dongia=in.nextFloat();
        System.out.print("Nhập đơn vị tính: ");
        in.nextLine();
        dvt=in.nextLine();
    }
    public void nhap(String masp)
    {
        Scanner in = new Scanner(System.in);
        this.masp = masp;
        System.out.print("Nhập mã loại: ");
        maloai=in.nextLine();
        check_Maloai();
        System.out.print("Nhập tên sản phẩm: ");
        tensp=in.nextLine();
        System.out.print("Nhập số lượng: ");
        sl=in.nextInt();
        System.out.print("Nhập đơn giá: ");
        dongia=in.nextFloat();
        System.out.print("Nhập đơn vị tính: ");
        in.nextLine();
        dvt=in.nextLine();
    }
    public void xuat()
    {
        System.out.printf("| %10s | %10s | %15s | %10s | %10s | %15s |\n",masp,maloai,tensp,sl,dongia,dvt);
    }
    
    public void WriteFile(String file) throws IOException
    {
        DataOutputStream stream = new DataOutputStream(new FileOutputStream(file,Boolean.TRUE));
        stream.writeUTF(masp);
        stream.writeUTF(maloai);
        stream.writeUTF(tensp);
        stream.writeInt(sl);
        stream.writeFloat(dongia);
        stream.writeUTF(dvt);
        stream.close();
    }
    public void check_Maloai()
    {
        Scanner scan = new Scanner(System.in);
        int id = dslsp.Find_id(maloai);
        while(id == -1 )
            {
                System.out.println("<---Mã loai không đúng--->");
                dslsp.xuat();
                System.out.print("Nhập mã loại: ");
                maloai = scan.nextLine();
                id = dslsp.Find_id(maloai);
            }
    }
    public String check_Maloai(String maloai)
    {
        Scanner scan = new Scanner(System.in);
        int id = dslsp.Find_id(maloai);
        while(id == -1 )
            {
                System.out.println("<---Mã loai không đúng--->");
                dslsp.xuat();
                System.out.print("Nhập mã loại: ");
                maloai = scan.nextLine();
                id = dslsp.Find_id(maloai);
            }
        return maloai;
    }
    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public float getDongia() {
        return dongia;
    }

    public void setDongia(float dongia) {
        this.dongia = dongia;
    }

    public String getDvt() {
        return dvt;
    }

    public void setDvt(String dvt) {
        this.dvt = dvt;
    }

    public String getMaloai() {
        return maloai;
    }

    public void setMaloai(String maloai) {
        this.maloai = maloai;
    }
    
    
}
