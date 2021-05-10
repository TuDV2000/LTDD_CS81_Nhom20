package com.example.salebookapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salebookapp.entities.Bill;
import com.example.salebookapp.entities.Book;

import java.util.List;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.BillViewHolder> {
    List<Bill> billList;

    @NonNull
    @Override
    public BillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_item, parent, false);

        return new BillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillViewHolder holder, int position) {
        Bill bill = billList.get(position);

        if (bill == null){
            return;
        }

        holder.tvBillId.setText(String.valueOf(bill.getBillID()));
        holder.tvBillDayExport.setText(String.valueOf(bill.getDateOfExport()));
        holder.tvBillTotal.setText(String.valueOf(bill.getTotal()));
    }

    @Override
    public int getItemCount() {
        if (billList != null){
            return billList.size();
        }
        return 0;
    }

    public void setData(List<Bill> list){
        this.billList = list;
        notifyDataSetChanged();
    }

    public class BillViewHolder extends RecyclerView.ViewHolder{
        private TextView tvBillId, tvBillDayExport, tvBillTotal;


        public BillViewHolder(@NonNull View itemView) {
            super(itemView);

            tvBillId = itemView.findViewById(R.id.tv_bill_id);
            tvBillDayExport = itemView.findViewById(R.id.tv_bill_day_export);
            tvBillTotal = itemView.findViewById(R.id.tv_bill_total);
        }
    }
}
