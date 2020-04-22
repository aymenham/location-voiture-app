package MenuClient;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.locationvoiture.R;

import java.util.List;

import Myclass.Avis;

/**
 * Created by LENOVO on 22/03/2018.
 */

class AvisAdapter extends RecyclerView.Adapter<AvisAdapter.MyHolder> {

  private List<Avis> avisList ;

  private Context context ;

    public AvisAdapter(List<Avis> avisList, Context context) {
        this.avisList = avisList;
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_avis , parent , false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        holder.user_comment.setText(avisList.get(position).getUsername());
        holder.comment_date.setText(avisList.get(position).getDate());
        holder.comment_descr.setText(avisList.get(position).getComment());

    }

    @Override
    public int getItemCount() {
        return avisList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{

        TextView user_comment , comment_date , comment_descr;


        public MyHolder(View itemView) {
            super(itemView);

            user_comment = itemView.findViewById(R.id.comment_client);
            comment_date = itemView.findViewById(R.id.comment_date);
            comment_descr = itemView.findViewById(R.id.comment_description);
        }
    }
}
