package com.bhasanoglu.rickandmortyl.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bhasanoglu.rickandmortyl.R;
import com.bhasanoglu.rickandmortyl.data.ExampleItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private Context mContext;
    private ArrayList<ExampleItem> mExampleList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {           //listener main activity
        void onItemClick(int position);             //Position --> onItemClick
    }

    public void setOnItemClickListener(OnItemClickListener listener) {  //listenerı oluşturur mListener ismi altında
        mListener = listener;
    }

    public ExampleAdapter(Context context, ArrayList<ExampleItem> exampleList) {        //adaptör constructer (onCreateViewHolder(), onBindViewHolder(),getItemCount() metodlarını içerir)
        mContext = context;
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.example_item,parent,false);      //Girdi olarak bir XML dosyasını alır ve ondan View nesnelerini oluşturur.
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {         // ExampleItem türünden item tanımlayıp getterlara ulaşır verileri çeker
        ExampleItem currentItem = mExampleList.get(position);        //get img url

        String imageUrl = currentItem.getmImageUrl();                //get metodı ile verileri çekme
        String name = currentItem.getmName();
        String status = currentItem.getmStatus();
        String location = currentItem.getmLocName();
        String species = currentItem.getmSpecies();
        String gender = currentItem.getmGender();

        holder.mTextViewName.setText(name);                         // değerleri setter aracılığı ile atar
        holder.mTextViewStatus.setText("Status: " + status);
        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.mImageView);         // picasso kütüphanesi aracalığı ile resimi fit eder

        if(status.equals("Dead")){              //Status Durumuna göre renk ayarlama
            holder.mTextViewStatus.setTextColor(Color.parseColor("#BB2817"));
        }
        else if(status.equals("Alive")){
            holder.mTextViewStatus.setTextColor(Color.GREEN);
        }
        else{
            holder.mTextViewStatus.setTextColor(Color.LTGRAY);
        }

    }

    @Override
    public int getItemCount() {      //Item sizeı üzerinden item sayısı döndürür
        return mExampleList.size();
    }

    public class  ExampleViewHolder extends  RecyclerView.ViewHolder {      // ViewHolder recycle viewe extend ederek verilerimi tanıtıyor
        public ImageView mImageView;
        public TextView mTextViewName;
        public TextView mTextViewStatus;
        public TextView mTextViewLocation;


        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);            // example_item.xmldeki verilere bağlantı kurar
            mTextViewName = itemView.findViewById(R.id.text_view_name);
            mTextViewStatus = itemView.findViewById(R.id.text_view_status);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        int position = getAdapterPosition();            //pozisyon atama
                        if(position != RecyclerView.NO_POSITION)        //doğrulama
                        {
                            mListener.onItemClick(position);            //mlistenera pozisyonu atar detay sayfasına iletim amaçlı
                        }
                    }
                }
            });

        }
    }
}
