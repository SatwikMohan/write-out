package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.gigawattstechnology.writeout.Modal;
import com.gigawattstechnology.writeout.R;
import com.gigawattstechnology.writeout.authtransfer;
import com.gigawattstechnology.writeout.pdfview;

import java.util.Set;

public class myadaptermy extends FirebaseRecyclerAdapter<Modal,adapter.myadaptermy.RecyclerViewHolder> {
    //private ArrayList<String> name=new ArrayList<>();
    Set<String> name;
    Context context;

    public myadaptermy(@NonNull FirebaseRecyclerOptions<Modal> options) {
        super(options);
    }


    @Override
    public int getItemViewType(final int position) {
        return R.layout.fragmentmyarticle_textview;
    }

    @NonNull
    @Override
    public adapter.myadaptermy.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new myadaptermy.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter.myadaptermy.RecyclerViewHolder holder, int position, @NonNull Modal modal) {
        holder.doc.setImageResource(R.drawable.doc);
        holder.tab.setImageResource(R.drawable.tabart);
        String[] Name = name.toArray(new String[name.size()]);
        holder.getView().setText(modal.getName());
        holder.stext().setText(authtransfer.givename());
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public Button tview;
        private ImageView doc,tab;
        private TextView musername;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            context=itemView.getContext();
            tab=itemView.findViewById(R.id.tabart);
            doc=itemView.findViewById(R.id.tabicon);
            musername=itemView.findViewById(R.id.musername);
            tview = itemView.findViewById(R.id.randomText);
            tview.setOnClickListener(this);
        }
        public Button getView(){
            return tview;
        }
        public TextView stext(){return musername;}
        @Override
        public void onClick(View view) {
            authtransfer.storeusername(musername.getText().toString());
            authtransfer.storekey(tview.getText().toString().replace(" ","").replace("/",""));
            Intent intent=new Intent(context,pdfview.class);
            context.startActivity(intent);

        }
    }
}

