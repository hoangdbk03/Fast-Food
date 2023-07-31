package com.example.fastfood;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.fastfood.dao.NguoiDungDAO;

public class UserFragment extends Fragment {

    ConstraintLayout btnQLHoaDon, btnDoiMatKhau, btnQLCLSanPham, btnThongKe, btnQLND, btnQLSP;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        btnQLHoaDon = view.findViewById(R.id.btnQLHoaDon);
        btnDoiMatKhau = view.findViewById(R.id.btnDoiMatKhau);
        btnQLCLSanPham = view.findViewById(R.id.btnQLCLSanPham);
        btnThongKe = view.findViewById(R.id.btnThongKe);
        btnQLND = view.findViewById(R.id.btnQLND);
        btnQLSP = view.findViewById(R.id.btnQLSP);
        TextView txtTenND = view.findViewById(R.id.txtTenND);
        btnQLSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), QLSanPhamActivity.class));
            }
        });
        btnQLHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QLHoaDonActivity.class);
                startActivity(intent);
            }
        });
        btnDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogDoiMatKhau();
            }
        });
        btnQLCLSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), QLCLSanPhamActivity.class));
            }
        });
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ThongKeActivity.class));
            }
        });
        btnQLND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), QLNguoiDungActivity.class));
            }
        });

        TextView btnDangXuat = view.findViewById(R.id.btnDangXuat);
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        String hoten = sharedPreferences.getString("tennd", "");
        txtTenND.setText(hoten);


        return view;
    }

    private void showDialogDoiMatKhau(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setPositiveButton("Cập nhật", null)
                .setNegativeButton("Hủy", null);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_doimatkhau, null);

        EditText edtOldPass = view.findViewById(R.id.edtOldPass);
        EditText edtNewPass = view.findViewById(R.id.edtNewPass);
        EditText edtRePass = view.findViewById(R.id.edtRePass);

        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass = edtOldPass.getText().toString();
                String newPass = edtNewPass.getText().toString();
                String rePass = edtRePass.getText().toString();
                if (oldPass.equals("") || newPass.equals("") || rePass.equals("")){
                    Toast.makeText(getContext(), "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    if (newPass.equals(rePass)){
                        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
                        String mand = sharedPreferences.getString("mand", "");

                        NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(getContext());
                        int check = nguoiDungDAO.capNhatMatKhau(mand, oldPass, newPass);
                        if (check == 1){
                            Toast.makeText(getContext(), "Cập nhật mật khẩu thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getContext(), LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }else if (check == 0){
                            Toast.makeText(getContext(), "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getContext(), "Cập nhật mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}
