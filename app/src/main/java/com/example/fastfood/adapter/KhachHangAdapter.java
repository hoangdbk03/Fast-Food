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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastfood.R;
import com.example.fastfood.dao.KhachHangDAO;
import com.example.fastfood.model.KhachHang;

import java.util.ArrayList;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.ViewHolder>{
    private Context context;
    private ArrayList<KhachHang> list;
    private KhachHangDAO khachHangDAO;

    public KhachHangAdapter(Context context, ArrayList<KhachHang> list, KhachHangDAO khachHangDAO) {
        this.context = context;
        this.list = list;
        this.khachHangDAO = khachHangDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_khachhang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaKH.setText("ID: "+list.get(position).getMakh());
        holder.txtTenKH.setText(list.get(position).getTenkh());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogChinhSua(list.get(holder.getAdapterPosition()));
            }
        });

        holder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = khachHangDAO.xoaThongTinKH(list.get(holder.getAdapterPosition()).getMakh());
                switch (check){
                    case 1:
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                        break;
                    case 0:
                        Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        break;
                    case -1:
                        Toast.makeText(context, "Khách hàng chưa thanh toán hóa đơn", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtMaKH, txtTenKH;
        ConstraintLayout btnEdit;
        ImageView btnXoa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtMaKH = itemView.findViewById(R.id.txtMaKH1);
            txtTenKH = itemView.findViewById(R.id.txtTenKH1);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnXoa = itemView.findViewById(R.id.btnXoa);
        }
    }

    private void showDialogChinhSua(KhachHang khachHang){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sua_khachhang, null);
        builder.setView(view);

        EditText edtMaKH = view.findViewById(R.id.edtMaKH);
        EditText edtTenKH = view.findViewById(R.id.edtTenKH);

        edtMaKH.setText(khachHang.getMakh());
        edtTenKH.setText(khachHang.getTenkh());

        builder.setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String makh = edtMaKH.getText().toString();
                String tenkh = edtTenKH.getText().toString();

                boolean check = khachHangDAO.capNhatThongTinKH(makh, tenkh);
                if (check){
                    loadData();
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
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
        list = khachHangDAO.getDSKhachHang();
        notifyDataSetChanged();
    }
}
