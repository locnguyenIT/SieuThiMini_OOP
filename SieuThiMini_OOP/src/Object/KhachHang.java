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
/**
 *
 * @author ntloc
 */
public class KhachHang implements In_Out{

    private String makh;
    private String hokh;
    private String tenkh;
    private String diachi;
    private String sdt;

    public KhachHang() {}
    
    public KhachHang(String makh, String hokh, String tenkh, String diachi, String sdt) 
    {
        this.makh = makh;
        this.hokh = hokh;
        this.tenkh = tenkh;
        this.diachi = diachi;
        this.sdt = sdt;
    }
    @Override
    public void nhap()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Nhập Mã khách hàng: ");
        makh=in.nextLine();
        System.out.print("Nhập Họ: ");
        hokh=in.nextLine();
        System.out.print("Nhập Tên: ");
        tenkh=in.nextLine();
        System.out.print("Nhập Địa chỉ: ");
        diachi=in.nextLine();
        System.out.print("Nhập sđt: ");
        sdt=in.nextLine();
        
    }
    public void nhap(String makh)
    {
        Scanner in = new Scanner(System.in);
        this.makh = makh;
        System.out.print("Nhập Họ: ");
        hokh=in.nextLine();
        System.out.print("Nhập Tên: ");
        tenkh=in.nextLine();
        System.out.print("Nhập Địa chỉ: ");
        diachi=in.nextLine();
        System.out.print("Nhập sđt: ");
        sdt=in.nextLine();
        
    }
    @Override
    public void xuat()
    {
        System.out.printf("| %10s | %10s | %15s | %35s | %10s |\n",makh,hokh,tenkh,diachi,sdt);
    }
    public void WriteFile(String file)
    {
        try
        {
           DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(file,Boolean.TRUE)); // Ghi tiep vao file
           outputStream.writeUTF(makh);
           outputStream.writeUTF(hokh);
           outputStream.writeUTF(tenkh);
           outputStream.writeUTF(diachi);
           outputStream.writeUTF(sdt);
           outputStream.close();
        }catch(IOException e){System.out.println(e);}
        
    }
    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getHokh() {
        return hokh;
    }

    public void setHokh(String hokh) {
        this.hokh = hokh;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
    
    
}
