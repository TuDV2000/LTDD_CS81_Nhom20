package com.example.salebookapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salebookapp.entities.Bill;
import com.example.salebookapp.entities.Book;

import java.util.List;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.BillViewHolder> {

    private List<Bill> billList;
    private IClickConfirmListener iClickConfirmListener;

    public interface IClickConfirmListener{
        void onClickConfirm(Bill bill, BillViewHolder holder);
    }

    public void setData(List<Bill> list, IClickConfirmListener iClickConfirmListener){
        this.billList = list;
        this.iClickConfirmListener = iClickConfirmListener;
        notifyDataSetChanged();
    }

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
        if (Utils.accLogin.getAccType().equals("Saler")) {
            if (bill.getStatus() == 0) {
                holder.btnConfirm.setEnabled(true);
                holder.btnConfirm.setAlpha(1);
                holder.btnConfirm.setText("Xác nhận");
                holder.tvStatus.setText("Trạng thái: Chờ xác nhận");
            } else {
                holder.btnConfirm.setEnabled(false);
                holder.btnConfirm.setAlpha(0);
                if (bill.getStatus() == 2) {
                    holder.tvStatus.setText("Trạng thái: Đã nhận");
                } else {
                    holder.tvStatus.setText("Trạng thái: Đang giao");
                }
            }
        } else {
            if (bill.getStatus() == 1) {
                holder.btnConfirm.setText("Đã nhận");
                holder.tvStatus.setText("Trạng thái: Đang giao");
                holder.btnConfirm.setEnabled(true);
                holder.btnConfirm.setAlpha(1);
            } else {
                holder.btnConfirm.setEnabled(false);
                holder.btnConfirm.setAlpha(0);
                if (bill.getStatus() == 0){
                    holder.tvStatus.setText("Trạng thái: Chờ xác nhận");
                } else {
                    holder.tvStatus.setText("Trạng thái: Đã nhận");
                }
            }
        }
        holder.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickConfirmListener.onClickConfirm(bill, holder);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (billList != null){
            return billList.size();
        }
        return 0;
    }

    public List<Bill> getBillList() {
        return billList;
    }

    public class BillViewHolder extends RecyclerView.ViewHolder{
        private TextView tvBillId;
        private TextView tvBillDayExport;
        private TextView tvBillTotal;
        private TextView tvStatus;
        private Button btnConfirm;


        public BillViewHolder(@NonNull View itemView) {
            super(itemView);

            tvBillId = itemView.findViewById(R.id.tv_bill_id);
            tvBillDayExport = itemView.findViewById(R.id.tv_bill_day_export);
            tvBillTotal = itemView.findViewById(R.id.tv_bill_total);
            tvStatus = itemView.findViewById(R.id.tv_status);
            btnConfirm = itemView.findViewById(R.id.btn_confirm);
        }

        public TextView getTvBillId() {
            return tvBillId;
        }

        public void setTvBillId(TextView tvBillId) {
            this.tvBillId = tvBillId;
        }

        public TextView getTvBillDayExport() {
            return tvBillDayExport;
        }

        public void setTvBillDayExport(TextView tvBillDayExport) {
            this.tvBillDayExport = tvBillDayExport;
        }

        public TextView getTvBillTotal() {
            return tvBillTotal;
        }

        public void setTvBillTotal(TextView tvBillTotal) {
            this.tvBillTotal = tvBillTotal;
        }

        public Button getBtnConfirm() {
            return btnConfirm;
        }

        public void setBtnConfirm(Button btnConfirm) {
            this.btnConfirm = btnConfirm;
        }

        public TextView getTvStatus() {
            return tvStatus;
        }

        public void setTvStatus(TextView tvStatus) {
            this.tvStatus = tvStatus;
        }
    }
}
