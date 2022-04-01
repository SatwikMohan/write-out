package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gigawattstechnology.writeout.R;

import java.util.Random;

public class RandomNumListAdapter2 extends RecyclerView.Adapter<RandomNumListAdapter2.RecyclerViewHolder> {
    private Random random;
    private String[] name;
    public RandomNumListAdapter2(String[] name) {
        this.name =name;
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.fragmentothersarticle_textview;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.doc.setImageResource(R.drawable.doc);
        holder.tab.setImageResource(R.drawable.tabart);
        holder.getView().setText(name[position]);
    }

    @Override
    public int getItemCount() {
        return name.length;
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView doc,tab,staroff,staron;
        private Button tview;
        private RatingBar ratingBar;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tab=itemView.findViewById(R.id.tabart);
            doc=itemView.findViewById(R.id.tabicon);
            tview = itemView.findViewById(R.id.randomText2);
            staroff=itemView.findViewById(R.id.staroff);
            staron=itemView.findViewById(R.id.staron);
            ratingBar=itemView.findViewById(R.id.ratingBar);
        }

        public Button getView(){
            return tview;
        }
    }
}
