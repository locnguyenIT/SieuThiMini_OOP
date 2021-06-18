/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sieuthimini_oop;

import Object.NhaCungCap;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author ntloc
 */
public class DanhSachNhaCungCap implements In_Out{
    private NhaCungCap[] ncc = new NhaCungCap[2];
    private int n;
    private static final String file ="danhsachncc.txt";
    public DanhSachNhaCungCap(){}
    @Override
    public void nhap()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Nhap so luong ncc: ");
        this.n=in.nextInt();
        ncc=Arrays.copyOf(ncc, n);
        //System.out.println(nv);
        for(int i=0; i<n;i++)
        {
            ncc[i] = new NhaCungCap();
            System.out.println("===Nhập nhân viên thứ "+(i+1)+"===");
            ncc[i].nhap();
            System.out.println("===Lưu Thông tin NV thứ "+(i+1)+" thành công!!!===");
        }
    }
    @Override
    public void xuat()
    {
        System.out.printf("| %10s | %15s | %35s |\n","Mã NCC","Tên NCC","Địa chỉ");
        for(int i=0;i<n;i++)
        {
            ncc[i].xuat(); 
        }
    }
    public void Add(String mancc) throws IOException //number la sl them
    {
       int id = 0;
       DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(file,Boolean.TRUE));
       ncc=Arrays.copyOf(ncc,n+1); //Them sl nv coppy new array
       for(int i=n; i< n+1; i++) //n = sl nv trong file
       {
           ncc[i] = new NhaCungCap();       
           id = Find_id(mancc);
           //System.out.println(id);
           Check_Id(i,id, mancc);

       }
        this.n=n+1; // Cap nhat lai sl nhân viên 
        //System.out.println(n); //n = sl nhan vien sau khi Add 
         outputStream.close();
    }
    public void Check_Id(int i, int id, String mancc) throws IOException
    {
        CHECK:
        while(true)
        {
            LOOP:
            while(id != -1)
            {
                System.out.println("<---Mã nhà cung cấp "+mancc+" đã tồn tại. Vui lòng nhập lại--->");
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
                            System.out.print("Nhập Mã nhà cung cấp: ");
                            in.nextLine();
                            mancc = in.nextLine();
                            ncc[i] = new NhaCungCap();
                            id = Find_id(mancc);
                           break;
                        case 0:
                            break CHECK;
                    }
            }
            ncc[i].nhap(mancc);
            System.out.println("<---Thêm nhà cung cấp "+ncc[i].getMancc()+ " thành công--->");
            ncc[i].WriteFile(file);
            break CHECK;
        }
    }
     public void Delete(String mancc) throws IOException
    {   
        int id = Find_id(mancc);
        if(id >= 0)
        {
           for(int i=id; i<n-1;i++)
           {
               ncc[i] = ncc[i+1]; //Ghi de len gia tri phan tu can xoa
           }
           ncc = Arrays.copyOf(ncc, n-1); //Sau khi xóa thì copy giảm sl nv
           this.n = n-1;
           System.out.println("<---Xóa nhà cung cấp "+mancc+ " thành công--->");
        }
        else
        {
            System.out.println("<---Không có nhà cung cấp "+mancc+ " trong danh sách--->");
        }
        WriteFile();
        //System.out.println(n); //n = sl nhan vien sau khi delete
    }
    public void Search_Mancc(String mancc)
    {
        int id = Find_id(mancc);
        if(id >= 0)
        {
            System.out.println("              -----Nhà cung cấp "+mancc+"-----");
            System.out.printf("| %10s | %15s | %35s |\n","Mã NCC","Tên NCC","Địa chỉ         ");
            ncc[id].xuat();
        }
        else
        {
            System.out.println("<---Không có nhà cung cấp "+mancc+" trong danh sách--->");
        }
    }
    public void Set_NCC(String mancc) throws IOException
    {
        int id = Find_id(mancc);
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
                Menu_Sua_NhaCungCap();
                chon = in.nextInt();
                switch(chon)
                {
                    case 1: //Mã nhà cung cấp
                        System.out.print("Nhập mã nhà cung cấp: ");
                        in.nextLine();
                        ncc[id].setMancc(in.nextLine());
                        System.out.println("-----SỬA HOÀN TẤT-----");
                        break LOOP;
                    case 2: //Tên
                        System.out.print("Nhập tên: ");
                        in.nextLine();
                        ncc[id].setTenncc(in.nextLine());
                        System.out.println("-----SỬA HOÀN TẤT-----");
                        break LOOP;
                    case 3: //Địa chỉ
                        System.out.print("Nhập địa chỉ: ");
                        in.nextLine();
                        ncc[id].setDiachi(in.nextLine());
                        System.out.println("-----SỬA HOÀN TẤT-----");
                        break LOOP;
                    case 0: //Out
                        break LOOP;
                    default: 
                        System.out.println("Sai!!! Vui lòng nhập đúng lựa chọn.");
                    break;
                }
            }
            WriteFile();
        }
    }
    public void WriteFile() throws IOException
    {
        DataOutputStream outStream = new DataOutputStream(new FileOutputStream(file));
        for(int i=0;i<n;i++)
        {
            ncc[i].WriteFile(file);
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
                    String mancc = inputStream.readUTF();
                    String tenncc = inputStream.readUTF();
                    String diachi = inputStream.readUTF();
                    ncc[i] = new NhaCungCap(mancc, tenncc, diachi);
                    i++;
                    ncc = Arrays.copyOf(ncc,i+1);                   
                }
            }
            catch(IOException e){}
            this.n = i; // n = sl nhan vien trong file
            //System.out.println(n);
    }
    
    public int Find_id(String mancc)
    {
        int vt = -1;
        for(int i=0;i<n;i++) //Quet sl nhan vien
        {
            if(ncc[i].getMancc().equals(mancc))
            {
                vt = i; //Tim thay manv
                break; // Out for
            }
        }
        //System.out.println(vt);
        return vt; // tra ve vt of ncc
    }
    public String getMancc(String mancc)
    {
        return ncc[Find_id(mancc)].getMancc();
    }
    public String getTenncc(String mancc)        
    {
        return ncc[Find_id(mancc)].getTenncc();
    }
    public void Menu_Sua_NhaCungCap()
    {
        System.out.println("-----MENU SỬA THÔNG TIN-----");
        System.out.println("1. Mã nhà cung cấp");
        System.out.println("2. Tên");
        System.out.println("3. Địa chỉ");
        System.out.println("0. Thoát");
        System.out.print("Nhập lựa chọn: ");
    }

    
}
