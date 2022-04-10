package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gigawattstechnology.writeout.R;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class RandomNumListAdapter4 extends RecyclerView.Adapter<RandomNumListAdapter4.RecyclerViewHolder> {
    private Random random;
    private Set<String> name;
    private Set<String> namevalues;
    public RandomNumListAdapter4(Set<String> name,Set<String> namevalues) {
        this.name =name;
        this.namevalues=namevalues;
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.item;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        String[] Name=name.toArray(new String[name.size()]);
        String[] Nameval=namevalues.toArray(new String[namevalues.size()]);
        holder.getView().setText(Name[position]);
        holder.head.setText("Name: ");
        holder.article.setText("Article: ");
        holder.artname.setText(Nameval[position]);
    }

    @Override
    public int getItemCount() {
        return name.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView doc,tab;
        private TextView view,head,article,artname;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.name);
            head=itemView.findViewById(R.id.head);
            article=itemView.findViewById(R.id.article);
            artname=itemView.findViewById(R.id.artname);
        }

        public TextView getView(){
            return view;
        }
    }
}
