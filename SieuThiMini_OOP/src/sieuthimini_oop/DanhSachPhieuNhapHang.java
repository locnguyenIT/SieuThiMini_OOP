/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sieuthimini_oop;
import Object.HoaDon;
import Object.PhieuNhapHang;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ntloc
 */
public class DanhSachPhieuNhapHang implements In_Out{
    private int n;
    private PhieuNhapHang[] pnh = new PhieuNhapHang[2];
    private static final String file ="danhsachphieunhaphang.txt";
    public DanhSachPhieuNhapHang(){}
    @Override
    public void nhap()
    {
         Scanner in = new Scanner(System.in);
        System.out.print("Nhập số lượng phiếu nhập hàng: ");
        this.n = in.nextInt();
        pnh=Arrays.copyOf(pnh, n+1);
        for(int i=0;i<n+1;i++)
        {
            System.out.print("Nhập phiếu nhập hàng thứ "+(i+1)+": ");
             try {
                 pnh[i] = new PhieuNhapHang();
             } catch (IOException ex) {
                 Logger.getLogger(DanhSachPhieuNhapHang.class.getName()).log(Level.SEVERE, null, ex);
             }
            pnh[i].nhap();
            System.out.println("===Lưu thông tin pnh thứ "+(i+1)+" thành công!!!===");
        }
    }
    @Override
    public void xuat()
    {
        System.out.printf("| %10s | %10s | %15s | %15s | %30s |\n","Mã PNH","Mã NCC","Tên NCC","Tổng tiền PNH","Thời gian           ");
        for(int i=0;i<n;i++)
        {
            pnh[i].xuat(); //hd[i].xuat = new Object() inside switch case 
        }    
    }
    public void Add(String mapnh) throws FileNotFoundException, IOException
    {
        //int id = 0;
        pnh = Arrays.copyOf(pnh, n+1);
        DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(file,Boolean.TRUE));
        for(int i=n; i< n +1; i++)
        {
            pnh[i] = new PhieuNhapHang();
            pnh[i].nhap(mapnh);
            pnh[i].WriteFile(file);
        }
        this.n = n+1;
        //System.out.println(n);
        outputStream.close(); 
    }
     public void Delete(String mapnh) throws IOException
    {
        int id = Find_id(mapnh);
        if(id >= 0)
        {
           for(int i=id; i<n-1;i++)
           {
               pnh[i] = pnh[i+1]; //Dich gia tri[i+1] sang trai gia tri phan tu can xoa
           }
           pnh = Arrays.copyOf(pnh, n-1); //Sau khi xóa thì copy giảm sl nv
           this.n = n-1;
           System.out.println("<----Xóa Mã PNH "+mapnh+ " thành công--->");
        }
        else
        {
            System.out.println("<---Không có Mã PNH "+mapnh+ " trong danh sách--->");
        }
        WriteFile();
        //System.out.println(n); //n = sl nhan vien sau khi delete
    }
    public void Set_PhieuNhapHang(String mapnh) throws IOException
    {
        int id =Find_id(mapnh);
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
                DanhSachChiTietPNH dsctpnh = new DanhSachChiTietPNH();
                dsctpnh.ReadFile();
                ReadFile();
                int chon_pnh= 0, chon_ctpnh =0;
                Menu_Sua_PhieuNhapHang();
                chon_pnh = in.nextInt();
                switch(chon_pnh)
                {
                    case 1: //Mã nhà cung cấp
                        System.out.print("Nhập nhà cung cấp: ");
                        in.nextLine();
                        pnh[id].setMancc(in.nextLine());
                        //Set Tennv khi Set Manv
                        setTenncc(id);
                        System.out.println("-----SỬA HOÀN TẤT-----");
                        xuat_pnh(mapnh);
                        dsctpnh.xuat_ctpnh(mapnh);
                        break LOOP;
                    case 2: // Thông tin CTHD sản phẩm
                        System.out.print("Nhập Mã sản phẩm để sửa thông tin: ");
                        in.nextLine();
                        String masp = in.nextLine();
                        CTHD:
                        while(true)
                        {
                            Menu_Sua_CTPNH();
                            float tongtienpnh=0;
                            String setMasp = "";
                            int setSl = 0;
                            int setGiamgia = 0;
                            chon_ctpnh = in.nextInt();
                            switch(chon_ctpnh)
                            {
                                case 1: //Mã sản phẩm

                                    System.out.print("Nhập mã sản phẩm: ");
                                    in.nextLine();
                                    setMasp=in.nextLine();
                                    dsctpnh.Set_MASP(setMasp, mapnh, masp);

                                    tongtienpnh = dsctpnh.getTongTienPNH(mapnh);

                                    Update(mapnh, tongtienpnh);

                                    System.out.println("-----SỬA HOÀN TẤT-----");
                                    xuat_pnh(mapnh);
                                    dsctpnh.xuat_ctpnh(mapnh);
                                    break CTHD;
                                case 2: //Số lượng
                                    System.out.print("Nhập số lượng: ");
                                    in.nextLine();
                                    setSl=in.nextInt();
                                    dsctpnh.Set_SLSP(setSl, mapnh, masp);

                                    tongtienpnh = dsctpnh.getTongTienPNH(mapnh);

                                    Update(mapnh, tongtienpnh);
                                    System.out.println("-----SỬA HOÀN TẤT-----");
                                    xuat_pnh(mapnh);
                                    dsctpnh.xuat_ctpnh(mapnh);
                                    break CTHD;
                                case 3: //Giảm giá %
                                    System.out.print("Nhập giảm giá %: ");
                                    in.nextLine();
                                    setGiamgia=in.nextInt();
                                    dsctpnh.Set_GiamGia(setGiamgia, mapnh, masp);

                                    tongtienpnh = dsctpnh.getTongTienPNH(mapnh);

                                    Update(mapnh, tongtienpnh);
                                    System.out.println("-----SỬA HOÀN TẤT-----");
                                    xuat_pnh(mapnh);
                                    dsctpnh.xuat_ctpnh(mapnh);
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

    public void setTenncc(int id) throws IOException
    {
        DanhSachNhaCungCap dsncc = new DanhSachNhaCungCap();
        dsncc.ReadFile(); //Đọc dữ liệu dsnv
        String setTen = "";
        String mancc =pnh[id].getMancc(); //Get manv khi vừa set manv trước đó 
        if(mancc.equals(dsncc.getMancc(mancc))) //Kiểm tra xem manv vừa get này có trong dữ liệu dsnv(manv) 
        {
           //True
           setTen = dsncc.getTenncc(mancc);   // Gán lại tennv thuộc manv đó có trong dsnv
           //System.out.println(tenUp);
        }
        pnh[id].setTenncc(setTen); //Set lại tennv thuộc manv vừa lấy từ dsnv 
    }
    public void xuat_pnh(String mapnh)
    {
        int id = Find_id(mapnh);
        if(id>=0)
        {
            System.out.println("                                  -------------Phiếu nhập hàng-------------");
           System.out.printf("| %10s | %10s | %15s | %15s | %30s |\n","Mã PNH","Mã NCC","Tên NCC","Tổng tiền PNH","Thời gian           ");
            //System.out.println(id);
            pnh[id].xuat();
        }
        else
        {
            System.out.println("<----Không có Mã PNH "+mapnh+" trong danh sách--->");
        }
    }
    public void Update(String mapnh, float tongtienhd) throws IOException
    {
        Date thoigian = new Date();         //Update Thoi gian
        setThoiGian(mapnh,thoigian.toString());

        setTongtien(mapnh,tongtienhd);       //Update Tongtien HD
    }
     public void setThoiGian(String mapnh,String thoigian) throws IOException
    {
        this.pnh[Find_id(mapnh)].setDate(thoigian);
        WriteFile();
    }
    public void setTongtien(String mapnh, float tongtien) throws IOException
    {
        this.pnh[Find_id(mapnh)].setTongtien(tongtien);
        WriteFile();
    }
    
    public void WriteFile() throws IOException
    {
        DataOutputStream outStream = new DataOutputStream(new FileOutputStream(file));
        for(int i=0;i<n;i++)
        {
            pnh[i].WriteFile(file);
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
                    String mapnh = inputStream.readUTF();
                    String mancc = inputStream.readUTF();
                    String tenncc = inputStream.readUTF();
                    float tongtien = inputStream.readFloat();
                    String date = inputStream.readUTF();
                    pnh[i] = new PhieuNhapHang(mapnh, mancc, tenncc, tongtien, date);
                    i++;
                    pnh = Arrays.copyOf(pnh,i+1);
                    //System.out.printf("| %10s | %10s | %20s | %10s | %20s | %10s | %10s |\n",mahd,manv,tennv,makh,tenkh,tongtien,date);
                }
            }
            catch(IOException e){}
            this.n = i; // n = sl nhan vien trong file
            //System.out.println(n);
    }
    public int Find_id(String mapnh)
    {
        int vt = -1;
        for(int i=0;i<n;i++) 
        {
            if(pnh[i].getMapnh().equals(mapnh))
            {
                vt = i; 
                break;
            }
        }
        //System.out.println(vt);
        return vt; 
    }
    public float getTongtien(String mapnh)
    {
        return pnh[Find_id(mapnh)].getTongtien();
        
    }
    public void Menu_Sua_PhieuNhapHang()
    {
        System.out.println("-----MENU SỬA THÔNG TIN NHẬP HÀNG & CTPNH-----");
        System.out.println("1. Mã nhà cung cấp: ");
        System.out.println("2. Thông tin CTPNH");
        System.out.println("0. Thoát");
        System.out.print("Nhập lựa chọn: ");
    }
    public void Menu_Sua_CTPNH()
    {
        System.out.println("-----MENU SỬA THÔNG TIN CTPNH-----");
        System.out.println("1. Mã sản phẩm");
        System.out.println("2. Số lượng");
        System.out.println("3. Giảm giá %");
        System.out.println("0. Thoát");
        System.out.print("Nhập lựa chọn: ");
    }
    
}
