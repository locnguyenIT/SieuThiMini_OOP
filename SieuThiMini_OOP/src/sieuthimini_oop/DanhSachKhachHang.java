/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sieuthimini_oop;
import Object.KhachHang;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
/**
 *
 * @author ntloc
 */
public class DanhSachKhachHang implements In_Out{
    private int n;
    private KhachHang[] kh = new KhachHang[2];
    private static final String file ="danhsachkhachhang.txt";
    public DanhSachKhachHang(){}
    
    @Override
    public void nhap()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Nhập số lượng khách hàng: ");
        this.n=in.nextInt();
        kh = Arrays.copyOf(kh, n+1);
        for(int i=0;i<n;i++)
        {
            kh[i] = new KhachHang();
            System.out.println("Nhập khách hàng thứ "+(i+1)+":");
            kh[i].nhap();
            System.out.println("===Lưu thông tin KH thứ "+(i+1)+" thành công!!!===");
        }
        
    }
    @Override
    public void xuat()
    {
        System.out.printf("| %10s | %10s | %15s | %35s | %10s |\n","Mã KH","Họ KH","Tên KH","Địa Chỉ","SĐT");
        for(int i=0;i<n;i++)
        {
            kh[i].xuat(); 
        }
    }
     public void Add(String makh) throws IOException
    {
       int id = 0;
       DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(file,Boolean.TRUE));
       kh=Arrays.copyOf(kh,n+1); //+ 1 kh vao data
       for(int i=n; i< n+1; i++)
       {
           kh[i] = new KhachHang();
           id = Find_id(makh);
           //System.out.println(id);
           Check_Id(i, id, makh);
       }
       this.n = n +1;
       outputStream.close();
    }
    public void Check_Id(int i, int id, String makh) throws IOException
    {
        CHECK:
        while(true)
        {
            LOOP:
            while(id != -1)
            {
                System.out.println("<---Mã khách hàng "+makh+" đã tồn tại. Vui lòng nhập lại--->");
                int chon = 0;
                Scanner in = new Scanner(System.in);
                    System.out.println("Tiếp tục = 1 | Hủy bỏ = 0");
                    System.out.print("Nhập lựa chọn: ");
                    chon = in.nextInt();
                    switch(chon)
                    {
                        case 1:
                            System.out.print("Nhập Mã khách hàng: ");
                            in.nextLine();
                            makh = in.nextLine();
                            kh[i] = new KhachHang();
                            id=Find_id(makh);
                            //System.out.println(id);
                           break;
                        case 0:
                            break CHECK;
                        default:
                            System.out.println("Sai !!! Vui lòng nhập đúng lựa chọn.");
                            break;
                    }
            }
            kh[i].nhap(makh);
            System.out.println("<---Thêm khách hàng "+kh[i].getMakh()+ " thành công--->");
            kh[i].WriteFile(file);
            break CHECK;
                
        }
    }
    public void ReadFile() throws IOException
    {
        int i=0;
        DataInputStream inputStream = new DataInputStream(new FileInputStream(file));
        try
        {
           while(true)
           {
               String makh = inputStream.readUTF();
               String hokh = inputStream.readUTF();
               String tenkh = inputStream.readUTF();
               String diachi = inputStream.readUTF();
               String sdt = inputStream.readUTF();
               kh[i] = new KhachHang(makh, hokh, tenkh, diachi, sdt);
               i++;
               kh= Arrays.copyOf(kh, i+1);
           }
        }catch(IOException e){}
        this.n=i; // n = So luong Khach Hang trong file
        //System.out.println(n);
    }
    public void WriteFile() throws IOException 
    {
        try
        {
           DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(file));
           for(int i=0;i<n;i++)
           {
               kh[i].WriteFile(file);
           }
           outputStream.close();
        }catch(FileNotFoundException e){}
        
    }
    public int Find_id(String makh)
    {
        int vt = -1;
        for(int i=0;i<n;i++) //Quet sl nhan vien
        {
            if(kh[i].getMakh().equals(makh))
            {
                vt = i; //Tim thay manv
                break; // Out for
            }
        }
        //System.out.println(vt);
        return vt; // tra ve Id of nhan vien 
    }
    public void Search_Makh(String makh)
    {
        int id = Find_id(makh);
        if(id >= 0)
        {
            System.out.println("                            -----Khách hàng "+makh+"-----");
            System.out.printf("| %10s | %10s | %15s | %35s | %10s |\n","MA KH","Họ","Tên","Địa Chỉ","SĐT");
            kh[id].xuat();
        }
        else
        {
            System.out.println("<---Không có khách hàng "+makh+" trong danh sách--->");
        }
    }
    public void Search_TenKH(String tenkh)
    {
        for( int i=0; i<n; i++)
        {
            if(kh[i].getTenkh().contains(tenkh))
            {
                System.out.println("    -----Khách hàng "+tenkh+"-----");
                System.out.printf("| %10s | %10s | %10s | %35s | %10s |\n","MA KH","Họ","Tên","Địa chỉ","SĐT");
                kh[i].xuat();
            }
            else
            {
                System.out.println("<---Không có khách hàng "+tenkh+" trong danh sách--->");
            }
        }   
    }
     public void Delete_Makh(String makh) throws IOException
    {   
        int id = Find_id(makh);
        if(id >= 0)
        {
           for(int i=id; i < n-1; i++)
           {
               kh[i] = kh[i+1]; //Ghi de len gia tri phan tu can xoa
           }
           kh = Arrays.copyOf(kh, n-1); //Sau khi xóa thì copy giảm sl kh
           this.n = n-1;
           System.out.println("<---Xóa khách hàng "+makh+ " thành công--->");
        }
        else
        {
            System.out.println("<---Không có khách hàng "+makh+" trong danh sách--->");
        }
        WriteFile();
        //System.out.println(n); //n = sl nhan vien sau khi delete
    }
    public void Set_KhachHang(String makh) throws IOException
    {
        int id = Find_id(makh);
        if(id < 0)
        {
            return;
        }
        else
        {
            Scanner in = new Scanner(System.in);
            int chon = 0;
            LOOP:
            while(true)
            {
                Menu_Sua_KhachHang();
                chon = in.nextInt();
                switch(chon)
                {
                    case 1: //Mã khách hàng
                        System.out.print("Nhập mã khách hàng: ");
                        in.nextLine();
                        kh[id].setMakh(in.nextLine());
                        System.out.println("-----SỬA HOÀN TẤT-----");
                        break LOOP;
                    case 2: //Họ
                        System.out.print("Nhập họ: ");
                        in.nextLine();
                        kh[id].setHokh(in.nextLine());
                        System.out.println("-----SỬA HOÀN TẤT-----");
                        break LOOP;
                    case 3: //Tên
                        System.out.print("Nhập tên: ");
                        in.nextLine();
                        kh[id].setTenkh(in.nextLine());
                        System.out.println("-----SỬA HOÀN TẤT-----");
                        break LOOP;
                    case 4: //Địa chỉ
                        System.out.print("Nhập địa chỉ: ");
                        in.nextLine();
                        kh[id].setDiachi(in.nextLine());
                        System.out.println("-----SỬA HOÀN TẤT-----");
                        break LOOP;
                    case 5: //SDT
                        System.out.print("Nhập SDT: ");
                        in.nextLine();
                        kh[id].setSdt(in.nextLine());
                        System.out.println("-----SỬA HOÀN TẤT-----");
                        break LOOP;
                    case 0: //Out
                        break LOOP;
                    default: 
                        System.out.println("-----Sai!!! Vui lòng nhập đúng lựa chọn");
                    break;
                }
            }
            WriteFile();
        }
    }
    public String getTenkh(String makh)        
    {
        return kh[Find_id(makh)].getHokh()+" "+kh[Find_id(makh)].getTenkh();
    }
    public String getMakh(String makh)
    {
        return kh[Find_id(makh)].getMakh();
    }
    public void Menu_Sua_KhachHang()
    {
        System.out.println("-----MENU SỬA THÔNG TIN-----");
        System.out.println("1. Mã khách hàng");
        System.out.println("2. Họ");
        System.out.println("3. Tên");
        System.out.println("4. Địa chỉ");
        System.out.println("5. SDT");
        System.out.println("0. Thoát");
        System.out.print("Nhập lựa chọn: ");
    }
     
}
