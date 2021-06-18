/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sieuthimini_oop;

import Object.LoaiSP;
import Object.NhanVien;
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
public class DanhSachLoaiSP implements In_Out{
    private int n;
    private LoaiSP[] loai = new LoaiSP[2];
    private static final String file ="danhsachloaisp.txt";

    public DanhSachLoaiSP() {}
    @Override
    public void nhap()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Nhap so luong nhan vien: ");
        this.n=in.nextInt();
        loai=Arrays.copyOf(loai, n);
        //System.out.println(nv);
        for(int i=0; i<n;i++)
        {
            loai[i] = new LoaiSP();
            System.out.println("===Nhập Loai thứ "+(i+1)+"===");
            loai[i].nhap();
            System.out.println("===Lưu Thông tin Loai thứ "+(i+1)+" thành công!!!===");
        }
    }
    @Override
    public void xuat()
    {
        System.out.printf("| %10s | %15s |\n","Mã loại","Tên loại");
        for(int i=0;i<n;i++)
        {
            loai[i].xuat(); 
        }
    }
    public void Add(String maloai) throws IOException
    {
       int id = 0;
       DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(file,Boolean.TRUE));
       loai=Arrays.copyOf(loai,n+1); //+ 1 nv vao data
       for(int i=n; i< n+1; i++)
       {
           loai[i] = new LoaiSP();
           //nv[i].nhap(manv);
           id = Find_id(maloai);
           //System.out.println(id);
           Check_Id(i, id, maloai);
       }
       this.n = n +1;
       outputStream.close();
    }
    public void Check_Id(int i, int id, String maloai) throws IOException
    {
        CHECK:
        while(true)
        {
            LOOP:
            while(id != -1)
            {
                System.out.println("<---Mã loại "+maloai+" đã tồn tại. Vui lòng nhập lại--->");
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
                            System.out.print("Nhập Mã loại: ");
                            in.nextLine();
                            maloai = in.nextLine();
                            loai[i] = new LoaiSP();
                            id = Find_id(maloai);
                           break;
                        case 0:
                            break CHECK;
                    }
            }
            loai[i].nhap(maloai);
            System.out.println("<---Thêm loại sản phẩm "+loai[i].getMaloai()+ " thành công--->");
            loai[i].WriteFile(file);
            break CHECK;
        }
    }
    public int Find_id(String maloai)
    {
        int vt = -1;
        for(int i=0;i<n;i++) //Quet sl nhan vien
        {
            if(loai[i].getMaloai().equals(maloai))
            {
                vt = i; //Tim thay manv
                break; // Out for
            }
        }
        //System.out.println(vt);
        return vt; // tra ve vt of nhan vien 
    }
     public void Search_Maloai(String maloai)
    {
        int id = Find_id(maloai);
        if(id >= 0)
        {
            System.out.println("      ---Loại SP "+maloai+"---");
            System.out.printf("| %10s | %15s |\n","Mã loại","Tên loại");
            loai[id].xuat();
        }
        else
        {
            System.out.println("<---Không có mã loại SP "+maloai+" trong danh sách--->");
        }
    }
    public void Search_Tenloai(String tenloai)
    {
        for( int i=0; i<n; i++)
        {
            if(loai[i].getTenloai().contains(tenloai))
            {
                System.out.println("---Loại SP "+tenloai+"---");
                System.out.printf("| %10s | %15s |\n","Mã loại","Tên loại");
                loai[i].xuat();
            }
            else
            {
                System.out.println("<---Không có loại SP "+tenloai+" trong danh sách--->");
            }
        }
       
    }
     public void Delete(String maloai) throws IOException
    {   
        int id = Find_id(maloai);
        if(id >= 0)
        {
           for(int i=id; i<n-1;i++)
           {
               loai[i] = loai[i+1]; //Ghi de len gia tri phan tu can xoa
           }
            loai = Arrays.copyOf(loai, n-1); //Sau khi xóa thì copy giảm sl nv
           this.n = n-1;
           System.out.println("-----Xóa loại SP "+maloai+ " thành công-----");
        }
        else
        {
            System.out.println("-----Không có loại SP "+maloai+ " trong danh sách-----");
        }
        WriteFile();
        //System.out.println(n); //n = sl nhan vien sau khi delete
    }
    public void WriteFile() throws IOException
    {
        DataOutputStream outStream = new DataOutputStream(new FileOutputStream(file));
        for(int i=0;i<n;i++)
        {
            loai[i].WriteFile(file);
        }
        outStream.close();
    }
    public void ReadFile() 
    {
        int i = 0; 
        try{
            DataInputStream inputStream = new DataInputStream(new FileInputStream(file));
            try{
                 while(true)
                    {                    
                        String maloai = inputStream.readUTF();
                        String tenloai = inputStream.readUTF();
                        loai[i] = new LoaiSP(maloai,tenloai); //Đọc nv[i] 
                        i++;
                        loai = Arrays.copyOf(loai,i+1);                   
                    }
                }
                catch(IOException e){}
                 // n = sl nhan vien trong file
                //System.out.println(n);
        }catch(FileNotFoundException e){}
        this.n = i;
    }
    public void Set_LoaiSP(String maloai) throws IOException
    {
        int id = Find_id(maloai);
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
                Menu_Sua_LoaiSP();
                chon = in.nextInt();
                switch(chon)
                {
                    case 1: //Mã loai
                        System.out.print("Nhập Mã loại: ");
                        in.nextLine();
                        loai[id].setMaloai(in.nextLine());
                        System.out.println("-----SỬA HOÀN TẤT-----");
                        break LOOP;
                    case 2: //Ten
                        System.out.print("Nhập Tên loại: ");
                        in.nextLine();
                        loai[id].setTenloai(in.nextLine());
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
    public void Menu_Sua_LoaiSP()
    {
        System.out.println("-----MENU SỬA THÔNG TIN-----");
        System.out.println("1. Mã loại ");
        System.out.println("2. Tên loại");
        System.out.println("0. Thoát");
        System.out.print("Nhập lựa chọn: ");
    }
}
