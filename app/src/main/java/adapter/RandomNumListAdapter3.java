package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gigawattstechnology.writeout.R;

import java.util.Random;
import java.util.Set;

public class RandomNumListAdapter3 extends RecyclerView.Adapter<RandomNumListAdapter3.RecyclerViewHolder> {
    private Random random;
    private Set<String> name;
    public RandomNumListAdapter3(Set<String> name) {
        this.name =name;
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.fragmentmyfavorite_textview;
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
        String[] Name=name.toArray(new String[name.size()]);
        holder.getView().setText(Name[position]);
    }

    @Override
    public int getItemCount() {
        return name.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView doc,tab;
        private TextView view;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tab=itemView.findViewById(R.id.tabart);
            doc=itemView.findViewById(R.id.tabicon);
            view = itemView.findViewById(R.id.randomText3);
        }

        public TextView getView(){
            return view;
        }
    }
}
