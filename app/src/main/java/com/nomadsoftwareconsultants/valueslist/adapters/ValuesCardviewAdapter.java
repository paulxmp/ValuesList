package com.nomadsoftwareconsultants.valueslist.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nomadsoftwareconsultants.valueslist.R;
import com.nomadsoftwareconsultants.valueslist.callbacks.ItemTouchHelperViewHolder;
import com.nomadsoftwareconsultants.valueslist.listeners.OnStartDragListener;
import com.nomadsoftwareconsultants.valueslist.listeners.ValueListItemTouchHelperListener;
import com.nomadsoftwareconsultants.valueslist.parse.Values;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.view.MotionEventCompat;
import androidx.recyclerview.widget.RecyclerView;

public class ValuesCardviewAdapter extends RecyclerView.Adapter<ValuesCardviewAdapter.ViewHolder>
    implements ValueListItemTouchHelperListener {

    private static final String TAG = ValuesCardviewAdapter.class.getSimpleName();

    private Context context;
    private List<Values> valuesList;
    private final OnStartDragListener dragStartListener;

    public ValuesCardviewAdapter(Context context, List<Values> valuesList, OnStartDragListener dragStartListener) {
        this.context = context;
        this.valuesList = valuesList;
        this.dragStartListener = dragStartListener;
    }

    @NonNull
    @Override
    public ValuesCardviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.values_cardview_item, parent, false));
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull ValuesCardviewAdapter.ViewHolder viewHolder, int position) {
        Values value = valuesList.get(position);
        if (value.has("ValueImageFile")) {
            ParseFile valueImageFile = value.getValueImageFile();
            valueImageFile.getDataInBackground(new GetDataCallback() {
                public void done(final byte[] data, ParseException e) {
                    if (e == null) {
                        Glide.with(context)
                                .load(data)
                                .centerCrop()
                                .into(viewHolder.valuesCardviewImageview);
                    } else {
                        Log.i(TAG, "Error on retrieving image: " + e.getMessage());
                    }
                }
            });
        } else {
            Glide.with(context)
                    .load(context.getDrawable(R.drawable.glide_place))
                    .centerCrop()
                    .into(viewHolder.valuesCardviewImageview);
        }

        // is there a value name
        viewHolder.valuesCardviewNameTextView.setText(value.getValueName());

        // Is there a value description
        viewHolder.valuesCardviewDescriptionTextView.setText(value.getValueDescription());

        // Start a drag whenever the handle view it touched
        viewHolder.valuesCardviewHandleView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    dragStartListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return valuesList.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(valuesList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        valuesList.remove(position);
        notifyItemRemoved(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
        ImageView valuesCardviewImageview;
        TextView valuesCardviewNameTextView;
        TextView valuesCardviewDescriptionTextView;
        ImageView valuesCardviewHandleView;

        public ViewHolder(View view) {
            super(view);
            valuesCardviewImageview = view.findViewById(R.id.valuesCardviewImageview);
            valuesCardviewNameTextView = view.findViewById(R.id.valuesCardviewNameTextView);
            valuesCardviewDescriptionTextView = view.findViewById(R.id.valuesCardviewDescriptionTextView);
            valuesCardviewHandleView = view.findViewById(R.id.valuesCardviewHandleView);
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }
}
