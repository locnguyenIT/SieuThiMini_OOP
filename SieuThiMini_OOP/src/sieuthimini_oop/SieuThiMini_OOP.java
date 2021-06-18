/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sieuthimini_oop;
import Object.HoaDon;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author ntloc
 */
public class SieuThiMini_OOP {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        DanhSachSanPham dssp = new DanhSachSanPham();
        dssp.ReadFile();
        DanhSachLoaiSP dslsp = new DanhSachLoaiSP();
        dslsp.ReadFile();
        DanhSachNhanVien dsnv = new DanhSachNhanVien();
        dsnv.ReadFile();
        DanhSachKhachHang dskh = new DanhSachKhachHang();
        dskh.ReadFile();
        DanhSachHoaDon dshd = new DanhSachHoaDon();
        dshd.ReadFile();
        DanhSachChiTietHD dscthd = new DanhSachChiTietHD();
        dscthd.ReadFile();
        DanhSachNhaCungCap dsncc = new DanhSachNhaCungCap();
        dsncc.ReadFile();
        DanhSachPhieuNhapHang dspnh = new DanhSachPhieuNhapHang();
        dspnh.ReadFile();
        DanhSachChiTietPNH dsctpnh = new DanhSachChiTietPNH();
        dsctpnh.ReadFile();
        Scanner in = new Scanner(System.in);
        Welcome();
        Login();
        MENU_MAIN:
        while(true)
        { 
            Menu_Main();
            int menu_main=0, menu_info = 0, menu_quanly =0, menu_banhang = 0, menu_nhaphang = 0, menu_thongke=0;
            menu_main=in.nextInt();
            switch(menu_main)
            {
                case 1: //QUẢN LÝ
                    MENU_QUANLY:
                    while(true)
                    {
                        
                        Menu_QuanLy();
                        menu_quanly = in.nextInt();
                        switch(menu_quanly)
                        {
                            case 1: //QUẢN LÝ SẢN PHẨM
                            MENU_INFO:
                            while(true)
                            {
                                String masp = "";
                                Menu_SanPham();
                                dssp.ReadFile();
                                menu_info = in.nextInt();
                                //in.nextLine();
                                switch(menu_info)
                                {  
                                    case 1: //Danh sách
                                           System.out.println("                                       "
                                                   + "              -----------DANH SÁCH SẢN PHẨM----------");
                                           dssp.xuat();
                                        break;
                                    case 2: //Tìm kiếm
                                        System.out.print("Nhập Mã sản phẩm cần tìm kiếm: ");
                                        in.nextLine();
                                        masp = in.nextLine();
                                        dssp.Search_Masp(masp);
                                        break;
                                    case 3: //Thêm 
                                        dssp.Add_SP();
                                        break;
                                    case 4: //Xóa
                                        System.out.print("Nhập Mã sản phẩm cần xóa: ");
                                        in.nextLine();
                                        masp = in.nextLine();
                                        dssp.Delete_Masp(masp);
                                        break;
                                    case 5: //Sửa
                                        System.out.print("Nhập Mã sản phẩm cần sửa: ");
                                        in.nextLine();
                                        masp = in.nextLine();
                                        dssp.Search_Masp(masp);
                                        dssp.Set_SanPham(masp);
                                        break;
                                    case 0: //Out
                                        break MENU_INFO;
                                    default:
                                        System.out.println("===Vui lòng nhập đúng lựa chọn===");
                                        break;
                                }
                                    
                            }
                            break;
                            case 2: //QUẢN LÝ LOẠI SP
                            MENU_INFO:
                            while(true)
                            {
                                Menu_LoaiSP();
                                dslsp.ReadFile();
                                String maloai = "";
                                menu_info = in.nextInt();
                                switch(menu_info)
                                {
                                    case 1: //Danh sách
                                        System.out.println("  ----DANH SÁCH LOẠI SP---");
                                        dslsp.xuat();
                                        break;
                                    case 2: //Tìm kiếm
                                        int chon = 0;
                                        System.out.println("Tìm theo Mã = 1 | Tìm theo Tên = 0");
                                        System.out.print("Nhập lựa chon: ");
                                        chon =in.nextInt();
                                        switch(chon)
                                        {
                                            case 1:  
                                                System.out.print("Nhập Mã loại cần tìm kiếm: ");
                                                in.nextLine();
                                                maloai = in.nextLine();
                                                dslsp.Search_Maloai(maloai);
                                                break;
                                            case 0:
                                                System.out.print("Nhập Tên loại cần tìm kiếm: ");
                                                in.nextLine();
                                                maloai = in.nextLine();
                                                dslsp.Search_Tenloai(maloai);
                                                break;
                                        }                                                                        
                                        break;
                                    case 3: //Thêm
                                        System.out.print("Nhập Mã loại: ");
                                        in.nextLine();
                                        maloai = in.nextLine();
                                        dslsp.Add(maloai);
                                        break;
                                    case 4: //Xóa
                                        System.out.print("Nhập Mã loại cần xóa: ");
                                        in.nextLine();
                                        maloai = in.nextLine();
                                        dslsp.Delete(maloai);
                                        break;
                                    case 5: //Sửa
                                        System.out.print("Nhập Mã loại cần sửa: ");
                                        in.nextLine();
                                        maloai = in.nextLine();
                                        dslsp.Search_Maloai(maloai);
                                        dslsp.Set_LoaiSP(maloai);
                                        break;
                                    case 0: //Out
                                    break MENU_INFO;
                                    default:
                                        System.out.println("<---Sai!!! Vui lòng nhập đúng lựa chọn--->");
                                    break;
                                }
                                    
                            }
                            break;         
                            case 3: //QUẢN LÝ NHÂN VIÊN
                            MENU_INFO:
                            while(true)
                            {
                                Menu_NhanVien();
                                dsnv.ReadFile();
                                String manv = "";
                                menu_info = in.nextInt();
                                switch(menu_info)
                                {
                                    case 1: //Danh sách
                                        System.out.println("                               "
                                                   + "---------DANH SÁCH NHÂN VIÊN----------");
                                        dsnv.xuat();
                                        break;
                                    case 2: //Tìm kiếm
                                        LOOP:
                                        while(true)
                                        {
                                            int chon = 0;
                                            System.out.println("Tìm theo Mã = 1 | Tìm theo Tên = 0");
                                            System.out.print("Nhập lựa chon: ");
                                            chon =in.nextInt();
                                            switch(chon)
                                            {
                                                case 1:  
                                                    System.out.print("Nhập Mã nhân viên cần tìm kiếm: ");
                                                    in.nextLine();
                                                    manv = in.nextLine();
                                                    dsnv.Search_Manv(manv);
                                                    break LOOP;
                                                case 0:
                                                    System.out.print("Nhập tên nhân viên cần tìm kiếm: ");
                                                    in.nextLine();
                                                    manv = in.nextLine();
                                                    dsnv.Search_Tennv(manv);
                                                    break LOOP;
                                            }        
                                        }                                                                   
                                        break;
                                    case 3: //Thêm
                                        System.out.print("Nhập Mã nhân viên: ");
                                        in.nextLine();
                                        manv = in.nextLine();
                                        dsnv.Add(manv);
                                        break;
                                    case 4: //Xóa
                                        System.out.print("Nhập Mã nhân viên cần xóa: ");
                                        in.nextLine();
                                        manv = in.nextLine();
                                        dsnv.Delete(manv);
                                        break;
                                    case 5: //Sửa
                                        System.out.print("Nhập Mã nhân viên cần sửa: ");
                                        in.nextLine();
                                        manv = in.nextLine();
                                        dsnv.Search_Manv(manv);
                                        dsnv.Set_NhanVien(manv);
                                        break;
                                    case 0: //Out
                                    break MENU_INFO;
                                    default:
                                        System.out.println("===Vui lòng nhập đúng lựa chọn===");
                                    break;
                                }
                                    
                            }
                            break;                            
                            case 4: //QUẢN LÝ KHÁCH HÀNG
                            MENU_INFO:
                            while(true)
                            {
                                Menu_KhachHang();
                                dskh.ReadFile();
                                String makh = "";
                                menu_info = in.nextInt();
                                switch(menu_info)
                                {
                                    case 1: //Danh sách
                                        System.out.println("                         "
                                                   + "---------DANH SÁCH KHÁCH HÀNG---------");
                                        dskh.xuat();
                                        break;
                                    case 2: //Tìm kiếm
                                        LOOP:
                                        while(true)
                                        {
                                            int chon = 0;
                                            System.out.println("Tìm theo Mã = 1 | Tìm theo Tên = 0");
                                            System.out.print("Nhập lựa chon: ");
                                            chon =in.nextInt();
                                            switch(chon)
                                            {
                                                case 1:  
                                                    System.out.print("Nhập Mã khách hàng cần tìm kiếm: ");
                                                    in.nextLine();
                                                    makh = in.nextLine();
                                                    dskh.Search_Makh(makh);
                                                    break LOOP;
                                                case 0:
                                                    System.out.print("Nhập Tên khách hàng cần tìm kiếm: ");
                                                    in.nextLine();
                                                    makh = in.nextLine();
                                                    dskh.Search_TenKH(makh);
                                                    break LOOP;
                                                }   
                                            }
                                        break;
                                    case 3: //Thêm 
                                        System.out.print("Nhập Mã khách hàng: ");
                                        in.nextLine();
                                        makh = in.nextLine();
                                        dskh.Add(makh);
                                        break;
                                    case 4: //Xóa
                                        System.out.print("Nhập Mã khách hàng cần xóa: ");
                                        in.nextLine();
                                        makh = in.nextLine();
                                        dskh.Delete_Makh(makh);
                                        break;
                                    case 5: //Sửa
                                        System.out.print("Nhập Mã khách hàng cần sửa: ");
                                        in.nextLine();
                                        makh = in.nextLine();
                                        dskh.Search_Makh(makh);
                                        dskh.Set_KhachHang(makh);
                                        break;
                                    case 0: //Out
                                        break MENU_INFO;
                                    default:
                                        System.err.println("--->Sai. Vui lòng nhập đúng lựa chọn<---");
                                    break;
                                }         
                            }
                            break;
                            case 5: //QUẢN  LÝ HÓA ĐƠN
                            MENU_INFO:
                            while(true)
                            {                               
                                Menu_HoaDon();
                                dshd.ReadFile();
                                dscthd.ReadFile();
                                String mahd = "";
                                menu_info = in.nextInt();
                                switch(menu_info)
                                {
                                    case 1: //Danh sách
                                        System.out.println("                                               ----------DANH SÁCH HÓA ĐƠN----------");
                                        dshd.xuat();
                                        break;
                                    case 2: //Tìm kiếm
                                        System.out.print("Nhập mã hóa đơn cần tìm kiếm: ");
                                        in.nextLine();
                                        mahd= in.nextLine();
                                        //dshd.ReadFile();
                                        dshd.xuat_hd(mahd);
                                        //dscthd.ReadFile();
                                        dscthd.xuat_cthd(mahd);
                                        break;
                                    case 3: //Thêm CTHD 
                                        CHECK:
                                        while(true)
                                        {
                                            System.out.print("Nhập mã hóa đơn cần thêm CTHD: ");
                                            in.nextLine();
                                            mahd = in.nextLine();
                                            while(dshd.Find_id(mahd) < 0)
                                            {
                                                System.out.println("<---Mã hóa đơn "+mahd+" không có trong danh sách. Vui lòng nhập lại--->");
                                                int chon = 0;
                                                System.out.println("Tiếp tục = 1 | Hủy bỏ = 0");
                                                System.out.print("Nhập lựa chọn: ");
                                                chon = in.nextInt();
                                                switch(chon)
                                                {
                                                    case 1:
                                                        int id = 0;
                                                        System.out.print("Nhập mã hóa đơn cần thêm CTHD: ");
                                                        in.nextLine();
                                                        mahd = in.nextLine();
                                                        id = dshd.Find_id(mahd);
                                                        break;
                                                    case 0:
                                                        break CHECK;
                                                }
                                            }
                                            dshd.xuat_hd(mahd);
                                            dscthd.xuat_cthd(mahd);
                                            System.out.print("Số lượng sản phẩm mua:" );
                                            int sl = in.nextInt();
                                            dscthd.Add(sl, mahd);
                                            System.out.println("-----Thêm CTHD vào hóa đơn "+mahd+" thành công-----");
                                            dshd.ReadFile();
                                            dshd.xuat_hd(mahd);
                                            dscthd.ReadFile();
                                            dscthd.xuat_cthd(mahd);
                                            break CHECK;
                                        }
                                        break;
                                    case 4: //Xóa CTHD 
                                        System.out.print("Nhập mã hóa đơn cần xóa CTHD: ");
                                        in.nextLine();
                                        mahd = in.nextLine();
                                        dshd.xuat_hd(mahd);
                                        dscthd.xuat_cthd(mahd);
                                        System.out.print("Nhập Mã sản phẩm cần xóa: ");
                                        String masp = in.nextLine();
                                        dscthd.Delete_CTHD(mahd,masp); 
                                        System.out.println("-----Xóa sản phẩm "+masp+" thành công-----");
                                        dshd.ReadFile();
                                        dshd.xuat_hd(mahd);
                                        dscthd.ReadFile();
                                        dscthd.xuat_cthd(mahd);
                                        break;    
                                    case 5: //Xóa
                                        System.out.print("Nhập mã hóa đơn cần xóa: ");
                                        in.nextLine();
                                        mahd = in.nextLine();
                                        dshd.Delete(mahd);
                                        dscthd.Delete(mahd);
                                        //System.out.println("-----Xóa hóa đơn "+mahd+" thành công");
                                        break;
                                    case 6: //Sửa
                                        System.out.print("Nhập Mã hóa đơn cần sửa: ");
                                        in.nextLine();
                                        mahd=in.nextLine();
                                        dshd.xuat_hd(mahd);
                                        dscthd.xuat_cthd(mahd);
                                        dshd.Set_HoaDon(mahd);
                                        break;

                                    case 0: //Out
                                    break MENU_INFO;
                                    default:
                                        System.out.println("===Vui lòng nhập đúng lựa chọn===");
                                    break;
                                }
                                    
                            }
                            break;
                            case 6: //QUẢN LÝ NHÀ CUNG CẤP
                            MENU_INFO:
                            while(true)
                            {
                                Menu_NhaCungCap();
                                dsncc.ReadFile();
                                menu_info = in.nextInt();
                                String mancc= "";
                                switch(menu_info)
                                {
                                    case 1: //Danh sách
                                        System.out.println("             "
                                                   + "----------DANH SÁCH NHÀ CUNG CẤP---------");
                                        dsncc.xuat();
                                        break;
                                    case 2: //Tìm kiếm
                                        System.out.print("Nhập Mã NCC cần tìm kiếm: ");
                                        in.nextLine();
                                        mancc = in.nextLine();
                                        dsncc.Search_Mancc(mancc);
                                        break;
                                    case 3: //Thêm 
                                        System.out.print("Nhập Mã nhà cung cấp: ");   
                                        in.nextLine();
                                        mancc = in.nextLine();
                                        dsncc.Add(mancc);
                                        break;
                                    case 4: //Xóa
                                        System.out.print("Nhập Mã NCC cần xóa: ");
                                        in.nextLine();
                                        mancc=in.nextLine();
                                        dsncc.Delete(mancc);
                                        System.out.println("-----Xóa nhà cung cấp "+mancc+" thành công-----");
                                        break;
                                    case 5: //Sửa
                                        System.out.print("Nhập Mã NCC cần sửa: ");
                                        in.nextLine();
                                        mancc=in.nextLine();
                                        dsncc.Search_Mancc(mancc);
                                        dsncc.Set_NCC(mancc);
                                        break;
                                    case 0: //Out
                                        break MENU_INFO;
                                    default:
                                        System.out.println("===Vui lòng nhập đúng lựa chọn===");
                                    break;
                                }         
                            }
                            break;
                            case 7: //QUẢN LÝ PHIẾU NHẬP HÀNG 
                            MENU_INFO:
                            while(true)
                            {                               
                                Menu_PhieuNhapHang();
                                dspnh.ReadFile();
                                dsctpnh.ReadFile();
                                String mapnh = "";
                                menu_info = in.nextInt();
                                switch(menu_info)
                                {
                                    case 1: //Danh sách
                                        System.out.println("                           ----------DANH SÁCH PHIẾU NHẬP HÀNG----------");
                                        dspnh.xuat();
                                        break;
                                    case 2: //Tìm kiếm
                                        System.out.print("Nhập mã phiếu nhập cần tìm kiếm: ");
                                        in.nextLine();
                                        mapnh= in.nextLine();
                                        dspnh.xuat_pnh(mapnh);
                                        dsctpnh.xuat_ctpnh(mapnh);
                                        break;
                                    case 3: //Thêm CTPNH
                                        CHECK:
                                        while(true)
                                        {
                                            System.out.print("Nhập mã phiếu nhập cần thêm CTPN: ");
                                            in.nextLine();
                                            mapnh = in.nextLine();
                                            while(dspnh.Find_id(mapnh) < 0)
                                            {
                                                System.out.println("<---Mã phiếu nhập hàng "+mapnh+" không có trong danh sách. Vui lòng nhập lại--->");
                                                int chon = 0;
                                                System.out.println("Tiếp tục = 1 | Hủy bỏ = 0");
                                                System.out.print("Nhập lựa chọn: ");
                                                chon = in.nextInt();
                                                switch(chon)
                                                {
                                                    case 1:
                                                        int id = 0;
                                                        System.out.print("Nhập mã phiếu nhập cần thêm CTPN: ");
                                                        in.nextLine();
                                                        mapnh = in.nextLine();
                                                        id = dshd.Find_id(mapnh);
                                                        break;
                                                    case 0:
                                                        break CHECK;
                                                }
                                            }
                                            dspnh.xuat_pnh(mapnh);
                                            dsctpnh.xuat_ctpnh(mapnh);
                                            System.out.print("Số lượng sản phẩm nhập:" );
                                            int sl = in.nextInt();
                                            dsctpnh.Add(sl, mapnh);
                                            System.out.println("-----Thêm CTPNH vào phiếu nhập hàng "+mapnh+" thành công-----");
                                            dspnh.ReadFile();
                                            dspnh.xuat_pnh(mapnh);
                                            dsctpnh.ReadFile();
                                            dsctpnh.xuat_ctpnh(mapnh);
                                            break CHECK;
                                        }
                                        break;
                                    case 4: //Xóa CTPNH
                                        System.out.print("Nhập mã phiếu nhập cần xóa CTPN: ");
                                        in.nextLine();
                                        mapnh = in.nextLine();
                                        dspnh.xuat_pnh(mapnh);
                                        dsctpnh.xuat_ctpnh(mapnh);
                                        System.out.print("Nhập Mã sản phẩm cần xóa: ");
                                        String masp = in.nextLine();
                                        dsctpnh.Delete_CTPNH(mapnh,masp); 
                                        System.out.println("<-----Xóa CTPNH "+masp+" khỏi phiếu nhập hàng "+mapnh+" thành công----->");
                                        dspnh.ReadFile();
                                        dspnh.xuat_pnh(mapnh);
                                        dsctpnh.ReadFile();
                                        dsctpnh.xuat_ctpnh(mapnh);
                                        break;    
                                    case 5: //Xóa
                                        System.out.print("Nhập mã phiếu nhập cần xóa: ");
                                        in.nextLine();
                                        mapnh = in.nextLine();
                                        dspnh.Delete(mapnh);
                                        dsctpnh.Delete(mapnh);
                                        
                                        break;
                                    case 6: //Sửa
                                        System.out.print("Nhập Mã phiếu nhập cần sửa: ");
                                        in.nextLine();
                                        mapnh=in.nextLine();
                                        dspnh.xuat_pnh(mapnh);
                                        dsctpnh.xuat_ctpnh(mapnh);
                                        dspnh.Set_PhieuNhapHang(mapnh);
                                        break;
                                    case 0: //Out
                                    break MENU_INFO;
                                    default:
                                        System.out.println("===Vui lòng nhập đúng lựa chọn===");
                                    break;
                                }
                                    
                            }
                            break;
                            case 0: //OUT
                                break MENU_QUANLY;   
                            default:
                                System.out.println("-----Vui lòng nhập đúng lựa chọn-----");
                        }
                    }
                break;
                case 2: //BÁN HÀNG
                    MENU_BANHANG:
                    while(true)
                    {
                        Menu_BanHang();
                        String mahd = "";
                        menu_banhang = in.nextInt();
                        //HoaDon hd = new HoaDon();
                        switch(menu_banhang)
                        {
                            case 1:
                                CHECK:
                                while(true)
                                {
                                    System.out.print("Nhập Mã hóa đơn: "); 
                                    in.nextLine();
                                    mahd = in.nextLine();
                                    //dshd.Add(mahd);
                                    LOOP:
                                    while(dshd.Find_id(mahd) != -1)
                                    {
                                        System.out.println("<---Mã hóa đơn "+mahd+" đã tồn tại. Vui lòng nhập lại--->");
                                        int chon = 0;
                                        System.out.println("Tiếp tục = 1 | Hủy bỏ = 0");
                                        System.out.print("Nhập lựa chọn: ");
                                        chon = in.nextInt();
                                        switch(chon)
                                        {
                                            case 1:
                                                int id = 0;
                                                System.out.print("Nhập Mã hóa đơn: ");
                                                in.nextLine();
                                                mahd = in.nextLine();
                                                id = dshd.Find_id(mahd);
                                                break;
                                            case 0:
                                                break CHECK;
                                            default:
                                                System.out.println("Sai !!! Vui lòng nhập lựa chọn lại.");
                                                break;
                                        }
                                    }
                                    dshd.Add(mahd);
                                    System.out.print("Số lượng sản phẩm mua:" );
                                    int sl = in.nextInt();
                                    dscthd.Add(sl, mahd);
                                    PTTT:
                                    while(true)
                                    {
                                        System.out.println("-----Phương thức thanh toán-----");
                                        System.out.println("ATM = 1 | Cash = 0");
                                        System.out.print("Nhập lựa chọn: ");
                                        int chon_tt =0;
                                        chon_tt = in.nextInt();
                                        switch(chon_tt)
                                        {
                                            case 1: 
                                                dscthd.setPT_thanhtoan(mahd,"ATM");
                                                break PTTT;
                                            case 0:
                                                dscthd.setPT_thanhtoan(mahd,"Cash");
                                                break PTTT;
                                           default:
                                                System.out.println("Sai !!! Vui lòng nhập lại lựa chọn ");
                                                break;
                                        }
                                    }
                                    Confirm:
                                    while(true)
                                    {
                                        int chon =0;
                                        System.out.println("-----Thanh toán-----");
                                        System.out.println("Xác nhận = 1 | Hủy bỏ = 0");
                                        System.out.print("Nhập lựa chọn: ");
                                        chon = in.nextInt();
                                        switch(chon)
                                        {
                                            case 1:
                                                System.out.println("<---Thêm hóa đơn "+mahd+" thành công--->");
                                                break Confirm;
                                            case 0:
                                                dscthd.Delete(mahd);
                                                dshd.Delete(mahd);
                                                break CHECK;
                                            default:
                                                System.out.println("Sai !!! Vui lòng nhập lại lựa chọn ");
                                                break;    
                                        }
                                    }  
                                    dshd.ReadFile(); //Update lại file dshd sau khi tao hoa don (tổng tiền, thời gian)
                                    dscthd.ReadFile();
                                    dshd.xuat_hd(mahd);
                                    dscthd.xuat_cthd(mahd);
                                    break CHECK;
                                }
                                break;
                            case 0:
                                break MENU_BANHANG;
                            default:
                                System.out.println("!!!Sai. Vui lòng nhập lại!!!");
                                break;
                        }
                    }                
                  break;
                case 3: //NHẬP HÀNG
                    MENU_NHAPHANG:
                    while(true)
                    {
                        Menu_NhapHang();
                        String mapnh = "";
                        menu_nhaphang = in.nextInt();
                        switch(menu_nhaphang)
                        {
                            case 1:
                                CHECK:
                                while(true)
                                {
                                    System.out.print("Nhập Mã phiếu nhập hàng: "); 
                                    in.nextLine();
                                    mapnh = in.nextLine();
                                    //dshd.Add(mahd);
                                    LOOP:
                                    while(dspnh.Find_id(mapnh) != -1)
                                    {
                                        System.out.println("<---Mã phiếu nhập hàng "+mapnh+" đã tồn tại. Vui lòng nhập lại--->");
                                        int chon = 0;
                                        System.out.println("Tiếp tục = 1 | Hủy bỏ = 0");
                                        System.out.print("Nhập lựa chọn: ");
                                        chon = in.nextInt();
                                        switch(chon)
                                        {
                                            case 1:
                                                int id = 0;
                                                System.out.print("Nhập phiếu nhập hàng: ");
                                                in.nextLine();
                                                mapnh = in.nextLine();
                                                id = dspnh.Find_id(mapnh);
                                                break;
                                            case 0:
                                                break CHECK;
                                        }
                                    }
                                    dspnh.Add(mapnh);
                                    System.out.print("Số lượng nhập sản phẩm: ");
                                    int sl = in.nextInt();
                                    dsctpnh.Add(sl, mapnh);
                                    Confirm:
                                    while(true)
                                    {
                                        int chon =0;
                                        System.out.println("-----Nhập sản phẩm-----");
                                        System.out.println("Xác nhận = 1 | Hủy bỏ = 0");
                                        System.out.print("Nhập lựa chọn: ");
                                        chon = in.nextInt();
                                        switch(chon)
                                        {
                                            case 1:
                                                System.out.println("<---Thêm phiếu nhập hàng "+mapnh+" thành công--->");
                                                break Confirm;
                                            case 0:
                                                dspnh.Delete(mapnh);
                                                dsctpnh.Delete(mapnh);
                                                break CHECK;
                                            default:
                                                System.out.println("Sai !!! Vui lòng nhập lại lựa chọn ");
                                                break;    
                                        }
                                    }  
                                    dspnh.ReadFile();
                                    dsctpnh.ReadFile();
                                    dspnh.xuat_pnh(mapnh);
                                    dsctpnh.xuat_ctpnh(mapnh);
                                    break CHECK;
                                }
                                break;
                            case 0:
                                break MENU_NHAPHANG;
                            default:
                                System.out.println("!!!Sai. Vui lòng nhập lại!!!");
                                break;
                        }
                    }
                break;
                case 4: //THỐNG KÊ
                    LOOP:
                    while(true)
                    {
                        dscthd.ReadFile(); 
                        dsctpnh.ReadFile();
                         Menu_ThongKe();
                         menu_thongke = in.nextInt();
                         switch(menu_thongke)
                         {
                             case 1: //Thống kê bán hàng
                                 dscthd.ThongKe_SP();
                                 break;
                             case 2: //Thống kê nhập hàng
                                 dsctpnh.ThongKe_NhapHang();
                                 break;
                             case 0:
                                 break LOOP;
                             default:
                                 System.out.println("Sai!!! Vui lòng nhập lại lựa chọn.");
                                 break ;
                         }
                    }     
                    break;
                case 0: //OUT
                    System.out.println("            ଘ( ❀ ◠‿◠ )੭\n"
                            + "«´¨`•..¤: Xin chào & hẹn gặp lại:¤..•´¨`»");
                break MENU_MAIN;
                default:
                    System.out.println("-----Sai!!!Vui lòng nhập đúng lựa chọn-----");
                break;
            }
        }
    }   
    public static void Menu_ThongKe()
    {
        System.out.println("-----THỐNG KÊ-----");
        System.out.println("1. Thống kê bán hàng");
        System.out.println("2. Thống kê nhập hàng");
        System.out.println("0. Thoát");
        System.out.print("Nhập lưa chọn: "); 
    }
    public static void Menu_BanHang()
    {
        System.out.println("-----BÁN HÀNG-----");
        System.out.println("1. Tạo Hóa đơn");
        System.out.println("0. Thoát");
        System.out.print("Nhập lưa chọn: ");
    }
    public static void Menu_NhapHang()
    {
        System.out.println("-----NHẬP HÀNG-----");
        System.out.println("1. Tạo phiếu nhập hàng");
        System.out.println("0. Thoát");
        System.out.print("Nhập lưa chọn: ");
    }
    public static void Menu_QuanLy()
    {
        System.out.println("-----QUẢN LÝ-----");
        System.out.println("1. Quản lý Sản phẩm ");
        System.out.println("2. Quản lý Loại SP");
        System.out.println("3. Quản lý Nhân viên ");
        System.out.println("4. Quản lý Khách hàng ");
        System.out.println("5. Quản lý Hóa đơn ");
        System.out.println("6. Quản lý Nhà cung cấp ");
        System.out.println("7. Quản lý Phiếu nhập hàng ");
        System.out.println("0. Thoát");
        System.out.print("Nhập lựa chọn: ");
    }
    public static void Menu_SanPham()
    {
        System.out.println("-----QUẢN LÝ SẢN PHẨM-----");
        System.out.println("1. Danh sách");
        System.out.println("2. Tìm kiếm");
        System.out.println("3. Thêm ");
        System.out.println("4. Xóa");
        System.out.println("5. Sửa");
        System.out.println("0. Thoát");
        System.out.print("Nhập lựa chọn: ");
    }
    public static void Menu_NhanVien()
    {
        System.out.println("-----QUẢN LÝ NHÂN VIÊN-----");
        System.out.println("1. Danh sách");
        System.out.println("2. Tìm kiếm");
        System.out.println("3. Thêm ");
        System.out.println("4. Xóa");
        System.out.println("5. Sửa");
        System.out.println("0. Thoát");
        System.out.print("Nhập lựa chọn: ");
    }
    public static void Menu_LoaiSP()
    {
        System.out.println("-----QUẢN LÝ LOẠI SP-----");
        System.out.println("1. Danh sách");
        System.out.println("2. Tìm kiếm");
        System.out.println("3. Thêm ");
        System.out.println("4. Xóa");
        System.out.println("5. Sửa");
        System.out.println("0. Thoát");
        System.out.print("Nhập lựa chọn: ");
    }
    public static void Menu_KhachHang()
    {
        System.out.println("-----QUẢN LÝ KHÁCH HÀNG-----");
        System.out.println("1. Danh sách");
        System.out.println("2. Tìm kiếm");
        System.out.println("3. Thêm ");
        System.out.println("4. Xóa");
        System.out.println("5. Sửa");
        System.out.println("0. Thoát");
        System.out.print("Nhập lựa chọn: ");
    }
    
    public static void Menu_NhaCungCap()
    {
        System.out.println("-----QUẢN LÝ NHÀ CUNG CẤP-----");
        System.out.println("1. Danh sách");
        System.out.println("2. Tìm kiếm");
        System.out.println("3. Thêm ");
        System.out.println("4. Xóa");
        System.out.println("5. Sửa");
        System.out.println("0. Thoát");
        System.out.print("Nhập lựa chọn: ");
    }
    public static void Menu_HoaDon()
    {
        System.out.println("-----QUẢN LÝ HÓA ĐƠN-----");
        System.out.println("1. Danh sách");
        System.out.println("2. Tìm kiếm");
        System.out.println("3. Thêm CTHD");
        System.out.println("4. Xóa CTHD");
        System.out.println("5. Xóa");
        System.out.println("6. Sửa");
        System.out.println("0. Thoát");
        System.out.print("Nhập lựa chọn: ");
    }
    public static void Menu_PhieuNhapHang()
    {
        System.out.println("-----QUẢN LÝ PHIẾU NHẬP HÀNG-----");
        System.out.println("1. Danh sách");
        System.out.println("2. Tìm kiếm");
        System.out.println("3. Thêm CTPNH");
        System.out.println("4. Xóa CTPNH");
        System.out.println("5. Xóa");
        System.out.println("6. Sửa");
        System.out.println("0. Thoát");
        System.out.print("Nhập lựa chọn: ");
    }
    public static void Menu_Main()
    {
        System.out.println("-----SIÊU THỊ MINI POPPY-----");
        System.out.println("1. Quản lý");
        System.out.println("2. Bán Hàng");
        System.out.println("3. Nhập hàng");
        System.out.println("4. Thống kê");
        System.out.println("0. Thoát");
        System.out.print("Nhập lựa chọn: ");
    }
    public static void Welcome()
    {
        System.out.println( "                                       \n"+
                            "    (`’•.¸(`’•.¸¤*¤¸.-‘´)¸.•’´)\n" +
                            "«´¨`•..¤: SIÊU THỊ MINI POPPY :¤..•´¨`»\n" +
                            "    (¸.•’´(¸.•’´¤*¤`’•.¸)`’•.¸)\n"+
                            "                                       ");
    }
    public static void Login()
    {
        String user ="";
        String password ="";
        Scanner in = new Scanner(System.in);
        LOOP:
        while(true)
        {
            System.out.print("Tài khoản: ");
            user=in.nextLine();
            System.out.print("Mật khẩu: ");
            password=in.nextLine();
            if(user.equals("admin") && password.equals("admin"))
            {
                System.out.println("«´¨`•..¤: Chào "+user+""+" :¤..•´¨`»");
                break LOOP;    
            }
            else
            {
               System.err.println("Sai tài khoản hoặc mật khẩu. Vui lòng nhập lại !!!"); 
            }
            
        }
       
        
        
    }
    
}
