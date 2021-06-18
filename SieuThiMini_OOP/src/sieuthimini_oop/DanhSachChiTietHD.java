/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sieuthimini_oop;

import Object.ChiTietHD;
import Object.HoaDon;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
/**
 *
 * @author ntloc
 */
public class DanhSachChiTietHD implements In_Out{
    private int n;
    private ChiTietHD[] cthd = new ChiTietHD[2];
    private DanhSachHoaDon dshd = new DanhSachHoaDon();
    private static final String file ="danhsach_cthd.txt";
    @Override
    public void xuat()
    {
        System.out.printf("| %10s | %10s | %15s | %10s | %10s | %10s | %10s | %10s |\n","Mã HD","Mã SP","Tên SP","Đơn giá","Số lượng","Thành tiền","Giảm giá %","Tổng tiền");
        for(int i=0;i<n;i++)
        {
            cthd[i].xuat(); //hd[i].xuat = new Object() inside switch case 
        }    
    }
    public void Add(int sl, String mahd) throws IOException
    {
        int dem =0;
        cthd = Arrays.copyOf(cthd, n+sl);
        DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(file,Boolean.TRUE));
        for(int i=n; i< n +sl; i++)
        {
            System.out.println("-----Sản phẩm "+(++dem)+"-----");
            //System.out.println(i);
            cthd[i] = new ChiTietHD();
            cthd[i].nhap(mahd);
            cthd[i].WriteFile(file);
        }
        this.n = n+sl;
        Update_HD(mahd);
        //System.out.println(n);
        outputStream.close();
    }
    public void Delete(String mahd) throws IOException
    {
        DanhSachSanPham dssp = new DanhSachSanPham();
        dssp.ReadFile();
        SanPham[] sp = dssp.getAll();
        int id = Find_id(mahd);
        if(id >= 0)
        {
            for(int j=0; j< sp.length - 1; j++) //Set lại slsp trong dssp trước khi xóa các cthd[i]
            {
                if(cthd[id].getMasp().equals(sp[j].getMasp()))
                {
                    int slsp = dssp.getSL(sp[j].getMasp());    //Update SlSP
                    //ystem.out.println(sp[j].getMasp()+ " "+slsp);
                    int setSl = slsp + cthd[id].getSl();
                    //System.out.println(sp[j].getMasp()+ "  "+setSl);
                    dssp.setSl(sp[j].getMasp(),setSl);
                }
            }
            for(int i=id; i<n-1; i++)
            {
                cthd[i] = cthd[i+1]; //Ghi de len gia tri phan tu can xoa
            }
           cthd = Arrays.copyOf(cthd, n-1); //Sau khi xóa thì copy giảm sl nv
           this.n = n-1;
           Delete(mahd); //Đệ quy. Xóa các cthd cùng mahd
        }
        //Update(mahd);
        WriteFile();
        //System.out.println(n); //n = sl nhan vien sau khi delete
    }
    public void Delete_CTHD(String mahd,String masp) throws IOException
    {
        DanhSachSanPham dssp = new DanhSachSanPham();
        dssp.ReadFile();
        int id = Find_Masp(mahd,masp);
        if(id >= 0)
        {
           int slsp = dssp.getSL(masp);    //Update SlSP
           int setSl = slsp + cthd[id].getSl();
           dssp.setSl(masp,setSl);
           //System.out.println(id);
           for(int i=id; i<n-1;i++)
           {    
               cthd[i] = cthd[i+1]; //Dich sang trai gia tri phan tu can xoa
               //cthd[i+1].getSl(); 
           }
           cthd = Arrays.copyOf(cthd, n-1); //Sau khi xóa thì copy giảm sl nv
           this.n = n-1;
        }
        Update_HD(mahd);    
        WriteFile();
        //System.out.println(n); //n = sl nhan vien sau khi delete
    }
    public void setPT_thanhtoan(String mahd, String pttt) throws IOException
    {
        //dshd.ReadFile();
        dshd.setPT_thanhtoan(mahd, pttt);
    }
    public void Update_HD(String mahd) throws IOException
    {  
        dshd.ReadFile();
        Date thoigian = new Date();         //Update Thoi gian
        dshd.setThoiGian(mahd,thoigian.toString());
        float tongtienhd = getTongTienHD(mahd);
        //System.out.println(tongtienhd);
        dshd.setTongtien(mahd,tongtienhd);
    }
    public void WriteFile() throws IOException
    {
        DataOutputStream outStream = new DataOutputStream(new FileOutputStream(file));
        for(int i=0;i<n;i++)
        {
            cthd[i].WriteFile(file);
        }
        outStream.close();
    }
    public void ReadFile() throws IOException
    {
        int i = 0; 
        try{
             DataInputStream inputStream = new DataInputStream(new FileInputStream(file));
             while(true)
                {                    
                    String mahd = inputStream.readUTF();
                    String masp = inputStream.readUTF();
                    String tensp = inputStream.readUTF(); 
                    float dongia = inputStream.readFloat();
                    int sl = inputStream.readInt();
                    float thanhtien = inputStream.readFloat();
                    int giamgia = inputStream.readInt();
                    float tongtien = inputStream.readFloat();
                    cthd[i] = new ChiTietHD(mahd, masp, tensp, sl, dongia, thanhtien, giamgia, tongtien);
                    //System.out.println(i);
                    i++;
                    cthd = Arrays.copyOf(cthd,i+1);
                    
                    
                }
            }
            catch(IOException e){}
            this.n = i; // n = sl nhan vien trong file
            //System.out.println("aa"+n);
    } 
   
    public int Find_id(String mahd)
    {
        int vt = -1;
        for(int i=0;i<n;i++) 
        {
            if(cthd[i].getMahd().equals(mahd))
            {
                vt = i; 
                break; // Out for
            }
        }
        //System.out.println(vt);
        return vt; // 
    }
    public int Find_Masp(String mahd,String masp)
    {
        int id = Find_id(mahd);
        int vt = -1;
        for(int i=id;i<n;i++) 
        {
            if(cthd[i].getMasp().equals(masp))
            {
                vt = i; 
                break; // Out for
            }
        }
        //System.out.println(vt);
        //cthd[vt].xuat();
        return vt; // 
    }
    public void xuat_cthd(String mahd)
    {
        int id = Find_id(mahd);
        if(id>=0)
        {
            System.out.println("                                -----------------Chi tiết hóa đơn------------------");
            System.out.printf("| %10s | %10s | %15s | %10s | %10s | %15s | %10s | %15s |\n","Mã HD","Mã SP","Tên SP","Đơn giá","Số lượng","Thành tiền","Giảm giá %","Tổng tiền CTHD");
            for(int i = id ; i < n ; i++)   //Quet dscthd  để lấy ra mahd trùng nhau
            {
                if(cthd[i].getMahd().equals(mahd))
                {
                    cthd[i].xuat();

                }
            }
        }
        //System.out.println("tong tien xuat: "+tt);
    }
    public void Set_SLSP(int sl, String mahd, String masp) throws IOException
    {
        DanhSachSanPham dssp = new DanhSachSanPham();
        dssp.ReadFile();
        int id = Find_Masp(mahd, masp);
        SanPham[] sp = dssp.getAll();
        //int setSl =0;
        if(id >= 0)
        {
           int slsp = dssp.getSL(masp);    //Update SlSP
           int setSl = slsp + cthd[id].getSl();
           dssp.setSl(masp,setSl);
           cthd[id].setSl(sl); //SetSl người dùng nhập
           dssp.setSl(masp,setSl - sl);
        }   
        int slsp = cthd[id].getSl();
        float dongia = cthd[id].getDongia();
        float thanhtien = 0;
        thanhtien= dongia*slsp;
        cthd[id].setThanhtien(thanhtien);    //SetThanhtien
        System.out.println("Thành Tiền: "+thanhtien);
        int giamgia = cthd[id].getGiamgia();
        float tongtiencthd = thanhtien-(thanhtien*giamgia)/100;
        cthd[id].setTongtien(tongtiencthd); //SetTongtien
        System.out.println("Tổng tiền CTHD: "+tongtiencthd);
        WriteFile();
        
    }
    public void Set_GiamGia(int setGiamgia,String mahd,String masp) throws IOException
    {
        int id = Find_Masp(mahd, masp);
        cthd[id].setGiamgia(setGiamgia); //SetSl người dùng nhập
        int gg = cthd[id].getGiamgia();
        
        float thanhtien = cthd[id].getThanhtien();
        float tongtiencthd = thanhtien-(thanhtien*gg)/100;
        cthd[id].setTongtien(tongtiencthd); //SetTongtien
        System.out.println("Tổng tiền CTHD: "+tongtiencthd);
        WriteFile();
    }
    public void Set_MASP(String setMasp,String mahd, String masp) throws IOException
    {
        DanhSachSanPham dssp = new DanhSachSanPham();
        dssp.ReadFile();
        int id = Find_Masp(mahd, masp);
        cthd[id].setMasp(setMasp); //SetMasp người dùng nhập
        String setTen = "";
        float setDongia= 0;
        String cthd_masp = cthd[id].getMasp(); //Get masp khi vừa set masp trước đó
        if(cthd_masp.equals(dssp.getMasp(cthd_masp))) //Kiểm tra xem masp vừa get này có trong dữ liệu dssp(masp) 
        {
            //True
            setTen = dssp.getTenSP(cthd_masp);   // Gán lại tensp thuộc masp đó có trong dssp
            setDongia = dssp.getDongiasp(cthd_masp); //getdongia thuoc masp đó có trong dssp
        }
        cthd[id].setTensp(setTen);          //SetTensp
        System.out.println("Tên sản phẩm: "+setTen);
        cthd[id].setDongia(setDongia);      //SetDongiasp
        System.out.println("Đơn giá: "+setDongia);
        
        int sl = cthd[id].getSl();  
        System.out.println("Số lượng: "+sl);
        
        float thanhtien = setDongia*sl;
        cthd[id].setThanhtien(thanhtien);    //SetThanhtien
        System.out.println("Thành Tiền: "+thanhtien);
        int giamgia = cthd[id].getGiamgia();
        System.out.println("Giảm giá %: "+giamgia);
        
        float tongtiencthd = thanhtien-(thanhtien*giamgia)/100;
        cthd[id].setTongtien(tongtiencthd); //SetTongtien
        System.out.println("Tổng tiền CTHD: "+tongtiencthd);
        
        WriteFile();
    }
    public float getTongTienHD(String mahd)
    {
        float tt_cthd=0;
        float tt_hd =0;
        int id = Find_id(mahd);
        if(id >=0)
        {
            for(int i =id; i<n ; i++)
            {
                if(cthd[i].getMahd().equals(mahd))
                {
//                    System.out.println(i);
                    tt_cthd = cthd[i].getTongtien();
//                    System.out.println("Tong tien cthd "+i+": "+ tt);
                    tt_hd +=tt_cthd;
                }
            }
        }
        //System.out.println("tong tien hoadon: "+ ttcthd);
        return tt_hd;
    }
    public void ThongKe_SP()
    {
        DanhSachSanPham dssp = new DanhSachSanPham();
        dssp.ReadFile();
        SanPham[] sp = dssp.getAll();
        int total_sl = 0;
        float total_dt = 0 ;
        //Tìm Masp SanPham[] có trong Masp ChiTietHD
        //Đếm số lượng của từng Masp đã bán trong CTHD
        //Tính doanh thu của từng Masp đã bán trong CTHD
        System.out.println("                ----------THỐNG KÊ BÁN HÀNG----------");
        System.out.printf("| %10s | %15s | %10s | %15s |\n","Mã SP", "Tên SP", "SL bán", "Doanh thu");
        for(int i =0; i<sp.length - 1 ; i++) 
        {
            float doanhthu =0;
            int sl = 0;
            for(int j=0; j< n; j++)
            {
                if(sp[i].getMasp().equals(cthd[j].getMasp()))
                {
                    doanhthu += cthd[j].getTongtien();
                    sl += cthd[j].getSl();
                }
            }
            System.out.printf("| %10s | %15s | %10s | %15s |\n",sp[i].getMasp(),sp[i].getTensp(),sl,doanhthu);
            total_sl += sl;
            total_dt += doanhthu;
        }
        System.out.printf("  %10s   %15s | %10s | %15s |\n","","","----------","---------------");
        System.out.printf("  %10s   %15s | %10s | %15s |\n","","","TC: "+total_sl,"TC:  "+total_dt);
        dshd.ReadFile();
        dshd.Thongke_Doanhthu();
    }
    @Override
    public void nhap() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
