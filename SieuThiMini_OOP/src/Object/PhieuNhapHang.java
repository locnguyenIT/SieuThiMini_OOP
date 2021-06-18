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
import sieuthimini_oop.DanhSachNhaCungCap;
/**
 *
 * @author ntloc
 */
public class PhieuNhapHang {
    private String mapnh;
    private String mancc;
    private String tenncc;
    private float tongtien;
    private String date;
    private DanhSachNhaCungCap dsncc = new DanhSachNhaCungCap();
    public PhieuNhapHang() throws IOException
    {
        this.dsncc.ReadFile();
    }

    public PhieuNhapHang(String mapnh, String mancc, String tenncc,float tongtien, String date) throws IOException 
    {
        this.dsncc.ReadFile();
        this.mapnh = mapnh;
        this.mancc = mancc;
        this.tenncc = tenncc;
        this.tongtien = tongtien;
        this.date = date;
    }
    public void nhap(String mapnh) throws IOException
    {
        Scanner in = new Scanner(System.in);
        this.mapnh = mapnh;
        System.out.print("Nhập Mã nhà cung cấp: ");
        mancc = in.nextLine();
        
        check_Mancc();
        //tenncc = dsncc.getTenncc(mancc);
        System.out.println("Tên NCC: " +tenncc);
         

        date = "";
        tongtien = 0;
    }
    public void check_Mancc()
    {
        Scanner scan = new Scanner(System.in);
        int id = dsncc.Find_id(mancc);
        while(id == -1 )
        {
                System.out.println("<---Mã nhà cung cấp không đúng--->");
                dsncc.xuat();
                System.out.print("Nhập Mã nhà cung cấp: ");
                mancc = scan.nextLine();
                id = dsncc.Find_id(mancc);
        }
        this.tenncc = dsncc.getTenncc(mancc);
        //return id;
    }
    public void xuat()
    {
        System.out.printf("| %10s | %10s | %15s | %15s | %30s |\n",mapnh,mancc,tenncc,tongtien,date);
    }
    public void WriteFile(String file) throws IOException 
    {
        DataOutputStream outStream = new DataOutputStream(new FileOutputStream(file,Boolean.TRUE));
        outStream.writeUTF(mapnh);
        outStream.writeUTF(mancc);
        outStream.writeUTF(tenncc);
        outStream.writeFloat(tongtien);
        outStream.writeUTF(date);
        //System.out.printf("| %10s | %10s | %10s |%10s | %10s | %10s | %10s |\n",mahd,makh,manv,tennv,tenkh,date,tongtien);
        outStream.close();
    }     
    public void nhap()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Nhập Mã phiếu nhập hàng: ");
        mapnh = in.nextLine();
        System.out.print("Nhập Mã NCC: ");
        mancc = in.nextLine();
        System.out.print("Nhập Tên NCC: ");
        tenncc = in.nextLine();
        this.tongtien = 0;
        this.date = "";
    }
    public String getMapnh() {
        return mapnh;
    }

    public void setMapnh(String mapnh) {
        this.mapnh = mapnh;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public DanhSachNhaCungCap getDsncc() {
        return dsncc;
    }

    public void setDsncc(DanhSachNhaCungCap dsncc) {
        this.dsncc = dsncc;
    }


    public float getTongtien() {
        return tongtien;
    }

    public void setTongtien(float tongtien) {
        this.tongtien = tongtien;
    }
    
   
    
}
