package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gigawattstechnology.writeout.R;
import com.gigawattstechnology.writeout.authtransfer;
import com.gigawattstechnology.writeout.pdfviewoth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Random;
import java.util.Set;

public class RandomNumListAdapter3 extends RecyclerView.Adapter<RandomNumListAdapter3.RecyclerViewHolder> {
    private Random random;
    private Set<String> name;
    private Set<String> key;
    public RandomNumListAdapter3(Set<String> name,Set<String> key) {
        this.name =name;
        this.key=key;
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
        String[] Key=key.toArray(new String[key.size()]);
        holder.keytext.setText(Key[position]);
    }

    @Override
    public int getItemCount() {
        return name.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView doc,tab,favstaron,favstaroff;
        Context context;
        private Button bview;
        private TextView keytext;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            context= itemView.getContext();
            tab=itemView.findViewById(R.id.tabart);
            doc=itemView.findViewById(R.id.tabicon);
            favstaroff=itemView.findViewById(R.id.favstaroff);
            favstaron=itemView.findViewById(R.id.favstaron);
            bview = itemView.findViewById(R.id.randomText3);
            keytext=itemView.findViewById(R.id.textView10);
            bview.setOnClickListener(this);
            favstaron.setOnClickListener(this);
            favstaroff.setOnClickListener(this);
        }

        public Button getView(){
            return bview;
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context,"Please Click again",Toast.LENGTH_SHORT).show();
            favstaron.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    favstaron.setVisibility(View.INVISIBLE);
                    favstaroff.setVisibility(View.VISIBLE);
                    DatabaseReference favorite= FirebaseDatabase.getInstance().getReference("Articles").child(keytext.getText().toString()).child("favorite").child(authtransfer.givename());
                    favorite.setValue("#");
                    Toast.makeText(context,"Will be removed on next re-load",Toast.LENGTH_LONG).show();
                }
            });
            favstaroff.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    favstaron.setVisibility(View.VISIBLE);
                    favstaroff.setVisibility(View.INVISIBLE);
                    DatabaseReference favorite= FirebaseDatabase.getInstance().getReference("Articles").child(keytext.getText().toString()).child("favorite").child(authtransfer.givename());
                    favorite.setValue(bview.getText().toString());
                    Toast.makeText(context,"Re-added to favorites",Toast.LENGTH_LONG).show();
                }
            });
            bview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    authtransfer.storekey(keytext.getText().toString());
                    Intent intent=new Intent(context, pdfviewoth.class);
                    context.startActivity(intent);
                }
            });
        }
    }
}
