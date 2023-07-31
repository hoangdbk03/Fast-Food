package com.example.fastfood.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastfood.QLCLSanPhamActivity;
import com.example.fastfood.R;
import com.example.fastfood.dao.QLCLSanPhamDAO;
import com.example.fastfood.model.LoaiSanPham;

import java.util.ArrayList;

public class LoaiSanPhamAdapter extends RecyclerView.Adapter<LoaiSanPhamAdapter.ViewHolder>{
    private Context context;
    private ArrayList<LoaiSanPham> list;
    private QLCLSanPhamDAO qlclSanPhamDAO;

    public LoaiSanPhamAdapter(Context context, ArrayList<LoaiSanPham> list, QLCLSanPhamDAO qlclSanPhamDAO) {
        this.context = context;
        this.list = list;
        this.qlclSanPhamDAO = qlclSanPhamDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_qlclsp, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaLoai.setText("Mã loại: "+String.valueOf(list.get(position).getId()));
        holder.txtTenLoai.setText("Tên loại: "+list.get(position).getTenloai());

        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QLCLSanPhamDAO qlclSanPhamDAO = new QLCLSanPhamDAO(context);
                int check = qlclSanPhamDAO.xoaLoaiSanPham(list.get(holder.getAdapterPosition()).getId());
                switch (check){
                    case 1:
                        list.clear();
                        list = qlclSanPhamDAO.getDSLoaiSanPham();
                        notifyDataSetChanged();
                        break;
                    case -1:
                        Toast.makeText(context, "Không thể xóa", Toast.LENGTH_SHORT).show();
                        break;
                    case 0:
                        Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(list.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMaLoai, txtTenLoai;
        ImageView ivDel, ivEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaLoai = itemView.findViewById(R.id.txtMaLoai);
            txtTenLoai = itemView.findViewById(R.id.txtTenLoai);
            ivDel = itemView.findViewById(R.id.ivDel);
            ivEdit = itemView.findViewById(R.id.ivEdit);
        }
    }

    private void showDialog(LoaiSanPham loaiSanPham){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sua_clsp, null);
        builder.setView(view);

        EditText edtSuaMaSp = view.findViewById(R.id.edtSuaMaSP);
        EditText edtSuaTenSP = view.findViewById(R.id.edtSuaTenSP);

        edtSuaMaSp.setText(loaiSanPham.getId());
        edtSuaTenSP.setText(loaiSanPham.getTenloai());

        builder.setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String maloai = edtSuaMaSp.getText().toString();
                String tenloai = edtSuaTenSP.getText().toString();

                if (qlclSanPhamDAO.suaLoaiSanPham(maloai, tenloai) ){
                    loadData();
                    Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Sửa không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void loadData(){
        list.clear();
        list = qlclSanPhamDAO.getDSLoaiSanPham();
        notifyDataSetChanged();
    }
}
