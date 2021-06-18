/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sieuthimini_oop;
import Object.NhanVien;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
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
public class DanhSachNhanVien implements In_Out{
    private int n;
    private NhanVien[] nv = new NhanVien[2];
    private static final String file ="danhsachnhanvien.txt";
    public DanhSachNhanVien(){}
    
    @Override
    public void nhap()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Nhap so luong nhan vien: ");
        this.n=in.nextInt();
        nv=Arrays.copyOf(nv, n);
        //System.out.println(nv);
        for(int i=0; i<n;i++)
        {
            nv[i] = new NhanVien();
            System.out.println("===Nhập nhân viên thứ "+(i+1)+"===");
            nv[i].nhap();
            System.out.println("===Lưu Thông tin NV thứ "+(i+1)+" thành công!!!===");
        }
    }
    @Override
    public void xuat()
    {
        System.out.printf("| %10s | %10s | %15s | %10s | %30s | %10s |\n","Mã NV","Họ NV","Tên NV","Phái","Địa chỉ","Năm Sinh");
        for(int i=0;i<n;i++)
        {
            nv[i].xuat(); 
        }
    }
    public void Add(String manv) throws IOException
    {
       int id = 0;
       DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(file,Boolean.TRUE));
       nv=Arrays.copyOf(nv,n+1); //+ 1 nv vao data
       for(int i=n; i< n+1; i++)
       {
           nv[i] = new NhanVien();
           //nv[i].nhap(manv);
           id = Find_id(manv);
           //System.out.println(id);
           Check_Id(i, id, manv);
       }
       this.n = n +1;
       outputStream.close();
    }
    public void Check_Id(int i, int id, String manv) throws IOException
    {
        CHECK:
        while(true)
        {
            LOOP:
            while(id != -1)
            {
                System.out.println("<---Mã nhân viên "+manv+" đã tồn tại. Vui lòng nhập lại--->");
//                System.out.printf("| %10s | %10s | %15s | %10s | %30s | %10s |\n","Mã NV","Họ NV","Tên NV","Phái","Địa chỉ","Năm Sinh");
//                nv[id].xuat();
                int chon = 0;
                Scanner in = new Scanner(System.in);
                    System.out.println("Tiếp tục = 1 | Hủy bỏ = 0");
                    System.out.print("Nhập lựa chọn: ");
                    chon = in.nextInt();
                    switch(chon)
                    {
                        case 1:
                            System.out.print("Nhập Mã nhân viên: ");
                            in.nextLine();
                            manv = in.nextLine();
                            nv[i] = new NhanVien();
                            id = Find_id(manv);
                           break;
                        case 0:
                            break CHECK;
                        default:
                            System.out.println("Sai !!! Vui lòng nhập đúng lựa chọn.");
                            break;    
                    }
            }
            nv[i].nhap(manv);
            System.out.println("<---Thêm nhân viên "+nv[i].getManv()+ " thành công--->");
            nv[i].WriteFile(file);
            break CHECK;
        }
    }
    public int Find_id(String manv)
    {
        int vt = -1;
        for(int i=0;i<n;i++) //Quet sl nhan vien
        {
            if(nv[i].getManv().equals(manv))
            {
                vt = i; //Tim thay manv
                break; // Out for
            }
        }
        //System.out.println(vt);
        return vt; // tra ve vt of nhan vien 
    }
    public void Search_Manv(String manv)
    {
        int id = Find_id(manv);
        if(id >= 0)
        {
            System.out.println("                                    -----Nhân viên "+manv+"-----");
            System.out.printf("| %10s | %10s | %15s | %10s | %30s | %10s |\n","Mã NV","Họ NV","Tên NV","Phái","Địa chỉ","Năm Sinh");
            nv[id].xuat();
        }
        else
        {
            System.out.println("<---Không có nhân viên "+manv+" trong danh sách--->");
        }
    }
    public void Delete(String manv) throws IOException
    {   
        int id = Find_id(manv);
        if(id >= 0)
        {
           for(int i=id; i<n-1;i++)
           {
               nv[i] = nv[i+1]; //Ghi de len gia tri phan tu can xoa
           }
           nv = Arrays.copyOf(nv, n-1); //Sau khi xóa thì copy giảm sl nv
           this.n = n-1;
           System.out.println("<---Xóa nhân viên "+manv+ " thành công--->");
        }
        else
        {
            System.out.println("<---Không có nhân viên "+manv+ " trong danh sách--->");
        }
        WriteFile();
        //System.out.println(n); //n = sl nhan vien sau khi delete
    }
    public void Search_Tennv(String tennv)
    {
        for( int i=0; i<n; i++)
        {
            if(nv[i].getTennv().contains(tennv))
            {
                 System.out.println("                                           -----Nhân viên "+tennv+"-----");
                System.out.printf("| %10s | %10s | %15s | %10s | %30s | %10s |\n","Mã NV","Họ NV","Tên NV","Phái","Địa chỉ","Năm Sinh");
                nv[i].xuat();
            }
            else
            {
                 System.out.println("<---Không có nhân viên "+tennv+" trong danh sách--->");
            }
        }
       
    }
    public void WriteFile() throws IOException
    {
        DataOutputStream outStream = new DataOutputStream(new FileOutputStream(file));
        for(int i=0;i<n;i++)
        {
            nv[i].WriteFile(file);
        }
        outStream.close();
    }
    public void ReadFile() throws IOException
    {
        int i = 0; 
        DataInputStream inputStream = new DataInputStream(new FileInputStream(file));
        try{
             while(true)
                {                    
                    String manv = inputStream.readUTF();
                    String honv = inputStream.readUTF();
                    String tennv = inputStream.readUTF();
                    String phai = inputStream.readUTF();
                    String diachi = inputStream.readUTF();
                    int namsinh = inputStream.readInt();
                    nv[i] = new NhanVien(manv, honv, tennv, phai, diachi, namsinh); //Đọc nv[i] 
                    i++;
                    nv = Arrays.copyOf(nv,i+1);                   
                }
            }
            catch(IOException e){}
            this.n = i; // n = sl nhan vien trong file
            //System.out.println(n);
    } 
    public void Set_NhanVien(String manv) throws IOException
    {
        int id = Find_id(manv);
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
                Menu_Sua_NhanVien();
                chon = in.nextInt();
                switch(chon)
                {
                    case 1: //Mã nhân viên
                        System.out.print("Nhập mã nhân viên: ");
                        in.nextLine();
                        nv[id].setManv(in.nextLine());
                        System.out.println("-----SỬA HOÀN TẤT-----");
                        break LOOP;
                    case 2: //Họ
                        System.out.print("Nhập họ: ");
                        in.nextLine();
                        nv[id].setHonv(in.nextLine());
                        System.out.println("-----SỬA HOÀN TẤT-----");
                        break LOOP;
                    case 3: //Tên
                        System.out.print("Nhập tên: ");
                        in.nextLine();
                        nv[id].setTennv(in.nextLine());
                        System.out.println("-----SỬA HOÀN TẤT-----");
                        break LOOP;
                    case 4: //Phai
                        System.out.print("Nhập phái: ");
                        in.nextLine();
                        nv[id].setPhai(in.nextLine());
                        System.out.println("-----SỬA HOÀN TẤT-----");
                        break LOOP;
                    case 5: //Địa chỉ
                        System.out.print("Nhập địa chỉ: ");
                        in.nextLine();
                        nv[id].setDiachi(in.nextLine());
                        System.out.println("-----SỬA HOÀN TẤT-----");
                        break LOOP;
                    case 6: //Năm sinh
                        System.out.print("Nhập năm sinh: ");
                        in.nextLine();
                        nv[id].setNamsinh(in.nextInt());
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
    public String getTennv(String manv)        
    {
        return nv[Find_id(manv)].getHonv()+" "+nv[Find_id(manv)].getTennv();
    }
    public String getManv(String manv)
    {
        return nv[Find_id(manv)].getManv();
    }
    public void Menu_Sua_NhanVien()
    {
        System.out.println("-----MENU SỬA THÔNG TIN-----");
        System.out.println("1. Mã nhân viên");
        System.out.println("2. Họ");
        System.out.println("3. Tên");
        System.out.println("4. Phái");
        System.out.println("5. Địa chỉ");
        System.out.println("6. Năm sinh");
        System.out.println("0. Thoát");
        System.out.print("Nhập lựa chọn: ");
    }
}
    

