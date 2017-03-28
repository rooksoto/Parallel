package com.rooksoto.parallel.utility.widgets.recyclerview.profile;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rooksoto.parallel.R;
import com.rooksoto.parallel.objects.Answers;
import com.rooksoto.parallel.utility.Constants;

public class ProfileViewholder extends RecyclerView.ViewHolder {
    private TextView textViewQuestion;
    private ImageView imageViewLeft;
    private ImageView imageViewRight;

    public ProfileViewholder (View itemView) {
        super(itemView);
        textViewQuestion = (TextView) itemView.findViewById(R.id.fragment_hub_profile_textview_question);
        imageViewLeft = (ImageView) itemView.findViewById(R.id.fragment_hub_profile_leftanswer);
        imageViewRight = (ImageView) itemView.findViewById(R.id.fragment_hub_profile_rightanswer);
    }

    public void bind (int position, Answers answerP) {
        textViewQuestion.setText("" + answerP.getQuestion());
        if (answerP.getAnswer().equals(Constants.True)) {
            imageViewLeft.setVisibility(View.INVISIBLE);
        } else if (answerP.getAnswer().equals(Constants.False)) {
            imageViewRight.setVisibility(View.INVISIBLE);
        } else if (answerP.getAnswer().equals("2130837628")) { // Selected Android
            imageViewLeft.setVisibility(View.INVISIBLE);
            imageViewRight.setImageResource((R.drawable.ic_androidicon));
            imageViewRight.setColorFilter(itemView.getResources().getColor(R.color.teal));
        } else if (answerP.getAnswer().equals("2130837629")) { // Selected iOS
            imageViewRight.setVisibility(View.INVISIBLE);
            imageViewLeft.setImageResource(R.drawable.ic_appleicon);
            imageViewRight.setColorFilter(itemView.getResources().getColor(R.color.teal));
        }
    }
}