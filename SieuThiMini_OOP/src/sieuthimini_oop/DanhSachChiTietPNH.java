/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sieuthimini_oop;

import Object.ChiTietHD;
import Object.ChiTietPNH;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author ntloc
 */
public class DanhSachChiTietPNH implements In_Out{
    private int n;
    private ChiTietPNH[] ctpnh = new ChiTietPNH[2];
    private DanhSachPhieuNhapHang dspnh = new DanhSachPhieuNhapHang();
    private static final String file ="danhsach_ctpnh.txt";
    public DanhSachChiTietPNH(){}
    @Override
    public void nhap()
    {
        
    }
    @Override
    public void xuat()
    {
        System.out.printf("| %10s | %10s | %15s | %10s | %10s | %10s | %10s | %10s |\n","Mã PNH","Mã SP","Tên SP","Đơn giá","Số lượng","Thành tiền","Giảm giá %","Tổng tiền");
        for(int i=0;i<n;i++)
        {
            ctpnh[i].xuat(); //hd[i].xuat = new Object() inside switch case 
        }    
    }
    public void Add(int sl, String mapnh) throws IOException
    {
        int dem =0;
        ctpnh = Arrays.copyOf(ctpnh, n+sl);
        DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(file,Boolean.TRUE));
        for(int i=n; i< n +sl; i++)
        {
            System.out.println("-----Sản phẩm "+(++dem)+"-----");
            //System.out.println(i);
            ctpnh[i] = new ChiTietPNH();
            ctpnh[i].nhap(mapnh);
            ctpnh[i].WriteFile(file);
            
        }
        this.n = n+sl;
        Update(mapnh);
        //System.out.println(n);
        outputStream.close();
    }
    public void Delete(String mapnh) throws IOException
    {
        DanhSachSanPham dssp = new DanhSachSanPham();
        dssp.ReadFile();
        SanPham[] sp = dssp.getAll();
        int id = Find_id(mapnh);
        if(id >= 0)
        {
            for(int j=0; j< sp.length - 1; j++)
            {
                if(ctpnh[id].getMasp().equals(sp[j].getMasp()))
                {
                    int slsp = dssp.getSL(sp[j].getMasp());    //Update SlSP
                    //ystem.out.println(sp[j].getMasp()+ " "+slsp);
                    int setSl = slsp - ctpnh[id].getSl();
                    //System.out.println(sp[j].getMasp()+ "  "+setSl);
                    dssp.setSl(sp[j].getMasp(),setSl);
                }
            }
           for(int i=id; i<n-1;i++)
           {
               ctpnh[i] = ctpnh[i+1]; //Ghi de len gia tri phan tu can xoa
           }
           ctpnh = Arrays.copyOf(ctpnh, n-1); //Sau khi xóa thì copy giảm sl nv
           this.n = n-1;
           Delete(mapnh); //Đệ quy. Xóa các cthd cùng mapnh
        }
        WriteFile();
        //System.out.println(n); //n = sl nhan vien sau khi delete
    }
    public void Delete_CTPNH(String mapnh,String masp) throws IOException
    {
        DanhSachSanPham dssp = new DanhSachSanPham();
        dssp.ReadFile();
        int id = Find_Masp(mapnh,masp);
        if(id >= 0)
        {
           int slsp = dssp.getSL(masp);    //Update SlSP
           int setSl = slsp - ctpnh[id].getSl();
           dssp.setSl(masp,setSl);
            //System.out.println(id);
           for(int i=id; i<n-1;i++)
           {    
               ctpnh[i] = ctpnh[i+1]; //Dich sang trai gia tri phan tu can xoa
           }
           ctpnh = Arrays.copyOf(ctpnh, n-1); //Sau khi xóa thì copy giảm sl nv
           this.n = n-1;
        }
        
        Update(mapnh);    
        WriteFile();
        //System.out.println(n); //n = sl nhan vien sau khi delete
    }
    public void WriteFile() throws IOException
    {
        DataOutputStream outStream = new DataOutputStream(new FileOutputStream(file));
        for(int i=0;i<n;i++)
        {
            ctpnh[i].WriteFile(file);
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
                    String mapnh = inputStream.readUTF();
                    String masp = inputStream.readUTF();
                    String tensp = inputStream.readUTF(); 
                    float dongia = inputStream.readFloat();
                    int sl = inputStream.readInt();
                    float thanhtien = inputStream.readFloat();
                    int giamgia = inputStream.readInt();
                    float tongtien = inputStream.readFloat();
                    ctpnh[i] = new ChiTietPNH(mapnh, masp, tensp, sl, dongia, thanhtien, giamgia, tongtien);
                    i++;
                    ctpnh = Arrays.copyOf(ctpnh,i+1);
                    
                }
            }
            catch(IOException e){}
            this.n = i; // n = sl nhan vien trong file
            //System.out.println(n);
    }
     public void Set_MASP(String setMasp,String mapnh, String masp) throws IOException
    {
        DanhSachSanPham dssp = new DanhSachSanPham();
        dssp.ReadFile();
        int id = Find_Masp(mapnh, masp);
        ctpnh[id].setMasp(setMasp); //SetMasp người dùng nhập
        String setTen = "";
        float setDongia= 0;
        String ctpnh_masp = ctpnh[id].getMasp(); //Get masp khi vừa set masp trước đó
        if(ctpnh_masp.equals(dssp.getMasp(ctpnh_masp))) //Kiểm tra xem masp vừa get này có trong dữ liệu dssp(masp) 
        {
            //True
            setTen = dssp.getTenSP(ctpnh_masp);   // Gán lại tensp thuộc masp đó có trong dssp
            setDongia = dssp.getDongiasp(ctpnh_masp); //getdongia thuoc masp đó có trong dssp
        }
        ctpnh[id].setTensp(setTen);          //SetTensp
        System.out.println("Tên sản phẩm: "+setTen);
        ctpnh[id].setDongia(setDongia);      //SetDongiasp
        System.out.println("Đơn giá: "+setDongia);
        
        int sl = ctpnh[id].getSl();  
        System.out.println("Số lượng: "+sl);
        
        float thanhtien = setDongia*sl;
        ctpnh[id].setThanhtien(thanhtien);    //SetThanhtien
        System.out.println("Thành Tiền: "+thanhtien);
        int giamgia = ctpnh[id].getGiamgia();
        System.out.println("Giảm giá %: "+giamgia);
        
        float tongtienctpnh = thanhtien-(thanhtien*giamgia)/100;
        ctpnh[id].setTongtien(tongtienctpnh); //SetTongtien
        System.out.println("Tổng tiền CTPNH: "+tongtienctpnh);
        
        WriteFile();
    }
     public void Set_SLSP(int sl, String mapnh, String masp) throws IOException
    {
        DanhSachSanPham dssp = new DanhSachSanPham();
        dssp.ReadFile();
        SanPham[] sp = dssp.getAll();
        int id = Find_Masp(mapnh, masp);
        if(id >= 0)
        {
           int slsp = dssp.getSL(masp);    //Update SlSP
           int setSl = slsp - ctpnh[id].getSl();
           dssp.setSl(masp,setSl);
           ctpnh[id].setSl(sl); //SetSl người dùng nhập
           dssp.setSl(masp,setSl + sl);
        }   
        int slsp = ctpnh[id].getSl();
        float dongia = ctpnh[id].getDongia();
        float thanhtien = 0;
        thanhtien= dongia*slsp;
        ctpnh[id].setThanhtien(thanhtien);    //SetThanhtien
        System.out.println("Thành Tiền: "+thanhtien);
        
        int giamgia = ctpnh[id].getGiamgia();
        float tongtienctpnh = thanhtien-(thanhtien*giamgia)/100;
        ctpnh[id].setTongtien(tongtienctpnh); //SetTongtien
        System.out.println("Tổng tiền CTPNH: "+tongtienctpnh);
        WriteFile();
        
    }
      public void Set_GiamGia(int setGiamgia,String mapnh,String masp) throws IOException
    {
        int id = Find_Masp(mapnh, masp);
        ctpnh[id].setGiamgia(setGiamgia); //SetSl người dùng nhập
        int gg = ctpnh[id].getGiamgia();
        
        float thanhtien = ctpnh[id].getThanhtien();
        float tongtienctpnh = thanhtien-(thanhtien*gg)/100;
        ctpnh[id].setTongtien(tongtienctpnh); //SetTongtien
        System.out.println("Tổng tiền CTPNH: "+tongtienctpnh);
        WriteFile();
    }
    public void Update(String mapnh) throws IOException
    {
        dspnh.ReadFile();
        Date thoigian = new Date();         //Update Thoi gian
        dspnh.setThoiGian(mapnh,thoigian.toString());
        float tongtienpnh = getTongTienPNH(mapnh);
        //System.out.println(tongtienhd);
        dspnh.setTongtien(mapnh,tongtienpnh);       //Update Tongtien HD       //Update Tongtien HD
    }
    public void xuat_ctpnh(String mapnh)
    {
//        float tt =0;
//        float tongtien=0;
        int id = Find_id(mapnh);
        if(id>=0)
        {
            System.out.println("                         -----------------Chi tiết phiếu nhập hàng------------------");
            System.out.printf("| %10s | %10s | %15s | %10s | %10s | %10s | %10s | %10s |\n","Mã PNH","Mã SP","Tên SP","Đơn giá","Số lượng","Thành tiền","Giảm giá %","Tổng tiền CTPNH");
            for(int i = id ; i < n ; i++)   //Quet dscthd  để lấy ra mahd trùng nhau
            {
                if(ctpnh[i].getMapnh().equals(mapnh))
                {
                    //System.out.println(i);
                    //tongtien=cthd[i].getTongtien();
                    ctpnh[i].xuat();
                    //System.out.println("Tong tien cthd "+i+": "+ tongtien);
                    //tt+=tongtien;
                }
            }
        }
        //System.out.println("tong tien xuat: "+tt);
    }
     public float getTongTienPNH(String mapnh)
    {
        float tt_ctpnh=0;
        float tt_pnh =0;
        int id = Find_id(mapnh);
        if(id >=0)
        {
            for(int i =id; i<n ; i++)
            {
                if(ctpnh[i].getMapnh().equals(mapnh))
                {
//                    System.out.println(i);
                    tt_ctpnh = ctpnh[i].getTongtien();
//                    System.out.println("Tong tien cthd "+i+": "+ tt);
                    tt_pnh +=tt_ctpnh;
                }
            }
        }
        //System.out.println("tong tien hoadon: "+ ttcthd);
        return tt_pnh;
    }
    public int Find_id(String mapnh)
    {
        int vt = -1;
        for(int i=0;i<n;i++) 
        {
            if(ctpnh[i].getMapnh().equals(mapnh))
            {
                vt = i; 
                break; // Out for
            }
        }
        //System.out.println(vt);
        return vt; // 
    }
    public int Find_Masp(String mapnh,String masp)
    {
        int id = Find_id(mapnh);
        int vt = -1;
        for(int i=id;i<n;i++) 
        {
            if(ctpnh[i].getMasp().equals(masp))
            {
                vt = i; 
                break; // Out for
            }
        }
        //System.out.println(vt);
        //cthd[vt].xuat();
        return vt; // 
    }
    public void ThongKe_NhapHang()
    {
        DanhSachSanPham dssp = new DanhSachSanPham();
        dssp.ReadFile();
        SanPham[] sp = dssp.getAll();
        int total_sl = 0;
        float total_dt = 0 ;
        //Tìm Masp SanPham[] có trong Masp ChiTietHD
        //Đếm số lượng của từng Masp đã bán trong CTHD
        //Tính doanh thu của từng Masp đã bán trong CTHD
        System.out.println("              ----------THỐNG KÊ NHẬP HÀNG----------");
        System.out.printf("| %10s | %15s | %10s | %15s |\n","Mã SP", "Tên SP", "SL nhập", "Doanh thu");
        for(int i =0; i<sp.length - 1 ; i++) 
        {
            float doanhthu =0;
            int sl = 0;
            for(int j=0; j< n; j++)
            {
                if(sp[i].getMasp().equals(ctpnh[j].getMasp()))
                {
                    doanhthu += ctpnh[j].getTongtien();
                    sl += ctpnh[j].getSl();
                }
            }
            System.out.printf("| %10s | %15s | %10s | %15s |\n",sp[i].getMasp(),sp[i].getTensp(),sl,doanhthu);
            total_sl += sl;
            total_dt += doanhthu;
        }
        System.out.printf("  %10s   %15s | %10s | %15s |\n","","","----------","---------------");
        System.out.printf("  %10s   %15s | %10s | %15s |\n","","","TC: "+total_sl,"TC: "+total_dt);
    }
    
}
