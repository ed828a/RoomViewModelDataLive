package com.dew.edward.roomword.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dew.edward.roomword.R;
import com.dew.edward.roomword.data.Word;

import java.util.List;

/*
 * Created by Edward on 5/25/2018.
 */

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private LayoutInflater mInflater;
    private List<Word> mWords;

    public WordListAdapter(Context mContext) {
        this.mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item, parent,false);

        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        if(mWords != null) {
            Word word = mWords.get(position);
            holder.mWordText.setText(word.getWord());
        } else {
            holder.mWordText.setText("No word");
        }
    }

    public void setWords(List<Word> words){
        this.mWords = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mWords != null){
            return mWords.size();
        } else {
            return 0;
        }
    }

    class WordViewHolder extends RecyclerView.ViewHolder {

        private TextView mWordText;
        public TextView getWordText() {
            return mWordText;
        }

        private WordViewHolder(View itemView) {
            super(itemView);
            mWordText = itemView.findViewById(R.id.textView);
        }
    }
}
