package com.abhijeet.firebasedatabasepoc.adapters;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abhijeet.firebasedatabasepoc.R;
import com.abhijeet.firebasedatabasepoc.models.SampleModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
public class SampleAdapter extends RecyclerView.Adapter<SampleViewHolder> {
    ArrayList<SampleModel> sampleModels;
    FirebaseDatabase database;
    DatabaseReference myInfoRef;

    public SampleAdapter(ArrayList<SampleModel> sampleModels) {
        this.sampleModels = sampleModels;
    }

    @NonNull
    @Override
    public SampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.sample_layoutitem, null);
        return new SampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SampleViewHolder holder, final int position) {
        database = FirebaseDatabase.getInstance();
        myInfoRef = database.getReference("myInfoRef");
        holder.textName.setText(position + " " + sampleModels.get(position).getName() + sampleModels.get(position).getUniqueId());
        String hobbies = "";
        for (String s : sampleModels.get(position).getHobbies()
        ) {
            hobbies = "  " + hobbies + "  " + s;
        }
        holder.textHobies.setText(hobbies);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> hobies = new ArrayList<>();
                hobies.add("Listening music");
                hobies.add("Playing music");
                String name = "Abhijeet Vader";
                SampleModel sampleModel = new SampleModel();
                sampleModel.setName(name);
                sampleModel.setUniqueId(sampleModels.get(position).getUniqueId());
                sampleModel.setHobbies(hobies);
                // myInfoRef.child(id).setValue(sampleModel);
                myInfoRef.child(sampleModels.get(position).getUniqueId()).setValue(sampleModel);

            }
        });



        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                DatabaseReference myInfoRefFordelete;
                myInfoRefFordelete = database.getReference("myInfoRef").child(sampleModels.get(position).getUniqueId());
               myInfoRefFordelete.removeValue();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return sampleModels.size();
    }
}

class SampleViewHolder extends RecyclerView.ViewHolder {
    TextView textName;
    TextView textHobies;
    View itemView;

    public SampleViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        textName = itemView.findViewById(R.id.textName);
        textHobies = itemView.findViewById(R.id.textHobies);
    }
}