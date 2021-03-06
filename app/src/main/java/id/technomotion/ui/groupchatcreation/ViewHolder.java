package id.technomotion.ui.groupchatcreation;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.UUID;

import id.technomotion.R;
import id.technomotion.model.SelectableContact;

/**
 * Created by omayib on 05/11/17.
 */

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final String TAG = "ViewHolder";
    private TextView itemName;
    private TextView itemJob;
    private ImageView picture;
    private SelectableContact selectedContact;
    private CheckBox checkBox;
    private final ViewHolder.OnContactClickedListener listener;

    public ViewHolder(View itemView, ViewHolder.OnContactClickedListener listener) {
        super(itemView);
        checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
        itemName = (TextView) itemView.findViewById(R.id.textViewName);
        itemJob = (TextView) itemView.findViewById(R.id.textViewJob);
        picture = (ImageView) itemView.findViewById(R.id.imageViewProfile);
        this.listener = listener;

        itemView.setOnClickListener(this);
    }

    public void bindAlumni(SelectableContact person){
        this.checkBox.setChecked(person.isSelected());
        this.selectedContact = person;
        this.itemName.setText(person.getName());
        this.itemJob.setText(person.getJob());
        Picasso.with(this.picture.getContext()).load("http://lorempixel.com/200/200/people/"+ UUID.randomUUID().toString()).into(picture);
    }

    @Override
    public void onClick(final View v) {
        this.checkBox.setChecked(!this.selectedContact.isSelected());
        this.selectedContact.setSelected(this.checkBox.isChecked());

        if(this.checkBox.isChecked()){
            this.listener.onContactSelected(this.selectedContact.getEmail());
        }else{
            this.listener.onContactUnselected(this.selectedContact.getEmail());
        }
    }

    public interface OnContactClickedListener{
        void onContactSelected(String userEmail);
        void onContactUnselected(String userEmail);
    }
}
