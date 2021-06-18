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
import sieuthimini_oop.DanhSachKhachHang;
import sieuthimini_oop.DanhSachNhanVien;
/**
 *
 * @author ntloc
 */
public class HoaDon {
    private String mahd;
    private String makh;
    private String manv;
    private String tennv;
    private String tenkh;
    private String date;
    private String thanhtoan;
    private float tongtien;
    private DanhSachKhachHang dskh = new DanhSachKhachHang();
    private DanhSachNhanVien dsnv = new DanhSachNhanVien();
    public HoaDon() throws IOException
    {
        this.dskh.ReadFile();
        this.dsnv.ReadFile();
    }

    public HoaDon(String mahd,String manv, String tennv,String makh, String tenkh,float tongtien,String thanhtoan, String date) throws IOException
    {
        this.dskh.ReadFile();
        this.dsnv.ReadFile();
        this.mahd = mahd;
        this.manv = manv;
        this.tennv = tennv;
        this.makh = makh;
        this.tenkh = tenkh;
        this.tongtien = tongtien;
        this.thanhtoan = thanhtoan;
        this.date = date;
    }
    public void nhap(String mahd) throws IOException
    {
        Scanner in = new Scanner(System.in);
        this.mahd = mahd;
        System.out.print("Nhập Mã nhân viên: ");
        manv = in.nextLine();
        
        tennv = dsnv.getTennv(manv);
        System.out.println("Tên nhân viên: " +tennv);
         
        System.out.print("Nhập Mã khách hàng: ");
        makh = in.nextLine();
        check_kh(makh);
        
        tenkh = dskh.getTenkh(makh);
        System.out.println("Tên khách hàng: " +tenkh);

        thanhtoan = "";
        date = "";   
        tongtien = 0;
        
    }
    public void check_kh(String makh) throws IOException
    {
        int id = dskh.Find_id(makh);
        while(id < 0)
        {
            Scanner in = new Scanner(System.in);
            System.out.println("<---Không có khách hàng "+makh+" trong danh sách. Vui lòng thêm khách hàng--->");
            dskh.xuat();
            System.out.print("Nhập Mã khách hàng: ");
            makh = in.nextLine();
            dskh.Add(makh);
            id = dskh.Find_id(makh);  
        }
    }
    
    public void nhap()
    {
        Scanner in = new Scanner(System.in);
        this.mahd = mahd;
        System.out.print("Nhập Mã NV: ");
        manv = in.nextLine();
        
        tennv = dsnv.getTennv(manv);
        System.out.println("Tên NV:" +tennv);
         
        System.out.print("Nhập Mã KH: ");
        makh = in.nextLine();
        
        tenkh = dskh.getTenkh(makh);
        System.out.println("Tên KH:" +tenkh);

        date = "";
        tongtien = 0;
    }
    public void xuat()
    {
        System.out.printf("| %10s | %10s | %20s | %10s | %20s | %15s | %10s | %30s |\n",mahd,manv,tennv,makh,tenkh,tongtien,thanhtoan,date);
    }        
    public void WriteFile(String file) throws IOException 
    {
        DataOutputStream outStream = new DataOutputStream(new FileOutputStream(file,Boolean.TRUE));
        outStream.writeUTF(mahd);
        outStream.writeUTF(manv);
        outStream.writeUTF(tennv);
        outStream.writeUTF(makh);
        outStream.writeUTF(tenkh);
        outStream.writeFloat(tongtien);
        outStream.writeUTF(thanhtoan);
        outStream.writeUTF(date);
        //System.out.printf("| %10s | %10s | %10s |%10s | %10s | %10s | %10s |\n",mahd,makh,manv,tennv,tenkh,date,tongtien);
        outStream.close();
    } 

    public String getThanhtoan() {
        return thanhtoan;
    }

    public void setThanhtoan(String thanhtoan) {
        this.thanhtoan = thanhtoan;
    }

    public String getMahd() {
        return mahd;
    }

    public void setMahd(String mahd) {
        this.mahd = mahd;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getTongtien() {
        return tongtien;
    }

    public void setTongtien(float tongtien) {
        this.tongtien = tongtien;
    }

    public DanhSachKhachHang getDskh() {
        return dskh;
    }

    public void setDskh(DanhSachKhachHang dskh) {
        this.dskh = dskh;
    }

    public DanhSachNhanVien getDsnv() {
        return dsnv;
    }

    public void setDsnv(DanhSachNhanVien dsnv) {
        this.dsnv = dsnv;
    }
    
    
    
    
}
