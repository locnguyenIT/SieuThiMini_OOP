/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sieuthimini_oop;

import java.util.Arrays;
import java.util.Scanner;
import Object.ThucAn;
import Object.ThucUong;
import Object.HangGiaDung;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ntloc
 */
public class DanhSachSanPham implements In_Out{
    private int n;
    private SanPham[] sp = new SanPham[2];
    private static final String file="danhsachsanpham.txt";
    public DanhSachSanPham(){}    
    @Override
    public void nhap() 
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Nhap so luong san pham: ");
        this.n=in.nextInt();
        sp=Arrays.copyOf(sp, n);
        for(int i=0;i<n;i++)
        {
            sp[i]= new SanPham() {}; //khởi tạo object sp[i] = new SanPham()
            int chon=0;
            LOOP:
            while(true)
            {
                Menu_SanPham();
                chon=in.nextInt();
                switch(chon)
                {
                    case 1: {
                        try {
                            sp[i] = new ThucAn();
                        } catch (IOException ex) {
                            Logger.getLogger(DanhSachSanPham.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                            break LOOP;

                    case 2: {
                        try {
                            sp[i] = new ThucUong();
                        } catch (IOException ex) {
                            Logger.getLogger(DanhSachSanPham.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                            break LOOP;

                    case 3: {
                        try {
                            sp[i] = new HangGiaDung();
                        } catch (IOException ex) {
                            Logger.getLogger(DanhSachSanPham.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                            break LOOP;

                    default:
                        System.err.println("Nhập sai!!!");
                        System.err.println("Vui lòng nhập lại!!!");
                        break;
                }
            }
            sp[i].nhap(); //sp[i].nhap = new Object() inside switch case
            System.out.println("===Lưu thông tin SP thứ "+(i+1)+" thành công!!!===");
        }
    }
    @Override
    public void xuat()
    {
        System.out.printf("| %10s | %10s | %15s | %10s | %10s | %15s | %10s | %10s | %10s | %10s | %10s | %10s |\n",
                           "Mã SP","Mã Loại","Tên SP","Số Lượng","Đơn Giá","Đơn vị tính","Nguồn","Trạng Thái","Dung Tích","Tính Chất","Vật liệu","Công dụng");
        for(int i=0;i<n;i++)
        {
            sp[i].xuat(); 
        }
    }
    public void Add_SP()
    {
        try
        {
            int id = 0;
            Scanner in = new Scanner(System.in);
            DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(file,Boolean.TRUE));
            sp = Arrays.copyOf(sp, n+1); //coppy mảng mới kh sau khi thêm sl sản phẩm
            for(int i=n; i< n+1; i++) // n = sl san pham trong file
            {               
                    sp[i]= new SanPham() {}; //khởi tạo object sp[i] = new SanPham()
                    int chon=0;
                    LOOP:
                    while(true)
                    {
                        Menu_SanPham();
                        chon=in.nextInt();
                        switch(chon)
                        {
                            case 1: sp[i] = new ThucAn();
                                    break LOOP;
                            case 2: sp[i] = new ThucUong();
                                    break LOOP;
                            case 3: sp[i] = new HangGiaDung();
                                    break LOOP;
                            default:
                                System.out.println("Nhập sai!!!");
                                System.out.println("Vui lòng nhập lại!!!");
                                break;
                        }
                    }
                    System.out.print("Nhập Mã sản phẩm: ");
                    in.nextLine();
                    String masp = in.nextLine();
                    id = Find_id(masp);
                    //System.out.println(id);
                    Check_Id(i, id, masp);
               
            }
            outputStream.close();
        }catch(IOException e){System.err.println(e);}
        this.n = n+1;      // n = sl san pham sau khi them 
        //System.out.println(n); 
    }
    public void Check_Id(int i, int id, String masp) throws IOException
    {
        CHECK:
        while(true)
        {
            LOOP:
            while(id != -1)
            {
                System.out.println("<---Mã sản phẩm "+masp+" đã tồn tại. Vui lòng nhập lại--->");
                int chon = 0;
                Scanner in = new Scanner(System.in);
                System.out.println("Tiếp tục = 1| Hủy bỏ = 0");
                System.out.print("Nhập lựa chọn: ");
                chon = in.nextInt();            
                switch(chon)
                {
                    case 1:
                        sp[i] = new SanPham(){};
                        SANPHAM:
                        while(true)
                        {
                           Menu_SanPham();
                           int chon_sp = 0;
                           chon_sp = in.nextInt();
                           switch(chon_sp)
                           {
                               case 1:
                                   sp[i] = new ThucAn();
                                    break SANPHAM;
                               case 2: 
                                   sp[i] = new ThucUong();
                                    break  SANPHAM;
                               case 3: 
                                    sp[i] = new HangGiaDung();
                                    break  SANPHAM;
                           }
                        }
                        System.out.print("Nhập Mã sản phẩm: ");
                        in.nextLine();
                        masp = in.nextLine();
                        id=Find_id(masp);
                        //System.out.println(id);
                       break;
                    case 0:
                        break CHECK;
                    }
            }
            sp[i].nhap(masp);
            System.out.println("<---Thêm sản phẩm "+sp[i].getMasp()+ " thành công--->");
            sp[i].WriteFile(file);
            break CHECK;
                
        }
    }
    public int Find_id(String masp)
    {
        int vt = -1;
        for(int i=0;i<n;i++) //Quet sl sản phẩm
        {
            if(sp[i].getMasp().equals(masp))
            {
                vt = i; //Tìm thấy mã sp
                break; // Out for
            }
        }
        //System.out.println(vt);
        return vt; // tra ve Id của sản phẩm
    }
    public void Search_Masp(String masp)
    {
        int id = Find_id(masp);
        if(id >= 0)
        {
            System.out.println("                                       "
                              + "              -----------SẢN PHẨM "+masp+"----------");
            System.out.printf("| %10s | %10s | %15s | %10s | %10s | %15s | %10s | %10s | %10s | %10s | %10s | %10s |\n",
                           "Mã SP","Mã loại","Tên SP","Số luợng","Đơn giá","Đơn vị tính","Nguồn","Trạng Thái","Dung Tích","Tính Chất","Vật liệu","Công dụng");
            sp[id].xuat();
        }
        else
        {
            System.out.println("<---Không có sản phẩm "+masp+" trong danh sách--->");
        }
    }
    public void Search_TenSP(String tensp)
    {
        for( int i=0; i<n; i++)
        {
            if(sp[i].getTensp().contains(tensp))
            {
                System.out.println("                                       "
                              + "              -----------SẢN PHẨM "+tensp+"----------");
                System.out.printf("| %10s | %10s | %15s | %10s | %10s | %10s | %10s | %10s | %10s | %10s | %10s | %10s |\n",
                           "Ma SP","Ma Loai","Ten SP","So Luong","Don Gia","ĐVT","Nguồn","Trạng Thái","Dung Tích","Tính Chất","Vật liệu","Công dụng");
                sp[i].xuat();
            }
            else
            {
                System.out.println("<---Không có Sản phẩm "+tensp+" trong danh sách--->");
            }
        }   
    }
    public void Delete_Masp(String masp) throws IOException
    {   
        int id = Find_id(masp);
        if(id >= 0)
        {
           for(int i=id; i<n-1;i++)
           {
               sp[i] = sp[i+1]; //Ghi de len gia tri phan tu can xoa
           }
           sp = Arrays.copyOf(sp, n-1); //Sau khi xóa thì copy giảm sl nv
           this.n = n-1;
           System.out.println("<---Xóa sản phẩm "+masp+ " thành công--->");
        }
        else
        {
            System.out.println("<---Không có sản phẩm "+masp+ " trong danh sách<---");
        }
        WriteFile();
        //System.out.println(n); //n = sl nhan vien sau khi delete
    }
    
    public void ReadFile() 
    {
        int i = 0;
        try{
            DataInputStream inStream = new DataInputStream(new FileInputStream(file));
            try{
                while(true)
                {
                    int loai = 0;
                    loai = inStream.readInt();
                    String masp = inStream.readUTF();
                    String maloai = inStream.readUTF();
                    String ten = inStream.readUTF();
                    int sl = inStream.readInt();
                    float gia = inStream.readFloat();
                    String dvt = inStream.readUTF();
                    switch(loai)
                    {
                        case 1: //Thuc An
                            String nguon = inStream.readUTF();
                            String trangthai = inStream.readUTF();
                            sp[i] = new ThucAn(masp,maloai,ten,sl,gia,dvt,nguon,trangthai);
                        break;  
                        case 2: //Thuc Uong
                            String dungtich = inStream.readUTF();
                            String tinhchat = inStream.readUTF();
                            sp[i] = new ThucUong(masp,maloai,ten,sl,gia,dvt,dungtich,tinhchat);
                        break;
                        case 3: //Hang Gia Dung
                            String vatlieu = inStream.readUTF();
                            String congdung = inStream.readUTF();
                            sp[i] = new HangGiaDung(masp,maloai,ten,sl,gia,dvt,vatlieu,congdung);
                        break;
                    }
                    i++;
                    sp = Arrays.copyOf(sp,i+1);
                }
            }
            catch(IOException e){}
        }
        catch(FileNotFoundException e){}
        this.n = i; // n = so luong san pham trong file
        //System.out.println(n);
    }
    public void WriteFile() throws IOException
    {
        DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
        for(int i=0;i<n;i++)
        {
            if(sp[i] instanceof ThucAn)
            {
                sp[i]=(ThucAn)sp[i];
                sp[i].WriteFile(file);
                //System.out.println("Thuc an");
            }
            if(sp[i] instanceof ThucUong) 
            {
                sp[i]=(ThucUong)sp[i];
                sp[i].WriteFile(file);
                //System.out.println("Thuc uong");
            }
            if(sp[i] instanceof HangGiaDung)
            {
                sp[i]=(HangGiaDung)sp[i];
                sp[i].WriteFile(file);
                //System.out.println("Hang gia dung");
            }
        }
        out.close();
    }
    public int getSL(String masp)
    {
        return sp[Find_id(masp)].getSl();
    }
    public void setSl(String masp,int sl) throws IOException
    {
        this.sp[Find_id(masp)].setSl(sl);
        WriteFile();
    }
    public SanPham[] getAll()
    {
        for(int i= 0; i< n; i++)
        {
            sp[i].getMasp();
        }
        return sp;
    }
    public String getTenSP(String masp)
    {
       return sp[Find_id(masp)].getTensp();
    }
    public float getDongiasp(String masp)
    {
        return sp[Find_id(masp)].getDongia();
    }
    public String getMasp(String masp)
    {
        return sp[Find_id(masp)].getMasp();
    }
   
    public void Set_SanPham(String masp) throws IOException
    {
        int id = Find_id(masp);
        if(id < 0)
        {
            return;
        }
        else
        {
            if(sp[id] instanceof ThucAn)
            {
                ThucAn ta = new ThucAn();
                ta = (ThucAn)sp[id];
                Set_ThucAn(ta);  
            }   
            if(sp[id] instanceof ThucUong)
            {
                ThucUong tu = new ThucUong();
                tu = (ThucUong)sp[id];
                Set_ThucUong(tu);   
            }
            if(sp[id] instanceof HangGiaDung)
            {
                HangGiaDung hgd = new HangGiaDung();
                hgd = (HangGiaDung)sp[id];
                Set_HGD(hgd);
            }
            WriteFile();
        }
    }
    public void Set_ThucAn(ThucAn TA)
    {
        Scanner in = new Scanner(System.in);
        int chon = 0;
        THUCAN:
        while(true)
        {
            Menu_Sua_ThucAn();
            chon = in.nextInt();
            String maloai = "";
            switch(chon)
            {
                case 1: //Mã sản phẩm
                    System.out.print("Nhập mã sản phẩm: ");
                    in.nextLine();
                    TA.setMasp(in.nextLine());
                    System.out.println("-----SỬA HOÀN TẤT-----");
                    break THUCAN;
                case 2: //Mã Loại
                    System.out.print("Nhập mã loại: ");
                    in.nextLine();
                    maloai = in.nextLine();
                    SanPham sp = new SanPham() {};
                    String setMaloai = sp.check_Maloai(maloai);
                    TA.setMaloai(setMaloai);
                    System.out.println("-----SỬA HOÀN TẤT-----");
                    break THUCAN;
                case 3: //Tên sp
                    System.out.print("Nhập tên: ");
                    in.nextLine();
                    TA.setTensp(in.nextLine());
                    System.out.println("-----SỬA HOÀN TẤT-----");
                    break THUCAN;
                case 4: //Số lượng
                    System.out.print("Nhập số lượng: ");
                    in.nextLine();
                    TA.setSl(in.nextInt());
                    System.out.println("-----SỬA HOÀN TẤT-----");
                    break THUCAN;
                case 5: //Đơn giá
                    System.out.print("Nhập đơn giá : ");
                    in.nextLine();
                    TA.setDongia(in.nextFloat());
                    System.out.println("-----SỬA HOÀN TẤT-----");
                    break THUCAN;
                case 6: //Đơn vị tính
                    System.out.print("Nhập đơn vị tính : ");
                    in.nextLine();
                    TA.setDvt(in.nextLine());
                    System.out.println("-----SỬA HOÀN TẤT-----");
                    break THUCAN;
                case 7: //Nguồn
                    System.out.print("Nhập nguồn : ");
                    in.nextLine();
                    TA.setNguon(in.nextLine());
                    System.out.println("-----SỬA HOÀN TẤT-----");
                    break THUCAN;
                case 8: //Trạng thái
                    System.out.print("Nhập trạng thái : ");
                    in.nextLine();
                    TA.setTrangthai(in.nextLine());
                    System.out.println("-----SỬA HOÀN TẤT-----");
                    break THUCAN;
                case 0: //Out
                    break THUCAN;    
                default: 
                    System.out.println("-----Vui lòng nhập đúng lựa chọn-----");
                break;
            }
        } 
    }
    public void Set_ThucUong(ThucUong TU)
    {
        Scanner in = new Scanner(System.in);
        int chon = 0;
        THUCUONG:
        while(true)
        {
            String maloai = "";
            Menu_Sua_ThucUong();
            chon = in.nextInt();
            switch(chon)
            {
                case 1: //Mã sản phẩm
                    System.out.print("Nhập mã sản phẩm: ");
                    in.nextLine();
                    TU.setMasp(in.nextLine());
                    System.out.println("-----SỬA HOÀN TẤT-----");
                    break THUCUONG;
                case 2: //Mã Loại
                    System.out.print("Nhập mã loại: ");
                    in.nextLine();
                    maloai = in.nextLine();
                    SanPham sp = new SanPham() {};
                    String setMaloai = sp.check_Maloai(maloai);
                    TU.setMaloai(setMaloai);
                    System.out.println("-----SỬA HOÀN TẤT-----");
                    break THUCUONG;
                case 3: //Tên sp
                    System.out.print("Nhập tên: ");
                    in.nextLine();
                    TU.setTensp(in.nextLine());
                    System.out.println("-----SỬA HOÀN TẤT-----");
                    break THUCUONG;
                case 4: //Số lượng
                    System.out.print("Nhập số lượng: ");
                    in.nextLine();
                    TU.setSl(in.nextInt());
                    System.out.println("-----SỬA HOÀN TẤT-----");
                    break THUCUONG;
                case 5: //Đơn giá
                    System.out.print("Nhập đơn giá : ");
                    in.nextLine();
                    TU.setDongia(in.nextFloat());
                    System.out.println("-----SỬA HOÀN TẤT-----");
                    break THUCUONG;
                case 6: //Đơn vị tính
                    System.out.print("Nhập đơn vị tính : ");
                    in.nextLine();
                    TU.setDvt(in.nextLine());
                    System.out.println("-----SỬA HOÀN TẤT-----");
                    break THUCUONG;
                case 7: //Dung tích
                    System.out.print("Nhập dung tích : ");
                    in.nextLine();
                    TU.setDungtich(in.nextLine());
                    System.out.println("-----SỬA HOÀN TẤT-----");
                    break THUCUONG;
                case 8: //Tính chất
                    System.out.print("Nhập tính chất : ");
                    in.nextLine();
                    TU.setTinhchat(in.nextLine());
                    System.out.println("-----SỬA HOÀN TẤT-----");
                    break THUCUONG;
                case 0: //Out
                    break THUCUONG;    
                default: 
                    System.out.println("-----Vui lòng nhập đúng lựa chọn-----");
                break;
            }
        } 
    }
    public void Set_HGD(HangGiaDung HGD)
    {
        Scanner in = new Scanner(System.in);
        int chon = 0;
        HANGGIADUNG:
        while(true)
        {
            String maloai ="";
            Menu_Sua_HGD();
            chon = in.nextInt();
            switch(chon)
            {
                case 1: //Mã sản phẩm
                    System.out.print("Nhập mã sản phẩm: ");
                    in.nextLine();
                    HGD.setMasp(in.nextLine());
                    System.out.println("-----SỬA HOÀN TẤT-----");
                    break HANGGIADUNG;
                case 2: //Mã Loại
                    System.out.print("Nhập mã loại: ");
                    in.nextLine();
                    maloai=in.nextLine();
                    SanPham sp = new SanPham() {};
                    String setMaloai = sp.check_Maloai(maloai);
                    HGD.setMaloai(setMaloai);
                    System.out.println("-----SỬA HOÀN TẤT-----");
                    break HANGGIADUNG;
                case 3: //Tên sp
                    System.out.print("Nhập tên: ");
                    in.nextLine();
                    HGD.setTensp(in.nextLine());
                    System.out.println("-----SỬA HOÀN TẤT-----");
                    break HANGGIADUNG;
                case 4: //Số lượng
                    System.out.print("Nhập số lượng: ");
                    in.nextLine();
                    HGD.setSl(in.nextInt());
                    System.out.println("-----SỬA HOÀN TẤT-----");
                    break HANGGIADUNG;
                case 5: //Đơn giá
                    System.out.print("Nhập đơn giá : ");
                    in.nextLine();
                    HGD.setDongia(in.nextFloat());
                    System.out.println("-----SỬA HOÀN TẤT-----");
                    break HANGGIADUNG;
                case 6: //Đơn vị tính
                    System.out.print("Nhập đơn vị tính : ");
                    in.nextLine();
                    HGD.setDvt(in.nextLine());
                    System.out.println("-----SỬA HOÀN TẤT-----");
                    break HANGGIADUNG;
                case 7: //Vật liệu
                    System.out.print("Nhập vật liệu : ");
                    in.nextLine();
                    HGD.setVatlieu(in.nextLine());
                    System.out.println("-----SỬA HOÀN TẤT-----");
                    break HANGGIADUNG;
                case 8: //Công dụng
                    System.out.print("Nhập công dụng : ");
                    in.nextLine();
                    HGD.setCongdung(in.nextLine());
                    System.out.println("-----SỬA HOÀN TẤT-----");
                    break HANGGIADUNG;
                case 0: //Out
                    break HANGGIADUNG;    
                default: 
                    System.out.println("-----Vui lòng nhập đúng lựa chọn-----");
                break;
            }
        } 
    }
    public void Menu_SanPham()
    {
        System.out.println("-----MENU CHỌN SẢN PHẨM-----");
        System.out.println("1. Thức ăn.");
        System.out.println("2. Thức uống.");
        System.out.println("3. Hàng gia dụng.");
        System.out.print("Nhập lựa chọn: ");
    }
    public void Menu_Sua_ThucAn()
    {
        System.out.println("-----MENU SỬA THÔNG TIN-----");
        System.out.println("1. Mã sản phẩm");
        System.out.println("2. Mã loại");
        System.out.println("3. Tên sản phẩm");
        System.out.println("4. Số lượng");
        System.out.println("5. Đơn giá");
        System.out.println("6. Đơn vị tính");
        System.out.println("7. Nguồn");
        System.out.println("8. Trạng Thái");
        System.out.println("0. Thoát");
        System.out.print("Nhập lựa chọn: ");
    }
    public void Menu_Sua_ThucUong()
    {
        System.out.println("-----MENU SỬA THÔNG TIN-----");
        System.out.println("1. Mã sản phẩm");
        System.out.println("2. Mã loại");
        System.out.println("3. Tên sản phẩm");
        System.out.println("4. Số lượng");
        System.out.println("5. Đơn giá");
        System.out.println("6. Đơn vị tính");
        System.out.println("7. Dung tích");
        System.out.println("8. Tính chất");
        System.out.println("0. Thoát");
        System.out.print("Nhập lựa chọn: ");
    }
    public void Menu_Sua_HGD()
    {
        System.out.println("-----MENU SỬA THÔNG TIN-----");
        System.out.println("1. Mã sản phẩm");
        System.out.println("2. Mã loại");
        System.out.println("3. Tên sản phẩm");
        System.out.println("4. Số lượng");
        System.out.println("5. Đơn giá");
        System.out.println("6. Đơn vị tính");
        System.out.println("7. Vật liệu");
        System.out.println("8. Công dụng");
        System.out.println("0. Thoát");
        System.out.print("Nhập lựa chọn: ");
    }


    
    
}
