package com.example.fastfood.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastfood.QLSanPhamActivity;
import com.example.fastfood.R;
import com.example.fastfood.dao.SanPhamDAO;
import com.example.fastfood.model.SanPham;

import java.util.ArrayList;
import java.util.HashMap;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHolder>{
    private Context context;
    private ArrayList<SanPham> list;
    private  ArrayList<HashMap<String, Object>> lisHM;
    private SanPhamDAO sanPhamDAO;

    public SanPhamAdapter(Context context, ArrayList<SanPham> list, ArrayList<HashMap<String, Object>> lisHM, SanPhamDAO sanPhamDAO) {
        this.context = context;
        this.list = list;
        this.lisHM = lisHM;
        this.sanPhamDAO = sanPhamDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_sanpham, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaSP.setText(list.get(position).getMasp());
        holder.txtTenSP.setText(list.get(position).getTensp());
        holder.txtGia.setText(String.valueOf(list.get(position).getGiatien())+ " VNĐ");
        holder.txtMaLoai.setText(list.get(position).getMaloai());
        holder.txtTenLoai.setText(list.get(position).getTenloai());

        holder.btnEditSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(list.get(holder.getAdapterPosition()));
            }
        });
        holder.btnDelSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = sanPhamDAO.xoaSanPham(list.get(holder.getAdapterPosition()).getTensp());
                switch (check){
                    case 1:
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                        break;
                    case 0:
                        Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                        break;
                    case -1:
                        Toast.makeText(context, "Không thể xóa", Toast.LENGTH_SHORT).show();
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

        TextView txtMaSP, txtTenSP, txtGia, txtMaLoai, txtTenLoai;
        ImageView btnDelSP;
        LinearLayout btnEditSP;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtMaSP = itemView.findViewById(R.id.txtMaSP1);
            txtTenSP = itemView.findViewById(R.id.txtTenSP1);
            txtGia = itemView.findViewById(R.id.txtGia1);
            txtMaLoai = itemView.findViewById(R.id.txtLoaiSP1);
            txtTenLoai = itemView.findViewById(R.id.txtTenLoai1);
            btnDelSP = itemView.findViewById(R.id.btnDelSP);
            btnEditSP = itemView.findViewById(R.id.btnEditSP);

        }
    }
   private void showDialog(SanPham sanPham){
       AlertDialog.Builder builder = new AlertDialog.Builder(context);
       LayoutInflater inflater = ((Activity)context).getLayoutInflater();
       View view = inflater.inflate(R.layout.dialog_sua_sanpham, null);
       builder.setView(view);

       EditText edtTenSP = view.findViewById(R.id.edtSuaTenSP1);
       EditText edtTien = view.findViewById(R.id.edtSuaGiaSP);
       EditText edtMaSP = view.findViewById(R.id.edtSuaMaSP1);
       Spinner spnSanPham = view.findViewById(R.id.spnSuaLoaiSP);

       edtMaSP.setText(sanPham.getMasp());
       edtTenSP.setText(sanPham.getTensp());
       edtTien.setText(String.valueOf(sanPham.getGiatien()));

       SimpleAdapter simpleAdapter = new SimpleAdapter(
               context,
               lisHM,
               android.R.layout.simple_list_item_1,
               new String[]{"tenloai"},
               new int[]{android.R.id.text1}
       );
       spnSanPham.setAdapter(simpleAdapter);

       String index = "0";
       String postion = "-1";
       for (HashMap<String, Object> item : lisHM){
           if (item.get("maloai") == sanPham.getMaloai()){
               postion = index;
           }
           index = index + 1;
       }
       spnSanPham.setSelection(Integer.parseInt(postion));

       builder.setPositiveButton("cập nhật", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               String masp = edtMaSP.getText().toString();
               String tensp = edtTenSP.getText().toString();
               int tien = Integer.parseInt(edtTien.getText().toString());
               HashMap<String, Object> hs = (HashMap<String, Object>) spnSanPham.getSelectedItem();
               String maloai = (String) hs.get("maloai");

               boolean check = sanPhamDAO.capNhatThongTinSP(masp, tensp, tien, maloai);
               Log.d("//===", masp);
               if (check){
                   Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                   loadData();
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
        list = sanPhamDAO.getDSSanPham();
        notifyDataSetChanged();
   }
}
