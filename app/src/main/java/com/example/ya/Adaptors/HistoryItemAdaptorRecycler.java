package com.example.ya.Adaptors;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ya.Entity.HistoryItem;
import com.example.ya.R;

import java.util.List;


public class HistoryItemAdaptorRecycler extends RecyclerView.Adapter<HistoryItemAdaptorRecycler.CardHolder> {

    private List<HistoryItem> list;

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public HistoryItemAdaptorRecycler(List<HistoryItem> list) {
        this.list = list;
    }


    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2card, parent, false);
        CardHolder cardHolder = new CardHolder(view);
        return cardHolder;
    }

    @Override
    public void onBindViewHolder(CardHolder holder, int position) {
        holder.textViewSearchWord.setText(list.get(position).getSearchWord());
        holder.textViewTranslatedWord.setText(list.get(position).getTranslatedWord());
        holder.textViewLangFrom.setText(list.get(position).getSearchLang());
        holder.getTextViewLangTo.setText(list.get(position).getTranslatedLang());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CardHolder extends RecyclerView.ViewHolder {
        protected TextView textViewLangFrom;
        protected TextView getTextViewLangTo;
        protected TextView textViewSearchWord;
        protected TextView textViewTranslatedWord;

        public CardHolder(View itemView) {

            super(itemView);
            textViewSearchWord = (TextView) itemView.findViewById(R.id.searchTextView);
            textViewLangFrom = (TextView) itemView.findViewById(R.id.searchLangView);
            getTextViewLangTo = (TextView) itemView.findViewById(R.id.translatedLangView);
            textViewTranslatedWord = (TextView) itemView.findViewById(R.id.translatedWordView);
        }
    }

}

