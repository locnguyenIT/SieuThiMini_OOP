/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sieuthimini_oop;
import Object.HoaDon;
import Object.NhanVien;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import Object.HoaDon;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ntloc
 */
public class DanhSachHoaDon implements In_Out{
    private int n;
    private HoaDon[] hd = new HoaDon[2];
    private static final String file ="danhsachhoadon.txt";
    public void DanhSachHoaDon() throws IOException
    {
    }
    
    @Override
    public void nhap()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Nhập số lượng hóa đơn");
        this.n = in.nextInt();
        hd=Arrays.copyOf(hd, n+1);
        for(int i=0;i<n+1;i++)
        {
            System.out.print("Nhập Hoa Don thu"+(i+1)+": ");
            try {
                hd[i] = new HoaDon();
            } catch (IOException ex) {
                Logger.getLogger(DanhSachHoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }
            hd[i].nhap();
            System.out.println("===Lưu thông tin HD thứ "+(i+1)+" thành công!!!===");
        }
    }
    @Override
    public void xuat()
    {
        System.out.printf("| %10s | %10s | %20s | %10s | %20s | %15s | %10s | %30s |\n","Mã HD","Mã NV","Tên NV     ","Mã KH","Tên KH     ", "Tổng tiền HD","Thanh toán","Thời gian           ");
        for(int i=0;i<n;i++)
        {
            hd[i].xuat(); //hd[i].xuat = new Object() inside switch case 
        }    
    }
    public void Add(String mahd) throws FileNotFoundException, IOException
    {
        hd = Arrays.copyOf(hd, n+1);
        DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(file,Boolean.TRUE));
        for(int i=n; i< n +1; i++)
        {
            hd[i] = new HoaDon();  
            hd[i].nhap(mahd);
            hd[i].WriteFile(file);
        }
        this.n = n+1;
        //System.out.println(n);
        outputStream.close();
        
    }
    public void Delete(String mahd) throws IOException
    {
        int id = Find_id(mahd);
        if(id >= 0)
        {
           for(int i=id; i<n-1;i++)
           {
               hd[i] = hd[i+1]; //Dich gia tri[i+1] sang trai gia tri phan tu can xoa
           }
           hd = Arrays.copyOf(hd, n-1); //Sau khi xóa thì copy giảm sl nv
           this.n = n-1;
           System.out.println("<---Xóa hóa đơn "+mahd+ " thành công--->");
        }
        else
        {
            System.out.println("<---Không có hóa đơn "+mahd+ " trong danh sách--->");
        }
        WriteFile();
        //System.out.println(n); //n = sl nhan vien sau khi delete
    }
    public void WriteFile() throws IOException
    {
        DataOutputStream outStream = new DataOutputStream(new FileOutputStream(file));
        for(int i=0;i<n;i++)
        {
            hd[i].WriteFile(file);
        }
        outStream.close();
    }
    public void ReadFile() 
    {
        int i = 0; 
        try{
            DataInputStream inputStream = new DataInputStream(new FileInputStream(file));
             while(true)
                {                    
                    String mahd = inputStream.readUTF();
                    String manv = inputStream.readUTF();
                    String tennv = inputStream.readUTF();
                    String makh = inputStream.readUTF();
                    String tenkh = inputStream.readUTF();
                    float tongtien = inputStream.readFloat();
                    String thanhtoan = inputStream.readUTF();
                    String date = inputStream.readUTF();
                    hd[i] = new HoaDon(mahd, manv, tennv, makh, tenkh, tongtien, thanhtoan, date);
                    i++;
                    hd = Arrays.copyOf(hd,i+1);
                    //System.out.printf("| %10s | %10s | %20s | %10s | %20s | %10s | %10s |\n",mahd,manv,tennv,makh,tenkh,tongtien,date);
                }
            }
            catch(IOException e){}
            this.n = i; // n = sl nhan vien trong file
            //System.out.println(n);
    }
    public int Find_id(String mahd)
    {
        int vt = -1;
        for(int i=0;i<n;i++) 
        {
            if(hd[i].getMahd().equals(mahd))
            {
                vt = i; 
                break;
            }
        }
        //System.out.println(vt);
        return vt; 
    }
     
    public void xuat_hd(String mahd)
    {
        int id = Find_id(mahd);
        if(id>=0)
        {
            System.out.println("                                ---------------------Hóa đơn-------------------");
            System.out.printf("| %10s | %10s | %20s | %10s | %20s | %15s | %10s | %30s |\n","Mã HD","Mã NV","Tên NV     ","Mã KH","Tên KH     ", "Tổng tiền HD","Thanh toán","Thời gian         ");
            //System.out.println(id);
            hd[id].xuat();
        }
        else
        {
            System.out.println("<---Không có hóa đơn "+mahd+" trong danh sách--->");
        }
    }
    public void Set_HoaDon(String mahd) throws IOException
    {
        int id =Find_id(mahd);
        //System.out.println(id);
        if(id < 0)
        {
            return;
        }
        else
        {
            Scanner in = new Scanner(System.in);
            LOOP:
            while(true)
            {
                ReadFile();
                DanhSachChiTietHD dscthd = new DanhSachChiTietHD();
                dscthd.ReadFile();
                String manv = "";
                String makh = "";
                String thanhtoan = "";
                int chon_hd= 0, chon_cthd =0;
                Menu_Sua_HoaDon();
                chon_hd = in.nextInt();
                switch(chon_hd)
                {
                    case 1: //Mã nhân viên
                        System.out.print("Nhập mã nhân viên: ");
                        in.nextLine();
                        manv = in.nextLine();
                        hd[id].setManv(manv);
                        //Set Tennv khi Set Manv
                        setTennv(id);
                        System.out.println("-----SỬA HOÀN TẤT-----");
                        xuat_hd(mahd);
                        dscthd.xuat_cthd(mahd);
                        break LOOP;
                    case 2: //Mã khách hàng
                        System.out.print("Nhập mã khách hàng: ");
                        in.nextLine();
                        makh = in.nextLine();
                        hd[id].setMakh(makh);
                        setTenkh(id);
                        System.out.println("-----SỬA HOÀN TẤT-----");
                        xuat_hd(mahd);
                        dscthd.xuat_cthd(mahd);
                        break LOOP;
                    case 3: //Thanh toán
                        THANHTOAN:
                        while(true)
                        {
                            int chon = 0;
                            System.out.println("---Thanh toán---");
                            System.out.println("ATM = 1 | Cash = 0 ");
                            System.out.print("Nhập lựa chọn: ");
                            chon = in.nextInt();
                            switch(chon)
                            {
                                case 1: 
                                    hd[id].setThanhtoan("ATM");
                                    break THANHTOAN;
                                case 0:
                                    hd[id].setThanhtoan("Cash");
                                    break THANHTOAN;
                                default:
                                    System.out.println("Sai !!! Vui lòng nhập đúng lựa chọn.");
                                    break;
                            }
                        }
                        System.out.println("-----SỬA HOÀN TẤT-----");
                        xuat_hd(mahd);
                        dscthd.xuat_cthd(mahd);
                        break LOOP;    
                    case 4: // Thông tin CTHD sản phẩm
                        System.out.print("Nhập Mã sản phẩm để sửa thông tin: ");
                        in.nextLine();
                        String masp = in.nextLine();
                        CTHD:
                        while(true)
                        {
                            Menu_Sua_CTHD();
                            float tongtienhd=0;
                            String setMasp = "";
                            int setSl = 0;
                            int setGiamgia = 0;
                            chon_cthd = in.nextInt();
                            switch(chon_cthd)
                            {
                                case 1: //Mã sản phẩm

                                    System.out.print("Nhập mã sản phẩm: ");
                                    in.nextLine();
                                    setMasp=in.nextLine();
                                    dscthd.Set_MASP(setMasp, mahd, masp);

                                    tongtienhd = dscthd.getTongTienHD(mahd);

                                    Update(mahd, tongtienhd);

                                    System.out.println("-----SỬA HOÀN TẤT-----");
                                    xuat_hd(mahd);
                                    dscthd.xuat_cthd(mahd);
                                    break CTHD;
                                case 2: //Số lượng
                                    System.out.print("Nhập số lượng: ");
                                    in.nextLine();
                                    setSl=in.nextInt();
                                    dscthd.Set_SLSP(setSl, mahd, masp);

                                    tongtienhd = dscthd.getTongTienHD(mahd);

                                    Update(mahd, tongtienhd);
                                    System.out.println("-----SỬA HOÀN TẤT-----");
                                    xuat_hd(mahd);
                                    dscthd.xuat_cthd(mahd);
                                    break CTHD;
                                case 3: //Giảm giá %
                                    System.out.print("Nhập giảm giá (%): ");
                                    in.nextLine();
                                    setGiamgia=in.nextInt();
                                    dscthd.Set_GiamGia(setGiamgia, mahd, masp);

                                    tongtienhd = dscthd.getTongTienHD(mahd);

                                    Update(mahd, tongtienhd);
                                    System.out.println("-----SỬA HOÀN TẤT-----");
                                    xuat_hd(mahd);
                                    dscthd.xuat_cthd(mahd);
                                    break CTHD;
                                case 0:  //Out

                                    break CTHD;
                                default: 
                                    System.out.println("Sai!!! Vui lòng nhập đúng lựa chọn.");
                                    break;
                            }

                        }
                        break LOOP;

                    case 0: //Out
                        break LOOP;
                    default:
                        System.out.println("Sai!!! Vui lòng nhập lại.");
                        break;
                }
            }
            WriteFile();
        }
    }
    public void Update(String mahd, float tongtienhd) throws IOException
    {
        Date thoigian = new Date();         //Update Thoi gian
        setThoiGian(mahd,thoigian.toString());

        setTongtien(mahd,tongtienhd);       //Update Tongtien HD
    }
    public void setTennv(int id) throws IOException
    {
        DanhSachNhanVien dsnv = new DanhSachNhanVien(); 
        dsnv.ReadFile(); //Đọc dữ liệu dsnv
        String setTen = "";
        String manv =hd[id].getManv(); //Get manv khi vừa set manv trước đó 
        if(manv.equals(dsnv.getManv(manv))) //Kiểm tra xem manv vừa get này có trong dữ liệu dsnv(manv) 
        {
           //True
           setTen = dsnv.getTennv(manv);   // Gán lại tennv thuộc manv đó có trong dsnv
           //System.out.println(tenUp);
        }
        hd[id].setTennv(setTen); //Set lại tennv thuộc manv vừa lấy từ dsnv 
    }
    public void setTenkh(int id) throws IOException
    {
        DanhSachKhachHang dskh = new DanhSachKhachHang();
        dskh.ReadFile(); 
        String setTen = "";
        String makh =hd[id].getMakh(); 
        if(makh.equals(dskh.getMakh(makh))) 
        {
           setTen = dskh.getTenkh(makh);   
        }
        hd[id].setTenkh(setTen); 
    }
    public float getTongtien(String mahd)
    {
        return hd[Find_id(mahd)].getTongtien();
        
    }
    public void setThoiGian(String mahd,String thoigian) throws IOException
    {
        this.hd[Find_id(mahd)].setDate(thoigian);
        WriteFile();
    }
    public void setTongtien(String mahd, float tongtien) throws IOException
    {
        this.hd[Find_id(mahd)].setTongtien(tongtien);
        WriteFile();
    }
    public void setPT_thanhtoan(String mahd, String pttt) throws IOException
    {
        this.hd[Find_id(mahd)].setThanhtoan(pttt);
        WriteFile();
    }
    public void Thongke_Doanhthu()
    {
        float doanhthu_ATM = 0;
        float doanhthu_cash = 0;
        for(int i =0; i< n; i++)
        {
            if(hd[i].getThanhtoan().equals("ATM"))
            {
                doanhthu_ATM += hd[i].getTongtien();
            }
            if(hd[i].getThanhtoan().equals("Cash"))
            {
                doanhthu_cash += hd[i].getTongtien();
            }
            
        }
        System.out.printf("  %10s   %15s   %10s | %15s |\n","","","","---------------");
        System.out.printf("  %10s   %15s   %10s | %15s |\n","","","","ATM:  "+doanhthu_ATM);
        System.out.printf("  %10s   %15s   %10s | %15s |\n","","","","Cash:  "+doanhthu_cash);
    }
    public void Menu_Sua_HoaDon()
    {
        System.out.println("-----MENU SỬA THÔNG TIN HÓA ĐƠN & CTHD-----");
        System.out.println("1. Mã nhân viên");
        System.out.println("2. Mã khách hàng");
        System.out.println("3. Thanh toán");
        System.out.println("4. Thông tin CTHD");
        System.out.println("0. Thoát");
        System.out.print("Nhập lựa chọn: ");
    }
    public void Menu_Sua_CTHD()
    {
        System.out.println("-----MENU SỬA THÔNG TIN CTHD-----");
        System.out.println("1. Mã sản phẩm");
        System.out.println("2. Số lượng");
        System.out.println("3. Giảm giá %");
        System.out.println("0. Thoát");
        System.out.print("Nhập lựa chọn: ");
    }
}
