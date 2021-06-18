/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import sieuthimini_oop.DanhSachSanPham;
import sieuthimini_oop.DanhSachPhieuNhapHang;
import sieuthimini_oop.In_Out;
/**
 *
 * @author ntloc
 */
public class ChiTietPNH  implements In_Out{
    private String mapnh;
    private String masp;
    private String tensp;
    private int sl;
    private float dongia;
    private float thanhtien;
    private int giamgia;
    private float tongtien;
    private DanhSachSanPham dssp = new DanhSachSanPham();
    private DanhSachPhieuNhapHang dspnh = new DanhSachPhieuNhapHang();
    public ChiTietPNH()
    {
        dssp.ReadFile();
        dspnh.ReadFile();
    }

    public ChiTietPNH(String mapnh, String masp, String tensp, int sl, float dongia, float thanhtien, int giamgia, float tongtien) {
        dssp.ReadFile();
        dspnh.ReadFile();
        this.mapnh = mapnh;
        this.masp = masp;
        this.tensp = tensp;
        this.sl = sl;
        this.dongia = dongia;
        this.thanhtien = thanhtien;
        this.giamgia = giamgia;
        this.tongtien = tongtien;
    }
    @Override
    public void xuat()
    {
        System.out.printf("| %10s | %10s | %15s | %10s | %10s | %10s | %10s | %15s |\n",mapnh,masp,tensp,dongia,sl,thanhtien,giamgia,tongtien);
    }
    public void nhap(String mapnh) throws IOException
    {
        Scanner in = new Scanner(System.in); 
        this.mapnh = mapnh;
        System.out.print("Nhập Mã sản phẩm: ");
        masp = in.nextLine();
        
        tensp = dssp.getTenSP(masp);
        System.out.println("Tên sản phẩm: "+tensp);
        
        dongia = dssp.getDongiasp(masp);
        System.out.println("Đơn giá: "+dongia);
        
        System.out.print("Số lượng: ");
        sl = in.nextInt();
        
        thanhtien= dongia*sl;
        System.out.println("Thành tiền: "+thanhtien);
        
        System.out.print("Giảm giá %: ");
        giamgia=in.nextInt();
        
        
        tongtien = thanhtien-(thanhtien*giamgia)/100;
        System.out.println("Tổng tiền: "+tongtien);
        Update();
    }
    public void Update() throws IOException
    {
        int slsp = dssp.getSL(masp);    //Update SlSP
        int setSl = slsp + sl;
        dssp.setSl(masp,setSl);
        
        Date thoigian = new Date();         //Update Thoi gian
        dspnh.setThoiGian(mapnh,thoigian.toString());
        
        float tongtienHD = dspnh.getTongtien(mapnh); //Update Tongtien
        dspnh.setTongtien(mapnh,tongtienHD+tongtien);
        
//        System.out.println(tongtienHD);
//        System.out.println(tongtien);
        
    }
     public void WriteFile(String file) throws IOException 
    {
        DataOutputStream outStream = new DataOutputStream(new FileOutputStream(file,Boolean.TRUE));
        outStream.writeUTF(mapnh);
        outStream.writeUTF(masp);
        outStream.writeUTF(tensp);
        outStream.writeFloat(dongia);
        outStream.writeInt(sl);
        outStream.writeFloat(thanhtien);
        outStream.writeInt(giamgia);
        outStream.writeFloat(tongtien);
        outStream.close();
    } 
    public String getMapnh() {
        return mapnh;
    }

    public void setMapnh(String mapnh) {
        this.mapnh = mapnh;
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

    public float getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(float thanhtien) {
        this.thanhtien = thanhtien;
    }

    public int getGiamgia() {
        return giamgia;
    }

    public void setGiamgia(int giamgia) {
        this.giamgia = giamgia;
    }

    public float getTongtien() {
        return tongtien;
    }

    public void setTongtien(float tongtien) {
        this.tongtien = tongtien;
    }

    public DanhSachSanPham getDssp() {
        return dssp;
    }

    public void setDssp(DanhSachSanPham dssp) {
        this.dssp = dssp;
    }

    
    @Override
    public void nhap() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
