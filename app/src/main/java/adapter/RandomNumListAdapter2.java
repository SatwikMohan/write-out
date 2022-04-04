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

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class RandomNumListAdapter2 extends RecyclerView.Adapter<RandomNumListAdapter2.RecyclerViewHolder> {
    private Random random;
    //private String[] name;
    private Set<String> name;
    public RandomNumListAdapter2(Set<String> name) {
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
        holder.staroff.setImageResource(R.drawable.staroff);
        holder.staron.setImageResource(R.drawable.staron);
        String[] Name = name.toArray(new String[name.size()]);
        holder.getView().setText(Name[position]);
    }

    @Override
    public int getItemCount() {
        return name.size();
    }


    public class RecyclerViewHolder<onClickListener1> extends RecyclerView.ViewHolder implements View.OnClickListener {
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
            staroff.setOnClickListener(this);
            staron.setOnClickListener(this);
            staron.setVisibility(View.INVISIBLE);
            staroff.setVisibility(View.VISIBLE);
        }

        public Button getView(){
            return tview;
        }

        @Override
        public void onClick(View view) {
            staroff.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    staroff.setVisibility(View.INVISIBLE);
                    staron.setVisibility(View.VISIBLE);
                }
            });
            staron.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    staroff.setVisibility(View.VISIBLE);
                    staron.setVisibility(View.INVISIBLE);
                }
            });
        }
    }
}
