package com.example.fastfood.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastfood.R;
import com.example.fastfood.dao.HoaDonDAO;
import com.example.fastfood.model.HoaDon;

import java.util.ArrayList;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHolder>{

    private ArrayList<HoaDon> list;
    private Context context;

    public HoaDonAdapter(ArrayList<HoaDon> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_recycler_hoadon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaHDCT.setText(""+ list.get(position).getMahdct());
        holder.txtMaKH.setText(list.get(position).getMakh());
        holder.txtTenKH.setText(list.get(position).getTenkh());
        holder.txtTenSp.setText(list.get(position).getTensp());
        holder.txtGiaTien.setText(list.get(position).getGiatien()+" VNĐ");
        holder.txtNgay.setText("Ngày: "+list.get(position).getNgay());
        String trangthai ="";
        if (list.get(position).getThanhtoan() == 1){
            trangthai = "Đã thanh toán";
            holder.btnThanhToan.setVisibility(View.GONE);
        }else {
            trangthai = "Chưa thanh toán";
            holder.btnThanhToan.setVisibility(View.VISIBLE);
        }
        holder.txtTrangThai.setText("Trạng thái: "+trangthai);

        holder.btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HoaDonDAO hoaDonDAO = new HoaDonDAO(context);
                boolean kiemtra = hoaDonDAO.thayDoiTrangThai(list.get(holder.getAdapterPosition()).getMahdct());
                if (kiemtra){
                    list.clear();
                    list = hoaDonDAO.getDsHoaDon();
                    notifyDataSetChanged();
                }else{
                    Toast.makeText(context, "Yêu cầu thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtMaHDCT, txtMaKH, txtTenKH, txtTenSp, txtGiaTien, txtNgay, txtTrangThai;
        Button btnThanhToan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaHDCT = itemView.findViewById(R.id.txtMaHDCT);
            txtMaKH = itemView.findViewById(R.id.txtMaKH);
            txtTenKH = itemView.findViewById(R.id.txtTenKH);
            txtTenSp = itemView.findViewById(R.id.txtTenSP);
            txtGiaTien = itemView.findViewById(R.id.txtGiaTien);
            txtNgay = itemView.findViewById(R.id.txtNgay);
            txtTrangThai = itemView.findViewById(R.id.txtTrangThai);
            btnThanhToan = itemView.findViewById(R.id.btnThanhToan);
        }
    }
}
