package umn.ac.id.dwynntama_uts_28024;


import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ItemVideoViewHolder> {
    private LinkedList<Songs> listLagu;
    private LayoutInflater mInflater;
    private Context mContext;

    public SongAdapter(Context context,
                       LinkedList<Songs> Songlist) {
        this.mContext = context;
        this.listLagu = Songlist;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ItemVideoViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_song_listviewer,
                parent, false);
        return new ItemVideoViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemVideoViewHolder holder,
                                 int position) {
        Songs eachSong = listLagu.get(position);
        holder.tvJudul.setText(eachSong.getJudul());
    }

    @Override
    public int getItemCount() {
        return listLagu.size();
    }

    class ItemVideoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvJudul;
        private SongAdapter mAdapter;
        private int mPosisi;
        private Songs eachSong;
        public ItemVideoViewHolder(@NonNull View itemView,
                                   SongAdapter adapter) {
            super(itemView);
            mAdapter    = adapter;
            tvJudul     = (TextView) itemView.findViewById(R.id.tvJudul);

            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            mPosisi = getLayoutPosition();
            eachSong = listLagu.get(mPosisi);
            Intent detilInten = new Intent(mContext,
                    SongDetailsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("TestList", listLagu);
            bundle.putSerializable("songPosition", mPosisi);
            detilInten.putExtras(bundle);
            mContext.startActivity(detilInten);
        }
    }
}
