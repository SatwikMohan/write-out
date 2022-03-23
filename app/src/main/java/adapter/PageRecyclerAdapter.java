package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gigawattstechnology.writeout.R;

public class PageRecyclerAdapter extends RecyclerView.Adapter<PageRecyclerAdapter.ProgrammingViewHolder> {
    private String[] data;
    public PageRecyclerAdapter(String[] data)
    {
        this.data=data;
    }

    @NonNull
    @Override
    public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.tt,parent,false);
        return new ProgrammingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgrammingViewHolder holder, int position) {
        String title=data[position];
        holder.pagen.setImageResource(R.drawable.doc);
        holder.page.setImageResource(R.drawable.tabart);
        holder.articletitle.setText(title);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ProgrammingViewHolder extends RecyclerView.ViewHolder{
        ImageView page,pagen;
        TextView articletitle;
        public ProgrammingViewHolder(@NonNull View itemView) {
            super(itemView);
            page=itemView.findViewById(R.id.tabart);
            pagen=itemView.findViewById(R.id.tabicon);
            articletitle=itemView.findViewById(R.id.articletitle);
        }
    }
}
