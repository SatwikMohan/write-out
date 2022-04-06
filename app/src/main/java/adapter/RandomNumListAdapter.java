package adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gigawattstechnology.writeout.R;
import com.gigawattstechnology.writeout.User;
import com.gigawattstechnology.writeout.authtransfer;
import com.gigawattstechnology.writeout.pdfview;
import com.gigawattstechnology.writeout.workspace;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomNumListAdapter extends RecyclerView.Adapter<RandomNumListAdapter.RecyclerViewHolder> {
//private ArrayList<String> name=new ArrayList<>();
    Set<String> name,key;
    Context context;
    public RandomNumListAdapter(Set<String> name,Set<String> key) {
        this.name =name;
        this.key=key;
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
        holder.doc.setImageResource(R.drawable.doc);
        holder.tab.setImageResource(R.drawable.tabart);
        String[] Name = name.toArray(new String[name.size()]);
        holder.getView().setText(Name[position]);
    }

    @Override
    public int getItemCount() {
        return name.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
private ImageView doc,tab;
        public Button tview;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            context=itemView.getContext();
            tab=itemView.findViewById(R.id.tabart);
            doc=itemView.findViewById(R.id.tabicon);
            tview = itemView.findViewById(R.id.randomText);
            tview.setOnClickListener(this);
        }
        public Button getView(){
            return tview;
        }

        @Override
        public void onClick(View view) {
            int position=getLayoutPosition();
            authtransfer.storekey(tview.getText().toString().replace(" ","").replace("/",""));
            Intent intent=new Intent(context,pdfview.class);
            context.startActivity(intent);

        }
    }
}
