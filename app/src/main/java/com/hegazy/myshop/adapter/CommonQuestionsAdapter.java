package com.hegazy.myshop.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hegazy.myshop.R;
import com.hegazy.myshop.data.model.commonQuestionsModel.CommonQuestionsModelDatum;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommonQuestionsAdapter extends RecyclerView.Adapter<CommonQuestionsAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private List<CommonQuestionsModelDatum> commonQuestionsModelDatumList = new ArrayList<>();


    public CommonQuestionsAdapter(Context context, List<CommonQuestionsModelDatum> commonQuestionsModelDatumList) {
        this.context = context;
        this.commonQuestionsModelDatumList = commonQuestionsModelDatumList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_common_questions,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {

        holder.itemCommonQuestionsTvQuestion.setText(commonQuestionsModelDatumList.get(position).getQuestion());
        holder.itemCommonQuestionsTvAnswer.setText(commonQuestionsModelDatumList.get(position).getAnswer());

    }

    private void setAction(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return commonQuestionsModelDatumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_common_questions_tv_question)
        TextView itemCommonQuestionsTvQuestion;
        @BindView(R.id.item_common_questions_tv_answer)
        TextView itemCommonQuestionsTvAnswer;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
