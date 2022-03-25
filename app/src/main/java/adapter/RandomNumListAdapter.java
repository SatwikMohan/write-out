package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gigawattstechnology.writeout.R;

import java.util.Random;

public class RandomNumListAdapter extends RecyclerView.Adapter<RandomNumListAdapter.RecyclerViewHolder> {
    private Random random;
private String[] name;
    public RandomNumListAdapter(String[] name) {
        this.name =name;
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.fragmentmyarticle_textview;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.getView().setText(name[position]);
    }

    @Override
    public int getItemCount() {
        return name.length;
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView view;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.randomText);
        }

        public TextView getView(){
            return view;
        }
    }
}
